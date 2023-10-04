import { CContainer } from '@coreui/react'
import React from 'react'
import { Navigate, Route, Routes } from 'react-router-dom'
import HomeNavBar from 'src/components/clientSide/HomeNavBar'
import Annonces from 'src/views/clientSide/Annonces'
import Index from 'src/views/clientSide/Index'

// Home routes 
const homeRoutes = [
    {
        name: 'Acceuil',
        path: 'acceuil',
        element: <Index />
    },
    {
        name: 'Annonces',
        path: 'annonces',
        element: <Annonces />
    }
]

const HomeLayout = () => {
  return (
    <>
        <HomeNavBar />
        <CContainer>
            <Routes>
                {homeRoutes.map((route, index) => {
                    return <Route key={index} path={ route.path } name={route.name} element={route.element} />
                })}
                <Route path="/" element={<Navigate to="acceuil  " replace />} />
            </Routes>
        </CContainer>
    </>
  )
}

export default HomeLayout