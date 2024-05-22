import React from "react";
import "./Hotel.css";
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
      <img id="logo" src="./cslogo.png" />
      <div className="selector">
        <div id="tab">
          <button onClick={(n) => routeTo("/hotel")}>
            <img id="hotellogo" src="./hotellogo.png" />
            <p>Hotels</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => routeTo("plane")}>
            <img id="planelogo" src="./planelogo.png" />
            <p>Flights</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => routeTo("/train")}>
            <img id="trainlogo" src="./trainlogo.png" />
            <p>Trains</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => routeTo("/bus")}>
            <img id="buslogo" src="./buslogo.png" />
            <p>Buses</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => routeTo("/cab")}>
            <img id="carlogo" src="./carlogo.png" />
            <p>Cabs</p>
          </button>
        </div>
      </div>
      <div id="tab">
        <button className="login" onClick={accountDetails}>
          <img id="icon" src="./login.png" />
          Profile
        </button>
      </div>
      <div id="tab">
        <button className="cslogo" onClick={customerCare}>
          <img id="cslogo" src="./customer.png" />
          <p>Customer Service</p>
        </button>
      </div>
    </div>
  );
}

export default Tabs;
