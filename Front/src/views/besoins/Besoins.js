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
  CNavLink,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { NavLink, useLocation } from 'react-router-dom'
import { CheckPageAuthority } from 'src/components/auth/CheckAuth'
import { makeRequest } from 'src/components/utility/Api'

const Besoins = () => {
  CheckPageAuthority()

  const location = useLocation()
  // Ajout besoin modal visibility
  const [ajoutBesoinVisibility, setAjoutBesoinVisibility] = useState(false)

  // Obtenir la liste des services
  const [listeService, setListeService] = useState([])
  const getServicesDispo = () => {
    makeRequest({
      url: 'ListeServiceController',
      requestType: 'GET',
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

  useEffect(() => {
    getServicesDispo()
  }, [])

  // Obtenir la liste des postes de ce service
  const [listePostesService, setListePostesService] = useState([])
  // Changing the postes according to the service selected
  const [posteShown, setPostShown] = useState(false)

  let getPostesServices = () => {
    // alert(`PosteService?service=${service}`)
    let posteServicesToSelect = []
    let req = new Promise((resolve, reject) => {
      makeRequest({
        url: `PosteService?service=${service}`,
        requestType: 'GET',
        successCallback: (data) => {

          data.forEach((poste) => {
            posteServicesToSelect.push({
              label: poste.titreposte,
              value: poste.id,
              service: poste.service,
            })
          })
          
          resolve()
        },
        failureCallback: (error) => {
          alert(error)
        },
      })
    })
    
    req.then(() => {
      setListePostesService(posteServicesToSelect)
    })
  }

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

  // Submitting form
  const [service, setService] = useState()
  const [titre, setTitre] = useState()
  const [volumeTaches, setVolumeTaches] = useState()
  const [tauxJourHomme, setTauxJourHomme] = useState()
  const [description, setDescription] = useState()
  const [dateFin, setDateFin] = useState()
  const [posteService, setPosteService] = useState()

  const sendFormData = () => {
    let formData = new FormData()
    formData.set('service', service)
    formData.set('titre', titre)
    formData.set('volumeTaches', volumeTaches)
    formData.set('tauxJourHomme', tauxJourHomme)
    formData.set('description', description)
    formData.set('dateFin', dateFin)
    formData.set('posteService', posteService)

    makeRequest({
      url: 'InsertBesoinAPI',
      values: formData,
      successCallback: (data) => {},
      failureCallback: (error) => {
        alert(error)
      },
      isFormData: true,
    })
  }

  const handleSubmit = (event) => {
    sendFormData()
    event.preventDefault()

    // Resetting the values
    setService(0)
    setTitre()
    setVolumeTaches()
    setTauxJourHomme()
    setDescription()
    setDateFin()
  }

  // Obtenir les besoins
  const [listeBesoins, setListeBesoins] = useState([])

  const getListeBesoins = () => {
    makeRequest({
      url: 'ListeBesoinAPI',
      successCallback: (data) => {
        let listeBesoinsRow = []

        data.forEach((besoin) => {
          listeBesoinsRow.push({
            titre: besoin.titre,
            dateBesoin: besoin.dateBesoin,
            status: besoin.status == 0 ? 'En cours' : 'Ferme',
            more: (
              <CNavLink to={`${location.pathname}/${besoin.id}`} component={NavLink}>
                Plus infos
              </CNavLink>
            ),
            _cellProps: { id: { scope: 'row' } },
          })
        })

        setListeBesoins(listeBesoinsRow)
      },
      failureCallback: (error) => {},
    })
  }

  useEffect(() => {
    getListeBesoins()
  }, [ajoutBesoinVisibility])

  useEffect(() => {
    getPostesServices()
  }, [service])

  useEffect(() => {
    let hasId = false
      listePostesService.forEach((ps) => {
        // alert(`Poste s: ${ps.service} et service = ${service}`)
        if (ps.service == service) {
          setPostShown(true)
          hasId = true
        }
      })

      if (!hasId) setPostShown(false)
  }, [listePostesService])

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
              <CCol className='justify-content-end'>
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
              <CTable columns={columns} items={listeBesoins}></CTable>
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
        <CForm onSubmit={handleSubmit}>
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
                  <CFormSelect
                    id="depuisService"
                    options={['Services', ...listeService]}
                    value={service}
                    onChange={(e) => {
                      setService(e.target.value)
                    }}
                  />
                </CCol>
              </CRow>

              {posteShown && (
                <CRow>
                  <CCol>
                    <CFormLabel htmlFor="posteService">Poste</CFormLabel>
                  </CCol>
                  <CCol>
                    <CFormSelect
                      id="posteService"
                      options={['Postes', ...listePostesService]}
                      value={posteService}
                      onChange={(e) => {
                        setPosteService(e.target.value)
                      }}
                    />
                  </CCol>
                </CRow>
              )}

              <CRow>
                <CFormInput
                  type="text"
                  id="titre"
                  name="titre"
                  label="Titre de besoin"
                  placeholder="Titre besoin"
                  feedbackInvalid="Veuillez remplir ceci"
                  required
                  value={titre}
                  onChange={(e) => {
                    setTitre(e.target.value)
                  }}
                />
              </CRow>

              <CRow>
                <CFormLabel>Proprietes de besoins</CFormLabel>
                <CCol>
                  <CFormInput
                    type="number"
                    placeholder="Volume taches"
                    name="volumeTaches"
                    value={volumeTaches}
                    onChange={(e) => {
                      setVolumeTaches(e.target.value)
                    }}
                  />
                </CCol>
                <CCol>
                  <CFormInput
                    type="number"
                    placeholder="Taux jour/homme"
                    name="tauxJourHomme"
                    value={tauxJourHomme}
                    onChange={(e) => {
                      setTauxJourHomme(e.target.value)
                    }}
                  />
                </CCol>
              </CRow>

              <CRow>
                <CFormTextarea
                  name="description"
                  row={3}
                  label="Description"
                  value={description}
                  onChange={(e) => {
                    setDescription(e.target.value)
                  }}
                ></CFormTextarea>
              </CRow>

              <CRow>
                <CFormInput
                  name="dateFin"
                  type="date"
                  label="Date Fin"
                  value={dateFin}
                  onChange={(e) => {
                    setDateFin(e.target.value)
                  }}
                />
              </CRow>
            </CContainer>
          </CModalBody>
          <CModalFooter>
            <CRow>
              <CButton
                type="submit"
                onClick={() => {
                  setAjoutBesoinVisibility(false)
                }}
              >
                Ajouter
              </CButton>
            </CRow>
          </CModalFooter>
        </CForm>
      </CModal>
    </>
  )
}

export default Besoins
