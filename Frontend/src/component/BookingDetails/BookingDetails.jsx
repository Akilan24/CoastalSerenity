import React, { useState, useEffect } from "react";
import axios from "axios";
import "./BookingDetails.css";
import { useNavigate } from "react-router-dom";

function BookingDetails() {
  const [bookingDetailsList, setBookingDetailsList] = useState([]);
  const [travellers, setTravellers] = useState([]);
  const [postTravellers, setPostTravellers] = useState([]); // Initialize as an array
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

  useEffect(() => {
    async function fetchTravellers() {
      try {
        const response = await axios.get(
          `http://localhost:8080/HMA/User/getalltravellers/${localStorage.getItem(
            "username"
          )}`,
          config
        );
        setTravellers(response.data);
        console.log(response);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchTravellers();
  }, []);

  const handlePostTraveller = async (e, bookingid) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/HMA/BookingDetails/addguests/${bookingid}`,
        postTravellers,
        config
      );
      console.log(response.data);
      navigate(`/payment/${bookingid}`);
    } catch (error) {
      console.log(error.response.data.message);
    }
  };

  const handleSelectTraveller = (index, event) => {
    const selectedTraveller = travellers[index];
    if (event.target.checked) {
      setPostTravellers((prev) => [...prev, selectedTraveller]);
    } else {
      setPostTravellers((prev) =>
        prev.filter((traveller) => traveller !== selectedTraveller)
      );
    }
  };

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
              <span className="label">Hotel Address:</span>
              <span className="value">{bookingDetails.address}</span>
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
                <div id="guest">
                  <p>Add Guest:</p>
                  <div className="list">
                    {travellers.length > 0 ? (
                      travellers.map((item, index) => (
                        <div id="st" key={index} className="traveller-item">
                          <div id="input">
                            <input
                              type="checkbox"
                              value={index}
                              onChange={(event) =>
                                handleSelectTraveller(index, event)
                              }
                            ></input>
                            <p>{item.name}</p>
                          </div>
                        </div>
                      ))
                    ) : (
                      <p>No Guests are found. Add Guest in the travellers section.</p>
                    )}
                  </div>
                  <button
                    className="pay-button"
                    onClick={(e) =>
                      handlePostTraveller(e, bookingDetails.bookingid)
                    }
                  >
                    Pay
                  </button>
                </div>
              ) : (
                <button
                  className="pay-button"
                  onClick={() => navigate("/hotel")}
                >
                  Done
                </button>
              )}
            </div>
          </div>
        ))
      ) : (
        <p id="none">No booking details are found.</p>
      )}
    </div>
  );
}

export default BookingDetails;
