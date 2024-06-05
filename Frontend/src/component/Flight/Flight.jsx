import React, { useEffect, useState } from "react";
import axios from "axios";
import Tabs from "../Tabs/Tabs.jsx";
function Flight() {
  const [orgin, setOrgin] = useState([]);
  useEffect(() => {
    async function fetchCityNames() {
      try {
        const response = await axios.get(
          "http://localhost:8086/Flight/getallcitynames"
        );

        console.log(response.data);
      } catch (error) {
        console.log(error.response.data.message);
      }
    }
    fetchCityNames();
  }, []);
  return (
    <div>
      <Tabs />
      {/* <div>
        <form className="formclass" onSubmit={handleSubmit}>
          <div className="divclass">
            <label htmlFor="from">From:</label>
            <select
              className="from"
              id="from"
              name="from"
              value={formData.from}
              onChange={handleChange}
            >
              <option value="">Select city name</option>
              {cityNames.map((cityName, index) => (
                <option key={index} value={cityName}>
                  {cityName}
                </option>
              ))}
            </select>
          </div>
          <div className="divclass">
            <label htmlFor="checkin">Check-In:</label>
            <input
              type="date"
              name="checkin"
              id="checkin"
              value={formData.checkin}
              onChange={handleChange}
            />
          </div>
          <div className="divclass">
            <label htmlFor="checkout">Check-Out:</label>
            <input
              type="date"
              name="checkout"
              id="checkout"
              value={formData.checkout}
              onChange={handleChange}
            />
          </div>
          <div className="divclass">
            <label htmlFor="roomType">RoomType:</label>
            <select
              className="roomselect"
              id="roomType"
              name="roomType"
              value={formData.roomType}
              onChange={handleChange}
            >
              <option value="">Select Room Type</option>
              <option value="Single_Room">Single Room</option>
              <option value="Standard_Twin_Room">Standard Twin Room</option>
              <option value="Standard_Double_Room">Standard Double Room</option>
              <option value="Deluxe_Double_Room">Deluxe Double Room</option>
              <option value="Triple_room">Triple room</option>
              <option value="Quad_room">Quad room</option>
              <option value="Suite">Suite</option>
            </select>
          </div>
          <div>
            <button type="submit">Search</button>
          </div>
        </form>
      </div> */}
    </div>
  );
}

export default Flight;
