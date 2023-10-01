import { CButton, CCol, CContainer, CForm, CFormInput, CFormLabel, CRow } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { useLocation } from 'react-router'
import { makeRequest } from 'src/components/utility/Api'

const CritereCV = (props) => {
    var currentPath = useLocation();

  // Obtenir les critres
  const [criteres, setCriteres] = useState([])
  const getCriteres = () => {
    makeRequest({
      url: 'ListeCritereAPI',
      successCallback: (data) => {
        setCriteres(data)
      },
      failureCallback: (error) => {
        alert(error)
      },
    })
  }

  useEffect(() => {
    getCriteres()
  }, [])

  // Notes de criteres
  const [criteresInputs, setCriteresInputs] = useState({})

  const initializeCriteresCoefs = () => {
    criteres.forEach((critere) => {
      makeRequest({
        url: `CoefficientCritereAPI?besoin=${props.besoin}&critere=${critere.id}`,
        requestType: 'GET',
        successCallback: (data) => {
          let tempInputs = criteresInputs
          tempInputs[critere.id] = data.coefficient

          setCriteresInputs(tempInputs)

          console.log(criteresInputs)
        },
      })
    })
  }

  // Sous critere
  const [sousCriteres, setSousCriteres] = useState({})

  const getSousCritere = () => {
    criteres.forEach((critere) => {
      makeRequest({
        url: `ListeSousCritereAPI?critere=${critere.id}`,
        requestType: 'GET',
        successCallback: (data) => {
          let tempDictionnary = sousCriteres
          tempDictionnary[critere.id] = data

          setSousCriteres(tempDictionnary)

          console.log(sousCriteres)
        },
      })
    })
  }

  useEffect(() => {
    getSousCritere()
    initializeCriteresCoefs()
  }, [criteres])

  return (
    <CContainer style={{ marginTop: '1rem' }}>
      <CRow>
        <h6>Modifier criteres de selection</h6>
      </CRow>
      <CForm>
        <CRow>
          {criteres.map((critere, index) => {
            return (
              <CRow key={index + 1000}>
                <CCol xs={1}>
                  <CFormInput
                    key={critere.nom}
                    label={critere.nom}
                    name={critere.nom}
                    type="number"
                    onChange={(e) => {
                      let tempInputs = criteresInputs
                      tempInputs[critere.id] = e.target.value

                      setCriteresInputs(tempInputs)
                    }}
                    value={criteresInputs[critere.id]}
                  />
                </CCol>

                <CCol xs={1}></CCol>

                <CCol xs={10}>
                  {sousCriteres[critere.id] != undefined
                    ? sousCriteres[critere.id].map((sousCritere, index) => {
                        return (
                          <CRow key={index + 2000}>
                            <CCol key={sousCritere.id}>
                              <CFormLabel htmlFor={sousCritere.nom}>
                                {sousCritere.nom}
                              </CFormLabel>
                            </CCol>

                            <CCol>
                              <CFormInput
                                key={sousCritere.nom}
                                name={sousCritere.nom}
                                type="number"
                                id={sousCritere.nom}
                              />
                            </CCol>
                          </CRow>
                        )
                      })
                    : null}
                </CCol>
              </CRow>
            )
          })}
        </CRow>

        <CRow>
          <CButton type="submit">Modifier criteres</CButton>
        </CRow>
      </CForm>
    </CContainer>
  )
}

export default CritereCV
