import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./Payment.css";

function Payment() {
  const { value } = useParams();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const navigate = useNavigate();
  const [bookingDetails, setBookingDetails] = useState({});
  const [bookingId, setBookingId] = useState("");
  const [min, setMin] = useState(6);
  const [sec, setSec] = useState(59);
  const [isPaymentAllowed, setIsPaymentAllowed] = useState(true);

  useEffect(() => {
    const val = value.split("-");
    setBookingId(val[1]);
    if (val[0] === "hotel") {
      async function fetchBookingDetails() {
        try {
          const response = await axios.get(
            `http://localhost:8080/CS/Hotel/HotelBookingDetails/getbyid/${val[1]}`,
            config
          );
          setBookingDetails(response.data);
          console.log(response.data);
        } catch (error) {
          console.log(error.response.data.message);
        }
      }
      fetchBookingDetails();
    }
    if (val[0] === "flight") {
      async function fetchFlightBookingDetails() {
        try {
          const response = await axios.get(
            `http://localhost:8080/CS/Flight/getflightbookingdetailsbyid/${val[1]}`,
            config
          );
          setBookingDetails(response.data);
          console.log(response.data);
        } catch (error) {
          console.log(error.response);
        }
      }
      fetchFlightBookingDetails();
    }
    if (val[0] === "bus") {
      async function fetchBusBookingDetails() {
        try {
          const response = await axios.get(
            `http://localhost:8080/CS/Bus/getbusbookingdetailsbyid/${val[1]}`,
            config
          );
          setBookingDetails(response.data);
          console.log(response.data);
        } catch (error) {
          console.log(error.response);
        }
      }
      fetchBusBookingDetails();
    }
  }, [value]);

  useEffect(() => {
    const timer = setInterval(() => {
      setSec((prevSec) => {
        if (prevSec === 0) {
          setMin((prevMin) => {
            if (prevMin === 0) {
              clearInterval(timer);
              setIsPaymentAllowed(false);
              return 0;
            } else {
              return prevMin - 1;
            }
          });
          return 59;
        } else {
          return prevSec - 1;
        }
      });
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  async function doPayment() {
    try {
      const val = value.split("-");
      const response = await axios.post(
        `http://localhost:8080/CS/Payment/doPayment/${val[0]}-${bookingDetails.bookingId}`,
        {},
        config
      );
      navigate(`/myTrips`);
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  return (
    <div className="payment">
      <img id="logo" src="../cslogo.png" alt="Logo" />
      <h2>Payment Page</h2>
      <div className="paymentDetails">
        <p>
          <span className="label">Name :</span>
          <span className="value">{bookingDetails.name || "N/A"}</span>
        </p>
        <p>
          <span className="label">Booking Id :</span>
          <span className="value">{bookingId || "N/A"}</span>
        </p>
        <p>
          <span className="label">Amount :</span>
          <span className="value">{bookingDetails.totalPrice || "N/A"}</span>
        </p>
      </div>
      <div className="display">
        <p className="time">
          {min < 10 ? "0" + min : min}:{sec < 10 ? "0" + sec : sec}
        </p>
        <button onClick={doPayment} disabled={!isPaymentAllowed}>
          Pay
        </button>
      </div>
      {isPaymentAllowed && (
        <p className="warning">Don't press refresh button during transaction</p>
      )}
      {!isPaymentAllowed && (
        <p className="warning">Time is up. Please try again.</p>
      )}
    </div>
  );
}

export default Payment;
