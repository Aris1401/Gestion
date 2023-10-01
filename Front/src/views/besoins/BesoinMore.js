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
import { NavLink, Outlet, Route, Routes, useParams } from 'react-router-dom'
import React from 'react'

const BesoinMore = () => {
  return (
    <CContainer style={{ marginTop: '1rem' }}>
      <CRow>
        <CNav variant="tabs">
          <CNavItem>
            <CNavLink to="criteres" component={NavLink}>
              Modifier Criteres
            </CNavLink>
          </CNavItem>
          <CNavItem>
            <CNavLink to="cv" component={NavLink}>
              Liste CV
            </CNavLink>
          </CNavItem>
          <CNavItem>
            <CNavLink href="questionaires">Liste Questionnaires</CNavLink>
          </CNavItem>
        </CNav>
      </CRow>

      <CRow>
        <Outlet />
      </CRow>
    </CContainer>
  )
}

export default BesoinMore
