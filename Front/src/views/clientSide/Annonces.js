import {
    CButton,
    CButtonGroup,
    CCard,
    CCardBody,
    CCardText,
    CCardTitle,
    CNav,
    CNavItem,
    CNavLink,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/components/utility/Api'
import AnnonceCard from '../../components/clientSide/AnnonceCard'

const Annonces = () => {
    // Annonces de service selectionner
    const [selectionService, setSelectionService] = useState(-1)

    const handleNavClick = (event, id) => {
        setSelectionService(id)
    }

    // Obtenir tout les services
    const [listeService, setListeService] = useState([])
    const getServicesDispo = () => {
        makeRequest({
            url: 'ListeServiceController',
            requestType: 'GET',
            successCallback: (data) => {
                setListeService(data)
            },
            failureCallback: (error) => {
                alert(error)
            },
        })
    }

    useEffect(() => {
        getServicesDispo()
    }, [selectionService])

    // Obtenir les listes
    const [annonces, setAnnonces] = useState([])

    const obtenirAnnonces = () => {
        makeRequest({
            url: 'ListeAnnonceAPI',
            requestType: 'GET',
            successCallback: (data) => {
                setAnnonces(data)
            },
            failureCallback: (error) => {
                alert(error)
            },
        })
    }

    useEffect(() => {
        obtenirAnnonces()
    }, [selectionService])

    return (
        <div>
            <div style={{ marginTop: '1rem' }} className="d-flex justify-content-center">
                <CButtonGroup role="group">
                    <CButton
                        onClick={(e) => {
                            handleNavClick(e, -1)
                        }}
                        className={selectionService === -1 ? 'active' : ''}
                        variant="outline"
                    >
                        All
                    </CButton>

                    {listeService.map((service, index) => {
                        return (
                            <CButton
                                variant="outline"
                                key={service.id}
                                onClick={(e) => {
                                    handleNavClick(e, service.id)
                                }}
                                className={selectionService === service.id ? 'active' : ''}
                            >
                                {service.nom}
                            </CButton>
                        )
                    })}
                </CButtonGroup>
            </div>

            <div className="d-flex align-items-start" style={{ marginTop: '1rem', gap: '1rem' }}>
                {annonces.map((annonce, index) => {
                    return selectionService === -1 ? (
                        <AnnonceCard annonce={annonce} key={annonce.id} />
                    ) : annonce.service === selectionService ? (
                        <AnnonceCard annonce={annonce} key={annonce.id} />
                    ) : null
                })}
            </div>
        </div>
    )
}

export default Annonces
