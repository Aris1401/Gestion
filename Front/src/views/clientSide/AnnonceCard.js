import { CButton, CCard, CCardBody, CCardSubtitle, CCardText, CCardTitle } from '@coreui/react'
import React from 'react'

const AnnonceCard = (props) => {
  return (
    <CButton className='align-items-start'>
      <CCard style={{ width: '16rem', height: 'auto' }}>
        <CCardBody>
          <CCardTitle>
            <h3>Offre emploi</h3>
          </CCardTitle>
          <CCardSubtitle>{props.annonce.description}</CCardSubtitle>
          <CCardText style={{ marginTop: '1rem' }}>
            <b>Poste:</b> {props.annonce.titre}
          </CCardText>
        </CCardBody>
      </CCard>
    </CButton>
  )
}

export default AnnonceCard
