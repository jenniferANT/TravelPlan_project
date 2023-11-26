import { Router, useHistory, Link, useParams } from "react-router-dom";
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
function PlaningDetail() {
  // Lay ID cua plan can render ra page detail
  const { planingDetailId } = useParams();
  // tu id lay ra data can để render
  const planingDatas = []
  const detail = planingDatas.find((plan) => plan.id===parseInt(planingDetailId));
  if(!detail) {
    return <div>Planing Not Found</div>
  }
  return (
    <div className="planing-detail">
      <Header />
      <div className="content">
        <div className="detail-map-heading">
          <img className="detail-map-heading__img" src={DetailMap} />
          <div className="detail-map-heading-info">
            <div className="detail-map-heading__name-container">
              <h2 className="detail-map-heading__name">
                Chuyến du lịch vũng tàu
              </h2>
            </div>
            <div className="detail-map-heading-info__container">
              <div className="detail-map-heading-info__destination">
                <img src={destination} />
                <p>Vũng tàu</p>
              </div>
              <div className="detail-map-heading-info__time">
                <div className="detail-map-heading-info__time__start">
                  <img src={time} />
                  <p>7h30</p>
                  <p>20/11/2023</p>
                </div>
                <div className="border-bottom"></div>
                <div className="detail-map-heading-info__time__end">
                  <p style={{ padding: "0 12px 0 35px" }}>21h00</p>
                  <p>21/11/2023</p>
                </div>
              </div>
              <div className="detail-map-heading-info__trip">
                <div className="detail-map-heading-info__distance">
                  <img src={distance} />
                  <p>100Km</p>
                </div>
                <div className="detail-map-heading-info__vehicle">
                  <img src={vehicle} />
                  <p>2 vehicle</p>
                </div>
              </div>
              <div className="detail-map-heading-info__person">
                <img src={person} />
                <p>4 people</p>
              </div>
              <div className="detail-map-heading-info__price">
                <div className="detail-map-heading-info__price__total">
                  <img src={price} />
                  <p>4.000.000 VND</p>
                </div>
                <p className="detail-map-heading-info__price__vehicel">
                  Vehicle: <p>90000 VND</p>
                </p>
                <p className="detail-map-heading-info__price__eat">
                  Eat:
                  <p> 160070 VND</p>
                </p>
                <p className="detail-map-heading-info__price__play">
                  Play:
                  <p>105000 VND</p>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div className="detail-container">
          <div className="detail-activity-item-container">
            <div className="detail-activity-item">
              <p>Ngày</p>
              <div className="detail-activity-day">
                <lable className="detail-activity-day__lable">1</lable>
              </div>
              <div className="detail-activity-main">
                {/* ${`{time} {date}`} */}
                <p>8:15 25/10/2023</p>
                <h4>TP.HCM - CÔN ĐẢO (Ăn trưa, chiều)</h4>
                <div className="detail-activity-main__content">
                  <img src={map} />
                  <p>
                    Hẻm đường Trần Phú Vũng Tàu là một con hẻm nhỏ nằm ở giữa
                    hai căn nhà số 107 và 109 trên đường Trần Phú, thuộc phường
                    5, thành phố Vũng Tàu. Hẻm này chỉ dài khoảng 100 mét nhưng
                    lại rất nổi tiếng, đặc biệt là đối với giới trẻ.
                  </p>
                </div>
              </div>
            </div>
            <div className="detail-activity-item">
              <p>Ngày</p>
              <div className="detail-activity-day">
                <lable className="detail-activity-day__lable">1</lable>
              </div>
              <div className="detail-activity-main">
                {/* ${`{time} {date}`} */}
                <p>8:15 25/10/2023</p>
                <h4>TP.HCM - CÔN ĐẢO (Ăn trưa, chiều)</h4>
                <div className="detail-activity-main__content">
                  <img src={map} />
                  <p>
                    Hẻm đường Trần Phú Vũng Tàu là một con hẻm nhỏ nằm ở giữa
                    hai căn nhà số 107 và 109 trên đường Trần Phú, thuộc phường
                    5, thành phố Vũng Tàu. Hẻm này chỉ dài khoảng 100 mét nhưng
                    lại rất nổi tiếng, đặc biệt là đối với giới trẻ.
                  </p>
                </div>
              </div>
            </div>
          </div>

          <img className="top-img-map" src={map} />
          <img className="bottom-img-plane" src={airplan} />
        </div>
        <div className="detail-btn-container">
          <button className="detail-btn-edit">
            <p>Edit this trip</p>
            <img src={editImg} />
          </button>
          <button className="detail-btn-apply">
            <p>Apply this trip</p>
            <img src={sendPlan} />
          </button>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default PlaningDetail;
