import React, { useState, useEffect } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./FlightBookingDetails.css";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";

function FlightBookingDetails() {
  const { value } = useParams();
  const [bookingId, setBookingId] = useState("");
  const [flightBookingDetails, setFlightBookingDetails] = useState({});
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  useEffect(() => {
    if (value.includes("-")) {
      setBookingId(value.split("-")[1]);
    } else {
      setBookingId(value);
    }
  }, [value]);

  useEffect(() => {
    async function fetchFlightBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Flight/getflightbookingdetailsbyid/${bookingId}`,
          config
        );
        setFlightBookingDetails(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response);
      }
    }
    if (bookingId) {
      fetchFlightBookingDetails();
    }
  }, [bookingId]);

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

  const getDate = (time) => {
    if (!time) return "N/A";
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM yyyy");
  };

  const downloadPDF = () => {
    const capture = document.querySelector(".flightContainer");
    html2canvas(capture).then((canvas) => {
      const imgData = canvas.toDataURL("image/png");
      const pdf = new jsPDF("p", "mm", "a4");
      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
      pdf.save("FlightBookingDetails.pdf");
    });
  };

  return (
    <div className="flightbookingdetails">
      <div className="flightContainer">
        <img id="logo" src="../cslogo.png" alt="Logo" />
        <h2>Flight Booking Details</h2>
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
            <span className="detail-value">
              {flightBookingDetails.stopOver}
            </span>
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
            <span className="detail-label">Booked Date:</span>
            <span className="detail-value">
              {getDate(flightBookingDetails.bookedDate)}
            </span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Payment Status:</span>
            <span className="detail-value">
              {flightBookingDetails.paymentStatus}
            </span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Amount:</span>
            <span className="detail-value">
              &#8377;{flightBookingDetails.totalPrice}
            </span>
          </div>
        </div>
      </div>
      {value.includes("-") ? (
        <div className="detail-button">
          <button id="back" onClick={() => navigate(-1)}>
            Back
          </button>
          <button id="print" onClick={downloadPDF}>
            Print
          </button>
        </div>
      ) : (
        <button onClick={() => navigate(`/payment/flight-${bookingId}`)}>
          Continue
        </button>
      )}
    </div>
  );
}

export default FlightBookingDetails;
