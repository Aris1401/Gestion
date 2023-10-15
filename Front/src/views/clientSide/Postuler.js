import {
    CAccordion,
    CAccordionBody,
    CAccordionHeader,
    CAccordionItem,
    CButton,
    CCard,
    CCardBody,
    CCardHeader,
    CCol,
    CContainer,
    CForm,
    CFormCheck,
    CFormInput,
    CFormTextarea,
    CRow,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { redirect, useNavigate, useParams } from 'react-router-dom'
import { getBesoinDetails } from '../besoins/DetailsBesoin'
import { getCriteresForCV, getSousCriteres } from '../besoins/CriteresCV/CritereCV'
import { number } from 'prop-types'
import { makeRequest } from 'src/components/utility/Api'

const Postuler = () => {
    // Getting the current besoin
    const { besoin } = useParams()

    // Navidaton
    const navigate = useNavigate()

    // Getting the besoin details
    const [currentBesoin, setCurrentBesoin] = useState()
    useEffect(() => {
        getBesoinDetails(besoin).then((data) => {
            setCurrentBesoin(data)
        })
    }, [])

    // Criteres CV
    const [listeCriteres, setListeCriteres] = useState([])
    const [listeSousCritere, setListeSousCritere] = useState({})

    useEffect(() => {
        getCriteresForCV().then((data) => {
            setListeCriteres(data)
        })
    }, [])

    const initSousCriteres = async () => {
        let tempData = {}

        let request = listeCriteres.map(
            (critereItem) =>
                new Promise((resolve, reject) => {
                    getSousCriteres(critereItem).then((data) => {
                        tempData[critereItem.id] = data

                        // alert(JSON.stringify(tempData))
                        resolve()
                    })
                }),
        )

        await Promise.all(request)
        setListeSousCritere(tempData)
    }

    useEffect(() => {
        initSousCriteres()
        // alert()
    }, [listeCriteres])

    useEffect(() => {
        console.log(JSON.stringify(listeSousCritere))
    }, [listeSousCritere])

    // Reponses checkbox
    const [reponseCritere, setReponseCritere] = useState({})
    const hamdleReponseCritereChange = (e) => {
        setReponseCritere((prev) => ({...prev, [e.target.id]: e.target.checked}))
    }

    // CV inputs
    const [nom, setNom] = useState()
    const [prenom, setPrenom] = useState()
    const [addresse, setAddresse] = useState()
    const [datenaissance, setDateNaissance] = useState()
    const [email, setEmail] = useState()
    const [contact, setContact] = useState()
    const [description, setDescription] = useState()
    const [preuveDiplome, setPreuveDiplome] = useState()
    const [preuveDeTravail, setPreuveDeTravail] = useState()

    const handleCVFormSubmit = (e) => {
        e.preventDefault()

        let cv = new FormData()
        cv.set("nom", nom)
        cv.set("prenom", prenom)
        cv.set("addresse", addresse)
        cv.set("dateNaissance", datenaissance)
        cv.set("email", email)
        cv.set("contact", contact)
        cv.set("description", description)
        cv.set("besoin", besoin)
        
        // Preuves de diplome
        for (let i = 0; i < preuveDiplome.length; i++) {
            cv.append(`preuveDiplome${i + 1}`, preuveDiplome[i])
        }

        // Preuves de travail
        for (let i = 0; i < preuveDeTravail.length; i++) {
            cv.append(`preuveDeTravail${i + 1}`, preuveDeTravail[i])
        }

        makeRequest({
            url: 'InsertCVController',
            requestType: 'POST',
            isFormData: true,
            values: cv,
            successCallback: (data) => {

            }, 
            failureCallback: (error) => {
                alert(error)
            }
        })

        // Semding the critere values
        listeCriteres.forEach((critere) => {
            listeSousCritere[critere.id].forEach((item) => {
                if (reponseCritere[item.id]) {
                    makeRequest({
                        url: `AjoutReponseCV?critere=${critere.id}&sousCritere=${item.id}&besoin=${besoin}`,
                        requestType: 'GET',
                        successCallback: (data) => {

                        },
                        failureCallback: (error) => {
                            alert(error)
                        }
                    })
                }
            })
        })

        // Redirecting
        redirect("/acceuil")
    } 

    return (
        <CContainer style={{ marginTop: '1rem', marginBottom: '3rem' }}>
            <CCard>
                <CCardHeader className="text-center">
                    <h3>Jointure de CV</h3>
                </CCardHeader>

                <CCardBody>
                    <CForm onSubmit={handleCVFormSubmit} className="d-flex flex-column gap-3">
                        {/* Nom - prenom */}
                        <CRow>
                            <CCol>
                                <CFormInput
                                    name="nom"
                                    label="Nom"
                                    id="nom"
                                    placeholder="Nom..."
                                    type="text"
                                    value={nom}
                                    onChange={(e) => setNom(e.target.value)}
                                />
                            </CCol>
                            <CCol>
                                <CFormInput
                                    name="prenom"
                                    label="Prenom"
                                    id="prenom"
                                    placeholder="Prenom..."
                                    type="text"
                                    value={prenom}
                                    onChange={(e) => setPrenom(e.target.value)}
                                />
                            </CCol>
                        </CRow>

                        {/* Addresse - Date naissance */}
                        <CRow>
                            <CCol xs={8}>
                                <CFormInput
                                    name="adresse"
                                    label="Addresse"
                                    id="addresse"
                                    placeholder="Votre addresse..."
                                    type="text"
                                    value={addresse}
                                    onChange={(e) => setAddresse(e.target.value)}
                                />
                            </CCol>

                            <CCol xs={4}>
                                <CFormInput
                                    name="datenaissance"
                                    label="Date de naissance"
                                    id="datenaissance"
                                    type="date"
                                    value={datenaissance}
                                    onChange={(e) => setDateNaissance(e.target.value)}
                                />
                            </CCol>
                        </CRow>

                        {/* Email - Contact */}
                        <CRow>
                            <CCol xs={9}>
                                <CFormInput
                                    name="email"
                                    label="Email"
                                    id="email"
                                    placeholder="Votre email..."
                                    type="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                            </CCol>
                            <CCol xs={3}>
                                <CFormInput
                                    name="contact"
                                    label="Contact"
                                    id="contact"
                                    placeholder="03333333333"
                                    type="number"
                                    value={contact}
                                    onChange={(e) => setContact(e.target.value)}
                                />
                            </CCol>
                        </CRow>

                        {/* Description */}
                        <CRow>
                            <CCol>
                                <CFormTextarea
                                    name="description"
                                    rows={4}
                                    id="description"
                                    label="Description"
                                    placeholder="Parlez un peu de vous..."
                                    value={description}
                                    onChange={(e) => setDescription(e.target.value)}
                                />
                            </CCol>
                        </CRow>

                        {/* Preuves */}
                        <CRow>
                            <CCol>
                                <CFormInput
                                    type="file"
                                    multiple
                                    name="preuvediplome"
                                    id="preuvediplome"
                                    label="Preuve diplome"
                                    onChange={(e) => setPreuveDiplome(e.target.files)}
                                />
                            </CCol>

                            <CCol>
                                <CFormInput
                                    type="file"
                                    multiple
                                    name="preuvetravail"
                                    id="preuvetravail"
                                    label="Preuve de travail"
                                    onChange={(e) => setPreuveDeTravail(e.target.files)}
                                />
                            </CCol>
                        </CRow>

                        <CAccordion flush>
                            <CAccordionItem itemKey={1}>
                                <CAccordionHeader>Criteres de CV</CAccordionHeader>
                                <CAccordionBody>
                                    <CRow xs={{ cols: 2 }}>
                                        {listeCriteres.map((critereItem, index) => {
                                            return (
                                                <div key={critereItem.id}>
                                                    <CCol>
                                                        <h6>{critereItem.nom}</h6>
                                                    </CCol>
                                                    <CCol>
                                                        {listeSousCritere[critereItem.id] ==
                                                        undefined
                                                            ? null
                                                            : listeSousCritere[critereItem.id].map(
                                                                  (item) => {
                                                                      return (
                                                                          <CFormCheck
                                                                              key={item.id}
                                                                              label={item.nom}
                                                                              id={item.id}
                                                                              checked={reponseCritere[item.id] == undefined ? false : reponseCritere[item.id]}
                                                                              onChange={hamdleReponseCritereChange}
                                                                          />
                                                                      )
                                                                  },
                                                              )}
                                                    </CCol>
                                                </div>
                                            )
                                        })}
                                    </CRow>
                                </CAccordionBody>
                            </CAccordionItem>
                        </CAccordion>

                        <CRow className="d-grid gap-2">
                            <CCol className="d-grid gap-2">
                                <CButton type='submit'>Postuler</CButton>
                            </CCol>
                        </CRow>
                    </CForm>
                </CCardBody>
            </CCard>
        </CContainer>
    )
}

export default Postuler
