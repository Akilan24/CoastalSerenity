import React from "react";
import { useNavigate } from "react-router-dom";
import "./AccountDetails.css";
function AccountDetails() {
  const navigate = useNavigate();
  function routeTo(nav) {
    navigate(nav);
  }
  function logout() {
    localStorage.removeItem(`accesstoken`);
    localStorage.removeItem(`refreshtoken`);
    localStorage.removeItem(`username`);
    navigate("/login");
  }
  function changePassword() {
    const element = document.getElementById("passwordclass");
    if (element.style.display == "none") {
      element.style.display = "flex";
    } else {
      element.style.display = "none";
    }
  }
  return (
    <div id="profile">
      <div>
        <h3>Account Details</h3>
      </div>
      <div id="ptab">
        <button onClick={(n) => routeTo("/profile")}>
          <img id="img" src="../Profile/profile.png" />
          Profile
        </button>
      </div>
      <div id="ptab">
        <button onClick={(n) => routeTo("/bookingDetails")}>
          <img id="img" src="../Profile/booking.png" />
          Booking Details
        </button>
      </div>
      <div id="ptab">
        <button onClick={changePassword}>
          <img id="img" src="../Profile/password.png" />
          Change Password
        </button>
      </div>
      <div id="ptab">
        <button onClick={(n) => routeTo("/saveTraveller")}>
          <img id="img" src="../Profile/traveller.png" />
          Save Travellers
        </button>
      </div>
      <div id="ptab">
        <button onClick={logout}>
          <img id="img" src="../Profile/logout.png" />
          Logout
        </button>
      </div>
    </div>
  );
}

export default AccountDetails;
