import React, { useState, useEffect } from "react";
import axios from "axios";
import { parse, format } from "date-fns";
import { useNavigate, useParams } from "react-router-dom";
import "./BusSeatsSelection.css";

function BusSeatsSelection() {
  const { value } = useParams();
  const navigate = useNavigate();
  const [pickUpPointList, setPickUpPointList] = useState(new Map());
  const [dropPointList, setDropPointList] = useState(new Map());
  const [pickUpPoint, setPickUpPoint] = useState("");
  const [dropPoint, setDropPoint] = useState("");
  const [lowerSleeper, setLowerSleeper] = useState([]);
  const [lowerSeat, setLowerSeat] = useState([]);
  const [upperSleeper, setUpperSleeper] = useState([]);
  const [upperDoubleSleeper, setUpperDoubleSleeper] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [travellers, setTravellers] = useState([]);
  const [postTravellers, setPostTravellers] = useState([]);
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  useEffect(() => {
    async function fetchBus() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Bus/getbyid/${value}`,
          config
        );
        setBus(response.data);

        const sortedPickUpPoints = new Map(
          [...Object.entries(response.data.pickUpPoint)].sort(
            ([, timeA], [, timeB]) =>
              parse(timeA, "yyyy-MM-dd HH:mm:ss", new Date()) -
              parse(timeB, "yyyy-MM-dd HH:mm:ss", new Date())
          )
        );
        setPickUpPointList(sortedPickUpPoints);

        const sortedDropPoints = new Map(
          [...Object.entries(response.data.dropPoint)].sort(
            ([, timeA], [, timeB]) =>
              parse(timeA, "yyyy-MM-dd HH:mm:ss", new Date()) -
              parse(timeB, "yyyy-MM-dd HH:mm:ss", new Date())
          )
        );
        setDropPointList(sortedDropPoints);

        setLowerSleeper(
          response.data.busSeats.filter(
            (seat) => seat.seatType === "lowerSleeper"
          )
        ).sort((a, b) => a.seatNo.localeCompare(b.seatNo));
        setLowerSeat(
          response.data.busSeats.filter((seat) => seat.seatType === "lowerSeat")
        ).sort((a, b) => a.seatNo.localeCompare(b.seatNo));
        setUpperSleeper(
          response.data.busSeats.filter(
            (seat) => seat.seatType === "upperSleeper"
          )
        ).sort((a, b) => a.seatNo.localeCompare(b.seatNo));
        setUpperDoubleSleeper(
          response.data.busSeats.filter(
            (seat) => seat.seatType === "upperDoubleSleeper"
          )
        ).sort((a, b) => a.seatNo.localeCompare(b.seatNo));
      } catch (error) {
        console.log(error.response.data.message);
      }
    }

    fetchBus();
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

  const handlePostTravellersAndSeats = async (e, busid) => {
    e.preventDefault();
    const btbs = {
      travellers: postTravellers,
      busSeats: selectedSeats,
    };
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Bus/bookBus/${busid}/${localStorage.getItem(
          "username"
        )}/${pickUpPoint}/${dropPoint}`,
        btbs,
        config
      );
      console.log(response.data);
      navigate(`/busBookingDetails/${response.data.busBookingId}`);
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

  const handleSelectSeat = (seat) => {
    if (!seat.bookingStatus) return;

    setSelectedSeats((prevSelectedSeats) => {
      if (prevSelectedSeats.includes(seat)) {
        return prevSelectedSeats.filter((s) => s !== seat);
      } else {
        return [...prevSelectedSeats, seat];
      }
    });
  };
  const getDate = (time) => {
    const parsedDate = parse(time, "yyyy-MM-dd HH:mm:ss", new Date());
    return format(parsedDate, "HH:mm,dd MMM");
  };
  return (
    <div className="bss">
      <div className="seatBooking">
        <img id="logo" src="../cslogo.png" alt="Logo" />
        <div className="seat">
          <h2>Seat Selection</h2>
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
                      No Guests are found. Add Guest in the travellers section.
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
                    <button id="add" onClick={() => navigate("/saveTraveller")}>
                      Add
                    </button>
                  </div>
                )}
              </div>
            </div>
          </div>

          <div className="bus">
            <div id="contain">
              <div id="display">
                <div>
                  <p id="white"></p>
                  <p>Free</p>
                </div>
                <div>
                  <p id="blue"></p>
                  <p>Selected</p>
                </div>
                <div>
                  <p id="grey"></p>
                  <p>Booked</p>
                </div>
              </div>
              <div>
                <label htmlFor="pickUpPoint">Pickup Point:</label>
                <select
                  id="text"
                  value={pickUpPoint}
                  onChange={(e) => setPickUpPoint(e.target.value)}
                >
                  <option value="">Select Pickup Point</option>
                  {Array.from(pickUpPointList.entries()).map(([key, value]) => (
                    <option key={key} value={key}>
                      {key}-{getDate(value)}
                    </option>
                  ))}
                </select>
              </div>
              <div>
                <label htmlFor="dropPoint">Drop Point:</label>
                <select
                  id="dropPoint"
                  value={dropPoint}
                  onChange={(e) => setDropPoint(e.target.value)}
                >
                  <option value="">Select Drop Point</option>
                  {Array.from(dropPointList.entries()).map(([key, value]) => (
                    <option key={key} value={key}>
                      {key}-{getDate(value)}
                    </option>
                  ))}
                </select>
              </div>
            </div>
            <div className="busSeat">
              <div className="lowerBerth">
                <img id="img" src="../steering.png" />
                <div id="lb">
                  <div className="lowerSleeper">
                    {lowerSleeper.map((seat, index) => (
                      <button
                        key={index}
                        className="lsl"
                        style={{
                          backgroundColor: seat.bookingStatus
                            ? selectedSeats.includes(seat)
                              ? "#91c4e5"
                              : "white"
                            : "grey",
                        }}
                        onClick={() => handleSelectSeat(seat)}
                      >
                        {seat.seatNo}
                        <p>&#8377;{seat.seatPrice}</p>
                      </button>
                    ))}
                  </div>
                  <div className="lowerSeat">
                    {lowerSeat.map((seat, index) => (
                      <button
                        key={index}
                        className="ls"
                        style={{
                          backgroundColor: seat.bookingStatus
                            ? selectedSeats.includes(seat)
                              ? "#91c4e5"
                              : "white"
                            : "grey",
                        }}
                        onClick={() => handleSelectSeat(seat)}
                      >
                        {seat.seatNo}
                        <p>&#8377;{seat.seatPrice}</p>
                      </button>
                    ))}
                  </div>
                </div>
              </div>
              <div className="upperBerth">
                <div className="upperSleeper">
                  {upperSleeper.map((seat, index) => (
                    <button
                      key={index}
                      className="usl"
                      style={{
                        backgroundColor: seat.bookingStatus
                          ? selectedSeats.includes(seat)
                            ? "#91c4e5"
                            : "white"
                          : "grey",
                      }}
                      onClick={() => handleSelectSeat(seat)}
                    >
                      {seat.seatNo}
                      <p>&#8377;{seat.seatPrice}</p>
                    </button>
                  ))}
                </div>
                <div className="upperDoubleSleeper">
                  {upperDoubleSleeper.map((seat, index) => (
                    <button
                      key={index}
                      className="uds"
                      style={{
                        backgroundColor: seat.bookingStatus
                          ? selectedSeats.includes(seat)
                            ? "#91c4e5"
                            : "white"
                          : "grey",
                      }}
                      onClick={() => handleSelectSeat(seat)}
                    >
                      {seat.seatNo}
                      <p>&#8377;{seat.seatPrice}</p>
                    </button>
                  ))}
                </div>
              </div>
            </div>
          </div>
          <div>
            <button
              className="pay-button"
              onClick={(e) => handlePostTravellersAndSeats(e, value)}
            >
              Continue
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default BusSeatsSelection;
