import React, { useState } from "react";
import "./ResetPassword.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function ResetPassword() {
  const [formData, setFormData] = useState({
    username: "",
    resetpassword: "",
    newpassword: "",
  });
  const navigate = useNavigate();
  function onchangeinput(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  async function onreset(e) {
    e.preventDefault();

    try {
      const login = {
        username: formData.username,
        password: formData.resetpassword,
      };
      const token = await axios.post(
        "http://localhost:8080/CS/Auth/login",
        login
      );
      localStorage.setItem(
        `${formData.username}-access`,
        token.data.accessToken
      );
      localStorage.setItem(
        `${formData.username}-refresh`,
        token.data.refreshToken
      );
      console.log(token.data.accessToken + ` ` + token.data.refreshToken);
      const config = {
        headers: {
          Authorization: `Bearer ${token.data.accessToken}`,
        },
      };
      const response = await axios.post(
        "http://localhost:8080/CS/User/updatepassword/" +
          formData.username +
          "/" +
          formData.newpassword,
        config
      );
      setTimeout(() => {
        navigate("/login");
      }, 1000);
    } catch (error) {
      console.log(error.response.data.message);
      setTimeout(() => {
        navigate("/resetpassword");
      }, 1000);
    }
  }

  return (
    <div className="resetclass">
      <div id="tab">
        <img id="logo" src="./cslogo.png" />
        <button id="login" onClick={(e) => navigate("/login")}>
          Log in
        </button>
      </div>
      <div className="reset">
        <img id="img" src="./reset.jpg" />
        <form className="formclass" onSubmit={onreset}>
          <h2>Reset Password</h2>
          <div>
            <label htmlFor="username">Username: </label>
            <input
              type="text"
              name="username"
              value={formData.username}
              id="username"
              placeholder="Username"
              onChange={onchangeinput}
            />
          </div>
          <div>
            <label htmlFor="resetpassword">Reset Password: </label>
            <input
              type="resetpassword"
              name="resetpassword"
              value={formData.resetpassword}
              id="resetpassword"
              placeholder="Reset Password"
              onChange={onchangeinput}
            />
          </div>
          <div>
            <label htmlFor="newpassword">New Password: </label>
            <input
              type="newpassword"
              name="newpassword"
              value={formData.newpassword}
              id="newpassword"
              placeholder="New Password"
              onChange={onchangeinput}
            />
          </div>
          <div>
            <button type="submit">Reset</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ResetPassword;
