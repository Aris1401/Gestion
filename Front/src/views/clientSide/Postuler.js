import { CCard, CCardBody, CCardHeader, CCol, CContainer, CForm, CFormInput, CRow } from '@coreui/react'
import React, { useState } from 'react'
import { useParams } from 'react-router-dom'
import { getBesoinDetails } from '../besoins/DetailsBesoin';

const Postuler = () => {
    // Getting the current besoin
    const { besoin } = useParams();

    // Getting the besoin details
    const [currentBesoin, setCurrentBesoin] = useState();
    getBesoinDetails(besoin).then((data) => {
        setCurrentBesoin(data)
    })

  return (
    <CContainer style={{ marginTop: '1rem' }}>
        <CCard>
            <CCardHeader className='text-center'>
                <h3>
                    Jointure de CV
                </h3>
            </CCardHeader>
            
            <CCardBody>
                <CForm>
                    {/* Nom - prenom */}
                    <CRow>
                        <CCol>
                            <CFormInput name='nom' label='Nom' id='nom' placeholder='Nom...' />
                        </CCol>
                        <CCol>
                            <CFormInput name='prenom' label='Prenom' id='prenom' placeholder='Prenom...' />
                        </CCol>
                    </CRow>
                </CForm>
            </CCardBody>
        </CCard>
    </CContainer>
  )
}

export default Postuler