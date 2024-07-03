import React, { useState } from "react";
import "./Password.css";
import axios from "axios";
function Password() {
  const [password, setPassword] = useState("");
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  async function handlePasswordChange() {
    try {
      const response = await axios.put(
        `http://localhost:8080/CS/User/updatepassword/${localStorage.getItem(
          `username`
        )}/${password}`,
        config
      );
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
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
    <div id="passwordclass">
      <div className="ptab">
        <h3>Change Password</h3>
      </div>
      <div className="ptab">
        <label htmlFor="password">Password</label>
        <input
          type="password"
          name="password"
          id="password"
          value={password}
          placeholder="New Password"
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <div className="ptab" id="submit">
        <button id="c" onClick={changePassword}>
          Cancel
        </button>
        <button id="s" onClick={handlePasswordChange}>
          Save
        </button>
      </div>
    </div>
  );
}

export default Password;
