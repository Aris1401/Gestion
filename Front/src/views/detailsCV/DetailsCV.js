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
    CNavLink,
    CRow,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { detailsCV } from '../besoins/ListeCV/ListeCV'
import { useNavigate, useParams } from 'react-router-dom'
import CIcon from '@coreui/icons-react'
import { cilCloudDownload } from '@coreui/icons'
import { getCriteresForCV } from '../besoins/CriteresCV/CritereCV'
import { makeRequest } from 'src/components/utility/Api'

export const reponseCriteresCV = (cv, critere) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `ReponsesPourCV?cv=${cv}&critere=${critere}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                alert(error)
            }
        })
    })
}

export const checkObtenuEntretient = (cv) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `CheckEntretient?cv=${cv}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                alert(error)
                reject(error)
            }
        })
    })
}

const DetailsCV = () => {
    // CV inputs
    const [nom, setNom] = useState()
    const [prenom, setPrenom] = useState()
    const [addresse, setAddresse] = useState()
    const [datenaissance, setDateNaissance] = useState()
    const [email, setEmail] = useState()
    const [contact, setContact] = useState()
    const [description, setDescription] = useState()
    const [preuveDiplome, setPreuveDiplome] = useState([])
    const [preuveDeTravail, setPreuveDeTravail] = useState([])

    // Obtenir l'id du cv courant
    const { id } = useParams();

    // Verifier si la personne a deja obtenu un entretient
    const [hasEntretient, setHasEntretient] = useState(false)
    const [entretient, setEntretient] = useState();

    // Obtenir les details du cv
    useEffect(() => {
        detailsCV(id).then((data) => {
            setNom(data.nom)
            setPrenom(data.prenom)
            setAddresse(data.adresse)
            setDateNaissance(data.dateNaissance)
            setEmail(data.email)
            setContact(data.contact)
            setDescription(data.description)

            // Traitement preuve diplome et preuve de travail
            let diplomes = data.preuvediplome.split(",");
            let travails = data.preuvetravail.split(",");

            setPreuveDiplome(diplomes)
            setPreuveDeTravail(travails)

            // Changer le state de besoin
            checkObtenuEntretient(data.id).then((entretientData) => {
                if (entretientData != null) {
                    setHasEntretient(true)
                    setEntretient(entretientData);
                }
              })
        })
    }, [])

    // Obtention des criteres
    const [criteres, setCriteres] = useState([])
    useEffect(() => {
        getCriteresForCV().then((criteresCV) => {
            setCriteres(criteresCV)
        })
    }, [])

    // Obtenir les sous criteres repondu
    const [sousCriteres, setSousCriteres] = useState({})
    useEffect(() => {
        criteres.forEach((critere) => {
            reponseCriteresCV(id, critere.id).then((data) => {
                setSousCriteres(
                    (prev) => ({
                        ...prev,
                        [critere.id]: data
                    })
                    )
            })
        })
    }, [criteres])

    // Embaucher cv
    const navigate = useNavigate()
    const handleEmbaucheCv = () => {
        makeRequest({
            url: `EmbaucherCV?cv=${id}`,
            requestType: 'GET',
            successCallback: (data) => {
                return new navigate("/besoin")
            },
            failureCallback: (error) => {
                alert(error)
            }
        })
    } 

    return (
        <CContainer style={{ marginTop: '1rem', marginBottom: '3rem' }}>
            <CCard>
                <CCardHeader className="text-center">
                    <h3>Details de CV</h3>
                </CCardHeader>

                <CCardBody>
                    <CForm className="d-flex flex-column gap-3">
                        {/* Nom - prenom */}
                        <CRow>
                            <CCol>
                                <CFormInput
                                readOnly
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
                                readOnly
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
                                readOnly
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
                                readOnly
                                    name="datenaissance"
                                    label="Date de naissance"
                                    id="datenaissance"
                                    type="text"
                                    value={datenaissance}
                                    onChange={(e) => setDateNaissance(e.target.value)}
                                />
                            </CCol>
                        </CRow>

                        {/* Email - Contact */}
                        <CRow>
                            <CCol xs={9}>
                                <CFormInput
                                readOnly
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
                                readOnly
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
                                readOnly
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
                                <h6>Preuves de diplomes</h6>
                                {preuveDiplome.map((preuve, index) => {
                                    return (
                                        <CButton key={index}>
                                            <CNavLink href={preuve}><CIcon icon={cilCloudDownload} />{` Preuve diplome ${index + 1}`}</CNavLink>
                                        </CButton>
                                    )
                                })}
                            </CCol>

                            <CCol>
                                <h6>Preuves de traivails</h6>
                                {preuveDeTravail.map((preuve, index) => {
                                    return (
                                        <CButton key={index}><CNavLink href={preuve}><CIcon icon={cilCloudDownload} />{` Preuve travail ${index + 1}`}</CNavLink></CButton>
                                    )
                                })}
                            </CCol>
                        </CRow>

                        <CAccordion flush>
                            <CAccordionItem itemKey={1}>
                                <CAccordionHeader>Criteres de CV</CAccordionHeader>
                                <CAccordionBody>
                                    <CRow xs={{ cols: 2 }}>
                                        {criteres.map((critereItem, index) => {
                                            return (
                                                <div key={critereItem.id}>
                                                    <CCol>
                                                        <h6>{critereItem.nom}</h6>
                                                    </CCol>
                                                    <CCol>
                                                        {sousCriteres[critereItem.id] ==
                                                        undefined
                                                            ? null
                                                            : sousCriteres[critereItem.id].map(
                                                                  (item) => {
                                                                      return (
                                                                          <p key={item.nom} className='text-dark px-4' >{item.nom}</p>
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
                                {hasEntretient && <CButton onClick={(e) => {handleEmbaucheCv()}} type="submit">{`Embaucher - Entretient: ${entretient.dateEntretient}`}</CButton>}
                            </CCol>
                        </CRow>
                    </CForm>
                </CCardBody>
            </CCard>
        </CContainer>
    )
}

export default DetailsCV