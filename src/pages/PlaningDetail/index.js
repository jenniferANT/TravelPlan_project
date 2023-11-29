import { Router, useHistory, Link, useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import "./PlaningDetail.scss";
import airplan from "./img/Airplane.png";
import DetailMap from "./img/DetailMao.png";
import editImg from "./img/editImg.png";
import sendPlan from "./img/sendPlan.png";
import map from "./img/Map.png";
import priceImg from "./img/priceImg.png";
import quantityLugage from "./img/quantiyLugage.png";
import destination from "./img/destination.png";
import time from "./img/time.png";
import distance from "./img/distance.png";
import vehicle from "./img/vehicle.png";
import person from "./img/person.png";
import price from "./img/price.png";
import { handleAddToCart } from "../../service/UserApi";

function PlaningDetail() {
  const params = useParams();
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [plan, setPlan] = useState(null);
  const [planItem, setPlanItem] = useState(null);

  useEffect(() => {
    if (params && params.id) {
      fetch(`http://localhost:8081/api/v1/plan/${params.id}`)
        .then((res) => res.json())
        .then(
          (result) => {
            setIsLoaded(true);
            setPlan(result);
            setPlanItem(result.planItems);
          },
          (error) => {
            setIsLoaded(true);
            setError(error);
          }
        );
    }
  }, []);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <>
        <div className="planing-detail">
          <Header />
          <div className="content">
            <div className="detail-map-heading">
              <img className="detail-map-heading__img" src={DetailMap} />
              <div className="detail-map-heading-info">
                <div className="detail-map-heading__name-container">
                  <h2 className="detail-map-heading__name">{plan.title}</h2>
                </div>
                <div className="detail-map-heading-info__container">
                  <div className="detail-map-heading-info__destination">
                    <img src={destination} />
                    <p>{plan.destination}</p>
                  </div>
                  <div className="detail-map-heading-info__time">
                    <div className="detail-map-heading-info__time__start">
                      <img src={time} />
                      <p>{plan.beginDate}</p>
                    </div>
                    <div className="border-bottom"></div>
                    <div className="detail-map-heading-info__time__end">
                      <p>{plan.endDate}</p>
                    </div>
                  </div>
                  <div className="detail-map-heading-info__trip">
                    <div className="detail-map-heading-info__distance">
                      <img src={distance} />
                      <p>{plan.distance}Km</p>
                    </div>
                    <div className="detail-map-heading-info__vehicle">
                      <img src={vehicle} />
                      <p>
                        {plan.numberVehicle} {plan.vehicle.name}
                      </p>
                    </div>
                  </div>
                  <div className="detail-map-heading-info__person">
                    <img src={person} />
                    <p>{plan.numberPeople} people</p>
                  </div>
                  <div className="detail-map-heading-info__price">
                    <div className="detail-map-heading-info__price__total">
                      <img src={price} />
                      <p>{plan.expense} VND/people</p>
                    </div>
                    <p className="detail-map-heading-info__price__vehicel">
                      Vehicle: <p>{plan.costVehicle} VND</p>
                    </p>
                    <p className="detail-map-heading-info__price__eat">
                      Eat:
                      <p> {plan.costEat} VND</p>
                    </p>
                    <p className="detail-map-heading-info__price__play">
                      Play:
                      <p>{plan.costPlay} VND</p>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className="detail-container">
              <div className="detail-activity-item-container">
                {planItem.map((item) => (
                  <div className="detail-activity-item">
                    <p>Ngày</p>
                    <div className="detail-activity-day">
                      <lable className="detail-activity-day__lable">1</lable>
                    </div>
                    <div className="detail-activity-main">
                      {/* ${`{time} {date}`} */}
                      <p>{item.startTime}</p>
                      <h4>{item.title}</h4>
                      <div className="detail-activity-main__content">
                        <img src={map} />
                        <p>{item.content}</p>
                      </div>
                    </div>
                  </div>
                ))}
              </div>

              <img className="top-img-map" src={map} />
              <img className="bottom-img-plane" src={airplan} />
            </div>
            <div className="detail-btn-container">
              <button className="detail-btn-edit">
                <p>Edit this trip</p>
                <img src={editImg} />
              </button>
              <button
                className="detail-btn-apply"
                onClick={() => handleAddToCart(plan.id)}
              >
                <p>Apply this trip</p>
                <img src={sendPlan} />
              </button>
            </div>
          </div>
          <Footer />
        </div>
      </>
    );
  }
}

export default PlaningDetail;
