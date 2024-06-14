import React, { useState, useEffect } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./CabBookingDetails.css";

function TrainBookingDetails() {
  const { value } = useParams();
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
    setSeatType(val[0]);
    async function fetchTrain() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Train/getbyid/${val[1]}`,
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
        `http://localhost:8080/CS/Train/bookTrain/${
          val[1]
        }/${seatType}/${boarding}/${localStorage.getItem("username")}`,
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
  return (
    <div className="trainBookingDetails">
      <img id="logo" src="../cslogo.png" alt="Logo" />
      <h2>Train Booking Details</h2>
      <div className="trainClass">
        {train ? (
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
                      <p>&#8377; {train.seatPrice["SL - Sleeper"]}</p>
                    </div>
                    {train.trainBookingStatus["SL - Sleeper"] > 0 ? (
                      <p id="avail">
                        AVAILABLE {train.trainBookingStatus["SL - Sleeper"]}
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
                      <p>&#8377; {train.seatPrice["1A - 1st Class AC"]}</p>
                    </div>
                    {train.trainBookingStatus["1A - 1st Class AC"] > 0 ? (
                      <p id="avail">
                        AVAILABLE{" "}
                        {train.trainBookingStatus["1A - 1st Class AC"]}
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
                      <p>&#8377; {train.seatPrice["2A - 2 Tier AC"]}</p>
                    </div>
                    {train.trainBookingStatus["2A - 2 Tier AC"] > 0 ? (
                      <p id="avail">
                        AVAILABLE {train.trainBookingStatus["2A - 2 Tier AC"]}
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
                      <p>&#8377; {train.seatPrice["3A - 3 Tier AC"]}</p>
                    </div>
                    {train.trainBookingStatus["3A - 3 Tier AC"] > 0 ? (
                      <p id="avail">
                        AVAILABLE {train.trainBookingStatus["3A - 3 Tier AC"]}
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
                      <p>&#8377; {train.seatPrice["CC - CC Chair Car"]}</p>
                    </div>
                    {train.trainBookingStatus["CC - CC Chair Car"] > 0 ? (
                      <p id="avail">
                        AVAILABLE
                        {train.trainBookingStatus["CC - CC Chair Car"]}
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
                  {Array.from(boardingStation.entries()).map(([key, value]) => (
                    <option key={key} value={key}>
                      {key},{getDate1(value)}
                    </option>
                  ))}
                </select>
              </div>
            </div>
            <div id="guest">
              <p id="check">Add Guest:</p>
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
        ) : (
          <p>Loading...</p>
        )}
      </div>
    </div>
  );
}

export default TrainBookingDetails;
