import React, { useState, useEffect } from "react";
import axios from "axios";
import "./HotelBookingDetails.css";
import { useNavigate, useParams } from "react-router-dom";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
function HotelBookingDetails() {
  const { value } = useParams();
  const [bookingId, setBookingId] = useState("");
  const [hotelBookingDetails, setHotelBookingDetails] = useState(null);
  const [travellers, setTravellers] = useState([]);
  const [postTravellers, setPostTravellers] = useState([]);
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const downloadPDF = () => {
    const capture = document.querySelector(".hotelBookingDetails");
    html2canvas(capture).then((canvas) => {
      const imgData = canvas.toDataURL("image/png");
      const pdf = new jsPDF("p", "mm", "a4");
      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
      pdf.save("HotelBookingDetails.pdf");
    });
  };
  const navigate = useNavigate();
  useEffect(() => {
    if (value.includes("-")) {
      setBookingId(value.split("-")[1]);
    } else {
      setBookingId(value);
    }
  }, [value]);

  useEffect(() => {
    async function fetchHotelBookingDetails() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Hotel/HotelBookingDetails/getbyusernameandhotelname/${localStorage.getItem(
            "username"
          )}/${bookingId}`,
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
  const getDate = (time) => {
    if (!time) return "N/A";
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM yyyy");
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
          {value.split("-") && (
            <p>
              <span className="label">Guest Names:</span>
              <span className="value">
                {hotelBookingDetails.hotelGuest &&
                  hotelBookingDetails.hotelGuest.map((guest, index) => (
                    <span key={index}>
                      {guest.name}
                      {index < hotelBookingDetails.hotelGuest.length - 1 &&
                        ", "}
                    </span>
                  ))}
              </span>
            </p>
          )}
          <p>
            <span className="label">Payment Status:</span>
            <span className="value">{hotelBookingDetails.paymentStatus}</span>
          </p>
          <p>
            <span className="label">Booked Date:</span>
            <span className="value">
              {getDate(hotelBookingDetails.bookedDate)}
            </span>
          </p>
          <p>
            <span className="label">Amount:</span>
            <span className="value">{hotelBookingDetails.totalPrice}</span>
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
                  handlePostTraveller(e, bookingDetails.hotelBookingId)
                }
              >
                Continue
              </button>
            </div>
          )}
        </div>
      )}
      {value.includes("-") && (
        <div className="detail-button">
          <button id="back" onClick={() => navigate(-1)}>
            Back
          </button>
          <button id="print" onClick={downloadPDF}>
            Print
          </button>
        </div>
      )}
    </div>
  );
}

export default HotelBookingDetails;
