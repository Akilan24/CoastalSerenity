import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./BusBookingDetails.css";

function BusBookingDetails() {
  const { value } = useParams();
  const [busBookingDetails, setBusBookingDetails] = useState({});
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  useEffect(() => {
    async function fetchBusBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Bus/getbusbookingdetailsbyid/${value}`,
          config
        );
        setBusBookingDetails(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response);
      }
    }
    fetchBusBookingDetails();
  }, [value]);
  const getDuration = (duration) => {
    if (!duration) {
      return "N/A";
    }
    const parts = duration.split(":");
    if (parts.length !== 2) {
      return "N/A";
    }
    const [hours, minutes] = parts;
    return `${hours}h ${minutes}m`;
  };

  return (
    <div className="busbookingdetails">
      <img id="logo" src="../cslogo.png" alt="Logo" />
      <h2>Bus Booking Details</h2>
      <div className="details-container">
        <div className="detail-row">
          <span className="detail-label">Bus Company:</span>
          <span className="detail-value">{busBookingDetails.busCompany}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Bus Model:</span>
          <span className="detail-value">{busBookingDetails.busModel}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Origin:</span>
          <span className="detail-value">{busBookingDetails.origin}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Destination:</span>
          <span className="detail-value">{busBookingDetails.destination}</span>
        </div>

        <div className="detail-row">
          <span className="detail-label">Passenger Names:</span>
          <span className="detail-value">
            {busBookingDetails.busPassenger &&
              busBookingDetails.busPassenger.map((passenger, index) => (
                <span key={index}>
                  {passenger.name}
                  {index < busBookingDetails.busPassenger.length - 1 && ", "}
                </span>
              ))}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Seat No(s):</span>
          <span className="detail-value">
            {busBookingDetails.busPassenger &&
              busBookingDetails.busPassenger.map((passenger, index) => (
                <span key={index}>
                  {passenger.seatNo}
                  {index < busBookingDetails.busPassenger.length - 1 && ", "}
                </span>
              ))}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Route Via:</span>
          <span className="detail-value">{busBookingDetails.route}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Departure Time:</span>
          <span className="detail-value">
            {busBookingDetails.departureTime}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Arrival Time:</span>
          <span className="detail-value">{busBookingDetails.arrivalTime}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Duration:</span>
          <span className="detail-value">
            {getDuration(busBookingDetails.duration)}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Booked Date:</span>
          <span className="detail-value">
            {getDuration(busBookingDetails.bookedDate)}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Payment Status:</span>
          <span className="detail-value">
            {getDuration(busBookingDetails.paymentStatus)}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Amount:</span>
          <span className="detail-value">
            &#8377;{busBookingDetails.totalPrice}
          </span>
        </div>
      </div>
      <button onClick={() => navigate(`/payment/bus-${value}`)}>
        Continue
      </button>
    </div>
  );
}

export default BusBookingDetails;
