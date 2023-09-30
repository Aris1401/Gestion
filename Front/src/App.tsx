import { BrowserRouter, Routes, Route } from "react-router-dom";
import Admin from "./pages/Admin";
import DashboardRoutes from "./Routes";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/admin" element={ <Admin /> }>
          {DashboardRoutes.map((route, index) => {
              if (route.page === "/admin") {
                  return (
                      <Route path={route.page + route.path} element={<route.element/>} />
                  );
              } else return null;
          })}
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;