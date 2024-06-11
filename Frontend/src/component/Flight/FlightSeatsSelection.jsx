import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./FlightSeatsSelection.css";

function FlightSeatsSelection() {
  const { value } = useParams();
  const navigate = useNavigate();
  const [flight, setFlight] = useState(null);
  const [seats, setSeats] = useState([]);
  const [economySeats, setEconomySeats] = useState([]);
  const [businessSeats, setBusinessSeats] = useState([]);
  const [firstclassSeats, setFirstclassSeats] = useState([]);
  const [selectedSeats, setSelectedSeats] = useState([]);
  const [travellers, setTravellers] = useState([]);
  const [postTravellers, setPostTravellers] = useState([]);
  const config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("accesstoken")}`,
    },
  };

  useEffect(() => {
    async function fetchFlight() {
      try {
        const response = await axios.get(
          `http://localhost:8080/CS/Flight/getbyid/${value}`,
          config
        );
        setFlight(response.data);
        setSeats(response.data.flightSeats);

        const firstClass = response.data.flightSeats.filter(
          (seat) => seat.seatClass === "firstclass"
        );
        const business = response.data.flightSeats.filter(
          (seat) => seat.seatClass === "business"
        );
        const economy = response.data.flightSeats.filter(
          (seat) => seat.seatClass === "economy"
        );

        setFirstclassSeats(firstClass);
        setBusinessSeats(business);
        setEconomySeats(economy);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }

    fetchFlight();
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

  const handlePostTravellersAndSeats = async (e, flightid) => {
    e.preventDefault();
    const ftfs = {
      travellers: postTravellers,
      flightSeats: selectedSeats,
    };
    try {
      const response = await axios.post(
        `http://localhost:8080/CS/Flight/bookflight/${flightid}/${localStorage.getItem(
          `username`
        )}`,
        ftfs,
        config
      );
      console.log(response.data);
      navigate(`/flightBookingDetails/${response.data.flightBookingId}`);
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

  function handleSelectSeat(seat) {
    if (!seat.available) return;

    setSelectedSeats((prevSelectedSeats) => {
      if (prevSelectedSeats.includes(seat)) {
        return prevSelectedSeats.filter((s) => s !== seat);
      } else {
        return [...prevSelectedSeats, seat];
      }
    });
  }

  return (
    <div className="fss">
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

          <div className="plane">
            <div id="display">
              <div>
                <p id="green"></p>
                <p>Free</p>
              </div>
              <div>
                <p id="blue"></p>
                <p>Selected</p>
              </div>
              <div>
                <p id="red"></p>
                <p>Booked</p>
              </div>
            </div>
            <img src="../src/assets/FlightLogo/Front.png" alt="Front" />
            <div className="body">
              <p id="class">FIRST CLASS</p>
              <div className="firstclassSeats">
                {firstclassSeats.map((firstclassSeat, index) => (
                  <div
                    key={index}
                    className="fcs"
                    style={{
                      backgroundColor: firstclassSeat.available
                        ? selectedSeats.includes(firstclassSeat)
                          ? "#1f78b4"
                          : "#4bad57"
                        : "#df5656",
                    }}
                  >
                    <button onClick={() => handleSelectSeat(firstclassSeat)}>
                      {firstclassSeat.seatNumber}
                    </button>
                  </div>
                ))}
              </div>
              <p id="class">BUSINESS</p>
              <div className="businessSeats">
                {businessSeats.map((businessSeat, index) => (
                  <div
                    key={index}
                    className="bs"
                    style={{
                      backgroundColor: businessSeat.available
                        ? selectedSeats.includes(businessSeat)
                          ? "#1f78b4"
                          : "#4bad57"
                        : "#df5656",
                    }}
                  >
                    <button onClick={() => handleSelectSeat(businessSeat)}>
                      {businessSeat.seatNumber}
                    </button>
                  </div>
                ))}
              </div>
              <p id="class">ECONOMY</p>
              <div className="economySeats">
                {economySeats.map((economySeat, index) => (
                  <div
                    key={index}
                    className="es"
                    style={{
                      backgroundColor: economySeat.available
                        ? selectedSeats.includes(economySeat)
                          ? "#1f78b4"
                          : "#4bad57"
                        : "#df5656",
                    }}
                  >
                    <button onClick={() => handleSelectSeat(economySeat)}>
                      {economySeat.seatNumber}
                    </button>
                  </div>
                ))}
              </div>
            </div>
            <img src="../src/assets/FlightLogo/Tail.png" alt="Tail" />
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

export default FlightSeatsSelection;
