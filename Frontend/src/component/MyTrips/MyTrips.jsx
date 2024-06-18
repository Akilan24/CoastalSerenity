import React, { useState } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./MyTrips.css";

function MyTrips() {
  const navigate = useNavigate();
  const { value } = useParams();
  const [tripType, setTripType] = useState(value);
  const [bookingDetails, setBookingDetails] = useState("");
  const username = localStorage.getItem("username");
  const getDate = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm", new Date());
    return format(parsedDate, "dd MMM yyyy");
  };

  async function fetchHotelBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Hotel/HotelBookingDetails/getbyusername/${username}`,
        config
      );
      setTripType("hotel");
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
      setTripType("flight");
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
      setTripType("bus");
      setBookingDetails("");
      setBookingDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  }
  async function fetchTrainBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Bus/getTrainbookingdetailsbyusername/${username}`,
        config
      );
      setTripType("train");
      setBookingDetails("");
      setBookingDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  }
  async function fetchCabBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Bus/getCabbookingdetailsbyusername/${username}`,
        config
      );
      setTripType("cab");
      setBookingDetails("");
      setBookingDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  }
  async function fetchRentalCabBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Bus/getRentalCabbookingdetailsbyusername/${username}`,
        config
      );
      setTripType("rentalCab");
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
    else if (tripType === "rentalCab") fetchRentalCabBookingDetails();
  }

  return (
    <div id="tripsClass">
      <div className="selector">
        <div id="tab">
          <button onClick={(n) => navigate("/hotel")}>
            <img id="hotellogo" src="../src/assets/TabIcons/hotellogo.png" />
            <p>Hotels</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => navigate("/flight")}>
            <img id="planelogo" src="../src/assets/TabIcons/planelogo.png" />
            <p>Flights</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => navigate("/train")}>
            <img id="trainlogo" src="../src/assets/TabIcons/trainlogo.png" />
            <p>Trains</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => navigate("/bus")}>
            <img id="buslogo" src="../src/assets/TabIcons/buslogo.png" />
            <p>Buses</p>
          </button>
        </div>
        <div id="tab">
          <button onClick={(n) => navigate("/cab")}>
            <img id="carlogo" src="../src/assets/TabIcons/cablogo.png" />
            <p>Cabs</p>
          </button>
        </div>
      </div>
      <div id="myTrips">
        <img id="logo" src="./cslogo.png" />
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
          <div id="ttab">
            <input
              type="radio"
              name="tripType"
              onChange={() => handleSelection("rentalCab")}
            />
            <label htmlFor="rentalCab">Rental Cab</label>
          </div>
        </div>
        <div>
          {tripType === "hotel" &&
            bookingDetails.map((bookingDetail, index) => (
              <div id="cabClass" key={index}>
                <div className="detail-row">
                  <span className="detail-label">Booking Id:</span>
                  <span className="detail-value">
                    {bookingDetail.hotelBookingId}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Hotel Name:</span>
                  <span className="detail-value">{bookingDetail.hotename}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Room No:</span>
                  <span className="detail-value">{bookingDetail.roomno}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Booked Date:</span>
                  <span className="detail-value">
                    {getDate(bookingDetail.bookedDate)}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Amount:</span>
                  <span className="detail-value">{bookingDetail.amount}</span>
                </div>
                <button
                  id="view"
                  onClick={(e) =>
                    navigate(
                      `/hotelBookingDetails/hotel-${bookingDetail.bookingId}`
                    )
                  }
                >
                  View
                </button>
              </div>
            ))}
          {tripType === "flight" &&
            bookingDetails.map((bookingDetail, index) => (
              <div id="cabClass" key={index}>
                <div className="detail-row">
                  <span className="detail-label">Booking Id:</span>
                  <span className="detail-value">
                    {bookingDetail.flightBookingId}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Airline:</span>
                  <span className="detail-value">{bookingDetail.airline}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Origin:</span>
                  <span className="detail-value">{bookingDetail.origin}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Destination:</span>
                  <span className="detail-value">
                    {bookingDetail.destination}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Booked Date:</span>
                  <span className="detail-value">
                    {getDate(bookingDetail.bookedDate)}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Amount:</span>
                  <span className="detail-value">
                    {bookingDetail.totalPrice}
                  </span>
                </div>
                <button
                  id="view"
                  onClick={(e) =>
                    navigate(
                      `/flightBookingDetails/flight-${bookingDetail.flightBookingId}`
                    )
                  }
                >
                  View
                </button>
              </div>
            ))}
          {tripType === "bus" &&
            bookingDetails.map((bookingDetail, index) => (
              <div id="cabClass" key={index}>
                <div className="detail-row">
                  <span className="detail-label">Booking Id:</span>
                  <span className="detail-value">
                    {bookingDetail.busBookingId}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Bus Company:</span>
                  <span className="detail-value">
                    {bookingDetail.busCompany}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Origin:</span>
                  <span className="detail-value">{bookingDetail.origin}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Destination:</span>
                  <span className="detail-value">
                    {bookingDetail.destination}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Booked Date:</span>
                  <span className="detail-value">
                    {getDate(bookingDetail.bookedDate)}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Amount:</span>
                  <span className="detail-value">
                    {bookingDetail.totalPrice}
                  </span>
                </div>
                <button
                  id="view"
                  onClick={(e) =>
                    navigate(
                      `/busBookingDetails/bus-${bookingDetail.busBookingId}`
                    )
                  }
                >
                  View
                </button>
              </div>
            ))}
          {tripType === "train" &&
            bookingDetails.map((bookingDetail, index) => (
              <div id="cabClass" key={index}>
                <div className="detail-row">
                  <span className="detail-label">Booking Id:</span>
                  <span className="detail-value">
                    {bookingDetail.trainBookingId}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Train Name:</span>
                  <span className="detail-value">
                    {bookingDetail.trainName}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Origin:</span>
                  <span className="detail-value">{bookingDetail.origin}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Destination:</span>
                  <span className="detail-value">
                    {bookingDetail.destination}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Booked Date:</span>
                  <span className="detail-value">
                    {getDate(bookingDetail.bookedDate)}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Amount:</span>
                  <span className="detail-value">
                    {bookingDetail.totalPrice}
                  </span>
                </div>
                <button
                  id="view"
                  onClick={(e) =>
                    navigate(
                      `/trainBookingDetails/train-${bookingDetail.trainBookingId}`
                    )
                  }
                >
                  View
                </button>
              </div>
            ))}
          {tripType === "cab" &&
            bookingDetails.map((bookingDetail, index) => (
              <div id="cabClass" key={index}>
                <div className="detail-row">
                  <span className="detail-label">Booking Id:</span>
                  <span className="detail-value">
                    {bookingDetail.cabBookingId}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Origin:</span>
                  <span className="detail-value">{bookingDetail.origin}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Destination:</span>
                  <span className="detail-value">
                    {bookingDetail.destination}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Booked Date:</span>
                  <span className="detail-value">
                    {getDate(bookingDetail.bookedDate)}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Amount:</span>
                  <span className="detail-value">{bookingDetail.cabPrice}</span>
                </div>
                <button
                  id="view"
                  onClick={(e) =>
                    navigate(
                      `/cabBookingDetails/cab-${bookingDetail.cabBookingId}`
                    )
                  }
                >
                  View
                </button>
              </div>
            ))}
          {tripType === "rentalCab" &&
            bookingDetails.map((bookingDetail, index) => (
              <div id="cabClass" key={index}>
                <div className="detail-row">
                  <span className="detail-label">Booking Id:</span>
                  <span className="detail-value">
                    {bookingDetail.rentalCabBookingId}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Origin:</span>
                  <span className="detail-value">{bookingDetail.origin}</span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Package Type:</span>
                  <span className="detail-value">
                    {bookingDetail.packageType}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Booked Date:</span>
                  <span className="detail-value">
                    {getDate(bookingDetail.bookedDate)}
                  </span>
                </div>
                <div className="detail-row">
                  <span className="detail-label">Amount:</span>
                  <span className="detail-value">
                    {bookingDetail.rentalCabPrice}
                  </span>
                </div>
                <button
                  id="view"
                  onClick={(e) =>
                    navigate(
                      `/rentalCabBookingDetails/rentalCab-${bookingDetail.rentalCabBookingId}`
                    )
                  }
                >
                  View
                </button>
              </div>
            ))}
        </div>
      </div>
    </div>
  );
}

export default MyTrips;
