import React, { useEffect, useState } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate } from "react-router-dom";
import "./Cab.css";
import Tabs from "../Tabs/Tabs.jsx";
import CustomerService from "../Profile/CustomerService/CustomerService.jsx";
import AccountDetails from "../AccountDetails/AccountDetails.jsx";
import Password from "../Profile/Password/Password.jsx";

function Cab() {
  const [origins, setOrigins] = useState([]);
  const [destinations, setDestinations] = useState([]);
  const [cabs, setCabs] = useState([]);
  const [trip, setTrip] = useState();
  const [tripType, setTripType] = useState("Outstation");
  const [rentalCities, setRentalCities] = useState([]);
  const [rentalCabs, setRentalCabs] = useState([]);
  const [rentalPackage, setRentalPackage] = useState();
  const [when, setWhen] = useState();
  const [formData, setFormData] = useState({
    from: "",
    to: "",
    journeyType: "One-Way",
    duration: "",
    rentalPackage: "",
    departDate: getDefaultDate(),
    departTime: getCurrentTime(),
    returnDate: getDefaultDate(),
    returnTime: getCurrentTime(),
  });
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

  function getCurrentTime() {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, "0");
    const minutes = String(now.getMinutes()).padStart(2, "0");
    return `${hours}:${minutes}`;
  }

  useEffect(() => {
    async function fetchCityNames() {
      if (tripType === "Outstation") {
        try {
          const response = await axios.get(
            "http://localhost:8080/CS/Cab/getallcitynames",
            config
          );
          setOrigins(response.data[0]);
          setDestinations(response.data[1]);
          console.log(response.data);
        } catch (error) {
          console.log(error.response.data.message);
        }
      } else {
        try {
          const response = await axios.get(
            "http://localhost:8080/CS/Cab/getallRentalcitynames",
            config
          );
          setRentalCities(response.data);
          console.log(response.data);
        } catch (error) {
          console.log(error.response.data.message);
        }
      }
    }
    fetchCityNames();
  }, []);

  async function fetchCabDetailsAndTripDetails(e) {
    e.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Cab/getCabDetailsAndTripDetails/${formData.from}/${formData.to}`,
        config
      );
      setCabs(response.data[0]);
      setTrip(response.data[1]);
      setFormData({ ...formData, duration: response.data[1].duration });
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  async function fetchRentalCabAndRentalPackageDetails(e) {
    e.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Cab/getRentalCabAndRentalPackageDetails/${formData.from}`,
        config
      );
      setRentalCabs(response.data[0]);
      setRentalPackage(response.data[1]);
      setFormData({ ...formData, duration: response.data[1].duration });
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
  async function handleBooking(id) {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Cab/bookCab/${id}/${localStorage.getItem(
          "username"
        )}`,
        formData,
        config
      );
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }
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

  return (
    <div id="cjs">
      <div id="cabClass">
        <Tabs />
        <div className="tripTypeSelection">
          <div>
            <input
              type="radio"
              name="Outstation"
              value="Outstation"
              checked={tripType === "Outstation"}
              onChange={(e) => setTripType(e.target.value)}
            />
            <label htmlFor="Outstation">Outstation</label>
          </div>
          <div>
            <input
              type="radio"
              name="Rental"
              value="Rental"
              checked={tripType === "Rental"}
              onChange={(e) => setTripType(e.target.value)}
            />
            <label htmlFor="Rental">Rental</label>
          </div>
        </div>
        {tripType === "Outstation" && (
          <div className="Outstation">
            <div id="cc">
              <form
                className="formclass"
                onSubmit={fetchCabDetailsAndTripDetails}
              >
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
                  <label htmlFor="journeyType">Journey:</label>
                  <select
                    className="journeyType"
                    id="journeyType"
                    name="journeyType"
                    value={formData.journeyType}
                    onChange={handleChange}
                  >
                    <option value="One-Way">One Way</option>
                    <option value="Round-Trip">Round Trip</option>
                  </select>
                </div>
                <div className="divclass">
                  <label htmlFor="departDate">Departure:</label>
                  <div className="dateTime">
                    <input
                      type="date"
                      id="departDate"
                      name="departDate"
                      value={formData.departDate}
                      onChange={handleChange}
                    />
                    <input
                      type="time"
                      id="departTime"
                      name="departTime"
                      value={formData.departTime}
                      onChange={handleChange}
                    />
                  </div>
                </div>
                {formData.journeyType === "Round-Trip" && (
                  <div className="divclass">
                    <label htmlFor="returnDate">returnDate:</label>
                    <div className="dateTime">
                      <input
                        type="date"
                        id="returnDate"
                        name="returnDate"
                        value={formData.returnDate}
                        onChange={handleChange}
                      />
                      <input
                        type="time"
                        id="returnTime"
                        name="returnTime"
                        value={formData.returnTime}
                        onChange={handleChange}
                      />
                    </div>
                  </div>
                )}
                <div>
                  <button id="cabSearch" type="submit">
                    Search
                  </button>
                </div>
              </form>
            </div>
            <div className="cabDetails">
              {cabs.length > 0 ? (
                cabs.map((cab, index) => (
                  <div key={index} className="cabDetail">
                    <img src={cab.cabImage} alt="Cab" />
                    <p>{cab.cabModel}</p>
                    <p>{cab.cabType}</p>
                    <p>{cab.totalSeat}</p>
                    <p>{cab.cabPrice * trip.distance}</p>
                    <button onClick={(e) => handleBooking(cab.cabId)}>
                      SELECT
                    </button>
                  </div>
                ))
              ) : (
                <p id="no">No available cabs found.</p>
              )}
            </div>
          </div>
        )}
        {tripType === "Rental" && (
          <div className="Rental">
            <div id="cc">
              <form
                className="formclass"
                onSubmit={fetchRentalCabAndRentalPackageDetails}
              >
                <div className="divclass">
                  <label htmlFor="from">From:</label>
                  <select
                    className="from"
                    id="from"
                    name="from"
                    value={formData.from}
                    onChange={handleChange}
                  >
                    <option value="">Select City name</option>
                    {rentalCities.map((city, index) => (
                      <option key={index} value={city}>
                        {city}
                      </option>
                    ))}
                  </select>
                </div>
                <div className="divclass">
                  <label htmlFor="rentalPackage">Package:</label>
                  <select
                    className="rentalPackage"
                    id="rentalPackage"
                    name="rentalPackage"
                    value={formData.rentalPackage}
                    onChange={handleChange}
                  >
                    <option value="Now">Now</option>
                    <option value="Later">Scheduled for Later</option>
                  </select>
                </div>
                <div className="divclass">
                  <label htmlFor="when">When:</label>
                  <select
                    className="when"
                    id="when"
                    name="when"
                    value={when}
                    onChange={(e) => setWhen(e.target.value)}
                  >
                    <option value="Now">Now</option>
                    <option value="Later">Scheduled for Later</option>
                  </select>
                </div>
                {when === "Later" && (
                  <div className="divclass">
                    <label htmlFor="departDate">Departure:</label>
                    <div className="dateTime">
                      <input
                        type="date"
                        id="departDate"
                        name="departDate"
                        value={formData.departDate}
                        onChange={handleChange}
                      />
                      <input
                        type="time"
                        id="departTime"
                        name="departTime"
                        value={formData.departTime}
                        onChange={handleChange}
                      />
                    </div>
                  </div>
                )}
                <div>
                  <button id="cabSearch" type="submit">
                    Search
                  </button>
                </div>
              </form>
            </div>
            <div className="cabDetails">
              {cabs.length > 0 ? (
                cabs.map((cab, index) => (
                  <div key={index} className="cabDetail">
                    <img src={cab.cabImage} alt="Cab" />
                    <p>{cab.cabModel}</p>
                    <p>{cab.cabType}</p>
                    <p>{cab.totalSeat}</p>
                    <p>{cab.cabPrice * trip.distance}</p>
                    <button onClick={(e) => handleBooking(cab.cabId)}>
                      SELECT
                    </button>
                  </div>
                ))
              ) : (
                <p id="no">No available cabs found.</p>
              )}
            </div>
          </div>
        )}
      </div>
      <AccountDetails />
      <Password />
      <CustomerService />
    </div>
  );
}

export default Cab;
