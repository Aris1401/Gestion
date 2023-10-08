import React, { useEffect, useState } from 'react'
import { Link, Navigate, redirect, useNavigate } from 'react-router-dom'
import {
    CButton,
    CCard,
    CCardBody,
    CCardGroup,
    CCol,
    CContainer,
    CForm,
    CFormInput,
    CInputGroup,
    CInputGroupText,
    CRow,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilLockLocked, cilUser } from '@coreui/icons'
import { makeRequest } from 'src/components/utility/Api'
import CheckAuth, { RedirectToAccessedPage } from 'src/components/auth/CheckAuth'

const Login = () => {
  RedirectToAccessedPage();

    // Login errors
    const [error, setError] = useState("")

    // User name and password inputs
    const [username, setUsername] = useState()
    const [password, setPassword] = useState()

    // Submitting the login form
    const handleLoginFormSubmit = (e) => {
        // Preventing from the default action
        e.preventDefault()

        // Sending form data
        let loginDetails = new FormData()
        loginDetails.set('email', username)
        loginDetails.set('password', password)

        makeRequest({
            url: 'Login',
            isFormData: true,
            requestType: 'POST',
            values: loginDetails,
            successCallback: (data) => {
                let compte = data
                setError("")
                if (data != "Connected") setError(data);
            },
            failureCallback: (error) => {
                alert(error)
            },
        })
    }

    return (
        <div className="bg-light min-vh-100 d-flex flex-row align-items-center">
            <CContainer>
                <CRow className="justify-content-center">
                    <CCol md={8}>
                        <CCardGroup>
                            <CCard className="p-4">
                                <CCardBody>
                                    <CForm onSubmit={handleLoginFormSubmit}>
                                        <h1>Login</h1>
                                        <p className="text-medium-emphasis">
                                            Sign In to your account
                                        </p>
                                        <CInputGroup className="mb-3">
                                            <CInputGroupText>
                                                <CIcon icon={cilUser} />
                                            </CInputGroupText>
                                            <CFormInput
                                                placeholder="Email"
                                                autoComplete="username"
                                                value={username}
                                                onChange={(e) => {
                                                    setUsername(e.target.value)
                                                }}
                                            />
                                        </CInputGroup>
                                        <CInputGroup className="mb-4">
                                            <CInputGroupText>
                                                <CIcon icon={cilLockLocked} />
                                            </CInputGroupText>
                                            <CFormInput
                                                type="password"
                                                placeholder="Password"
                                                value={password}
                                                onChange={(e) => {
                                                    setPassword(e.target.value)
                                                }}
                                            />
                                        </CInputGroup>
                                        <CRow>
                                            {error != "" && (
                                                <p
                                                    style={{
                                                        fontSize: '.8rem',
                                                        color: 'red',
                                                        fontWeight: 'bold',
                                                    }}
                                                >{error}</p>
                                            )}
                                            <CCol xs={6}>
                                                <CButton
                                                    color="primary"
                                                    type="submit"
                                                    className="px-4"
                                                >
                                                    Se connecter
                                                </CButton>
                                            </CCol>
                                            <CCol xs={6} className="text-right">
                                                <CButton color="link" className="px-0">
                                                    Forgot password?
                                                </CButton>
                                            </CCol>
                                        </CRow>
                                    </CForm>
                                </CCardBody>
                            </CCard>
                            <CCard className="text-white bg-primary py-5" style={{ width: '44%' }}>
                                <CCardBody className="text-center">
                                    <div>
                                        <h2>Sign up</h2>
                                        <p>
                                            Vous aviez pas de compte? Vous pouvez vous inscrire sur
                                            notre site
                                        </p>
                                        <Link to="/register">
                                            <CButton
                                                color="primary"
                                                className="mt-3"
                                                active
                                                tabIndex={-1}
                                            >
                                                {"S'inscrire"}
                                            </CButton>
                                        </Link>
                                    </div>
                                </CCardBody>
                            </CCard>
                        </CCardGroup>
                    </CCol>
                </CRow>
            </CContainer>
        </div>
    )
}

export default Login
