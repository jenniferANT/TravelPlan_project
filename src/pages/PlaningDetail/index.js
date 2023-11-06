import { Router, useHistory, Link } from "react-router-dom";
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
function PlaningDetail() {
  return (
    <div className="planing-detail">
      <Header />
      <div className="content">
        <div className="detail-map-heading">
          <img src={DetailMap} />
        </div>
        <div className="detail-container">
          <div className="detail-activity-item-container">
            <div className="detail-activity-item">
              <p>Ngày</p>
              <div className="detail-activity-day">
                <lable className="detail-activity-day__lable">1</lable>
              </div>
              <div className="detail-activity-main">
                <p>25/10/2023</p>
                <h4>TP.HCM - CÔN ĐẢO (Ăn trưa, chiều)</h4>
              </div>
            </div>
            <div className="detail-activity-item">
              <p>Ngày</p>
              <div className="detail-activity-day">
                <lable className="detail-activity-day__lable">1</lable>
              </div>
              <div className="detail-activity-main">
                <p>25/10/2023</p>
                <h4>TP.HCM - CÔN ĐẢO (Ăn trưa, chiều)</h4>
              </div>
            </div>
            <div className="detail-activity-item">
              <p>Ngày</p>
              <div className="detail-activity-day">
                <lable className="detail-activity-day__lable">1</lable>
              </div>
              <div className="detail-activity-main">
                <p>25/10/2023</p>
                <h4>TP.HCM - CÔN ĐẢO (Ăn trưa, chiều)</h4>
              </div>
            </div>
          </div>

          <div className="detail-info">
            <div className="detail-info-price">
              <p>400.000/</p>
              <img src={priceImg} />
            </div>
            <div className="detail-info-weight">
              <p>200km</p>
              <img src={quantityLugage} />
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
