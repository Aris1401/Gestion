import { CButton, CCollapse, CContainer, CDropdown, CDropdownDivider, CDropdownItem, CDropdownMenu, CDropdownToggle, CForm, CFormInput, CNavItem, CNavLink, CNavbar, CNavbarBrand, CNavbarNav, CNavbarToggler } from '@coreui/react'
import React, { useState } from 'react'
import { NavLink } from 'react-router-dom'

const HomeNavBar = () => {
  const [visible, setVisible] = useState(false)
  return (
    <>
      <CNavbar expand="lg" colorScheme="light" className="bg-light">
        <CContainer fluid>
          <CNavbarBrand href="#">RE-Rapide</CNavbarBrand>
          <CNavbarToggler onClick={() => setVisible(!visible)} />
          <CCollapse className="navbar-collapse justify-content-end" visible={visible}>
            <CNavbarNav>
              <CNavItem>
                <CNavLink to="acceuil" component={NavLink}>
                  Acceuil
                </CNavLink>
              </CNavItem>
              <CNavItem>
                <CNavLink to="annonces" component={NavLink}>Annonces</CNavLink>
              </CNavItem>
              {/* <CDropdown variant="nav-item" popper={false}>
                <CDropdownToggle color="secondary">Dropdown button</CDropdownToggle>
                <CDropdownMenu>
                  <CDropdownItem href="#">Action</CDropdownItem>
                  <CDropdownItem href="#">Another action</CDropdownItem>
                  <CDropdownDivider />
                  <CDropdownItem href="#">Something else here</CDropdownItem>
                </CDropdownMenu>
              </CDropdown> */}
              
            </CNavbarNav>
            <CForm className="d-flex">
              <CFormInput type="search" className="me-2" placeholder="Search" />
              <CButton type="submit" color="success" variant="outline">
                Search
              </CButton>
            </CForm>
          </CCollapse>
        </CContainer>
      </CNavbar>
    </>
  )
}

export default HomeNavBar
