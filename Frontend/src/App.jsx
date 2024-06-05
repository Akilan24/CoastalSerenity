import React, { useEffect, useRef } from "react";
import Home from "./component/Home/Home.jsx";
import { BrowserRouter } from "react-router-dom";
function App() {
  return (
    <BrowserRouter>
      <Home />
    </BrowserRouter>
  );
}

export default App;
