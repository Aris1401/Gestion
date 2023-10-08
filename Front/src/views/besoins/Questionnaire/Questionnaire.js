import {
    CButton,
    CCardText,
    CCol,
    CContainer,
    CForm,
    CFormTextarea,
    CInputGroup,
    CFormInput,
    CModal,
    CModalBody,
    CModalFooter,
    CModalHeader,
    CModalTitle,
    CRow,
    CFormLabel,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { useLocation, useParams } from 'react-router-dom'
import Question from 'src/components/questionnaire/Question'
import { makeRequest } from 'src/components/utility/Api'

const Questionnaire = ({ props }) => {
    // Getting the current besoin
    const { id } = useParams()
    const location = useLocation()

    // Ajout de question form
    const [question, setQuestion] = useState('')
    const handleQuestionFormSubmit = (e) => {
        e.preventDefault()

        makeRequest({
            url: `AjoutQuestionnaireController?besoin=${id}&estchoixmultiple=true&question=${question}`,
            requestType: 'GET',
            successCallback: (data) => {},
            failureCallback: (error) => {
                alert(error)
            },
        })

        // Resetting values
        setQuestion('')

        // Reinitiliser les questions
        getListeQuestions()
    }

    // Getting all the questions
    const [listeQuestions, setListeQuestions] = useState([])
    const getListeQuestions = () => {
        makeRequest({
            url: `ListeQuestions?besoin=${id}`,
            requestType: 'GET',
            successCallback: (data) => {
                setListeQuestions(data)
            },
            failureCallback: (error) => {
                alert(error)
            },
        })
    }

    useEffect(() => {
        getListeQuestions()
    }, [location])

    // Handling question modification
    const [modifQuestionVisibility, setModifQuestionVisibility] = useState(false)
    const [questionSelected, setQuestionSelected] = useState()
    const handleModificationQuestion = (question) => {
        // alert("clicked")
        setModifQuestionVisibility(true)
        setQuestionSelected(question)

        setReponse("")
        setNoteReponse(0)
    }

    // Submitting new reponse
    let [reponse, setReponse] = useState("")
    let [noteReponse, setNoteReponse] = useState(0)

    const handleReponseSubmit = (e) => {
        e.preventDefault()

        makeRequest({
            url: `AjoutChoixReponseController?questionnaire=${questionSelected}&note=${noteReponse}&reponse=${reponse}`,
            requestType: 'GET',
            successCallback: (data) => {

            },
            failureCallback: (error) => {
                alert(error)
            }
        })

        setModifQuestionVisibility(false)

        getListeQuestions()

        setReponse("")
        setNoteReponse(0)
    }

    return (
        <>
            <CContainer style={{ marginTop: '1rem' }}>
                <CRow>
                    <CCol>
                        <h5>Liste des questions et reponses</h5>
                    </CCol>
                </CRow>

                <CRow className="p-3 d-flex gap-2">
                    {listeQuestions.map((listeQuestion, index) => {
                        return (
                            <Question
                                key={listeQuestion.id}
                                qid={listeQuestion.id}
                                question={listeQuestion.question}
                                handleModificationQuestion={handleModificationQuestion}
                                modalVisibility={modifQuestionVisibility}
                            />
                        )
                    })}
                </CRow>

                <CRow>
                    <CForm onSubmit={handleQuestionFormSubmit}>
                        <CFormTextarea
                            rows={3}
                            label="Nouvelle question:"
                            id="question"
                            placeholder="Votre question ici..."
                            value={question}
                            onChange={(e) => setQuestion(e.target.value)}
                        ></CFormTextarea>
                        <CButton type="submit" style={{ marginTop: '1rem' }}>
                            Ajouter question
                        </CButton>
                    </CForm>
                </CRow>
            </CContainer>

            <CModal
                visible={modifQuestionVisibility}
                onClose={() => setModifQuestionVisibility(false)}
            >
                <CForm onSubmit={handleReponseSubmit}>
                    <CModalHeader>
                        <CModalTitle>Modifer question</CModalTitle>
                    </CModalHeader>
                    <CModalBody>
                        <CCardText>
                            <CRow className='p-3'>Question {questionSelected}</CRow>

                            <CRow className="p-3">
                                <CFormLabel htmlFor="reponse"><h6>Nouvelle reponse</h6></CFormLabel>
                                <CInputGroup>
                                    <CFormTextarea xs={10} name="reponse" id="reponse" value={reponse} onChange={(e) => setReponse(e.target.value)}></CFormTextarea>
                                    <CFormInput className="note" id="note" type="number" value={noteReponse} onChange={(e) => setNoteReponse(e.target.value)} />
                                </CInputGroup>
                            </CRow>
                        </CCardText>
                    </CModalBody>
                    <CModalFooter>
                        <CButton type='submit' color="primary">Modifier</CButton>
                    </CModalFooter>
                </CForm>
            </CModal>
        </>
    )
}

export default Questionnaire
