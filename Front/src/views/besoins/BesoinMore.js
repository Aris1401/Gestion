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
import { NavLink, Outlet, Route, Routes, useLocation, useParams } from 'react-router-dom'
import React, { useEffect } from 'react'
import CritereCV from './CriteresCV/CritereCV'
import ListeCV from './ListeCV/ListeCV'

const BesoinMore = (props) => {
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
        <Routes>
          <Route path="/criteres" element={<CritereCV besoin={props.besoin} />} />
          <Route path="/cv" element={<ListeCV besoin={props.besoin} />} />
        </Routes>
      </CRow>
    </CContainer>
  )
}

export default BesoinMore
