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
    CRow,
    CTable,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
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
            detailsCV(candidature.cv).then((data) => {
                let listeCandidature = {
                    nom: data.nom,
                    prenom: data.prenom,
                    note: candidature.total,
                    informations: <CButton>Plus d&apos;informations</CButton>,
                    entretient: (
                        <CButton
                            onClick={(e) => {
                                // Resetting values
                                setDateEntretient()
                                setDureeEntretient()

                                setEntretientModalVisibility(true)
                            }}
                        >
                            Arranger une date
                        </CButton>
                    ),
                    _cellProps: { id: { scope: 'row' } },
                }
                setCandidaturesItems((prev) => [...prev, listeCandidature])
            })
        })
    }, [candidatures])

    // Entretient modal
    const [entretientModalVisibility, setEntretientModalVisibility] = useState(false)

    // Date entretient inputs
    const [dateEntretient, setDateEntretient] = useState()
    const [dureeEntretient, setDureeEntretient] = useState()

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
                <CForm>
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
                                        type="date"
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
                        <CButton>Ajouter date</CButton>
                    </CModalFooter>
                </CForm>
            </CModal>
        </>
    )
}

export default Candidatures
