import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./FlightBookingDetails.css";
function FlightBookingDetails() {
  const { value } = useParams();
  const [flight, setFlight] = useState();
  const [seats, setSeats] = useState([]);
  useEffect(() => {
    async function fetchCityNames() {
      try {
        const response = await axios.get(
          `http://localhost:8080/HMA/Flight/getbyid/${value}`,
          config
        );
        setFlight(response.data);
        setSeats(flight.flightSeats);
        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchCityNames();
  }, []);
  return <div></div>;
}

export default FlightBookingDetails;
