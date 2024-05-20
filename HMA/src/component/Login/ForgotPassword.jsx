import React, { useState } from "react";
import "./Login.css";
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
        "http://localhost:8080/HMA/Auth/forgotpassword/" + formData
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
      <img id="logo" src="./cslogo.png" />
      <h2>Forgot Password</h2>
      <form className="formclass" onSubmit={onsubmit}>
        <div>
          <label htmlFor="email">Enter your mail id: </label>
          <input
            type="text"
            name="email"
            value={formData}
            id="email"
            onChange={onchangeinput}
          />
          <button type="submit">Send</button>
        </div>
      </form>
    </div>
  );
}

export default ForgotPassword;
