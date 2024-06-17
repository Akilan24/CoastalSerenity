import React, { useState, useEffect } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./CabBookingDetails.css";

function CabBookingDetails() {
  const { value } = useParams();
  const [cabBookingDetails, setCabBookingDetails] = useState("");
  const [rentalCabBookingDetails, setRentalCabBookingDetails] = useState("");
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const val = value.split("-");

  useEffect(() => {
    async function fetchCabBookingDetails() {
      if (val[0] === "cab") {
        try {
          const response = await axios.get(
            `http://localhost:8080/CS/Cab/getCabbookingdetailsbyid/${val[1]}`,
            config
          );
          setCabBookingDetails(response.data);
          console.log(response);
        } catch (error) {
          console.log(error.response.data.message);
        }
      } else {
        try {
          const response = await axios.get(
            `http://localhost:8080/CS/Cab/getRentalCabbookingdetailsbyid/${val[1]}`,
            config
          );
          setRentalCabBookingDetails(response.data);
          console.log(response);
        } catch (error) {
          console.log(error.response.data.message);
        }
      }
    }
    fetchCabBookingDetails();
  }, [value]);

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
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm", new Date());
    return format(parsedDate, "dd MMM yyyy");
  };
  return (
    <div className="cabBookingDetails">
      <img id="logo" src="../cslogo.png" alt="Logo" />
      <h2>Cab Booking Details</h2>
      {cabBookingDetails && (
        <>
          <div className="cabClass">
            <div className="detail-row">
              <span className="detail-label">Cab Model:</span>
              <span className="detail-value">{cabBookingDetails.cabModel}</span>
            </div>
            <div className="detail-row">
              <span className="detail-label"> Origin:</span>
              <span className="detail-value">{cabBookingDetails.origin}</span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Destination:</span>
              <span className="detail-value">
                {cabBookingDetails.destination}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Departure:</span>
              <span className="detail-value">
                {cabBookingDetails.departureTime}
              </span>
            </div>
            {cabBookingDetails.journeyType === "Round-Trip" && (
              <div className="detail-row">
                <span className="detail-label">Return:</span>
                <span className="detail-value">
                  {cabBookingDetails.returnTime}
                </span>
              </div>
            )}

            <div className="detail-row">
              <span className="detail-label">Journey Type:</span>
              <span className="detail-value">
                {cabBookingDetails.journeyType}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Booked Date:</span>
              <span className="detail-value">
                {getDate1(cabBookingDetails.bookedDate)}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Payment Status:</span>
              <span className="detail-value">
                {cabBookingDetails.paymentStatus}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Amount:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.cabPrice}
              </span>
            </div>
          </div>
          <button
            onClick={() =>
              navigate(`/payment/cab-${cabBookingDetails.cabBookingId}`)
            }
          >
            Continue
          </button>
        </>
      )}
      {rentalCabBookingDetails && (
        <>
          <div className="rentalCabClass">
            <div className="detail-row">
              <span className="detail-label">Cab Model:</span>
              <span className="detail-value">
                {cabBookingDetails.rentalCabModel}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label"> Origin:</span>
              <span className="detail-value">{cabBookingDetails.origin}</span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Departure:</span>
              <span className="detail-value">
                {cabBookingDetails.depatureTime}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Package Type:</span>
              <span className="detail-value">
                {cabBookingDetails.packageType}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Booked Date:</span>
              <span className="detail-value">
                {getDate1(cabBookingDetails.bookedDate)}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Payment Status:</span>
              <span className="detail-value">
                {cabBookingDetails.paymentStatus}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Amount:</span>
              <span className="detail-value">
                &#8377;{cabBookingDetails.rentalCabPrice}
              </span>
            </div>
          </div>
          <button
            onClick={() =>
              navigate(
                `/payment/rentalCab-${cabBookingDetails.rentalCabBookingId}`
              )
            }
          >
            Continue
          </button>
        </>
      )}
    </div>
  );
}

export default CabBookingDetails;
