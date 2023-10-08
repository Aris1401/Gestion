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
import { json, useLocation, useParams } from 'react-router-dom'
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
        const { id, value } = e.target

        setCriteresInputs((prev) => ({ ...prev, [id]: value }))
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

    // Sous criteres inputs
    const [sousCritereInputs, setSousCritereInputs] = useState({})
    const handleSousCritereInputChange = (e) => {
        const { id, value } = e.target

        setSousCritereInputs((prev) => ({ ...prev, [id]: value }))
    }

    // Initiliwing the sous critere note
    const initializeSousCritereNote = () => {
        let tempInputs = { ...sousCritereInputs }; // create a shallow copy of sousCritereInputs
    
        let promises = []; // array to hold all the promises
    
        criteres.forEach((critere) => {
            sousCriteres[critere.id].forEach((sousCritere) => {
                // create a new promise for each request
                let promise = new Promise((resolve, reject) => {
                    makeRequest({
                        url: `NoteSousCritereAPI?besoin=${
                            props.besoin ? props.besoin.id : id
                        }&sousCritere=${sousCritere.id}`,
                        requestType: 'GET',
                        successCallback: (data) => {
                            tempInputs[sousCritere.id] = data.note;
                            resolve(); // resolve the promise when the request is successful
                        },
                    });
                });
    
                // add the promise to the array
                promises.push(promise);
            });
        });
    
        // use Promise.all() to wait for all the promises to resolve
        Promise.all(promises)
            .then(() => {
                setSousCritereInputs(tempInputs);
            })
            .catch((error) => {
                console.error("An error occurred: ", error);
                // handle the error
            });
    }

    useEffect(() => {
        initializeSousCritereNote()
        console.log("TEST: " + JSON.stringify(sousCritereInputs))
    }, [sousCriteres])

    // Submitting the form
    const handleCritereNotationForm = (e) => {
        // Preventing the deafult action of the form
        e.preventDefault()

        // Getting all the inputs of the form
        console.log(JSON.stringify(sousCritereInputs))
        console.log(JSON.stringify(criteresInputs))

        // Looping through the critere inputs
        for (var key in criteresInputs) {
            makeRequest({
                url: `AjoutCritereController?besoin=${id}&critere=${key}&coefficient=${criteresInputs[key]}`,
                requestType: 'GET',
                successCallback: (data) => {},
                failureCallback: (error) => {
                    alert(error)
                },
            })
        }

        // Looping through sous critere inputs
        for (var key in sousCritereInputs) {
            makeRequest({
                url: `AjoutNoteSousCritereController?besoin=${id}&sousCritere=${key}&note=${sousCritereInputs[key]}`,
                requestType: 'GET',
                successCallback: (data) => {},
                failureCallback: (error) => {
                    alert(error)
                },
            })
        }
    }

    return (
        <CContainer style={{ marginTop: '1rem' }}>
            <CRow>
                <h6>Modifier criteres de selection</h6>
            </CRow>
            <CForm onSubmit={handleCritereNotationForm}>
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
