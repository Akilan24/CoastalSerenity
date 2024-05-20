import React, { useState, useEffect } from "react";
import "./Profile.css";
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
    async function fetchTravellers() {
      try {
        const response = await axios.get(
          `http://localhost:8080/HMA/User/getalltravellers/${localStorage.getItem(
            "username"
          )}`,
          config
        );
        setTravellers(response.data);
        console.log(response);
      } catch (error) {
        console.log(error);
      }
    }
    fetchTravellers();
  }, []);

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/HMA/User/addtraveller/${localStorage.getItem(
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
      addTraveller();
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  function addTraveller() {
    const element = document.getElementById("tc");
    if (element.style.display === "none") {
      element.style.display = "flex";
    } else {
      element.style.display = "none";
    }
  }

  return (
    <div>
      <img id="logo" src="./cslogo.png" alt="Logo" />
      <div className="savedtravellerclass">
        <div>
          <h2>Saved Traveller(s)</h2>
          {travellers.map((item, _) => (
            <div id="st" key={item.name} className="traveller-item">
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
                <button id="s" value={item.name}>
                  Edit
                </button>
                <button id="c" value={item.name}>
                  Delete
                </button>
              </div>
            </div>
          ))}
          <div>
            <button id="c" onClick={() => navigate("/hotel")}>
              Cancel
            </button>
            <button id="s" onClick={addTraveller}>
              Add
            </button>
          </div>
        </div>
      </div>
      <form
        id="tc"
        className="travellerclass"
        onSubmit={handleSubmit}
        style={{ display: "none" }}
      >
        <div>
          <h2>Add Traveller</h2>
        </div>
        <div>
          <label htmlFor="name">Name</label>
          <input
            type="text"
            name="name"
            id="name"
            value={traveller.name}
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
            onChange={handleTravellerChange}
            required
          />
        </div>
        <div>
          <label htmlFor="gender">Gender</label>
          <input
            type="text"
            name="gender"
            id="gender"
            value={traveller.gender}
            onChange={handleTravellerChange}
            required
          />
        </div>
        <div id="submit">
          <button id="c" type="button" onClick={addTraveller}>
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
