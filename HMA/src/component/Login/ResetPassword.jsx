import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function ResetPassword() {
  const [formData, setFormData] = useState({
    userid: "",
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
        userid: formData.userid,
        password: formData.resetpassword,
      };
      const token = await axios.post(
        "http://localhost:8080/HMA/Auth/login",
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
        "http://localhost:8080/HMA/User/updatepassword/" +
          formData.userid +
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
      <img id="logo" src="./cslogo.png" />
      <h2>Reset Password</h2>
      <form className="formclass" onSubmit={onreset}>
        <div>
          <label htmlFor="userid">Userid: </label>
          <input
            type="text"
            name="userid"
            value={formData.userid}
            id="userid"
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
            onChange={onchangeinput}
          />
        </div>
        <div>
          <button type="submit">Reset</button>
        </div>
      </form>
    </div>
  );
}

export default ResetPassword;
