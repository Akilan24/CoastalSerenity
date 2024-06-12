import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./TrainBookingDetails.css";

function TrainBookingDetails() {
  const { value } = useParams();
  const [trainBookingDetails, setTrainBookingDetails] = useState();
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const val = value.split("-");
  useEffect(() => {
    async function fetchTrainBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Train/getbyid/${val[1]}`,
          config
        );
        setTrainBookingDetails(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response);
      }
    }
    fetchTrainBookingDetails();
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
    <div className="trainbookingdetails">
      <img id="logo" src="../cslogo.png" alt="Logo" />
    </div>
  );
}

export default TrainBookingDetails;
