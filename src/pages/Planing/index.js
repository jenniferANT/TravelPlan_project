import { Router, useHistory, Link } from "react-router-dom";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import Select from "react-select";
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
import { format } from "date-fns";
import { useSelector, useDispatch } from "react-redux";

const time = [
  "03:00",
  "03:30",
  "04:00",
  "04:30",
  "05:00",
  "05:30",
  "06:00",
  "06:30",
  "07:00",
  "07:30",
  "08:00",
  "08:30",
  "09:00",
  "09:30",
  "10:00",
  "10:30",
  "11:00",
  "11:30",
  "12:00",
  "12:30",
  "13:00",
  "13:30",
  "14:00",
  "14:30",
  "15:00",
  "15:30",
  "16:00",
  "16:30",
  "17:00",
  "17:30",
  "18:00",
  "18:30",
  "19:00",
  "19:30",
  "20:00",
  "20:30",
  "21:00",
  "21:30",
  "22:00",
  "22:30",
  "23:00",
  "23:30",
];

function Planing() {
  const currentDate = new Date();
  const [error, setError] = useState(null);

  const [listVehicle, setListVehicle] = useState([]);
  const [listDestination, setListDestination] = useState([]);
  const [selectedCategories, setSelectedCategories] = useState([]);
  const [listCategories, setListCategories] = useState([]);

  const [latitude, setLatitude] = useState(0);
  const [longitude, setLongitude] = useState(0);
  const [destination, setDestination] = useState("Select destination");
  const [numberPeople, setNumberPeople] = useState(0);
  const [expense, setExpense] = useState(0);
  const [vehicleId, setVehicleId] = useState(1);

  const [timeBeginDate, setTimeBeginDate] = useState("06:00");
  const [timeEndDate, setTimeEndDate] = useState("18:00");
  const [beginDate, setBeginDate] = useState("");
  const [endDate, setEndDate] = useState("");

  //show form planing item
  const [showForm, setShowForm] = useState(true);
  const [planGenerate, setPlanGenerate] = useState(null);
  const currentUser = useSelector((state) => state.auth.login.currentUser);

  useEffect(() => {
    fetch("http://localhost:8081/api/v1/category/category-child/name?name=area")
      .then((res) => res.json())
      .then(
        (result) => {
          setListDestination(result);
        },
        (error) => {
          setError(error);
        }
      );
  }, []);
  useEffect(() => {
    fetch("http://localhost:8081/api/v1/category/category-child/name?name=like")
      .then((res) => res.json())
      .then(
        (result) => {
          setListCategories(result);
        },
        (error) => {
          setError(error);
        }
      );
  }, []);
  const options = listCategories.map((item) => {
    return {
      value: item.id,
      label: item.name,
    };
  });

  useEffect(() => {
    fetch("http://localhost:8081/api/v1/vehicle")
      .then((res) => res.json())
      .then(
        (result) => {
          setListVehicle(result);
        },
        (error) => {
          setError(error);
        }
      );
  }, []);

  useEffect(() => {}, [destination, vehicleId, beginDate, endDate]);

  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(getPostition);
    } else {
      console.log("Not supported");
    }
  }

  function getPostition(position) {
    const { latitude, longitude } = position.coords;
    setLatitude(latitude);
    setLongitude(longitude);

    console.log("latitude " + latitude);
    console.log("longitude " + longitude);
  }

  function handleSelectChangePeople(e) {
    setNumberPeople(e.target.value);
  }
  function handleSelectChangePrice(e) {
    setExpense(e.target.value);
  }
  function handleSelectChangeTrans(e) {
    setVehicleId(e.target.value);
  }
  function handleSelectChangeDestination(e) {
    setDestination(e.target.value);
  }
  function handleSelectChangeBeginTime(e) {
    setTimeBeginDate(e.target.value);
  }
  function handleSelectChangeEndTime(e) {
    setTimeEndDate(e.target.value);
  }
  function handleSelectDateBegin(e) {
    setBeginDate(e.target.value);
  }
  function handleSelectDateEnd(e) {
    setEndDate(e.target.value);
  }

  const generate = async (event) => {
    event.preventDefault();

    const categoryId = selectedCategories.map((item) => {
      return {
        id: item.value,
      };
    });

    const plan = {
      locationLatitude: latitude,
      locationLongitude: longitude,
      destination: destination,
      numberPeople: numberPeople,
      expense: expense,
      vehicleId: vehicleId,
      categoryId: categoryId,
      beginDate: timeBeginDate + " " + beginDate,
      endDate: timeEndDate + " " + endDate,
    };

    console.log(plan);

    try {
      if(false) {
      let response = await axios.post(
        "http://localhost:8081/api/v1/plan",
        plan,
        {
          Authorization: `Bearer ${currentUser.token}`,
          "Content-Type": "application/json",
        }
      );
    setPlanGenerate((plan) => [response.data, ...plan]);
    console.log(plan);
    } else {
      let response = await axios.post(
        "http://localhost:8081/api/v1/plan",
        plan
      );
      console.log(response.data);
    }
    } catch(err) {
      console.log(err.message);
    }
    
    
  };

  return (
    <div className="planing">
      <Header />
      <div className="planing-content">
        <div className="planing-option-container">
          <h3 className="planing-option-heading">
            Let's give us about your trip infomation
          </h3>
          <div className="planing-option-location">
            {/* địa điểm người dùng */}
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

            {/* dịa điểm đến  */}
            <div className="option-location__end planing-border-right">
              <lable className="option-lable">Destination</lable>
              <select
                className="planing-btn option-location__end-select"
                onChange={handleSelectChangeDestination}
              >
                {listDestination.map((destination) => (
                  <option key={destination.id} value={destination.name}>
                    {destination.name}
                  </option>
                ))}
              </select>
              <img
                className="planing-img planing-img-destination"
                src={location}
              />
            </div>

            {/* chọn ngày bắt đầu */}
            <div className="option-date__start planing-border-right">
              <label className="option-lable">Beginning Date</label>
              <select
                className="planing-btn option-location__end-select"
                onChange={handleSelectChangeBeginTime}
              >
                {time.map((time) => (
                  <option key={time} value={time}>
                    {time}
                  </option>
                ))}
              </select>

              {/* ------------------------------- */}
              <input
                className="input-date"
                type="date"
                data-date=""
                data-date-format="DD-MM-YYYY"
                onChange={handleSelectDateBegin}
              />

              {/* ----------------------------------- */}
              {/* <img className="planing-img planing-img-start" src={date} /> */}
            </div>

            {/* chọn ngày kết thúc */}
            <div className="option-date__end">
              <label className="option-lable">Ending Date</label>
              <select
                className="planing-btn option-location__end-select"
                onChange={handleSelectChangeEndTime}
              >
                {time.map((time) => (
                  <option key={time} value={time}>
                    {time}
                  </option>
                ))}
              </select>

              <input
              className="input-date"
                type="date"
                data-date=""
                data-date-format="DD-MM-YYYY"
                onChange={handleSelectDateEnd}
              />

              {/* <img className="planing-img planing-img-end" src={date} /> */}
            </div>
          </div>

          <div className="planing-option-detail">
            {/* chọn người  */}
            <div className="option-people-container">
              <img src={peopleImg} />
              <select
                className="option-people"
                value={numberPeople}
                onChange={handleSelectChangePeople}
              >
                <option value="">Chọn</option>
                <option value="1">1 People</option>
                <option value="2">2 People</option>
                <option value="3">3 People</option>
                <option value="4">4 People</option>
              </select>
            </div>

            {/* chọn giá trên người */}
            <div className="option-price-container">
              <img src={money} />
              <select
                className="option-price"
                value={expense}
                onChange={handleSelectChangePrice}
              >
                <option value="">Chọn</option>
                <option value="200000">200.000</option>
                <option value="300000">300.000</option>
                <option value="500000">500.000</option>
                <option value="800000">800.000</option>
                <option value="1000000">1.000.000</option>
                <option value="1500000">1.500.000</option>
                <option value="2000000">2.000.000</option>
                <option value="3000000">3.000.000</option>
                <option value="5000000">5.000.000</option>
              </select>
              <p className="option-price-lable">/People</p>
            </div>

            {/* chọn phương tiện  */}
            <div className="option-trans-container">
              <img src={car} />
              <select
                className="option-trans"
                value={vehicleId}
                onChange={handleSelectChangeTrans}
              >
                {listVehicle.map((vehicle) => (
                  <option key={vehicle.id} value={vehicle.id}>
                    {vehicle.name}
                  </option>
                ))}
              </select>
            </div>
          </div>

          {/* chọn danh mục */}
          <div className="option-category-like">
            <Select
              onChange={(item) => setSelectedCategories(item)}
              isMulti
              options={options}
            />
          </div>

          <button onClick={generate} className="planing-search-btn">
            Search Your Trip
            <img src={searchImg} />
          </button>

          <img className="planing-img-map" src={mapImg} />
        </div>

        {showForm ? (
          <h1>{planGenerate}</h1>
        ) : (
          // <div className="planing-form">
          //   <h1 className="planing-form-heading">Planing For Your Trip</h1>
          //   <div className="planing-form-item">
          //     <div className="form-choose-content">
          //       <div className="form-choose-content-plan">
          //         <div className="content-plan-row">
          //           <div className="form-choose-map__container">
          //             <img src={planMap} />
          //           </div>
          //           <div className="content-row-details">
          //             <div className="content-row-details-activity">
          //               <div className="row-details-activity-items">
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <div className="content-row-label-container">
          //                     <label className="content-row-1">1</label>
          //                   </div>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <div className="content-row-label-container">
          //                     <label className="content-row-1">2</label>
          //                   </div>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <div className="content-row-label-container">
          //                     <label className="content-row-1">3</label>
          //                   </div>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <div className="content-row-label-container">
          //                     <label className="content-row-1">3</label>
          //                   </div>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <div className="content-row-label-container">
          //                     <label className="content-row-1">1</label>
          //                   </div>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //               </div>
          //               <div className="form-choose-content-info">
          //                 <div className="info-price">
          //                   <p>400.000/</p>
          //                   <img src={priceImg} />
          //                 </div>
          //                 <div className="info-weight">
          //                   <p>200km</p>
          //                   <img src={distanceImg} />
          //                 </div>
          //               </div>
          //             </div>
          //           </div>
          //           <img src={airPlane} className="content-plan-row-img" />
          //         </div>
          //         <div className="row-btn-container">
          //           <Link className="row-btn-link" to="/planing-detail">
          //             More Detail
          //           </Link>
          //           <button>
          //             Apply this trip
          //             <img src={applyImg} />
          //           </button>
          //         </div>
          //       </div>
          //       <div className="form-choose-content-plan">
          //         <div className="content-plan-row">
          //           <div className="form-choose-map__container">
          //             <img src={planMap} />
          //           </div>
          //           <div className="content-row-details">
          //             <div className="content-row-details-activity">
          //               <div className="row-details-activity-items">
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <label className="content-row-1">1</label>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <label className="content-row-1">2</label>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //                 <div className="content-plan-day-container">
          //                   <h4 className="content-plan-startDay">
          //                     {startDate.toLocaleDateString("en-GB")}
          //                   </h4>
          //                 </div>
          //                 <div className="row-details-activity-item">
          //                   <p className="content-row-day">Ngày</p>
          //                   <label className="content-row-1">3</label>

          //                   <h2 className="content-plan-startDay-main">
          //                     TP.HCM - CÔN ĐẢO (Ăn trưa,chiều)
          //                   </h2>
          //                 </div>
          //               </div>
          //               <div className="form-choose-content-info">
          //                 <div className="info-price">
          //                   <p>400.000/</p>
          //                   <img src={priceImg} />
          //                 </div>
          //                 <div className="info-weight">
          //                   <p>200km</p>
          //                   <img src={distanceImg} />
          //                 </div>
          //               </div>
          //             </div>
          //           </div>
          //           <img src={airPlane} className="content-plan-row-img" />
          //         </div>
          //         <div className="row-btn-container">
          //           <Link className="row-btn-link" to="/planing-detail">
          //             More Detail
          //           </Link>
          //           <button>
          //             Apply this trip
          //             <img src={applyImg} />
          //           </button>
          //         </div>
          //       </div>
          //     </div>
          //   </div>
          // </div>
          <p>User is not logged in.</p>
        )}
      </div>

      <Footer />
    </div>
  );
}

export default Planing;
