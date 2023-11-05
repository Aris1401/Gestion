import { CButton, CCard, CCardBody, CCardText, CCardTitle, CCol, CForm, CModal, CModalBody, CModalFooter, CModalHeader, CModalTitle, CRow, CTable } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/components/utility/Api'

export const MotifsCongee = () => {
  return new Promise((resolve, reject) => {
    makeRequest({
      url: 'MotifCongee',
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

const DemandeCongee = () => {
  // Touts mes demandes de congees colonnes
  const demandesColonnes = [
    {
      key: 'datedemande',
      label: 'Date demande',
      _props: { scope: 'col' }
    },
    {
      key: 'datedebut',
      label: 'Date debut',
      _props: { scope: 'col' }
    },
    {
      key: 'datefin',
      label: 'Date fin',
      _props: { scope: 'col' }
    },
    {
      key: 'status',
      label: 'Status',
      _props: { scope: 'col' }
    }
  ]

  // Modal demande de congee
  const [visibilteModalCongee, setVisibiliteModalCongee] = useState(false)

  // Obtenir les motifs de congee
  useEffect(() => {
    MotifsCongee().then((data) => {
      console.log(data)
    })
  }, [])

  return (
    <CCard>
      <CCardBody>
        <CCardTitle>
          <CRow>
            <CCol>
              Tout mes demandes
            </CCol>
            <CCol>
              <CButton onClick={(e) => {
                setVisibiliteModalCongee(!visibilteModalCongee)
              }}>Faire une demande</CButton>
            </CCol>
          </CRow>

        </CCardTitle>


        <CCardText className='d-flex gap-2 flex-column'>
          <CRow>
            <CCol>
              <CTable columns={demandesColonnes} items={[]} />
            </CCol>
          </CRow>

        </CCardText>

        {/* Tableau demandes de congee */}
        <CModal
          onClose={() => {
            setVisibiliteModalCongee(false)
          }}
          visible={visibilteModalCongee}
        >
          <CForm>
            <CModalHeader>
              <CModalTitle>Demande congee</CModalTitle>
            </CModalHeader>
            <CModalBody>
              <p>React Modal body text goes here.</p>
            </CModalBody>
            <CModalFooter>
              <CButton color="secondary">Close</CButton>
            </CModalFooter>
          </CForm>
        </CModal>

      </CCardBody>
    </CCard>
  )
}

export default DemandeCongee