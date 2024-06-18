import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./BusBookingDetails.css";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
function BusBookingDetails() {
  const { value } = useParams();
  const [bookingId, setBookingId] = useState("");
  const [busBookingDetails, setBusBookingDetails] = useState({});
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
    async function fetchBusBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Bus/getbusbookingdetailsbyid/${bookingId}`,
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
  const getDate = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM yyyy");
  };
  const downloadPDF = () => {
    const capture = document.querySelector(".busContainer");
    html2canvas(capture).then((canvas) => {
      const imgData = canvas.toDataURL("image/png");
      const pdf = new jsPDF("p", "mm", "a4");
      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
      pdf.save("BusBookingDetails.pdf");
    });
  };
  return (
    <div className="busbookingdetails">
      <div className="busContainer">
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
            <span className="detail-value">
              {busBookingDetails.destination}
            </span>
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
            <span className="detail-value">
              {busBookingDetails.arrivalTime}
            </span>
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
              {getDate(busBookingDetails.bookedDate)}
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
        <button onClick={() => navigate(`/payment/bus-${bookingId}`)}>
          Continue
        </button>
      )}
    </div>
  );
}

export default BusBookingDetails;
