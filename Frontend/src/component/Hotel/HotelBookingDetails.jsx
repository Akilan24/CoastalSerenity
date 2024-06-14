import React, { useState, useEffect } from "react";
import axios from "axios";
import "./HotelBookingDetails.css";
import { useNavigate, useParams } from "react-router-dom";

function HotelBookingDetails() {
  const { value } = useParams();
  const [hotelBookingDetails, setHotelBookingDetails] = useState(null);
  const [travellers, setTravellers] = useState([]);
  const [postTravellers, setPostTravellers] = useState([]);
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  const navigate = useNavigate();

  useEffect(() => {
    async function fetchHotelBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Hotel/HotelBookingDetails/getbyusernameandhotelname/${localStorage.getItem(
            "username"
          )}/${value}`,
          config
        );
        setHotelBookingDetails(response.data);
        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchHotelBookingDetails();
  }, []);

  useEffect(() => {
    async function fetchTravellers() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/User/getalltravellers/${localStorage.getItem(
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
  }, [config]);

  const handlePostTraveller = async (e, bookingId) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Hotel/HotelBookingDetails/addguests/${bookingId}`,
        postTravellers,
        config
      );
      console.log(response.data);
      navigate(`/payment/hotel-${bookingId}`);
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
    <div className="hotelBookingDetails">
      <img id="logo" src="./cslogo.png" alt="Logo" />
      <h2>Hotel Booking Details</h2>
      {hotelBookingDetails && (
        <div className="details">
          <p>
            <span className="label">Name:</span>
            <span className="value">{hotelBookingDetails.name}</span>
          </p>
          <p>
            <span className="label">Hotel Name:</span>
            <span className="value">{hotelBookingDetails.hotelname}</span>
          </p>
          <p>
            <span className="label">Room No:</span>
            <span className="value">{hotelBookingDetails.roomno}</span>
          </p>
          <p>
            <span className="label">Check-In:</span>
            <span className="value">{hotelBookingDetails.booked_from}</span>
          </p>
          <p>
            <span className="label">Check-Out:</span>
            <span className="value">{hotelBookingDetails.booked_to}</span>
          </p>
          <p>
            <span className="label">Email:</span>
            <span className="value">{hotelBookingDetails.email}</span>
          </p>
          <p>
            <span className="label">Phone Number:</span>
            <span className="value">{hotelBookingDetails.phonenumber}</span>
          </p>
          <p>
            <span className="label">Hotel Address:</span>
            <span className="value">{hotelBookingDetails.address}</span>
          </p>
          <p>
            <span className="label">Amount:</span>
            <span className="value">{hotelBookingDetails.amount}</span>
          </p>
          <p>
            <span className="label">Payment Status:</span>
            <span className="value">{hotelBookingDetails.paymentStatus}</span>
          </p>
          {hotelBookingDetails.paymentStatus === "Payment has to be done" && (
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
                  <p>
                    No Guests are found. Add Guest in the travellers section.
                  </p>
                )}
              </div>
              <button
                className="pay"
                onClick={(e) =>
                  handlePostTraveller(e, bookingDetails.bookingId)
                }
              >
                Continue
              </button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default HotelBookingDetails;
