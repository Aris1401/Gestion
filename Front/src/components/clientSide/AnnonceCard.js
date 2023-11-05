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
import React, { useEffect, useState } from 'react'
import { makeRequest } from '../utility/Api'

const AnnonceCard = (props) => {
    // Deja postiler
    const [dejaPostuler, setDejaPostuler] = useState(false);

    const checkIfPostuler = () => {
        makeRequest({
            url: `DejaPostuler?besoin=${props.annonce.besoin}`,
            requestType: 'GET',
            successCallback: (data) => {
                setDejaPostuler(data == null ? false : true)
            }, failureCallback: (error) => {
                alert(error)
            }
        })
    }

    // Check if has done test

    useEffect(() => {
        checkIfPostuler()
    }, [])

    // Check si a deja repondu au test
    const [dejaReponduAuTest, setDejaReponduAuTest] = useState(false)
    const checkIfReponduTest = () => {
        makeRequest({
            url: `DejaReponduAuTest?besoin=${props.annonce.besoin}`,
            requestType: 'GET',
            successCallback: (data) => {
                setDejaReponduAuTest(data == null ? false : true)
            }, failureCallback: (error) => {
                alert(error)
            }
        })
    }

    // Check si la personne a deja un cv
    const [obtenuEntretient, setObtenuEntretient] = useState(false);
    const [entretient, setEntretient] = useState(null);
    const checkObtenuEntretient = () => {
        return new Promise((resolve, reject) => {
            makeRequest({
                url: `ObtenuEntretient?besoin=${props.annonce.besoin}`,
                requestType: 'GET',
                successCallback: (data) => {
                    resolve(data)
                },
                failureCallback: (error) => {
                    alert(error)
                    reject(error)
                }
            })
        })
    }

    useEffect(() => {
        checkIfReponduTest()
    }, [])

    useEffect(() => {
        checkObtenuEntretient().then((data) => {
            setObtenuEntretient(data == null ? false : true)
            if (data != null) setEntretient(data)
        })
    }, [])

    return (
        <CCard style={{ width: '16rem', height: 'auto' }}>
            <CHeader><h3>Offre emploi</h3></CHeader>
            <CCardBody>
                <CCardTitle></CCardTitle>
                <CCardSubtitle>{props.annonce.description}</CCardSubtitle>
                <CCardText style={{ marginTop: '1rem' }}>
                    <b>Poste:</b> {props.annonce.titre}
                </CCardText>
                {
                    obtenuEntretient ?
                    <CButton className="justify-content-end">Entretient: {`${entretient.dateEntretient} (${entretient.dureeEntretient} minutes)`} </CButton>
                    :
                    dejaReponduAuTest ?
                    <CButton className="justify-content-end">En attente</CButton>
                    : !dejaPostuler ?
                        <CButton className="justify-content-end" href={`#/acceuil/postuler/${props.annonce.besoin}`}>Postuler</CButton>
                        :
                        <CButton className="justify-content-end" href={`#/acceuil/test/${props.annonce.besoin}`} >Faire le test</CButton>
                }
                <CCardFooter className='text-medium-emphasis' style={{ marginTop: '1rem' }}><b>Date fin:</b> {props.annonce.dateFin}</CCardFooter>
            </CCardBody>
        </CCard>
    )
}

export default AnnonceCard
