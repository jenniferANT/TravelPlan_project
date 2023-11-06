import { Router, useHistory, Link } from "react-router-dom";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import "./planing.scss";
import car from "./img/car.png";
import date from "./img/date.png";
import location from "./img/location.png";
import money from "./img/money.png";
import peopleImg from "./img/people.png";
import { earthImg } from "../Regis";
import mapImg from "./img/Map.png";
import searchImg from ".//img/searchImg.png";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import planMap from "./img/Frame 10.png";
import priceImg from "./img/priceImg.png";
import airPlane from "./img/Airplane.png";
import lugage from "./img/Luggage.png";
import applyImg from "./img/applyImg.png";
import distanceImg from "./img/quantiyLugage.png";

const destinations = ["khanh hoa", "TPHCM", "Ha Long", "phu quoc", "da lat"];

function Planing() {
  const currentDate = new Date();

  const [people, setPeoPle] = useState(0);
  const [price, setPrice] = useState(0);
  const [trans, setTrans] = useState("");
  const [destination, setDestination] = useState("Select destination");
  const [startDate, setStartDate] = useState(currentDate);
  const [endDate, setEndDate] = useState(currentDate);

  //show form planing item
  const [showForm, setShowForm] = useState(true);

  const plans = [
    {
      imgSrc: planMap,
      imgDes: "",
      imgPlane: airPlane,
      start: startDate,
      end: endDate,
    },
  ];

  useEffect(() => {}, [destination, startDate, endDate, people, price, trans]);

  function getPostition(position) {
    const { latitude, longitude } = position.coords;
    console.log(latitude);
    console.log(longitude);
  }

  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(getPostition);
    } else {
      console.log("Not supported");
    }
  }

  function handleSelectChangePeople(e) {
    setPeoPle(e.target.value);
  }
  function handleSelectChangePrice(e) {
    setPrice(e.target.value);
  }
  function handleSelectChangeTrans(e) {
    setTrans(e.target.value);
  }

  function handleSelectChangeDestination(e) {
    setDestination(e.target.value);
  }
  function handleShowPlan() {
    setShowForm(true);
  }
  return (
    <div className="planing">
      <Header />
      <div className="planing-content">
        <div className="planing-option">
          <div className="planing-option-container">
            <h3 className="planing-option-heading">
              Let's give us about your trip infomation
            </h3>
            <div className="planing-option-location">
              <div className="option-location__start planing-border-right">
                <label className="option-lable">Your Location</label>
                <button className="planing-btn" onClick={getLocation}>
                  Center Point,...
                </button>
                <img
                  className="planing-img planing-img-location"
                  src={location}
                />
              </div>
              <div className="option-location__end planing-border-right">
                <lable className="option-lable">Destination</lable>
                <select
                  className="planing-btn option-location__end-select"
                  onChange={handleSelectChangeDestination}
                >
                  {destinations.map((destination, index) => (
                    <option key={index} value={destination}>
                      {destination}
                    </option>
                  ))}
                </select>
                <img
                  className="planing-img planing-img-destination"
                  src={location}
                />
              </div>
              <div className="option-date__start planing-border-right">
                <label className="option-lable">Beginning Date</label>
                <DatePicker
                  selected={startDate}
                  onChange={(date) => setStartDate(date)}
                  dateFormat="dd/MM/yyyy"
                  placeholderText="Select start date"
                  className="planing-btn"
                />
                <img className="planing-img planing-img-start" src={date} />
              </div>
              <div className="option-date__end">
                <label className="option-lable">Ending Date</label>
                <DatePicker
                  selected={endDate}
                  onChange={(date) => setEndDate(date)}
                  dateFormat="dd/MM/yyyy"
                  placeholderText="Select end date"
                  className="planing-btn"
                />
                <img className="planing-img planing-img-end" src={date} />
              </div>
            </div>
            <div className="planing-option-detail">
              <div className="option-people-container">
                <img src={peopleImg} />
                <select
                  className="option-people"
                  value={people}
                  onChange={handleSelectChangePeople}
                >
                  <option value="">Chọn</option>
                  <option value="option1">1 People</option>
                  <option value="option2">2 People</option>
                  <option value="option3">3 People</option>
                  <option value="option3">4 People</option>
                </select>
              </div>
              <div className="option-price-container">
                <img src={money} />
                <select
                  className="option-price"
                  value={price}
                  onChange={handleSelectChangePrice}
                >
                  <option value="">Chọn</option>
                  <option value="option1">1.000.000</option>
                  <option value="option2">2.000.000</option>
                  <option value="option3">3.000.000</option>
                  <option value="option3">4.000.000</option>
                </select>
                <p className="option-price-lable">/People</p>
              </div>
              <div className="option-trans-container">
                <img src={car} />
                <select
                  className="option-trans"
                  value={trans}
                  onChange={handleSelectChangeTrans}
                >
                  <option value=""></option>
                  <option value="option1">Car</option>
                  <option value="option2">Plane</option>
                  <option value="option3">Train</option>
                  <option value="option3">Motor Bike</option>
                </select>
              </div>
            </div>
            <div className="planing-input-box">
              <input className="planing-input" />
            </div>
          </div>

          <button className="planing-search-btn" onClick={handleShowPlan}>
            Search Your Trip
            <img src={searchImg} />
          </button>
          <img className="planing-img-earth" src={earthImg} />
          <img className="planing-img-map" src={mapImg} />
        </div>

        {showForm && (
          <div className="planing-form">
            <h1 className="planing-form-heading">Planing For Your Trip</h1>
            <div className="planing-form-item">
              <div className="form-choose-content">
                <div className="form-choose-content-plan">
                  <div className="content-plan-row">
                    <div className="form-choose-map__container">
                      <img src={planMap} />
                    </div>
                    <div className="content-row-details">
                      <div className="content-row-details-activity">
                        <div className="row-details-activity-items">
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <div className="content-row-label-container">
                              <label className="content-row-1">1</label>
                            </div>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <div className="content-row-label-container">
                              <label className="content-row-1">2</label>
                            </div>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <div className="content-row-label-container">
                              <label className="content-row-1">3</label>
                            </div>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <div className="content-row-label-container">
                              <label className="content-row-1">3</label>
                            </div>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <div className="content-row-label-container">
                              <label className="content-row-1">1</label>
                            </div>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                        </div>
                        <div className="form-choose-content-info">
                          <div className="info-price">
                            <p>400.000/</p>
                            <img src={priceImg} />
                          </div>
                          <div className="info-weight">
                            <p>200km</p>
                            <img src={distanceImg} />
                          </div>
                        </div>
                      </div>
                    </div>
                    <img src={airPlane} className="content-plan-row-img" />
                  </div>
                  <div className="row-btn-container">
                    <Link className="row-btn-link" to="/planing-detail">More Detail</Link>
                    <button>
                      Apply this trip
                      <img src={applyImg} />
                    </button>
                  </div>
                </div>
                <div className="form-choose-content-plan">
                  <div className="content-plan-row">
                    <div className="form-choose-map__container">
                      <img src={planMap} />
                    </div>
                    <div className="content-row-details">
                      <div className="content-row-details-activity">
                        <div className="row-details-activity-items">
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <label className="content-row-1">1</label>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <label className="content-row-1">2</label>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                          <div className="content-plan-day-container">
                            <h4 className="content-plan-startDay">
                              {startDate.toLocaleDateString("en-GB")}
                            </h4>
                          </div>
                          <div className="row-details-activity-item">
                            <p className="content-row-day">Ngày</p>
                            <label className="content-row-1">3</label>

                            <h2 className="content-plan-startDay-main">
                              TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
                            </h2>
                          </div>
                        </div>
                        <div className="form-choose-content-info">
                          <div className="info-price">
                            <p>400.000/</p>
                            <img src={priceImg} />
                          </div>
                          <div className="info-weight">
                            <p>200km</p>
                            <img src={distanceImg} />
                          </div>
                        </div>
                      </div>
                    </div>
                    <img src={airPlane} className="content-plan-row-img" />
                  </div>
                  <div className="row-btn-container">
                    <Link to="/planing-detail">More Detail</Link>
                    <button>
                      Apply this trip
                      <img src={applyImg} />
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>

      <Footer />
    </div>
  );
}

export default Planing;
