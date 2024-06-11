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
          <img id="imgIcon" src="../src/assets/Profile/profile.png" />
          Profile
        </button>
      </div>
      <div id="ptab">
        <button onClick={(n) => routeTo("/myTrips")}>
          <img id="imgIcon" src="../src/assets/Profile/booking.png" />
          My Trips
        </button>
      </div>
      <div id="ptab">
        <button onClick={changePassword}>
          <img id="imgIcon" src="../src/assets/Profile/password.png" />
          Change Password
        </button>
      </div>
      <div id="ptab">
        <button onClick={(n) => routeTo("/saveTraveller")}>
          <img id="imgIcon" src="../src/assets/Profile/traveller.png" />
          Save Travellers
        </button>
      </div>
      <div id="ptab">
        <button onClick={logout}>
          <img id="imgIcon" src="../src/assets/Profile/logout.png" />
          Logout
        </button>
      </div>
    </div>
  );
}

export default AccountDetails;
