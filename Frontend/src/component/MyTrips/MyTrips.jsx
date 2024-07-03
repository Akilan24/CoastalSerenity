import React, { useEffect, useState } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./MyTrips.css";

function MyTrips() {
  const navigate = useNavigate();
  const [tripType, setTripType] = useState("flight");
  const [hotelBookingDetails, setHotelBookingDetails] = useState([]);
  const [flightBookingDetails, setFlightBookingDetails] = useState([]);
  const [busBookingDetails, setBusBookingDetails] = useState([]);
  const [trainBookingDetails, setTrainBookingDetails] = useState([]);
  const [cabBookingDetails, setCabBookingDetails] = useState([]);
  const [rentalCabBookingDetails, setRentalCabBookingDetails] = useState([]);
  const username = localStorage.getItem("username");
  const getDate = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM yyyy");
  };
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  useEffect(() => {
    fetchFlightBookingDetails();
  }, []);

  async function fetchHotelBookingDetails() {
    try {
      const response = await axios.get(
        `http://localhost:8080/CS/Hotel/HotelBookingDetails/getbyusername/${username}`,
        config
      );
      setHotelBookingDetails(response.data);
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
      setFlightBookingDetails(response.data);
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
      setBusBookingDetails(response.data);
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
      setTrainBookingDetails(response.data);
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
      setCabBookingDetails(response.data);
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
      setRentalCabBookingDetails(response.data);
      console.log(response.data);
    } catch (error) {
      console.log(error.response);
    }
  }

  function handleSelection(tripType) {
    setTripType(tripType);
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
              defaultChecked
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
            (hotelBookingDetails.length > 0 ? (
              hotelBookingDetails.map((bookingDetail, index) => (
                <div id="containerClass" key={index}>
                  <div className="detail-row">
                    <span className="detail-label">Booking Id:</span>
                    <span className="detail-value">
                      {bookingDetail.hotelBookingId}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Hotel Name:</span>
                    <span className="detail-value">
                      {bookingDetail.hotename}
                    </span>
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
              ))
            ) : (
              <p id="notFound">Hotel booking details are not found.</p>
            ))}
          {tripType === "flight" &&
            (flightBookingDetails.length > 0 ? (
              flightBookingDetails.map((bookingDetail, index) => (
                <div id="containerClass" key={index}>
                  <div className="detail-row">
                    <span className="detail-label">Booking Id:</span>
                    <span className="detail-value">
                      {bookingDetail.flightBookingId}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Airline:</span>
                    <span className="detail-value">
                      {bookingDetail.airline}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Flight Model:</span>
                    <span className="detail-value">
                      {bookingDetail.flightModel}
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
                        `/flightBookingDetails/flight-${bookingDetail.bookingId}`
                      )
                    }
                  >
                    View
                  </button>
                </div>
              ))
            ) : (
              <p id="notFound">Flight booking details are not found.</p>
            ))}
          {tripType === "train" &&
            (trainBookingDetails.length > 0 ? (
              trainBookingDetails.map((bookingDetail, index) => (
                <div id="containerClass" key={index}>
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
                    <span className="detail-label">Train No:</span>
                    <span className="detail-value">
                      {bookingDetail.trainNo}
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
                    <span className="detail-value">{bookingDetail.amount}</span>
                  </div>
                  <button
                    id="view"
                    onClick={(e) =>
                      navigate(
                        `/trainBookingDetails/train-${bookingDetail.bookingId}`
                      )
                    }
                  >
                    View
                  </button>
                </div>
              ))
            ) : (
              <p id="notFound">Train booking details are not found.</p>
            ))}
          {tripType === "bus" &&
            (busBookingDetails.length > 0 ? (
              busBookingDetails.map((bookingDetail, index) => (
                <div id="containerClass" key={index}>
                  <div className="detail-row">
                    <span className="detail-label">Booking Id:</span>
                    <span className="detail-value">
                      {bookingDetail.busBookingId}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Bus Name:</span>
                    <span className="detail-value">
                      {bookingDetail.busName}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Bus No:</span>
                    <span className="detail-value">{bookingDetail.busNo}</span>
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
                        `/busBookingDetails/bus-${bookingDetail.bookingId}`
                      )
                    }
                  >
                    View
                  </button>
                </div>
              ))
            ) : (
              <p id="notFound">Bus booking details are not found.</p>
            ))}
          {tripType === "cab" &&
            (cabBookingDetails.length > 0 ? (
              cabBookingDetails.map((bookingDetail, index) => (
                <div id="containerClass" key={index}>
                  <div className="detail-row">
                    <span className="detail-label">Booking Id:</span>
                    <span className="detail-value">
                      {bookingDetail.cabBookingId}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Cab Name:</span>
                    <span className="detail-value">
                      {bookingDetail.cabName}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Cab No:</span>
                    <span className="detail-value">{bookingDetail.cabNo}</span>
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
                        `/cabBookingDetails/cab-${bookingDetail.bookingId}`
                      )
                    }
                  >
                    View
                  </button>
                </div>
              ))
            ) : (
              <p id="notFound">Cab booking details are not found.</p>
            ))}
          {tripType === "rentalCab" &&
            (rentalCabBookingDetails.length > 0 ? (
              rentalCabBookingDetails.map((bookingDetail, index) => (
                <div id="containerClass" key={index}>
                  <div className="detail-row">
                    <span className="detail-label">Booking Id:</span>
                    <span className="detail-value">
                      {bookingDetail.rentalCabBookingId}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Rental Cab Name:</span>
                    <span className="detail-value">
                      {bookingDetail.rentalCabName}
                    </span>
                  </div>
                  <div className="detail-row">
                    <span className="detail-label">Rental Cab No:</span>
                    <span className="detail-value">
                      {bookingDetail.rentalCabNo}
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
                    <span className="detail-value">{bookingDetail.amount}</span>
                  </div>
                  <button
                    id="view"
                    onClick={(e) =>
                      navigate(
                        `/rentalCabBookingDetails/rentalCab-${bookingDetail.bookingId}`
                      )
                    }
                  >
                    View
                  </button>
                </div>
              ))
            ) : (
              <p id="notFound">Rental Cab booking details are not found.</p>
            ))}
        </div>
      </div>
    </div>
  );
}

export default MyTrips;
