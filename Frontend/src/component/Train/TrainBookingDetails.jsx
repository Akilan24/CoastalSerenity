import React, { useState, useEffect } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./TrainBookingDetails.css";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
function TrainBookingDetails() {
  const { value } = useParams();
  const [bookingId, setBookingId] = useState("");
  const [train, setTrain] = useState(null);
  const [boardingStation, setBoardingStation] = useState(new Map());
  const [boarding, setBoarding] = useState("");
  const [seatType, setSeatType] = useState("");
  const navigate = useNavigate();
  const [travellers, setTravellers] = useState([]);
  const [postTravellers, setPostTravellers] = useState([]);
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };
  const val = value.split("-");
  useEffect(() => {
    if (value.includes("-")) {
      setBookingId(value.split("-")[1]);
    } else {
      setBookingId(value);
    }
  }, [value]);
  useEffect(() => {
    setSeatType(val[0]);
    async function fetchTrain() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Train/getbyid/${bookingId}`,
          config
        );
        setTrain(response.data);
        const boardingStation = new Map(
          [...Object.entries(response.data.boardingStation)].sort(
            ([, timeA], [, timeB]) =>
              parse(timeA, "yyyy-MM-dd HH:mm:ss", new Date()) -
              parse(timeB, "yyyy-MM-dd HH:mm:ss", new Date())
          )
        );
        setBoardingStation(boardingStation);
        console.log(response.data);
      } catch (error) {
        console.log(error.response);
      }
    }
    fetchTrain();
  }, [value]);

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
  }, []);

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

  async function handleBooking() {
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Train/bookTrain/${bookingId}/${seatType}/${boarding}/${localStorage.getItem(
          "username"
        )}`,
        postTravellers,
        config
      );
      navigate(`/payment/train-${response.data.trainBookingId}`);
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
  const getDate2 = (time) => {
    if (!time) return "N/A";
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "dd MMM yyyy");
  };
  const downloadPDF = () => {
    const capture = document.querySelector(".trainContainer");
    html2canvas(capture).then((canvas) => {
      const imgData = canvas.toDataURL("image/png");
      const pdf = new jsPDF("p", "mm", "a4");
      const imgProps = pdf.getImageProperties(imgData);
      const pdfWidth = pdf.internal.pageSize.getWidth();
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      pdf.addImage(imgData, "PNG", 0, 0, pdfWidth, pdfHeight);
      pdf.save("TrainBookingDetails.pdf");
    });
  };
  return (
    <div className="trainBookingDetails">
      <div className="trainContainer">
        <img id="logo" src="../cslogo.png" alt="Logo" />
        <h2>Train Booking Details</h2>
        {value.includes("-") ? (
          <div className="details-container">
            <div className="detail-row">
              <span className="detail-label">Train Name:</span>
              <span className="detail-value">{train.trainName}</span>
            </div>
            <div className="detail-row">
              <span className="detail-label">PNR:</span>
              <span className="detail-value">{train.pnr}</span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Origin:</span>
              <span className="detail-value">
                {flightBookingDetails.origin}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Destination:</span>
              <span className="detail-value">
                {flightBookingDetails.destination}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Passenger Names:</span>
              <span className="detail-value">
                {train.trainPassenger &&
                  train.trainPassenger.map((passenger, index) => (
                    <span key={index}>
                      {passenger.name}
                      {index < train.trainPassenger.length - 1 && ", "}
                    </span>
                  ))}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Departure Time:</span>
              <span className="detail-value">
                {flightBookingDetails.departureTime}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Arrival Time:</span>
              <span className="detail-value">
                {flightBookingDetails.arrivalTime}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Duration:</span>
              <span className="detail-value">
                {getDuration(flightBookingDetails.duration)}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Booked Date:</span>
              <span className="detail-value">
                {getDate2(flightBookingDetails.bookedDate)}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Payment Status:</span>
              <span className="detail-value">
                {flightBookingDetails.paymentStatus}
              </span>
            </div>
            <div className="detail-row">
              <span className="detail-label">Amount:</span>
              <span className="detail-value">
                &#8377;{flightBookingDetails.totalPrice}
              </span>
            </div>
          </div>
        ) : (
          <div className="trainClass">
            {train && (
              <>
                <div className="train">
                  <div className="td">
                    <div className="trainname">
                      <p id="company">{train.trainName}</p>
                      <p id="model">
                        #{train.pnr}&nbsp;&nbsp;|&nbsp;&nbsp;Depart on:&nbsp;
                        <span>{train.departOn}</span>
                      </p>
                    </div>
                  </div>
                  <div className="journeyDetails">
                    <div>
                      <div className="days">
                        <p id="time">{getTime(train.departureTime)}</p>
                        <p id="date">{getDate(train.departureTime)}</p>
                      </div>
                      <p>{train.origin}</p>
                    </div>
                    <div>
                      <p id="duration">{getDuration(train.duration)}</p>
                      <hr />
                      <p id="stopOver">{train.stopOver}</p>
                    </div>
                    <div>
                      <div className="days">
                        <div>
                          <p id="time">{getTime(train.arrivalTime)}</p>
                        </div>
                        <p id="date">{getDate(train.arrivalTime)}</p>
                      </div>
                      <p>{train.destination}</p>
                    </div>
                  </div>
                </div>
                <div className="contain">
                  <div className="seatType">
                    {val[0] === "SL" && (
                      <div id="type">
                        <div>
                          <p>SL</p>
                          <p>&#8377; {train.seatPrice["SL"]}</p>
                        </div>
                        {train.trainBookingStatus["SL"] > 0 ? (
                          <p id="avail">
                            AVAILABLE {train.trainBookingStatus["SL"]}
                          </p>
                        ) : (
                          <p>no</p>
                        )}
                      </div>
                    )}
                    {val[0] === "1A" && (
                      <div id="type">
                        <div>
                          <p>1A</p>
                          <p>&#8377; {train.seatPrice["1A"]}</p>
                        </div>
                        {train.trainBookingStatus["1A"] > 0 ? (
                          <p id="avail">
                            AVAILABLE {train.trainBookingStatus["1A"]}
                          </p>
                        ) : (
                          <p>no</p>
                        )}
                      </div>
                    )}
                    {val[0] === "2A" && (
                      <div id="type">
                        <div>
                          <p>2A</p>
                          <p>&#8377; {train.seatPrice["2A"]}</p>
                        </div>
                        {train.trainBookingStatus["2A"] > 0 ? (
                          <p id="avail">
                            AVAILABLE {train.trainBookingStatus["2A"]}
                          </p>
                        ) : (
                          <p>no</p>
                        )}
                      </div>
                    )}
                    {val[0] === "3A" && (
                      <div id="type">
                        <div>
                          <p>3A</p>
                          <p>&#8377; {train.seatPrice["3A"]}</p>
                        </div>
                        {train.trainBookingStatus["3A"] > 0 ? (
                          <p id="avail">
                            AVAILABLE {train.trainBookingStatus["3A"]}
                          </p>
                        ) : (
                          <p>no</p>
                        )}
                      </div>
                    )}
                    {val[0] === "CC" && (
                      <div id="type">
                        <div>
                          <p>CC</p>
                          <p>&#8377; {train.seatPrice["CC"]}</p>
                        </div>
                        {train.trainBookingStatus["CC"] > 0 ? (
                          <p id="avail">
                            AVAILABLE {train.trainBookingStatus["CC"]}
                          </p>
                        ) : (
                          <p>no</p>
                        )}
                      </div>
                    )}
                  </div>
                  <div className="boarding">
                    <label htmlFor="boarding">Boarding Station:</label>
                    <select
                      id="boarding"
                      value={boarding}
                      onChange={(e) => setBoarding(e.target.value)}
                    >
                      <option value="">Select Boarding Station</option>
                      {Array.from(boardingStation.entries()).map(
                        ([key, value]) => (
                          <option key={key} value={key}>
                            {key},{getDate1(value)}
                          </option>
                        )
                      )}
                    </select>
                  </div>
                </div>
                <div id="guest">
                  <p id="check">Add Guest :</p>
                  <div className="list">
                    <div className="travellerList">
                      {travellers.length > 0 ? (
                        travellers.map((item, index) => (
                          <div id="st" key={index}>
                            <div id="input">
                              <input
                                type="checkbox"
                                value={index}
                                onChange={(event) =>
                                  handleSelectTraveller(index, event)
                                }
                              />
                              <p>{item.name}</p>
                            </div>
                          </div>
                        ))
                      ) : (
                        <div>
                          <p id="check">
                            No Guests are found. Add Guest in the travellers
                            section.
                          </p>
                          <button
                            id="check"
                            onClick={(e) => navigate("/saveTraveller")}
                          >
                            Add
                          </button>
                        </div>
                      )}
                    </div>
                    <div id="button">
                      {travellers.length > 0 && (
                        <div>
                          <button
                            id="add"
                            onClick={() => navigate("/saveTraveller")}
                          >
                            Add
                          </button>
                        </div>
                      )}
                    </div>
                  </div>
                </div>
                <button id="pay" onClick={handleBooking}>
                  PAY & BOOK NOW
                </button>
              </>
            )}
          </div>
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

export default TrainBookingDetails;
