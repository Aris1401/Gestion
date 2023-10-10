import { CButton, CCol, CContainer, CRow, CTable } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { makeRequest } from 'src/components/utility/Api'

const ListeCV = (props) => {
  const listeCVTableColumns = [
    {
      key: 'nom',
      label: 'Nom',
      _props: { scope: 'col' }
    },
    {
      key: 'prenom',
      label: 'Prenom',
      _props: { scope: 'col' }
    },
    {
      key: 'email',
      label: 'Email',
      _props: { scope: 'col' }
    },
    {
      key: 'plus',
      label: '',
      _props: { scope: 'col' }
    },
  ]

  // Getting the current besoin
  const { id } = useParams()

  // Getting all of the cv
  const [listeCV, setListeCV] = useState([])

  const getAllCVForBesoin = () => {
    makeRequest({
      url: `ListeCVController?besoin=${id}`,
      requestType: 'GET',
      successCallback: (data) => {
        data.forEach((cv) => {
          let tempCV = {
            nom: cv.nom,
            prenom: cv.prenom,
            email: cv.email,
            plus: <CButton>Afficher plus information</CButton>
          }

          setListeCV([...listeCV, tempCV])
        })
      }, 
      failureCallback: (error) => {
        alert(error)
      }
    })
  }

  useEffect(() => {
    getAllCVForBesoin()
  }, [])

  return (
    <CContainer style={{ marginTop: '1rem' }}>
      <CRow>
        <CCol>
          <h4>Liste des CV</h4>
        </CCol>
      </CRow>

      <CRow>
        <CTable columns={listeCVTableColumns} items={listeCV} />
      </CRow>
    </CContainer>
  )
}

export default ListeCV