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

  return (
    <CCard style={{ padding: '1rem' }}>
      <CCardTitle>{besoin.titre}</CCardTitle>
      <CCardSubtitle className="mb-2 text-medium-emphasis">{besoin.description}</CCardSubtitle>
      
      <CCardBody>
        <CContainer>
          <CRow>
            <CCol>Volume de taches: {besoin.volumeTaches}h</CCol>
            <CCol>Taux Jour/Homme: {besoin.tauxJourHomme}jour/homme</CCol>
            {/* Nombre de personne a avoir */}
          </CRow>

          <BesoinMore besoin={besoin} />
        </CContainer>
      </CCardBody>
    </CCard>
  )
}

export default DetailsBesoin
