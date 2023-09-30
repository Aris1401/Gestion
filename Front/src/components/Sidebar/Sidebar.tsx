import { NavLink } from "react-router-dom"
import './Sidebar.css'
 
interface SidebarProps {
  routes : {name: string, element: any, path: any, page: any; } []
}

const Sidebar = ({routes} : SidebarProps) => {
  return (
    <>
      <div id="sidebar-wrapper">
        <ul className="sidebar-nav">
          <li className="sidebar-brand">
            Mikolo
          </li>

          {routes.map((route, index) => {
            return <>
              <li key={index}>
                <NavLink to={route.page + route.path}>{route.name}</NavLink>
              </li>
            </>
          })}
        </ul>
      </div>
    </>
  );
}

export default Sidebar;