import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./MyTrips.css";
import Tabs from "../Tabs/Tabs.jsx";

function MyTrips() {
  const navigate = useNavigate();
  const [bookingDetails, setBookingDetails] = useState("");
  const username = localStorage.getItem("username");
  async function fetchHotelBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Hotel/HotelBookingDetails/getbyusername/${username}`,
        config
      );
      setBookingDetails("");
      setBookingDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  async function fetchFlightBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Flight/getflightbookingdetailsbyusername/${username}`,
        config
      );
      setBookingDetails("");
      setBookingDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  }
  async function fetchBusBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Bus/getBusbookingdetailsbyusername/${username}`,
        config
      );
      setBookingDetails("");
      setBookingDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  }
  function handleSelection(tripType) {
    if (tripType === "hotel") fetchHotelBookingDetails();
    else if (tripType === "flight") fetchFlightBookingDetails();
    else if (tripType === "bus") fetchBusBookingDetails();
    else if (tripType === "train") fetchTrainBookingDetails();
    else if (tripType === "cab") fetchCabBookingDetails();
  }

  return (
    <>
      <Tabs />
      <div id="myTrips">
        <div className="tripTabs">
          <div id="ttab">
            <input
              type="radio"
              name="tripType"
              id="hotel"
              onChange={() => handleSelection("hotel")}
            />
            <label htmlFor="hotel">Hotel</label>
          </div>
          <div id="ttab">
            <input
              type="radio"
              name="tripType"
              id="flight"
              onChange={() => handleSelection("flight")}
            />
            <label htmlFor="flight">Flight</label>
          </div>
          <div id="ttab">
            <input
              type="radio"
              name="tripType"
              onChange={() => handleSelection("train")}
            />
            <label htmlFor="train">Train</label>
          </div>
          <div id="ttab">
            <input
              type="radio"
              name="tripType"
              onChange={() => handleSelection("bus")}
            />
            <label htmlFor="bus">Bus</label>
          </div>
          <div id="ttab">
            <input
              type="radio"
              name="tripType"
              onChange={() => handleSelection("cab")}
            />
            <label htmlFor="cab">Cab</label>
          </div>
        </div>
        <div></div>
      </div>
    </>
  );
}

export default MyTrips;
