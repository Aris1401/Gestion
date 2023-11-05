import {
    CButton,
    CCol,
    CForm,
    CFormInput,
    CFormLabel,
    CInputGroup,
    CModal,
    CModalBody,
    CModalFooter,
    CModalHeader,
    CModalTitle,
    CNavLink,
    CRow,
    CTable,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { NavLink, useParams } from 'react-router-dom'
import { makeRequest } from 'src/components/utility/Api'
import { detailsCV } from '../ListeCV/ListeCV'

export const listeMeilleuresCandidatures = (besoin) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `Candidatures?besoin=${besoin}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
        })
    })
}

export const entretientPourCv = (cv) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `Entretient?cv=${cv}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                alert(error)
                reject()
            }
        })
    })
}

export const AjouterDateEntretient = (dureeEntretient, dateEntretient, selectedCV) => {
    // Creating a new form data to be sent
    let entretientData = new FormData()
    entretientData.set("dateEntretient", dateEntretient)
    entretientData.set("dureeEntretient", dureeEntretient)
    entretientData.set("cv", selectedCV)

    return new Promise((resolve, reject) => {
        makeRequest({
            url: `Entretient`,
            isFormData: true,
            requestType: 'POST',
            values: entretientData,
            successCallback: (data) => {
                resolve()
            },
            failureCallback: (error) => {
                alert(error)
                reject()
            }
        })
    })
}

const Candidatures = () => {
    // Get the id of the parameter
    const { id } = useParams()

    // Obtention des meilleures canditatures
    const [candidatures, setCandidatures] = useState([])
    useEffect(() => {
        listeMeilleuresCandidatures(id).then((data) => {
            setCandidatures(data)
        })
    }, [])

    // Table columns
    const candidaturesColumns = [
        {
            key: 'nom',
            label: 'Nom',
            _props: { scope: 'col' },
        },
        {
            key: 'prenom',
            label: 'Prenom',
            _props: { scope: 'col' },
        },
        {
            key: 'note',
            label: 'Note',
            _props: { scope: 'col' },
        },
        {
            key: 'informations',
            label: 'Information',
            _props: { scope: 'col' },
        },
        {
            key: 'entretient',
            label: 'Entretient',
            _props: { scope: 'col' },
        },
    ]

    const [candidaturesItems, setCandidaturesItems] = useState([])
    useEffect(() => {
        setCandidaturesItems([])

        candidatures.forEach((candidature) => {
            // Check si a deja obtenu un entretient
            entretientPourCv(candidature.cv).then((entretientCV) => {
                detailsCV(candidature.cv).then((data) => {
                    // Chemin vers les details du cv de la personne
                    let pathDetails = `/detailsCV/${candidature.cv}`;
                    let listeCandidature = {
                        nom: data.nom,
                        prenom: data.prenom,
                        note: candidature.total,
                        informations: <CButton>
                                <CNavLink to={pathDetails} component={NavLink}>Plus d&apos;informations</CNavLink>
                            </CButton>,
                        entretient:
                            entretientCV == null ?
                                <CButton
                                    onClick={(e) => {
                                        // Resetting values
                                        setDateEntretient()
                                        setDureeEntretient()

                                        // Setting the selected candidat
                                        setSelectedCandidat(data.id)

                                        setEntretientModalVisibility(true)
                                    }}
                                >
                                    Arranger une date
                                </CButton>
                                :
                                `${entretientCV.dateEntretient} (${entretientCV.dureeEntretient} minutes)`

                        ,
                        _cellProps: { id: { scope: 'row' } },
                    }
                    setCandidaturesItems((prev) => [...prev, listeCandidature])
                })
            })
        })
    }, [candidatures])

    // Entretient modal
    const [entretientModalVisibility, setEntretientModalVisibility] = useState(false)

    // Date entretient inputs
    const [dateEntretient, setDateEntretient] = useState()
    const [dureeEntretient, setDureeEntretient] = useState()
    const [selectedCandidat, setSelectedCandidat] = useState()

    // Ajouter date entretient
    const handleEntretientFormSubmit = (e) => {
        e.preventDefault()

        AjouterDateEntretient(dureeEntretient, dateEntretient, selectedCandidat).then(() => {
            setEntretientModalVisibility(false)
        })
    }

    return (
        <>
            <CTable columns={candidaturesColumns} items={candidaturesItems} />

            {/* Modal de arrangement de date */}
            <CModal
                visible={entretientModalVisibility}
                onClose={() => {
                    setEntretientModalVisibility(false)
                }}
            >
                <CForm
                    onSubmit={handleEntretientFormSubmit}
                >
                    <CModalHeader>
                        <CModalTitle>Arrangement de date</CModalTitle>
                    </CModalHeader>
                    <CModalBody>
                        <CRow>
                            <CCol xs={4}>
                                <CFormLabel htmlFor="date-entretient">Date entretient</CFormLabel>
                            </CCol>
                            <CCol xs={8}>
                                <CInputGroup id="date-entretient">
                                    <CFormInput
                                        type="datetime-local"
                                        value={dateEntretient}
                                        onChange={(e) => {
                                            setDateEntretient(e.target.value)
                                        }}
                                    />
                                    <CFormInput
                                        type="number"
                                        step={0.01}
                                        placeholder="Duree"
                                        value={dureeEntretient}
                                        onChange={(e) => {
                                            setDureeEntretient(e.target.value)
                                        }}
                                    />
                                </CInputGroup>
                            </CCol>
                        </CRow>
                    </CModalBody>
                    <CModalFooter>
                        <CButton type='submit'>Ajouter date</CButton>
                    </CModalFooter>
                </CForm>
            </CModal>
        </>
    )
}

export default Candidatures
