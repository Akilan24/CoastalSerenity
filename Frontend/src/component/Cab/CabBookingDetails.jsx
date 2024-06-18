import React, { useState, useEffect } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./CabBookingDetails.css";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";

function CabBookingDetails() {
  const { value } = useParams();
  const [bookingId, setBookingId] = useState("");
  const [cabBookingDetails, setCabBookingDetails] = useState(null);
  const [rentalCabBookingDetails, setRentalCabBookingDetails] = useState(null);
  const navigate = useNavigate();
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  useEffect(() => {
    if (value.includes("-")) {
      setBookingId(value.split("-")[1]);
    } else {
      setBookingId(value);
    }
  }, [value]);

  useEffect(() => {
    async function fetchCabBookingDetails() {
      try {
        if (val[0] === "cab") {
          const response = await axios.get(
            `http://localhost:8080/CS/Cab/getCabbookingdetailsbyid/${bookingId}`,
            config
          );
          setCabBookingDetails(response.data);
        } else {
          const response = await axios.get(
            `http://localhost:8080/CS/Cab/getRentalCabbookingdetailsbyid/${bookingId}`,
            config
          );
          setRentalCabBookingDetails(response.data);
        }
      } catch (error) {
        console.error(error.response.data.message);
      }
    }

    fetchCabBookingDetails();
  }, [value]);

  const getDate1 = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM yyyy");
  };

  const downloadPDF = () => {
    const capture = document.querySelector(".cabContainer");
    html2canvas(capture).then((canvas) => {
      const imgData = canvas.toDataURL("image/png");
      const pdf = new jsPDF("p", "mm", "a4");
      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
      pdf.save("CabBookingDetails.pdf");
    });
  };

  return (
    <div className="cabBookingDetails">
      <div className="cabContainer">
        <img id="logo" src="../cslogo.png" alt="Logo" />
        <h2>Cab Booking Details</h2>
        {cabBookingDetails && (
          <>
            <div className="details-container">
              <div className="detail-row">
                <span className="detail-label">Cab Model:</span>
                <span className="detail-value">
                  {cabBookingDetails.cabModel}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Origin:</span>
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
            {!value.includes("-") && (
              <button
                onClick={() =>
                  navigate(`/payment/cab-${cabBookingDetails.cabBookingId}`)
                }
              >
                Continue
              </button>
            )}
          </>
        )}
        {rentalCabBookingDetails && (
          <>
            <div className="details-container">
              <div className="detail-row">
                <span className="detail-label">Cab Model:</span>
                <span className="detail-value">
                  {rentalCabBookingDetails.rentalCabModel}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Origin:</span>
                <span className="detail-value">
                  {rentalCabBookingDetails.origin}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Departure:</span>
                <span className="detail-value">
                  {rentalCabBookingDetails.departureTime}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Package Type:</span>
                <span className="detail-value">
                  {rentalCabBookingDetails.packageType}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Booked Date:</span>
                <span className="detail-value">
                  {getDate1(rentalCabBookingDetails.bookedDate)}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Payment Status:</span>
                <span className="detail-value">
                  {rentalCabBookingDetails.paymentStatus}
                </span>
              </div>
              <div className="detail-row">
                <span className="detail-label">Amount:</span>
                <span className="detail-value">
                  &#8377;{rentalCabBookingDetails.rentalCabPrice}
                </span>
              </div>
            </div>
            {!value.includes("-") && (
              <button
                onClick={() =>
                  navigate(
                    `/payment/rentalCab-${rentalCabBookingDetails.rentalCabBookingId}`
                  )
                }
              >
                Continue
              </button>
            )}
          </>
        )}
      </div>
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

export default CabBookingDetails;
