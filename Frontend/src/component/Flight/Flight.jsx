import React, { useEffect, useState } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate } from "react-router-dom";
import "./Flight.css";
import Tabs from "../Tabs/Tabs.jsx";
import CustomerService from "../Profile/CustomerService/CustomerService.jsx";
import AccountDetails from "../AccountDetails/AccountDetails.jsx";
import Password from "../Profile/Password/Password.jsx";
function Flight() {
  const [origins, setOrigins] = useState([]);
  const [destinations, setDestinations] = useState([]);
  const [formData, setFormData] = useState({
    from: "",
    to: "",
    departure: getDefaultDate(),
    travellerClass: "",
  });
  const [flights, setFlights] = useState([]);
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
          "http://localhost:8080/CS/Flight/getallcitynames",
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

  const getDuration = (duration) => {
    const [hours, minutes] = duration.split(":");
    return `${hours}h ${minutes}m`;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log(formData.departure);
      const response = await axios.get(
        `http://localhost:8080/CS/Flight/getallavailableflights/${formData.from}/${formData.to}/${formData.departure}/${formData.travellerClass}`,
        config
      );
      setFlights(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  };

  return (
    <>
      <div id="travellerClass">
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
                type="time"
                name="departure"
                id="departure"
                value={formData.departure}
                onChange={handleChange}
              />
            </div>
            <div className="divclass">
              <label htmlFor="to">Traveller Class:</label>
              <select
                className="travellerClass"
                id="travellerClass"
                name="travellerClass"
                value={formData.travellerClass}
                onChange={handleChange}
              >
                <option value="">Select Traveller Class</option>
                <option value="economy">Economy</option>
                <option value="business">Business</option>
                <option value="firstclass">First Class</option>
              </select>
            </div>
            <div>
              <button type="submit">Search</button>
            </div>
          </form>
        </div>
      </div>
      <div className="flightDetails">
        {flights.length > 0 ? (
          flights.map((flight, index) => (
            <div key={index} className="flight">
              <div className="airline">
                <div>
                  <img id="imgh" src={flight.airlineLogo} alt="Airline Logo" />
                </div>
                <div className="flightname">
                  <p style={{ color: "black" }}>{flight.airline}</p>
                  <p style={{ color: "grey", fontSize: "18px" }}>
                    {flight.flightModel}
                  </p>
                </div>
              </div>

              <div className="journeyDetails">
                <div>
                  <p style={{ color: "black" }}>
                    {getTime(flight.departureTime)}
                  </p>
                  <p>{flight.origin}</p>
                </div>
                <div>
                  <p style={{ color: "grey", fontSize: "18px" }}>
                    {getDuration(flight.duration)}
                  </p>
                  <hr />
                  <p style={{ color: "grey", fontSize: "18px" }}>
                    {flight.stopOver}
                  </p>
                </div>
                <div>
                  <div className="days">
                    <p style={{ color: "black" }}>
                      {getTime(flight.arrivalTime)}
                    </p>
                    {flight.nextDay && <p id="count">{flight.nextDay}</p>}
                  </div>
                  <p>{flight.destination}</p>
                </div>
                <div className="price">
                  <p>&#8377;{flight.seatPrice[formData.travellerClass]}</p>
                  <p>Per Adult</p>
                </div>
              </div>
              <div>
                <button
                  id="but"
                  onClick={(e) =>
                    navigate(`/flightSeatsSelection/${flight.flightId}`)
                  }
                >
                  BOOK NOW
                </button>
              </div>
            </div>
          ))
        ) : (
          <p>No Flights available for the selected cities.</p>
        )}
      </div>
      <AccountDetails />
      <Password />
      <CustomerService />
    </>
  );
}

export default Flight;
