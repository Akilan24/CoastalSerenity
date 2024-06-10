import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./FlightBookingDetails.css";

function FlightBookingDetails() {
  const { value } = useParams();
  const [flightBookingDetails, setFlightBookingDetails] = useState({});
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  useEffect(() => {
    async function fetchFlightBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Flight/getflightbookingdetailsbyid/${value}`,
          config
        );
        setFlightBookingDetails(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response);
      }
    }
    fetchFlightBookingDetails();
  }, [value]);
  const getDuration = (duration) => {
    const [hours, minutes] = duration.split(":");
    return `${hours}h ${minutes}m`;
  };
  return (
    <div className="flightbookingdetails">
      <img id="logo1" src="../cslogo.png" alt="Logo" />
      <div className="details-container">
        <div className="detail-img">
          <img src={flightBookingDetails.airlineLogo} alt="Airline Logo" />
        </div>
        <div className="detail-row">
          <span className="detail-label">Airline:</span>
          <span className="detail-value">{flightBookingDetails.airline}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Flight Model:</span>
          <span className="detail-value">
            {flightBookingDetails.flightModel}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Origin:</span>
          <span className="detail-value">{flightBookingDetails.origin}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Destination:</span>
          <span className="detail-value">
            {flightBookingDetails.destination}
          </span>
        </div>

        <div className="detail-row">
          <span className="detail-label">Passenger Names:</span>
          <span className="detail-value">
            {flightBookingDetails.flightPassenger &&
              flightBookingDetails.flightPassenger.map((passenger, index) => (
                <span key={index}>
                  {passenger.name}
                  {index < flightBookingDetails.flightPassenger.length - 1 &&
                    ", "}
                </span>
              ))}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Seat No(s):</span>
          <span className="detail-value">
            {flightBookingDetails.flightPassenger &&
              flightBookingDetails.flightPassenger.map((passenger, index) => (
                <span key={index}>
                  {passenger.seatNo}
                  {index < flightBookingDetails.flightPassenger.length - 1 &&
                    ", "}
                </span>
              ))}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Stop Over:</span>
          <span className="detail-value">{flightBookingDetails.stopOver}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Departure Time:</span>
          <span className="detail-value">
            {flightBookingDetails.departureTime}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Arrival Time:</span>
          <span className="detail-value">
            {flightBookingDetails.arrivalTime}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Duration:</span>
          <span className="detail-value">
            {getDuration(flightBookingDetails.duration)}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Total Price:</span>
          <span className="detail-value">
            {flightBookingDetails.totalPrice}
          </span>
        </div>
      </div>
      <button onClick={() => navigate(`/payment/flight-${value}`)}>
        Continue
      </button>
    </div>
  );
}

export default FlightBookingDetails;
