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
import { NavLink, Outlet, Route, Routes, useParams } from 'react-router-dom'
import { makeRequest } from 'src/components/utility/Api'
import ListeCV from './ListeCV/ListeCV'
import BesoinMore from './BesoinMore'
import CritereCV from './CriteresCV/CritereCV'

const DetailsBesoin = () => {
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

  useEffect(() => {
    getDetailsBesoins()
  }, [])

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

          <Routes>
            <Route path='/' element={<BesoinMore />}>
                <Route path='/criteres' element={<CritereCV besoin={besoin} />} />
                <Route path='/cv' element={ <ListeCV besoin={besoin} /> } />
            </Route>
          </Routes>
        </CContainer>
      </CCardBody>
    </CCard>
  )
}

export default DetailsBesoin
