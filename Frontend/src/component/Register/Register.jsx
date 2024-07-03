import React, { useState } from "react";
import axios from "axios";
import "./Register.css";
import { useNavigate } from "react-router-dom";
function Register() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: "",
    name: "",
    password: "",
    email: "",
    roles: "user",
    mobile: "",
    address: "",
    gender: "",
    maritalStatus: "",
  });

  function onchangeinput(e) {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/CS/Auth/register",
        formData
      );
      window.alert("User registered successfully");

      navigate("/login");
    } catch (error) {
      window.alert("Error registering user");
      console.error("Error registering user:", error);
      setTimeout(() => {
        navigate("/register");
      }, 1000);
    }
  }
  return (
    <div className="register">
      <div id="tab">
        <img id="logo" src="./cslogo.png" />
        <button id="login" onClick={(e) => navigate("/login")}>
          Log in
        </button>
      </div>

      <div id="main">
        <form className="registerclass" onSubmit={handleSubmit}>
          <div className="form-group">
            <p id="register">Create an account</p>
          </div>

          <div className="form-group">
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              name="username"
              placeholder="Username"
              value={formData.username}
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="name">Name:</label>
            <input
              type="text"
              id="name"
              name="name"
              value={formData.name}
              placeholder="Name"
              onChange={onchangeinput}
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Password"
              value={formData.password}
              onChange={onchangeinput}
            />
          </div>
          <div className="input">
            <div className="form-group">
              <label htmlFor="email">Email:</label>
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                placeholder="Email"
                onChange={onchangeinput}
              />
            </div>
            <div className="form-group">
              <label htmlFor="mobile">Mobile:</label>
              <input
                type="text"
                id="mobile"
                name="mobile"
                value={formData.mobile}
                placeholder="Mobile number"
                onChange={onchangeinput}
              />
            </div>
          </div>

          <div className="input">
            <div className="form-group">
              <label htmlFor="gender">Gender:</label>
              <select
                id="gender"
                name="gender"
                value={formData.gender}
                onChange={onchangeinput}
              >
                <option value="">Select your gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Others">Others</option>
              </select>
            </div>
            <div className="form-group">
              <label htmlFor="maritalStatus">Marital Status:</label>
              <select
                id="maritalStatus"
                name="maritalStatus"
                value={formData.maritalStatus}
                onChange={onchangeinput}
              >
                <option value="">Select Marital Status</option>
                <option value="Single">Single</option>
                <option value="Married">Married</option>
                <option value="Divorced">Divorced</option>
              </select>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="address">Address:</label>
            <input
              type="text"
              id="address"
              name="address"
              placeholder="Address"
              value={formData.address}
              onChange={onchangeinput}
            />
          </div>
          <div>
            <button type="submit">Register</button>
          </div>
        </form>

        <img id="l" src="./3.jpg" />
      </div>
    </div>
  );
}

export default Register;
