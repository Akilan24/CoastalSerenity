import React from "react";
import "./Home.css";
import { Route, Routes } from "react-router-dom";
import Register from "../Register/Register.jsx";
import ForgotPassword from "../Login/ForgotPassword.jsx";
import ResetPassword from "../Login/ResetPassword.jsx";
import Login from "../Login/Login.jsx";
import Hotel from "../Hotel/Hotel.jsx";
import Flight from "../Flight/Flight.jsx";
import Bus from "../Bus/Bus.jsx";
import Profile from "../Profile/Profile.jsx";
import SaveTraveller from "../Profile/SaveTraveller/SaveTraveller.jsx";
import HotelBookingDetails from "../Hotel/HotelBookingDetails.jsx";
import Payment from "../Payment/Payment.jsx";
import FlightSeatsSelection from "../Flight/FlightSeatsSelection.jsx";
import FlightBookingDetails from "../Flight/FlightBookingDetails.jsx";
import BusSeatsSelection from "../Bus/BusSeatsSelection.jsx";
import BusBookingDetails from "../Bus/BusBookingDetails.jsx";
import MyTrips from "../MyTrips/MyTrips.jsx";
function Home() {
  return (
    <>
      <div className="homeclass">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/forgotpassword" element={<ForgotPassword />} />
          <Route path="/resetpassword" element={<ResetPassword />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/saveTraveller" element={<SaveTraveller />} />
          <Route path="/myTrips" element={<MyTrips />} />
          <Route path="/hotel" element={<Hotel />} />
          <Route
            path="/hotelBookingDetails/:value"
            element={<HotelBookingDetails />}
          />
          <Route path="/flight" element={<Flight />} />
          <Route
            path="/flightSeatsSelection/:value"
            element={<FlightSeatsSelection />}
          />
          <Route
            path="/flightBookingDetails/:value"
            element={<FlightBookingDetails />}
          />
          <Route path="/bus" element={<Bus />} />
          <Route
            path="/busSeatsSelection/:value"
            element={<BusSeatsSelection />}
          />
          <Route
            path="/busBookingDetails/:value"
            element={<BusBookingDetails />}
          />
          <Route path="/payment/:value" element={<Payment />} />
        </Routes>
      </div>
    </>
  );
}

export default Home;
