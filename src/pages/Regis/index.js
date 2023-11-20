import { Routes, Route, Link, useHistory, useNavigate } from "react-router-dom";
import axios from "axios";

import "./regis.scss";
import Home from "../Home";
import earthImg from "./img/Earth.png";
import iconRobot from "./img/icon-robot.png";
import iconMobile from "./img/icon-moble.png";
import iconDrone from "./img/icon-drone.png";
import iconScience from "./img/icon-science.png";
import iconBook from "./img/icon-book.png";
import qrCode from "./img/Frame 38.png";
import { useState } from "react";
import { registerUser } from "../../redux/apiRequest";
import { useDispatch } from "react-redux";
import { clear } from "@testing-library/user-event/dist/clear";

export {
  earthImg,
  iconBook,
  iconDrone,
  iconMobile,
  iconRobot,
  iconScience,
  qrCode,
};

function Regis() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [errorUser, setErrorUser] = useState("");
  const [errorEmail, setErrorEmail] = useState("");
  const [errorpassword, setErrorPassword] = useState("");

  const isValidEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const clearInput = () => {
    setName("");
    setEmail("");
    setUsername("");
    setPassword("");
  };

  const handleRegsister = (event) => {
    event.preventDefault();
    const newUser = {
      name: name,
      email: email,
      password: password,
      username: username,
    };
    if (username.length <= 8 || username.includes(" ")) {
      setErrorUser(
        "Username phải có ít nhất 8 kí tự và không chứa khoảng trắng"
      );
      if (errorUser !== "") {
        console.log(errorUser);
      }
    } else if (!isValidEmail(email)) {
      setErrorEmail("Email không hợp lệ");
      if (errorEmail !== "") {
        console.log(errorEmail);
      }
    } else if (password.length <= 8 || password.includes(" ")) {
      setErrorPassword(
        "Password phải có ít nhất 8 kí tự và không chứa khoảng trắng"
      );
      if (errorpassword !== "") {
        console.log(errorpassword);
      }
    }

    registerUser(newUser, dispatch, navigate);
    clearInput();
  };

  return (
    <div className="body">
      <div className="body-up">
        <p className="body-up__Main-text">Sign up to discover</p>
        <h1 className="body-up__sub-text">Your Travel Planning</h1>
      </div>
      <div className="body-down">
        <img alt="icon-robot" className="icon icon-robot" src={iconRobot}></img>
        <img
          alt="icon-mobile"
          className="icon icon-mobile"
          src={iconMobile}
        ></img>
        <img alt="icon-drone" className="icon icon-drone" src={iconDrone}></img>
        <img alt="icon-book" className="icon icon-book" src={iconBook}></img>
        <img
          alt="icon-science"
          className="icon icon-science"
          src={iconScience}
        ></img>
        <img alt="qrCode" className="icon qr-code" src={qrCode} />
        <p className="text-download">Scan to download app</p>
      </div>
      <div className="body-img__container">
        <img className="login-img" src={earthImg} alt="Earth-img" />
      </div>
      <div className="regis__container">
        <div className="regis__container-header">
          <p className="regis-header-text">Welcome to</p>
          <div className="header-link-container">
            <Link className="header-link-home" to="/">
              TravelPlan
            </Link>
          </div>
          <div className="clear"></div>
          <h2 className="header-sign-text">Sign up</h2>
        </div>
        <div className="regis-form">
          <div className="regis-form__element">
            <input
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
              placeholder="Your Name:"
            />
          </div>

          <div className="regis-form__element">
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              placeholder="Your email:"
            />
          </div>

          <div className="regis-form__element">
            <input
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              placeholder="Username:"
            />
          </div>

          <div className="regis-form__element">
            <input
              value={password}
              type="password"
              onChange={(e) => setPassword(e.target.value)}
              required
              placeholder="Password:"
            />
          </div>

          <button onClick={handleRegsister} className="regis-form-btn">
            Sign up
          </button>

          <div className="to-log-in">
            <p>
              Bạn đã có tài khoản?
              <Link to="/login" className="link-log-in">
                Log in
              </Link>
            </p>
          </div>
        </div>
      </div>
      {/* <Routes>
        <Route path='/' element={<Home />}/>
    </Routes> */}
    </div>
  );
}

export default Regis;
