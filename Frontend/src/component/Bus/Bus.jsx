import React, { useEffect, useState } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate } from "react-router-dom";
import "./Bus.css";
import Tabs from "../Tabs/Tabs.jsx";
import CustomerService from "../Profile/CustomerService/CustomerService.jsx";
import AccountDetails from "../AccountDetails/AccountDetails.jsx";
import Password from "../Profile/Password/Password.jsx";
function Bus() {
  const [origins, setOrigins] = useState([]);
  const [destinations, setDestinations] = useState([]);
  const [formData, setFormData] = useState({
    from: "",
    to: "",
    departure: getDefaultDate(),
  });
  const [buses, setBuses] = useState([]);
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  function getDefaultDate() {
    const today = new Date();
    const day = String(today.getDate()).padStart(2, "0");
    const month = String(today.getMonth() + 1).padStart(2, "0");
    const year = today.getFullYear();
    return `${year}-${month}-${day}`;
  }

  useEffect(() => {
    async function fetchCityNames() {
      try {
        const response = await axios.get(
          "http://localhost:8080/CS/Bus/getallcitynames",
          config
        );
        setOrigins(response.data[0]);
        setDestinations(response.data[1]);
        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchCityNames();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const getTime = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "HH:mm");
  };
  const getDate = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM");
  };

  const getDuration = (duration) => {
    const [hours, minutes] = duration.split(":");
    return `${hours}h ${minutes}m`;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log(formData.departure);
      const response = await axios.get(
        `http://localhost:8080/CS/Bus/getallavailablebuses/${formData.from}/${formData.to}/${formData.departure}`,
        config
      );
      setBuses(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  };

  return (
    <>
      <div id="busClass">
        <Tabs />
        <div>
          <form className="formclass" onSubmit={handleSubmit}>
            <div className="divclass">
              <label htmlFor="from">From:</label>
              <select
                className="from"
                id="from"
                name="from"
                value={formData.from}
                onChange={handleChange}
              >
                <option value="">Select Origin</option>
                {origins.map((origin, index) => (
                  <option key={index} value={origin}>
                    {origin}
                  </option>
                ))}
              </select>
            </div>
            <div className="divclass">
              <label htmlFor="to">To:</label>
              <select
                className="to"
                id="to"
                name="to"
                value={formData.to}
                onChange={handleChange}
              >
                <option value="">Select Destination</option>
                {destinations.map((destination, index) => (
                  <option key={index} value={destination}>
                    {destination}
                  </option>
                ))}
              </select>
            </div>
            <div className="divclass">
              <label htmlFor="departure">Departure:</label>
              <input
                type="date"
                name="departure"
                id="departure"
                value={formData.departure}
                onChange={handleChange}
              />
            </div>
            <div>
              <button id="busSearch" type="submit">
                Search
              </button>
            </div>
          </form>
        </div>
      </div>
      <div className="busDetails">
        {buses.length > 0 ? (
          buses.map((bus, index) => (
            <div key={index} className="bus">
              <div className="busDetails">
                <div className="busname">
                  <p id="company">{bus.busCompany}</p>
                  <p id="model" style={{ color: "grey", fontSize: "18px" }}>
                    {bus.busModel}
                  </p>
                </div>
              </div>
              <div className="journeyDetails">
                <div>
                  <div className="days">
                    <p id="time">{getTime(bus.departureTime)}</p>
                    <p id="date">{getDate(bus.departureTime)}</p>
                  </div>
                  <p>{bus.origin}</p>
                </div>
                <div>
                  <p id="duration">{getDuration(bus.duration)}</p>
                  <hr />
                  <p id="stopOver">{bus.stopOver}</p>
                </div>
                <div>
                  <div className="days">
                    <div>
                      <p id="time">{getTime(bus.arrivalTime)}</p>
                    </div>
                    <p id="date">{getDate(bus.arrivalTime)}</p>
                  </div>
                  <p>{bus.destination}</p>
                </div>
                <div className="price">
                  <p id="p">&#8377;{bus.seatPrice["lowerSeat"]}</p>
                  <p id="count">
                    {bus.busBookingStatus["lowerSeat"] +
                      bus.busBookingStatus["lowerSleeper"] +
                      bus.busBookingStatus["upperSleeper"] +
                      bus.busBookingStatus["upperDoubleSleeper"]}
                    <span> Seats left</span>
                  </p>
                </div>
              </div>
              <div>
                <button id="but"
                  onClick={(e) => navigate(`/busSeatsSelection/${bus.busId}`)}
                >
                  BOOK NOW
                </button>
              </div>
            </div>
          ))
        ) : (
          <p>No Buses available for the selected cities.</p>
        )}
      </div>
      <AccountDetails />
      <Password />
      <CustomerService />
    </>
  );
}

export default Bus;
