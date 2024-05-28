import React, { useState, useEffect } from "react";
import axios from "axios";
import "./BookingDetails.css";
import { useNavigate } from "react-router-dom";
function BookingDetails() {
  const [bookingDetailsList, setBookingDetailsList] = useState([]);
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const navigate = useNavigate();
  useEffect(() => {
    async function fetchBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/HMA/BookingDetails/getbyusername/${localStorage.getItem(
            "username"
          )}`,
          config
        );
        setBookingDetailsList(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchBookingDetails();
  }, []);
  return (
    <div className="bookingdetails">
      <img id="logo1" src="./cslogo.png" alt="Logo" />
      {bookingDetailsList.length > 0 ? (
        bookingDetailsList.map((bookingDetails, index) => (
          <div key={index} className="details">
            <p>
              <span className="label">Name:</span>
              <span className="value">{bookingDetails.name}</span>
            </p>
            <p>
              <span className="label">Hotel Name:</span>
              <span className="value">{bookingDetails.hotelname}</span>
            </p>
            <p>
              <span className="label">Room No:</span>
              <span className="value">{bookingDetails.roomno}</span>
            </p>
            <p>
              <span className="label">Check-In:</span>
              <span className="value">{bookingDetails.booked_from}</span>
            </p>
            <p>
              <span className="label">Check-Out:</span>
              <span className="value">{bookingDetails.booked_to}</span>
            </p>
            <p>
              <span className="label">Email:</span>
              <span className="value">{bookingDetails.email}</span>
            </p>
            <p>
              <span className="label">Phone Number:</span>
              <span className="value">{bookingDetails.phonenumber}</span>
            </p>
            <p>
              <span className="label">Amount:</span>
              <span className="value">{bookingDetails.amount}</span>
            </p>
            <p>
              <span className="label">Payment Status:</span>
              <span className="value">{bookingDetails.paymentStatus}</span>
            </p>
            <div className="payment-status">
              {bookingDetails.paymentStatus === "Payment has to be done" ? (
                <button
                  className="pay-button"
                  onClick={(e) =>
                    navigate(`/payment/${bookingDetails.bookingid}`)
                  }
                >
                  Pay
                </button>
              ) : (
                <button
                  className="pay-button"
                  onClick={(e) => navigate("/hotel")}
                >
                  Done
                </button>
              )}
            </div>
          </div>
        ))
      ) : (
        <p>No booking details found.</p>
      )}
    </div>
  );
}

export default BookingDetails;
