import { Fragment } from 'react';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import { publicRoutes } from './routes'
import DefaultLayout from './Component/GlobalStyles/Layout/DefaultLayout';
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          {publicRoutes.map((route, index) => {
            const Page = route.component;
            return <Route key={index} path={route.path} element={<Page />} />;
          })}
        </Routes>

        <ToastContainer />
      </div>
    </Router>
  );
}

export default App;
