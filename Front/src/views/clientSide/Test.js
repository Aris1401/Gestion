import {
    CButton,
    CCard,
    CCardBody,
    CCardHeader,
    CCol,
    CContainer,
    CForm,
    CFormCheck,
    CRow,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { getListeQuestions } from '../besoins/Questionnaire/Questionnaire'
import { getReponsesQuestion } from 'src/components/questionnaire/Question'
import { makeRequest } from 'src/components/utility/Api'

const Test = () => {
    // Obtenir tout les questionnaire
    const { besoin } = useParams()

    const [listeQuestion, setListeQuestion] = useState([])
    useEffect(() => {
        getListeQuestions(besoin).then((data) => {
            setListeQuestion(data)
        })
    }, [])

    // Reponses question
    const [reponseQuestion, setReponseQuestion] = useState({})

    const getListeReponses = async () => {
        let reponsesTemp = {}
        let req = listeQuestion.map(
            (item) =>
                new Promise((resolve, reject) => {
                    getReponsesQuestion(item.id).then((data) => {
                        reponsesTemp[item.id] = data
                        resolve()
                    })
                }),
        )
        await Promise.all(req)
        setReponseQuestion(reponsesTemp)
    }

    useEffect(() => {
        getListeReponses()
    }, [listeQuestion])

    // Sumbitting the form
    const [reponsesCheckbox, setReponsesCheckbox] = useState({})
    const handleCheckboxChange = (e) => {
        setReponsesCheckbox((prev) => ({
            ...prev,
            [e.target.id]: e.target.checked,
        }))
    }

    const handleQuestionnaireFormSumbit = (e) => {
        e.preventDefault()

        listeQuestion.forEach((question) => {
            reponseQuestion[question.id].forEach((item) => {
                if (reponsesCheckbox[item.id] == true) {
                    makeRequest(({
                        url: `AjoutReponseQuestionnaireController?questionnaire=${question.id}&reponse=${item.id}&besoin=${besoin}`,
                        requestType: 'GET',
                        successCallback: (data) => {

                        },
                        failureCallback: (error) => {
                            alert(error)
                        }
                    }))
                }
            })
        })
    }

    return (
        <CContainer style={{ marginTop: '1rem' }}>
            <CCard>
                <CForm onSubmit={handleQuestionnaireFormSumbit}>
                    <CCardHeader className="text-center">
                        <h3>Questionnaire</h3>
                    </CCardHeader>

                    <CCardBody>
                        {listeQuestion.map((question, index) => {
                            return (
                                <CRow key={question.id}>
                                    <CCol>
                                        <CRow>
                                            <h5>Question {index + 1}</h5>
                                        </CRow>

                                        {/* Question */}
                                        <CRow>
                                            <p>{question.question}</p>
                                        </CRow>

                                        <CRow className="p-3">
                                            {reponseQuestion[question.id] == undefined
                                                ? null
                                                : reponseQuestion[question.id].map(
                                                      (item, index) => {
                                                          return (
                                                              <CFormCheck
                                                                  key={item.id}
                                                                  label={item.reponse}
                                                                  id={item.id}
                                                                  checked={reponsesCheckbox[item.id] ? reponsesCheckbox[item.id] : false}
                                                                  onChange={handleCheckboxChange}
                                                              />
                                                          )
                                                      },
                                                  )}
                                        </CRow>
                                    </CCol>
                                </CRow>
                            )
                        })}
                        <CRow>
                            <CCol>
                                <CButton type="submit">Valider questionnaire</CButton>
                            </CCol>
                        </CRow>
                    </CCardBody>
                </CForm>
            </CCard>
        </CContainer>
    )
}

export default Test
