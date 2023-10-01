import { CCardBody, CCardTitle, CContainer, CRow, CCard, CTable, CCol, CButton } from '@coreui/react'
import React from 'react'

const Besoins = () => {
  // Creatting a table of all the needs
  const columns = [
    {
      key: 'titre',
      labe: 'Titre',
      _props: {scope: 'col'}
    },
    {
      key: 'dateBesoin',
      label: 'Date Besoin',
      _props: {scope: 'col'}
    },
    {
      key: 'status',
      label: 'Status',
      _props: {scope: 'col'}
    },
    {
      key: 'more',
      label: 'Plus d\'information',
      _props: {scope: 'col'}
    }
  ];

  return (<>
    <CCard style={{ padding: '1rem' }}>
      <CCardTitle>
        <h1>Besoins</h1>
      </CCardTitle>

      <CCardBody>
        <CContainer>
          <CRow className='justify-content-between'>
            <CCol><p>Liste des besoins</p></CCol>
            <CCol><CButton>Ajouter besoin</CButton></CCol>
          </CRow>
          <CRow>
            <CTable columns={columns} items={[]}></CTable>
          </CRow>
        </CContainer>
      </CCardBody>
    </CCard>
  </>)
}

export default Besoins
