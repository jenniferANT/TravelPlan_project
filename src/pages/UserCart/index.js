import { Router, useHistory, Link } from "react-router-dom";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import "./usercart.scss";

const images = {
  moneyI: require("./img/moneyI.png"),
  locationI: require("./img/locationI.png"),
  timeI: require("./img/timeI.png"),
};
function UserCart() {

    function handleClick() {
        
    }

  return (
    <div className="user-cart">
      <Header />
      <div className="cart-app">
        <div className="cart-container">
          <h3 className="cart__username">Nguyen huyen duyen's cart</h3>
         {/* {`/planing-detail/${planingDetailId}`} */}
          <Link className="cart-item" to={`/planing-detail`}>
            <div className="cart-item-info">
              <div className="cart-item-info-col1">
                <h4 className="cart-item-info__title">Ha Long Bay Traveling</h4>
                <p className="cart-item-info__price">
                    <img src={images.moneyI}/>
                    4000000</p>
              </div>
              <div className="cart-item-info-col2">
                <h5 className="cart-item-info__name">
                    <img src={images.locationI}/>
                    Ha Long Bay</h5>
                <p className="cart-item-info__date">
                    <img src={images.timeI}/>
                    10/10/2023 - 10/11/2023
                </p>
              </div>
            </div>
            <div className="cart-item-time">
                <p>23/09/2023</p>
            </div>
          </Link>
          <Link className="cart-item" onClick={handleClick}>
            <div className="cart-item-info">
              <div className="cart-item-info-col1">
                <h4 className="cart-item-info__title">Ha Long Bay Traveling</h4>
                <p className="cart-item-info__price">
                    <img src={images.moneyI}/>
                    4000000</p>
              </div>
              <div className="cart-item-info-col2">
                <h5 className="cart-item-info__name">
                    <img src={images.locationI}/>
                    Ha Long Bay</h5>
                <p className="cart-item-info__date">
                    <img src={images.timeI}/>
                    10/10/2023 - 10/11/2023
                </p>
              </div>
            </div>
            <div className="cart-item-time">
                <p>23/09/2023</p>
            </div>
          </Link>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default UserCart;
