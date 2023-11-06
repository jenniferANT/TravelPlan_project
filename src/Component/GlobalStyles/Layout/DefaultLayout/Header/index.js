import { Router, useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
import Regis from "../../../../../pages/Regis";
import Login from "../../../../../pages/Login";
import "./header.scss";
import logo from "./img/Union.png";
import airplane from "./img/airplane.png";
import earthSvg from "./img/earth.svg";
import image from "./img/Image.png";
import map from "./img/map.png";
import search from "./img/search.png";

function Header() {
  return (
    <div className="content-header">
      <div className="header ">
        <div className="header-heading-left">
          <Link className="reset-link" to="/">
            <div className="logo">
              <img className="logo-img" src={logo} />
              <p className="logo-name font">TravelPlan</p>
            </div>
          </Link>

          <button className="header-home">
            <Link className="font header__link-left header__link-hover" to="/">
              Home
            </Link>
          </button>

          <button className="header-planing">
            <Link className="font header__link-left header__link-hover" to="/planing">
              Planning
            </Link>
          </button>

          <button className="header-discover">
            <Link className="font header__link-left header__link-hover" to="/discover">
              Discover
            </Link>
          </button>

          <button className="header-blog">
            <Link className="font header__link-left header__link-hover" to="/blog">
              Blog
            </Link>
          </button>
        </div>
        <div className="header-heading-right">
          <button className="heading-right-button">
            <Link className="font header__link-right " to="/login">Log in now</Link>
            <img src={airplane} alt="icon-airPlane" />
          </button>
        </div>
      </div>
    </div>
  );
}

export default Header;
