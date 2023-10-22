import {
    CButton,
    CCard,
    CCardBody,
    CCardText,
    CCardTitle,
    CCol,
    CContainer,
    CForm,
    CFormInput,
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
import { getCriteresForCV, getSousCriteres } from '../besoins/CriteresCV/CritereCV'
import { makeRequest } from 'src/components/utility/Api'

const Criteres = () => {
    const columns = [
        {
            key: 'nom',
            label: 'Nom',
            _props: { scope: 'col' },
        },
        {
            key: 'modifier',
            label: 'Mofidier',
            _props: { scope: 'col' },
        },
    ]

    // Items du tableux pour la modification
    const [critereItems, setCritereItems] = useState([])
    useEffect(() => {
        getCriteresForCV().then((data) => {
            let items = []
            data.forEach((item) => {
                items.push({
                    nom: item.nom,
                    modifier: (
                        <CButton onClick={(e) => handleModalModificationClick(e, item.id)}>
                            Modifier
                        </CButton>
                    ),
                    _cellProps: { id: { scope: 'row' } },
                })
            })
            setCritereItems(items)
        })
    }, [])

    // Modal de modification
    const [visibilityModificationModal, setVisibilityModificationModal] = useState(false)
    const handleModalModificationClick = (e, critereId) => {
        setVisibilityModificationModal(true)

        // reinit
        setNewSousCritere("")

        getCriteresForCV()
            .then((data) => {
                data.forEach((item) => {
                    if (item.id === critereId) {
                        setCritereModificationInput(item.nom)
                        setCritereModificationId(item.id)
                    }
                })
            })
            .then(() => {
                getSousCriteres({ id: critereId }).then((data) => {
                    let inputs = []
                    data.forEach((item) => {
                        inputs.push({
                            id: item.id,
                            value: item.nom,
                        })
                    })

                    setSousCriteresInputs(inputs)
                })
            })
    }

    // Input de modal de moficition
    const [critereModificationInput, setCritereModificationInput] = useState('')
    const [critereModificationId, setCritereModificationId] = useState(-1)
    const [sousCriteresInputs, setSousCriteresInputs] = useState()
    const [newSousCritere, setNewSousCritere] = useState("")

    const ajouterSousCritere = () => {
        // Creaing form data
        let formData = new FormData()
        formData.set("critere", critereModificationId)
        formData.set("nom", newSousCritere)

        makeRequest({
            url: `SousCritere`,
            requestType: 'POST',
            isFormData: true,
            values: formData,
            successCallback: (data) => {

            },
            failureCallback: (error) => {
                alert(error)
            }
        })

        // Reinit.
        setNewSousCritere("")
        setVisibilityModificationModal(false)
    }

    const handleDeleteSousCritere = (sousCritereId) => {
        makeRequest({
            url: `SousCritere?id=${sousCritereId}`,
            requestType: 'DELETE',
            successCallback: (data) => {

            },
            failureCallback: (error) => {
                alert(error)
            }
        })

        setVisibilityModificationModal(false)
    }

    return (
        <>
            <CContainer>
                <CCard className="p-2">
                    <CCardBody>
                        <CCardTitle>
                            <CRow>
                                <CCol>Liste des criteres</CCol>

                                <CCol>
                                    <CButton>Ajouter critere</CButton>
                                </CCol>
                            </CRow>
                        </CCardTitle>

                        <CRow>
                            <CCol>
                                <CTable columns={columns} items={critereItems} />
                            </CCol>
                        </CRow>
                    </CCardBody>
                </CCard>
            </CContainer>

            {/* Modal de modification */}
            <CModal
                visible={visibilityModificationModal}
                onClose={() => setVisibilityModificationModal(false)}
            >
                <CForm>
                    <CModalHeader>
                        <CModalTitle>Modifier critere</CModalTitle>
                    </CModalHeader>
                    <CModalBody>
                        <CRow>
                            <CCol>
                                <CFormInput
                                    label="Critere"
                                    type="text"
                                    value={critereModificationInput}
                                />
                            </CCol>
                        </CRow>

                        <CRow className="p-2">
                            <CCol className="d-flex gap-2 flex-column">
                                <h5>Liste des sous criteres</h5>
                                {sousCriteresInputs !== undefined &&
                                    sousCriteresInputs.map((item, index) => {
                                        return (
                                            <>
                                                <CInputGroup key={item.id}>
                                                    <CFormInput value={item.value} />
                                                    <CButton>Modifier</CButton>
                                                    <CButton onClick={(e) => {handleDeleteSousCritere(item.id)}}>Supprimer</CButton>
                                                </CInputGroup>
                                            </>
                                        )
                                    })}

                                <CInputGroup>
                                    <CFormInput placeholder="Sous-critere..." value={newSousCritere} onChange={(e) => setNewSousCritere(e.target.value)} />
                                    <CButton onClick={(e) => {ajouterSousCritere()}}>Ajouter</CButton>
                                </CInputGroup>
                            </CCol>
                        </CRow>
                    </CModalBody>
                    <CModalFooter>
                        <CButton type="submit" color="primary">
                            Modifier
                        </CButton>
                    </CModalFooter>
                </CForm>
            </CModal>
        </>
    )
}

export default Criteres
