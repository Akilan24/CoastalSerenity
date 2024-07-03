import React, { useState, useEffect } from "react";
import "./SaveTraveller.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function SaveTraveller() {
  const [traveller, setTraveller] = useState({
    name: "",
    age: "",
    gender: "",
    mobile: "",
    address: "",
  });
  const [travellers, setTravellers] = useState([]);
  const [update, setUpdate] = useState(0);
  const navigate = useNavigate();

  function handleTravellerChange(e) {
    const { name, value } = e.target;
    setTraveller({ ...traveller, [name]: value });
  }

  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  useEffect(() => {
    fetchTravellers();
  }, []);

  async function fetchTravellers() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/getalltravellers/${localStorage.getItem(
          "username"
        )}`,
        config
      );
      setTravellers(response.data);
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/User/addtraveller/${localStorage.getItem(
          "username"
        )}`,
        traveller,
        config
      );
      setTraveller({
        name: "",
        age: "",
        gender: "",
        mobile: "",
        address: "",
      });
      setTravellers([...travellers, response.data]);
      console.log(response.data);
      toggleTravellerForm();
      fetchTravellers();
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function getTraveller(e) {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/User/gettraveller/${localStorage.getItem(
          "username"
        )}/${e.target.value}`,
        config
      );
      setTraveller(response.data);
      setUpdate(1);
      toggleTravellerForm();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function updateTraveller(e) {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/CS/User/updatetraveller/${localStorage.getItem(
          "username"
        )}`,
        traveller,
        config
      );
      fetchTravellers();
      setTraveller({
        name: "",
        age: "",
        gender: "",
        mobile: "",
        address: "",
      });
      setUpdate(0);
      toggleTravellerForm();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function deleteTraveller(e) {
    try {
      const response = await axios.delete(
        `http://localhost:8080/CS/User/deletetraveller/${localStorage.getItem(
          "username"
        )}/${e.target.value}`,
        config
      );
      fetchTravellers();
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  function toggleTravellerForm() {
    const element = document.getElementById("tc");
    if (element.style.display === "none") {
      element.style.display = "flex";
    } else {
      element.style.display = "none";
    }
  }

  return (
    <div className="stc">
      <img id="logo" src="./cslogo.png" alt="Logo" />
      <div className="savedtravellerclass">
        <div id="stdiv">
          <h2>Saved Traveller(s)</h2>
          {travellers.map((item, index) => (
            <div id="st" key={index} className="traveller-item">
              <div className="input">
                <p>
                  <strong>Name:</strong> {item.name}
                </p>
                <p>
                  <strong>Age:</strong> {item.age}
                </p>
                <p>
                  <strong>Gender:</strong> {item.gender}
                </p>
              </div>
              <div className="button">
                <button
                  id="s"
                  value={item.name}
                  onClick={(e) => getTraveller(e)}
                >
                  Edit
                </button>
                <button
                  id="c"
                  value={item.name}
                  onClick={(e) => deleteTraveller(e)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
          <div id="submit">
            <button id="c" onClick={() => navigate(-1)}>
              Cancel
            </button>
            <button id="s" onClick={toggleTravellerForm}>
              Add
            </button>
          </div>
        </div>
      </div>
      <form
        id="tc"
        className="travellerclass"
        onSubmit={update > 0 ? updateTraveller : handleSubmit}
        style={{ display: "none" }}
      >
        <div>
          <h2>{update > 0 ? "Update" : "Add"} Traveller</h2>
        </div>
        <div>
          <label htmlFor="name">Name</label>
          <input
            type="text"
            name="name"
            id="name"
            value={traveller.name}
            placeholder="Name"
            onChange={handleTravellerChange}
            required
          />
        </div>
        <div>
          <label htmlFor="age">Age</label>
          <input
            type="number"
            name="age"
            id="age"
            value={traveller.age}
            placeholder="Age"
            onChange={handleTravellerChange}
            required
          />
        </div>
        <div>
          <label htmlFor="mobile">Mobile</label>
          <input
            type="text"
            name="mobile"
            id="mobile"
            value={traveller.mobile}
            placeholder="Mobile number"
            onChange={handleTravellerChange}
            required
          />
        </div>
        <div>
          <label htmlFor="address">Address</label>
          <input
            type="text"
            name="address"
            id="address"
            value={traveller.address}
            placeholder="Address"
            onChange={handleTravellerChange}
            required
          />
        </div>
        <div>
          <label htmlFor="gender">Gender</label>
          <select
            className="gender"
            id="gender"
            name="gender"
            value={traveller.gender}
            onChange={handleTravellerChange}
          >
            <option value="">Select Your Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Others">Others</option>
          </select>
        </div>
        <div id="submit">
          <button id="c" type="button" onClick={toggleTravellerForm}>
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

export default SaveTraveller;
