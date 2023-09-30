import React from 'react'
import { Route, Routes, Outlet } from "react-router-dom";
import Sidebar from '../components/Sidebar/Sidebar';
import DashboardRoutes from '../Routes';

const Admin = () => {
  return (
    <>
        <div className='wrapper'>
            <Sidebar routes={DashboardRoutes}/>
            <div className='main-content'>
                
                <Outlet/>
            </div>
        </div>
    </>
  );
}

export default Admin