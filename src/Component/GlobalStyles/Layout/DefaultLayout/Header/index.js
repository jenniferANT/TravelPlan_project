import { Router, useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

import { useState } from "react";
import { logOut } from "../../../../../redux/apiRequest";

import Regis from "../../../../../pages/Regis";
import Login from "../../../../../pages/Login";
import "./header.scss";
import logo from "./img/Union.png";
import airplane from "./img/airplane.png";
import earthSvg from "./img/earth.svg";
import image from "./img/Image.png";
import map from "./img/map.png";
import search from "./img/search.png";
import authSlice from "../../../../../redux/authSlice";
import cart from "./img/cart.png";
import profile from "./img/profile.png";
import logout from "./img/logout.png";
import down from "./img/down.png";
import history from "./img/history (1).png";
import favorite from "./img/favorite.png";
function Header() {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const currentUser = useSelector((state) => state.auth.user);
  

  function handleLogout() {
    console.log("logout");
    logOut(dispatch, navigate)
  }
  function toggleDropdown() {
    setIsDropdownOpen(!isDropdownOpen);
  }
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
            <Link
              className="font header__link-left header__link-hover"
              to="/planing"
            >
              Planning
            </Link>
          </button>

          <button className="header-discover">
            <Link
              className="font header__link-left header__link-hover"
              to="/discover"
            >
              Discover
            </Link>
          </button>

          <button className="header-blog">
            <Link
              className="font header__link-left header__link-hover"
              to="/blog"
            >
              Blog
            </Link>
          </button>
        </div>
        <div className="header-heading-right">
          {currentUser ? (
            <div className="current-user">
              <img className="current-user-avt" src={currentUser.avt} />
              <p className="current-user-name">{currentUser.name}</p>
              <button onClick={toggleDropdown} className="current-user-button">
                <img src={down} />
                {isDropdownOpen && (
                  <div className="dropdown-menu">
                    <ul>
                      <li>
                        <img src={profile} />
                        <Link className="reset-link" to="/user">
                          <p>Information</p>
                        </Link>
                      </li>
                      <li>
                        <img src={cart} />
                        <Link className="reset-link">
                          <p>Your Cart</p>
                        </Link>
                      </li>
                      <li>
                        <img src={favorite} />
                        <Link className="reset-link">
                          <p>Your Favorite</p>
                        </Link>
                      </li>
                      <li>
                        <img src={history} />
                        <Link className="reset-link">
                          <p>Your History</p>
                        </Link>
                      </li>
                      <li>
                        <img src={logout} />
                        <button className="logout-btn" onClick={handleLogout}>Log out</button>
                      </li>
                    </ul>
                  </div>
                )}
              </button>
            </div>
          ) : (
            <button className="heading-right-button">
              <Link className="font header__link-right " to="/login">
                Log in now
              </Link>
              <img src={airplane} alt="icon-airPlane" />
            </button>
          )}

          {/* <button className="heading-right-button">
              <Link className="font header__link-right " to="/login">Log in now</Link>
              <img src={airplane} alt="icon-airPlane" />
            </button> */}
        </div>
      </div>
    </div>
  );
}

export default Header;
