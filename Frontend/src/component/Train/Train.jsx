import React, { useEffect, useState } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate } from "react-router-dom";
import "./Train.css";
import Tabs from "../Tabs/Tabs.jsx";
import CustomerService from "../Profile/CustomerService/CustomerService.jsx";
import AccountDetails from "../AccountDetails/AccountDetails.jsx";
import Password from "../Profile/Password/Password.jsx";
function Train() {
  const [origins, setOrigins] = useState([]);
  const [destinations, setDestinations] = useState([]);
  const [formData, setFormData] = useState({
    from: "",
    to: "",
    departure: getDefaultDate(),
  });
  const [trains, setTrains] = useState([]);
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
          "http://localhost:8080/CS/Train/getallcitynames",
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

  const getTime = (datetime) => {
    const date = new Date(datetime);
    return date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
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
        `http://localhost:8080/CS/Train/getallavailableTrains/${formData.from}/${formData.to}/${formData.departure}`,
        config
      );
      setTrains(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  };

  return (
    <div id="tjs">
      <div id="trainClass">
        <Tabs />
        <div id="tc">
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
              <button id="trainSearch" type="submit">
                Search
              </button>
            </div>
          </form>
        </div>
      </div>
      <div className="trainDetails">
        {trains.length > 0 ? (
          trains.map((train, index) => (
            <div key={index} className="trainSeats">
              <div key={index} className="train">
                <div className="td">
                  <div className="trainname">
                    <p id="company">{train.trainName}</p>
                    <p id="model">
                      #{train.pnr}&nbsp;&nbsp;|&nbsp;&nbsp;Depart on:&nbsp;
                      <span>{train.departOn}</span>
                    </p>
                  </div>
                </div>
                <div className="journeyDetails">
                  <div>
                    <div className="days">
                      <p id="time">{getTime(train.departureTime)}</p>
                      <p id="date">{getDate(train.departureTime)}</p>
                    </div>
                    <p>{train.origin}</p>
                  </div>
                  <div>
                    <p id="duration">{getDuration(train.duration)}</p>
                    <hr />
                    <p id="stopOver">{train.stopOver}</p>
                  </div>
                  <div>
                    <div className="days">
                      <div>
                        <p id="time">{getTime(train.arrivalTime)}</p>
                      </div>
                      <p id="date">{getDate(train.arrivalTime)}</p>
                    </div>
                    <p>{train.destination}</p>
                  </div>
                </div>
              </div>
              <div className="seats">
                <button
                  id="type"
                  onClick={(e) =>
                    navigate(`/trainBookingDetails/SL-${train.trainId}`)
                  }
                >
                  <div>
                    <p>SL</p>
                    <p>&#8377; {train.seatPrice["SL"]}</p>
                  </div>
                  {train.trainBookingStatus["SL"] > 0 ? (
                    <p id="avail">AVAILABLE {train.trainBookingStatus["SL"]}</p>
                  ) : (
                    <p>no</p>
                  )}
                </button>
                <button
                  id="type"
                  onClick={(e) =>
                    navigate(`/trainBookingDetails/1A-${train.trainId}`)
                  }
                >
                  <div>
                    <p>1A</p>
                    <p>&#8377; {train.seatPrice["1A"]}</p>
                  </div>
                  {train.trainBookingStatus["1A"] > 0 ? (
                    <p id="avail">AVAILABLE {train.trainBookingStatus["1A"]}</p>
                  ) : (
                    <p>no</p>
                  )}
                </button>
                <button
                  id="type"
                  onClick={(e) =>
                    navigate(`/trainBookingDetails/2A-${train.trainId}`)
                  }
                >
                  <div>
                    <p>2A</p>
                    <p>&#8377; {train.seatPrice["2A"]}</p>
                  </div>
                  {train.trainBookingStatus["2A"] > 0 ? (
                    <p id="avail">AVAILABLE {train.trainBookingStatus["2A"]}</p>
                  ) : (
                    <p>no</p>
                  )}
                </button>
                <button
                  id="type"
                  onClick={(e) =>
                    navigate(`/trainBookingDetails/3A-${train.trainId}`)
                  }
                >
                  <div>
                    <p>3A</p>
                    <p>&#8377; {train.seatPrice["3A"]}</p>
                  </div>
                  {train.trainBookingStatus["3A"] > 0 ? (
                    <p id="avail">AVAILABLE {train.trainBookingStatus["3A"]}</p>
                  ) : (
                    <p>no</p>
                  )}
                </button>
                <button
                  id="type"
                  onClick={(e) =>
                    navigate(`/trainBookingDetails/CC-${train.trainId}`)
                  }
                >
                  <div>
                    <p>CC</p>
                    <p>&#8377; {train.seatPrice["CC"]}</p>
                  </div>
                  {train.trainBookingStatus["CC"] > 0 ? (
                    <p id="avail">AVAILABLE {train.trainBookingStatus["CC"]}</p>
                  ) : (
                    <p>no</p>
                  )}
                </button>
              </div>
            </div>
          ))
        ) : (
          <p>No Trains available for the selected cities.</p>
        )}
      </div>
      <AccountDetails />
      <Password />
      <CustomerService />
    </div>
  );
}

export default Train;
