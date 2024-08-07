import React from "react";
import "./Tabs.css";
import { useNavigate } from "react-router-dom";

function Tabs() {
  const navigate = useNavigate();
  function routeTo(nav) {
    navigate(nav);
  }
  function accountDetails() {
    const element = document.getElementById("profile");
    const element1 = document.getElementById("passwordclass");
    const element2 = document.getElementById("csclass");
    if (element.style.display == "none") {
      element2.style.display = "none";
      element.style.display = "flex";
    } else {
      element.style.display = "none";
      element1.style.display = "none";
    }
  }
  function customerCare() {
    const element = document.getElementById("csclass");
    const element1 = document.getElementById("profile");
    const element2 = document.getElementById("passwordclass");
    if (element.style.display == "none") {
      element1.style.display = "none";
      element2.style.display = "none";
      element.style.display = "flex";
    } else {
      element.style.display = "none";
    }
  }
  return (
    <div className="menu">
      <img id="tabsLogo" src="./cslogo.png" />
      <div className="selector">
        <div id="tabs">
          <button onClick={(n) => routeTo("/hotel")}>
            <img id="hotellogo" src="../src/assets/TabIcons/hotellogo.png" />
            <p>Hotels</p>
          </button>
        </div>
        <div id="tabs">
          <button onClick={(n) => routeTo("/flight")}>
            <img id="planelogo" src="../src/assets/TabIcons/planelogo.png" />
            <p>Flights</p>
          </button>
        </div>
        <div id="tabs">
          <button onClick={(n) => routeTo("/train")}>
            <img id="trainlogo" src="../src/assets/TabIcons/trainlogo.png" />
            <p>Trains</p>
          </button>
        </div>
        <div id="tabs">
          <button onClick={(n) => routeTo("/bus")}>
            <img id="buslogo" src="../src/assets/TabIcons/buslogo.png" />
            <p>Buses</p>
          </button>
        </div>
        <div id="tabs">
          <button onClick={(n) => routeTo("/cab")}>
            <img id="carlogo" src="../src/assets/TabIcons/cablogo.png" />
            <p>Cabs</p>
          </button>
        </div>
      </div>
      <div id="tabs">
        <button className="login" onClick={accountDetails}>
          <img id="icon" src="../login.png" />
          Profile
        </button>
      </div>
      <div id="tabs">
        <button className="cslogo" onClick={customerCare}>
          <img id="cslogo" src="../customer.png" />
          <p>Customer Service</p>
        </button>
      </div>
    </div>
  );
}

export default Tabs;
