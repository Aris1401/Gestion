import {
    CCard,
    CCardTitle,
    CCardSubtitle,
    CCardBody,
    CContainer,
    CRow,
    CCol,
    CNav,
    CNavItem,
    CNavLink,
    CButton,
    CModal,
    CModalHeader,
    CModalTitle,
    CModalBody,
    CModalFooter,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { NavLink, Outlet, Route, Routes, useLocation, useParams } from 'react-router-dom'
import { makeRequest } from 'src/components/utility/Api'
import ListeCV from './ListeCV/ListeCV'
import BesoinMore from './BesoinMore'
import CritereCV from './CriteresCV/CritereCV'
import { CheckPageAuthority } from 'src/components/auth/CheckAuth'

export const getBesoinDetails = (besoin) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `InfosBesoinAPI?id=${besoin}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
        })
    })
}

const DetailsBesoin = () => {
    CheckPageAuthority()

    // Get the id of the parameter
    const { id } = useParams()

    // Obtenir le detail du besoin selectionner
    const [besoin, setBesoin] = useState({})
    const getDetailsBesoins = () => {
        makeRequest({
            url: `InfosBesoinAPI?id=${id}`,
            requestType: 'GET',
            successCallback: (data) => {
                setBesoin(data)
            },
        })
    }

    // Location
    const location = useLocation()

    useEffect(() => {
        getDetailsBesoins()
    }, [location])

    // Liste candidatures modal
    const [visibilityListeCandidature, setVisibilityListeCandidature] = useState(false)

    return (
        <>
            <CCard style={{ padding: '1rem' }}>
                <CCardTitle>{besoin.titre}</CCardTitle>
                <CCardSubtitle className="mb-2 text-medium-emphasis">
                    {besoin.description}
                </CCardSubtitle>

                <CCardBody>
                    <CContainer>
                        <CRow>
                            <CCol>
                                <CButton onClick={() => setVisibilityListeCandidature(true)}>Afficher meilleures candidatures</CButton>
                            </CCol>
                            <CCol>Volume de taches: {besoin.volumeTaches}h</CCol>
                            <CCol>Taux Jour/Homme: {besoin.tauxJourHomme}jour/homme</CCol>
                            {/* Nombre de personne a avoir */}
                        </CRow>

                        <BesoinMore besoin={besoin} />
                    </CContainer>
                </CCardBody>
            </CCard>

            <CModal
            visible={visibilityListeCandidature}
            onClose={() => setVisibilityListeCandidature(false)}>
                <CModalHeader>
                    <CModalTitle>Liste des meilleurs candidatures</CModalTitle>
                </CModalHeader>
                <CModalBody>
                    <p>React Modal body text goes here.</p>
                </CModalBody>
                <CModalFooter>
                    <CButton onClick={() => setVisibilityListeCandidature(false)} color="secondary">Close</CButton>
                </CModalFooter>
            </CModal>
        </>
    )
}

export default DetailsBesoin
