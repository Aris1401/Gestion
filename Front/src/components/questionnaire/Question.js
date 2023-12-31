import {
    CButton,
    CCard,
    CCardBody,
    CCardFooter,
    CCardText,
    CFormInput,
    CInputGroup,
    CInputGroupText,
    CListGroup,
    CListGroupItem,
} from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { makeRequest } from '../utility/Api'
import { useLocation } from 'react-router-dom'

export const getReponsesQuestion = (question) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `ListeReponse?question=${question}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                alert(error)
            },
        })
    })
}

const Question = (props) => {
    // Getting the question id
    const questionId = props.qid

    // Location
    const location = useLocation()

    // Get reponses
    const [reponses, setReponses] = useState([])
    useEffect(() => {
        getReponsesQuestion(questionId).then((listeReponses) => {
            setReponses(listeReponses)
        })
    }, [location, props.modalVisibility])

    return (
        <CCard>
            <CCardBody>
                <CCardText>{props.question}</CCardText>
            </CCardBody>
            <CListGroup flush>
                <CListGroupItem>
                    {reponses.map((reponse, index) => {
                        return (
                            <CInputGroup key={reponse.id} className="mb-3">
                                <CFormInput value={reponse.reponse} disabled />
                                <CInputGroupText id="basic-addon1">{reponse.note}</CInputGroupText>
                            </CInputGroup>
                        )
                    })}
                </CListGroupItem>
            </CListGroup>
            <CCardFooter className='d-flex gap-2 p-2'>
                <CButton onClick={(e) => props.onModification(questionId)}>
                    Modifier question
                </CButton>
                <CButton onClick={(e) => props.onDelete(questionId)}>
                    Supprimer question
                </CButton>
            </CCardFooter>
        </CCard>
    )
}

export default Question
