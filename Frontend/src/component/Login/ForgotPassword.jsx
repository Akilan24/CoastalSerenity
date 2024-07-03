import React, { useState } from "react";
import "./ForgotPassword.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function ForgotPassword() {
  const [formData, setFormData] = useState("");
  const navigate = useNavigate();
  function onchangeinput(e) {
    setFormData(e.target.value);
  }

  async function onsubmit(e) {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/CS/Auth/forgotpassword/" + formData
      );
      console.log("Email send");
      window.alert("Email send");
      setTimeout(() => {
        navigate("/");
      }, 1000);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  return (
    <div className="forgotclass">
      <div id="tab">
        <img id="logo" src="./cslogo.png" />
        <div>
          <button id="login" onClick={(e) => navigate("/login")}>
            Log in
          </button>
          <button id="join" onClick={(e) => navigate("/register")}>
            Register
          </button>
        </div>
      </div>

      <div className="forgot">
        <img id="l" src="./a.jpg" />
        <form className="formclass" onSubmit={onsubmit}>
          <h2>Forgot Password</h2>
          <div>
            <label htmlFor="email">Enter your mail id: </label>
            <input
              type="text"
              name="email"
              value={formData}
              id="email"
              placeholder="Email"
              onChange={onchangeinput}
            />
            <p>
              We’ll send a verification code to this email if it matches an
              existing Coastal Serenity account.
            </p>
            <button type="submit">Send</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ForgotPassword;
