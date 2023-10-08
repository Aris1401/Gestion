import {
    CButton,
    CCard,
    CCardBody,
    CCardFooter,
    CCardSubtitle,
    CCardText,
    CCardTitle,
    CHeader,
} from '@coreui/react'
import React from 'react'

const AnnonceCard = (props) => {
    return (
        <CCard style={{ width: '16rem', height: 'auto' }}>
            <CHeader><h3>Offre emploi</h3></CHeader>
            <CCardBody>
                <CCardTitle></CCardTitle>
                <CCardSubtitle>{props.annonce.description}</CCardSubtitle>
                <CCardText style={{ marginTop: '1rem' }}>
                    <b>Poste:</b> {props.annonce.titre}
                </CCardText>
                <CButton className="justify-content-end">Postuler</CButton>
                <CCardFooter style={{ marginTop: '1rem' }}><b>Date fin:</b> {props.annonce.dateFin}</CCardFooter>
            </CCardBody>
        </CCard>
    )
}

export default AnnonceCard
