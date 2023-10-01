import {
  CCardBody,
  CCardTitle,
  CContainer,
  CRow,
  CCard,
  CTable,
  CCol,
  CButton,
  CModal,
  CModalHeader,
  CModalBody,
  CModalFooter,
  CForm,
  CFormLabel,
  CFormInput,
  CFormSelect,
  CFormTextarea,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/components/utility/Api'

const Besoins = () => {
  // Ajout besoin modal visibility
  const [ajoutBesoinVisibility, setAjoutBesoinVisibility] = useState(false)

  // Obtenir la liste des services
  const [listeService, setListeService] = useState([])
  const getServicesDispo = () => {
    makeRequest({
      url: 'GetServicesAPI',
      successCallback: (data) => {
        let servicesToSelect = []

        data.forEach((service) => {
          servicesToSelect.push({
            label: service.nom,
            value: service.id,
          })
        })

        setListeService(servicesToSelect)
      },
      failureCallback: (error) => {
        alert(error)
      },
    })
  }

  const gettingServices = useEffect(() => {
    getServicesDispo()
  })

  // Creatting a table of all the needs
  const columns = [
    {
      key: 'titre',
      labe: 'Titre',
      _props: { scope: 'col' },
    },
    {
      key: 'dateBesoin',
      label: 'Date Besoin',
      _props: { scope: 'col' },
    },
    {
      key: 'status',
      label: 'Status',
      _props: { scope: 'col' },
    },
    {
      key: 'more',
      label: "Plus d'information",
      _props: { scope: 'col' },
    },
  ]

  return (
    <>
      <CCard style={{ padding: '1rem' }}>
        <CCardTitle>
          <h1>Besoins</h1>
        </CCardTitle>

        <CCardBody>
          <CContainer>
            <CRow className="justify-content-between">
              <CCol>
                <p>Liste des besoins</p>
              </CCol>
              <CCol>
                <CButton
                  onClick={() => {
                    setAjoutBesoinVisibility(true)
                  }}
                >
                  Ajouter besoin
                </CButton>
              </CCol>
            </CRow>
            <CRow>
              <CTable columns={columns} items={[]}></CTable>
            </CRow>
          </CContainer>
        </CCardBody>
      </CCard>

      <CModal
        visible={ajoutBesoinVisibility}
        onClose={() => {
          setAjoutBesoinVisibility(false)
        }}
      >
        <CForm>
          <CModalHeader
            onClose={() => {
              setAjoutBesoinVisibility(false)
            }}
          >
            <h3>Ajouter nouveau besoin</h3>
          </CModalHeader>
          <CModalBody>
            <CContainer>
              <CRow>
                <CCol>
                  <CFormLabel htmlFor="depuisService">Depuis</CFormLabel>
                </CCol>
                <CCol>
                  <CFormSelect id="depuisService" options={['Services', ...listeService]} />
                </CCol>
              </CRow>

              <CRow>
                <CFormInput
                  type="text"
                  id="titre"
                  name="titre"
                  label="Titre de besoin"
                  placeholder="Titre besoin"
                  feedbackInvalid="Veuillez remplir ceci"
                  required
                />
              </CRow>

              <CRow>
                <CFormLabel>Proprietes de besoins</CFormLabel>
                <CCol>
                  <CFormInput type="number" placeholder="Volume taches" name="volumeHoraire" />
                </CCol>
                <CCol>
                  <CFormInput type="number" placeholder="Taux jour/homme" name="tauJourHomme" />
                </CCol>
              </CRow>

              <CRow>
                <CFormTextarea name="description" row={3} label="Description"></CFormTextarea>
              </CRow>

              <CRow>
                <CFormInput name="dateFin" type="date" label="Date Fin" />
              </CRow>
            </CContainer>
          </CModalBody>
          <CModalFooter>
            <CRow>
              <CButton type="submit">Ajouter</CButton>
            </CRow>
          </CModalFooter>
        </CForm>
      </CModal>
    </>
  )
}

export default Besoins
