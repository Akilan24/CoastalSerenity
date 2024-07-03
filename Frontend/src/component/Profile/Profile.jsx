import React, { useState, useEffect } from "react";
import "./Profile.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function Profile() {
  const [profile, setProfile] = useState({
    name: "",
    email: "",
    mobile: "",
    address: "",
    gender: "",
    maritalStatus: "",
  });
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  useEffect(() => {
    async function fetchUserDetails() {
      try {
        const response = await axios.get(
          "http://localhost:8080/CS/User/getuserbyname/" +
            localStorage.getItem(`username`),
          config
        );
        setProfile(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchUserDetails();
  }, []);
  function handleprofileChange(e) {
    const { name, value } = e.target;
    setProfile({ ...profile, [name]: value });
  }
  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await axios.put(
        "http://localhost:8080/CS/User/updateuser/" +
          localStorage.getItem(`username`),
        profile,
        config
      );
      setProfile(response.data);
      console.log(response.data);
      navigate("/hotel");
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  return (
    <div className="profile">
      <img id="logo" src="./cslogo.png" />
      <form className="profileclass" onSubmit={handleSubmit}>
        <div>
          <h2>Profile</h2>
        </div>
        <div>
          <label htmlFor="name">Name</label>
          <input
            type="text"
            name="name"
            id="name"
            value={profile.name}
            placeholder="Name"
            onChange={handleprofileChange}
          />
        </div>
        <div>
          <label htmlFor="email">Email</label>
          <input
            type="text"
            name="email"
            id="email"
            value={profile.email}
            placeholder="Email"
            onChange={handleprofileChange}
          />
        </div>
        <div>
          <label htmlFor="mobile">Mobile</label>
          <input
            type="text"
            name="mobile"
            id="mobile"
            value={profile.mobile}
            placeholder="Mobile number"
            onChange={handleprofileChange}
          />
        </div>
        <div>
          <label htmlFor="address">Address</label>
          <input
            type="text"
            name="address"
            id="address"
            value={profile.address}
            placeholder="Address"
            onChange={handleprofileChange}
          />
        </div>
        <div>
          <label htmlFor="gender">Gender</label>
          <select
            className="gender"
            id="gender"
            name="gender"
            value={profile.gender}
            onChange={handleprofileChange}
          >
            <option value="">Select Your Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Others">Others</option>
          </select>
        </div>
        <div>
          <label htmlFor="maritalStatus">Marital Status</label>
          <select
            className="maritalStatus"
            id="maritalStatus"
            name="maritalStatus"
            value={profile.maritalStatus}
            onChange={handleprofileChange}
          >
            <option value="">Select Marital Status</option>
            <option value="Single">Single</option>
            <option value="Married">Married</option>
            <option value="Widowed">Widowed</option>
          </select>
        </div>
        <div id="submit">
          <button id="c" type="button" onClick={(n) => navigate("/hotel")}>
            Cancel
          </button>
          <button id="s" type="submit">
            Save
          </button>
        </div>
      </form>
    </div>
  );
}

export default Profile;
