import React, { useEffect, useState } from "react";
import axios from "axios";
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
  const [trip, setTrip] = useState(null);
  const [tripType, setTripType] = useState("Outstation");
  const [rentalCities, setRentalCities] = useState([]);
  const [rentalCabs, setRentalCabs] = useState([]);
  const [rentalPrice, setRentalPrice] = useState(new Map());
  const [when, setWhen] = useState("Now");
  const [formData, setFormData] = useState({
    from: "",
    to: "",
    journeyType: "One-Way",
    duration: "",
    rentalPackage: "",
    departDate: getDefaultDate(),
    departTime: getCurrentTime(),
    returnDate: getDefaultDate(),
    returnTime: getReturnTime(),
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

  function getReturnTime() {
    const now = new Date();
    now.setHours(now.getHours() + 6);
    now.setMinutes(now.getMinutes() + 6);
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
          setOrigins(response.data[0] || []);
          setDestinations(response.data[1] || []);
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
          setRentalCities(response.data || []);
          console.log(response.data);
        } catch (error) {
          console.log(error.response.data.message);
        }
      }
    }
    fetchCityNames();
  }, [tripType]);

  async function fetchCabDetailsAndTripDetails(e) {
    e.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Cab/getCabDetailsAndTripDetails`,
        {
          params: {
            from: formData.from,
            to: formData.to,
          },
          headers: config.headers,
        }
      );
      setCabs(response.data.cabs);
      setTrip(response.data.tripDetails);
      setFormData((prevData) => ({
        ...prevData,
        duration: response.data.tripDetails.duration,
      }));
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function fetchRentalCabAndRentalPackageDetails(e) {
    e.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Cab/getRentalCabAndRentalPackageDetails`,
        {
          params: {
            from: formData.from,
            packageName: formData.rentalPackage,
          },
          headers: config.headers,
        }
      );
      setRentalCabs(response.data.rentalCabs || []);
      const packagePrice = new Map([...Object.entries(response.data.cabPrice)]);
      setRentalPrice(packagePrice);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleCabBooking(id) {
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Cab/bookCab/${id}/${localStorage.getItem(
          "username"
        )}`,
        formData,
        config
      );
      navigate(`/cabBookingDetails/${response.data.cabBookingId}`);

      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleRentalCabBooking(id) {
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Cab/bookRentalCab/${id}/${localStorage.getItem(
          "username"
        )}`,
        formData,
        config
      );
      navigate(`/cabBookingDetails/${response.data.cabBookingId}`);

      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  return (
    <div id="cjs">
      <div id="cabClass">
        <Tabs />
        <div className="tripTypeSelection">
          <div>
            <input
              type="radio"
              name="tripType"
              value="Outstation"
              checked={tripType === "Outstation"}
              onChange={(e) => setTripType(e.target.value)}
            />
            <label htmlFor="Outstation">Outstation</label>
          </div>
          <div>
            <input
              type="radio"
              name="tripType"
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
                    <label htmlFor="returnDate">Return:</label>
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
                    <div id="detail">
                      <p id="model">{cab.cabModel}</p>
                      <div id="type">
                        <p>{cab.cabType}</p>
                        <p>
                          <li></li>
                        </p>
                        <p>{trip.distance} kms</p>
                        <p>
                          <li></li>
                        </p>
                        <p>{cab.totalSeat} seats</p>
                      </div>
                      <p>&#8377;{cab.cabPrice * trip.distance}</p>
                    </div>
                    <button
                      id="book"
                      onClick={() => handleCabBooking(cab.cabId)}
                    >
                      Book
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
                    <option value="">Select Package</option>
                    <option value="1_hrs_10_kms">1 hrs 10 kms</option>
                    <option value="1_hrs_15_kms">1 hrs 15 kms</option>
                    <option value="2_hrs_20_kms">2 hrs 20 kms</option>
                    <option value="2_hrs_25_kms">2 hrs 25 kms</option>
                    <option value="3_hrs_30_kms">3 hrs 30 kms</option>
                    <option value="4_hrs_40_kms">4 hrs 40 kms</option>
                    <option value="5_hrs_50_kms">5 hrs 50 kms</option>
                    <option value="6_hrs_60_kms">6 hrs 60 kms</option>
                    <option value="7_hrs_70_kms">7 hrs 70 kms</option>
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
              {rentalCabs.length > 0 ? (
                rentalCabs.map((cab, index) => (
                  <div key={index} className="cabDetail">
                    <img src={cab.cabImage} alt="Cab" />
                    <div id="detail">
                      <p id="model">{cab.cabModel}</p>
                      <div id="type">
                        <p>{cab.cabType}</p>
                        <p>
                          <li></li>
                        </p>
                        <p>{cab.totalSeat} seats</p>
                      </div>
                      <p>&#8377;{rentalPrice.get(cab.cabType)}</p>
                    </div>
                    <button
                      id="book"
                      onClick={() => handleRentalCabBooking(cab.rentalCabId)}
                    >
                      Book
                    </button>
                  </div>
                ))
              ) : (
                <p id="no">No available rental cabs found.</p>
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
