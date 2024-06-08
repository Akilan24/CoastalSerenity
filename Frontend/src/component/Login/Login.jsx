import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
function Login() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const navigate = useNavigate();
  function onchangeinput(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  async function onlogin(e) {
    e.preventDefault();

    try {
      const login = {
        username: formData.username,
        password: formData.password,
      };
      const token = await axios.post(
        "http://localhost:8080/CS/Auth/login",
        login
      );
      localStorage.setItem(`accesstoken`, token.data.accessToken);
      localStorage.setItem(`refreshtoken`, token.data.refreshToken);
      localStorage.setItem(`username`, formData.username);
      console.log(token);
      console.log(token.data.accessToken + ` ` + token.data.refreshToken);
      if (token.status == 200) navigate("/hotel");
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  return (
    <div className="loginclass">
      <img id="logo" src="./cslogo.png" />
      <form className="formclass" onSubmit={onlogin}>
        <h2>Login</h2>
        <div>
          <label htmlFor="username">Username: </label>
          <input
            type="text"
            name="username"
            value={formData.username}
            id="username"
            onChange={onchangeinput}
          />
        </div>
        <div>
          <label htmlFor="password">Password: </label>
          <input
            type="password"
            name="password"
            value={formData.password}
            id="password"
            onChange={onchangeinput}
          />
        </div>
        <div>
          <button id="log" type="submit">
            Login
          </button>
        </div>
        <div>
          <Link id="a" to="/forgotpassword">
            Forgot Password?
          </Link>
          <Link id="a" to="/register">
            Register
          </Link>
        </div>
      </form>
    </div>
  );
}
export default Login;
