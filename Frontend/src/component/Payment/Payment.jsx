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
  const [bookingDetails, setBookingDetails] = useState("");
  const [min, setMin] = useState(6);
  const [sec, setSec] = useState(59);
  const [isPaymentAllowed, setIsPaymentAllowed] = useState(true);

  useEffect(() => {
    async function fetchBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Hotel/HotelBookingDetails/getbyid/${value}`,
          config
        );
        setBookingDetails(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchBookingDetails();
  }, [value, config]);

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
      const response = await axios.post(
        `http://localhost:8080/CS/Payment/doPayment/${bookingDetails.bookingid}`,
        {},
        config
      );
      navigate("/bookingDetails");
      console.log(response.data);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  return (
    <div className="payment">
      <img id="logo" src="../cslogo.png" />
      <h2>Payment Page</h2>
      <div className="paymentDetails">
        <p>
          <span className="label">Name :</span>
          <span className="value">{bookingDetails.name}</span>
        </p>
        <p>
          <span className="label">Booking Id :</span>
          <span className="value">{bookingDetails.bookingid}</span>
        </p>
        <p>
          <span className="label">Amount :</span>
          <span className="value">{bookingDetails.amount}</span>
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
