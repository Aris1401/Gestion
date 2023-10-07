import {
    CButton,
    CCol,
    CContainer,
    CForm,
    CFormInput,
    CFormLabel,
    CInputGroup,
    CInputGroupText,
    CRow,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { useLocation, useParams } from 'react-router-dom'
import { makeRequest } from 'src/components/utility/Api'

const CritereCV = (props) => {
    // Besoin alternative
    const { id } = useParams()
    const location = useLocation()

    // Obtenir les critres
    const [criteres, setCriteres] = useState([])
    const [criteresInputs, setCriteresInputs] = useState({})
    const [sousCriteres, setSousCriteres] = useState({})

    const getCriteres = () => {
        makeRequest({
            url: 'ListeCritereAPI',
            successCallback: (data) => {
                setCriteres(data)
            },
            failureCallback: (error) => {
                alert(error)
            },
        })
    }

    useEffect(() => {
        getCriteres()
    }, [location])

    // Notes de criteres
    const initializeCriteresCoefs = () => {
        if (!props.besoin) return

        criteres.forEach((critere) => {
            makeRequest({
                url: `CoefficientCritereAPI?besoin=${
                    props.besoin.id ? props.besoin.id : id
                }&critere=${critere.id}`,
                requestType: 'GET',
                successCallback: (data) => {
                    let tempInputs = criteresInputs
                    tempInputs[critere.id] = data.coefficient

                    setCriteresInputs(tempInputs)
                },
            })
        })
    }

    const handleCritereInputsChange = (e) => {
      const {id, value} = e.target

      setCriteresInputs(prev => ({...prev, [id]: value}))
    }

    // Sous critere
    const getSousCritere = async () => {
        let insertionSousCriteres = {}

        let requests = criteres.map(
            (critere) =>
                new Promise((resolve, reject) => {
                    makeRequest({
                        url: `ListeSousCritereAPI?critere=${critere.id}`,
                        requestType: 'GET',
                        successCallback: (data) => {
                            insertionSousCriteres[critere.id] = data
                            resolve()
                        },
                        errorCallback: (error) => {
                            reject(error)
                        },
                    })
                }),
        )

        try {
            await Promise.all(requests)
            console.log(insertionSousCriteres)
            setSousCriteres(insertionSousCriteres)
        } catch (error) {
            console.error('Error:', error)
        }
    }

    // Initialiser les coefficients des criteres
    useEffect(() => {
        initializeCriteresCoefs()
        getSousCritere()
    }, [criteres])

    // Obtenir la note des sous-critere
    const [notesSousCritere, setNotesSousCritere] = useState({})
    const getNoteSousCritere = () => {
        let tempNotesSousCriteres = {}

        criteres.map((critere, index) => {
            sousCriteres[critere.id].map((sousCritere, index) => {
                makeRequest({
                    url: `NoteSousCritereAPI?besoin=${id}&sousCritere=${sousCritere.id}`,
                    requestType: 'GET',
                    successCallback: (data) => {
                        tempNotesSousCriteres = notesSousCritere
                        tempNotesSousCriteres[sousCritere.id] = data
                    },
                    failureCallback: (error) => {
                        alert(error)
                    },
                })
            })
        })

        setNotesSousCritere(tempNotesSousCriteres)
    }

    useEffect(() => {
        getNoteSousCritere()
    }, [location])

    // Sous criteres inputs
    const [sousCritereInputs, setSousCritereInputs] = useState({})
    const handleSousCritereInputChange = (e) => {
      const {id, value} = e.target

      setSousCritereInputs(prev => ({...prev, [id]: value}))
    }

    
    // Submitting the form

    return (
        <CContainer style={{ marginTop: '1rem' }}>
            <CRow>
                <h6>Modifier criteres de selection</h6>
            </CRow>
            <CForm>
                <CRow>
                    {criteres.map((critere, index) => {
                        return (
                            <CRow key={critere.nom}>
                                <CCol xs={1}>
                                    <CFormInput
                                        label={critere.nom}
                                        name={critere.nom}
                                        id={critere.id}
                                        type="number"
                                        onChange={handleCritereInputsChange}
                                        value={
                                            criteresInputs[critere.id] !== undefined
                                                ? criteresInputs[critere.id]
                                                : 0
                                        }
                                    />
                                </CCol>

                                <CCol xs={5}></CCol>

                                <CCol xs={6}>
                                    {sousCriteres[critere.id] !== undefined &&
                                        sousCriteres[critere.id].map((sousCritere, index_sous) => {
                                            return (
                                                <CRow key={sousCritere.nom}>
                                                    <CInputGroup>
                                                        <CInputGroupText>
                                                            {sousCritere.nom}
                                                        </CInputGroupText>
                                                        <CFormInput
                                                            name={sousCritere.nom}
                                                            type="number"
                                                            id={sousCritere.id}
                                                            onChange={handleSousCritereInputChange}
                                                            value={
                                                                sousCritereInputs[
                                                                    sousCritere.id
                                                                ] !== undefined
                                                                    ? sousCritereInputs[
                                                                          sousCritere.id
                                                                      ]
                                                                    : 0
                                                            }
                                                        />
                                                    </CInputGroup>
                                                </CRow>
                                            )
                                        })}
                                </CCol>
                            </CRow>
                        )
                    })}
                </CRow>

                <CRow>
                    <CButton style={{ marginTop: '1rem' }} type="submit">
                        Modifier criteres
                    </CButton>
                </CRow>
            </CForm>
        </CContainer>
    )
}

export default CritereCV
