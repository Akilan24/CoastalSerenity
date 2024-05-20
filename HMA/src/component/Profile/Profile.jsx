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
          "http://localhost:8080/HMA/User/getuserbyname/" +
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
        "http://localhost:8080/HMA/User/updateuser/" +
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
    <div>
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
            onChange={handleprofileChange}
          />
        </div>
        <div>
          <label htmlFor="gender">Gender</label>
          <input
            type="text"
            name="gender"
            id="gender"
            value={profile.gender}
            onChange={handleprofileChange}
          />
        </div>
        <div>
          <label htmlFor="maritalStatus">Marital Status</label>
          <input
            type="text"
            name="maritalStatus"
            id="maritalStatus"
            value={profile.maritalStatus}
            onChange={handleprofileChange}
          />
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
