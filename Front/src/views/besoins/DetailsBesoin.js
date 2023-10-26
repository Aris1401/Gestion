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
    CTable,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { NavLink, Outlet, Route, Routes, useLocation, useParams } from 'react-router-dom'
import { makeRequest } from 'src/components/utility/Api'
import ListeCV, { detailsCV } from './ListeCV/ListeCV'
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

export const getNombrePersonnel = (besoin) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `NombreMaximumCandidature?besoin=${besoin}`,
            requestType: "GET",
            successCallback: (data) => {
                resolve(data)
            }
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

    // Nombre de personnel
    const [nombrePersonnel, setNombrePersonnel] = useState(0)
    const [nombreCandidature, setNombreCandidature] = useState(0)
    useState(() => {
        getNombrePersonnel(id).then((data) => {
            setNombreCandidature(data.entretient);
            setNombrePersonnel(data.reel)
        })
    }, [])

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
                            <CCol>
                                <CRow>
                                    <CCol>Volume de taches: {besoin.volumeTaches}h</CCol>
                                    <CCol>Taux Jour/Homme: {besoin.tauxJourHomme}jour/homme</CCol>
                                </CRow>
                            </CCol>
                            <CCol>Personnel: { nombrePersonnel }Personnes</CCol>
                            <CCol>Candidature: { nombreCandidature }Personnes</CCol>
                            {/* Nombre de personne a avoir */}
                        </CRow>

                        <BesoinMore besoin={besoin} />
                    </CContainer>
                </CCardBody>
            </CCard>

            {/* <CModal
            size='lg'
            visible={visibilityListeCandidature}
            onClose={() => setVisibilityListeCandidature(false)}>
                <CModalHeader>
                    <CModalTitle>Liste des meilleurs candidatures</CModalTitle>
                </CModalHeader>
                <CModalBody>
                    
                </CModalBody>
                <CModalFooter>
                    <CButton onClick={() => setVisibilityListeCandidature(false)} color="secondary">Close</CButton>
                    <CButton>Afficher agenda entretient</CButton>
                </CModalFooter>
            </CModal> */}
        </>
    )
}

export default DetailsBesoin
