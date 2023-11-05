import { CButton, CCard, CCardBody, CCardTitle, CTable } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/components/utility/Api'

export const getListePersonnel = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'ListePersonnel',
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

const ListePersonnel = () => {
    const listePersonnelColumn = [
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
          key: 'age',
          label: 'Age',
          _props: { scope: 'col' }
        },
        {
            key: 'dateEmbauche',
            label: 'Date embauche',
            _props: { scope: 'col' }
        },
        {
            key: 'anciennete',
            label: 'Anciennete',
            _props: { scope: 'col' }
          },
          {
            key: 'poste',
            label: 'Poste',
            _props: { scope: 'col' }
          },
          {
            key: 'service',
            label: 'Service',
            _props: { scope: 'col' }
          },
          {
            key: 'contrat',
            label: 'Contrat',
            _props: { scope: 'col' }
          }
    ]

    const [listePersonnelItems, setListePersonnelItems] = useState([])
    useEffect(() => {
        setListePersonnelItems([])
        getListePersonnel().then((data) => {
            data.forEach((item) => {
                let personnel = {
                    nom: item.nom,
                    prenom: item.prenom,
                    email: item.email,
                    age: `${item.age} ans`,
                    dateEmbauche: item.dateEmbauche,
                    anciennete: item.anciennete,
                    poste: item.poste,
                    service: item.service,
                    contrat: <CButton>Afficher contrat</CButton>
                }

                setListePersonnelItems((prev) => ([...prev, personnel]))
            })
        })
    }, [])

  return (
    <CCard>
        <CCardBody>
            <CCardTitle>
                Liste personnel
            </CCardTitle>

            <CTable columns={listePersonnelColumn} items={listePersonnelItems}></CTable>
        </CCardBody>
    </CCard>
  )
}

export default ListePersonnel