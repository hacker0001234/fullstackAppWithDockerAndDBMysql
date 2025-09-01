import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Main from "./elements/Main.jsx";
import TestApiToBackend from "./elements/TestApiToBackend.jsx";
import TestApiToBackendGet from "./elements/TestApiToBackendGet.jsx";
import TestApiToBD from "./elements/TestApiToBD.jsx";

function App() {

  return (
    <BrowserRouter>
      <Routes>
          <Route path={"/"} element={<Main/>} />
          <Route path={"/backend"} element={<TestApiToBackend/>}/>
          <Route path={"/post"} element={<TestApiToBD/>}/>
          <Route path={"/get"} element={<TestApiToBackendGet/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
