import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./BusSeatsSelection.css";

function BusSeatsSelection() {
  const { value } = useParams();
  const navigate = useNavigate();
  const [bus, setBus] = useState(null);
  const [seats, setSeats] = useState([]);
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
        setSeats(response.data.busSeats);
        console.log(response.data);
        setLowerSleeper(
          response.data.busSeats.filter(
            (seat) => seat.seatType === "lowerSleeper"
          )
        );
        setLowerSeat(
          response.data.busSeats.filter((seat) => seat.seatType === "lowerSeat")
        );
        setUpperSleeper(
          response.data.busSeats.filter(
            (seat) => seat.seatType === "upperSleeper"
          )
        );
        setUpperDoubleSleeper(
          response.data.busSeats.filter(
            (seat) => seat.seatType === "upperDoubleSleeper"
          )
        );
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
        `http://localhost:8080/CS/Bus/bookbus/${busid}/${localStorage.getItem(
          "username"
        )}`,
        btbs,
        config
      );
      console.log(response.data);
      navigate(`/busBookingDetails/${bus.busId}`);
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
              <div>
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
                            : "#grey",
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
                            : "#grey",
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
                          : "#grey",
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
                          : "#grey",
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
