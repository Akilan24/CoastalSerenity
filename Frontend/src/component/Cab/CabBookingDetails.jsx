import React, { useState, useEffect } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./CabBookingDetails.css";

function CabBookingDetails() {
  const { value } = useParams();
  const [cabBookingDetails, setCabBookingDetails] = useState();
  const [rentalCabBookingDetails, setRentalCabBookingDetails] = useState();
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const val = value.split("-");

  useEffect(() => {
    async function fetchCabBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Train/getbyid/${val[1]}`,
          config
        );
        setCabBookingDetails(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response);
      }
    }
    fetchCabBookingDetails();
  }, [value]);

  async function handleCabBookingDetails() {
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Cab/getCabbookingdetailsbyid/${val[1]}`,
        config
      );
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  async function handleRentalCabBookingDetails() {
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Cab/getRentalCabbookingdetailsbyid/${val[1]}`,
        config
      );
      navigate(`/payment/rentalCab-${response.data.trainBookingId}`);
      console.log(response);
    } catch (error) {
      console.log(error.response.data.message);
    }
  }

  const getDuration = (duration) => {
    const [hours, minutes] = duration.split(":");
    return `${hours}h ${minutes}m`;
  };

  const getTime = (datetime) => {
    const date = new Date(datetime);
    return date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
  };
  const getDate = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM");
  };

  const getDate1 = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "HH:mm(dd MMM)");
  };
  return (
    <div className="cabBookingDetails">
      <img id="logo" src="../cslogo.png" alt="Logo" />
      <h2>Cab Booking Details</h2>
      {cabBookingDetails ? <div className="cabClass"></div> : <p></p>}
      {rentalCabBookingDetails ? (
        <>
          <div className="rentalCabClass">
            <div className="detail-row">
              <span className="detail-label">Cab Model:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.cabModel}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label"> Origin:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.origin}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Destination:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.destination}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Departure:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.depatureTime}
              </span>
            </div>
            {cabBookingDetails.journeyType === "Round-Trip" && (
              <div className="detail-row">
                <span className="detail-label">Return:</span>
                <span className="detail-value">
                  &#8377;{cabBookingDetails.returnTime}
                </span>
              </div>
            )}

            <div className="detail-row">
              <span className="detail-label">Journey Type:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.journeyType}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Booked Date:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.bookedDate}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Payment Status:</span>
              <span className="detail-value">
                {getDuration(cabBookingDetails.paymentStatus)}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Amount:</span>
              <span className="detail-value">
                {getDuration(cabBookingDetails.cabPrice)}
              </span>
            </div>
          </div>
          <button onClick={() => navigate(`/payment/cab-${value}`)}>
            Continue
          </button>
        </>
      ) : (
        <p></p>
      )}
    </div>
  );
}

export default CabBookingDetails;
