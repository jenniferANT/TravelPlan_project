import {
  iconRobot,
  iconDrone,
  iconBook,
  iconMobile,
  iconScience,
  qrCode,
  earthImg,
} from "../Regis";
import { Routes, Route, Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useState, useContext, useRef, useEffect } from "react";
import axios from "axios";
import "./login.scss";
import { loginUser } from "../../redux/apiRequest";
import { useDispatch } from "react-redux";

function Login() {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");

  const [userDatas, setUserDatas] = useState([]);

  const [errorUser, setErrorUser] = useState("");
  const [errorEmail, setErrorEmail] = useState("");
  const [errorpassword, setErrorPassword] = useState("");

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const clearInput = () => {
    setName("");
    setEmail("");
    setUserName("");
    setPassword("");
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const newUser = {
      userName: userName,
      password: password,
    };
    loginUser(newUser, dispatch, navigate);
    // if (userName.length <= 8 || userName.includes(" ")) {
    //     setErrorUser("Username phải có ít nhất 8 kí tự và không chứa khoảng trắng")
    //     if(errorUser!= '') {console.log(errorUser);}
    // // }else if(!isValidEmail(email)) {
    // //     setErrorEmail("Email không hợp lệ");
    // //     if(errorEmail!='') {console.log(errorEmail);}
    // }else if (password.length <=8 || password.includes(" ")) {
    //     setErrorPassword("Password phải có ít nhất 8 kí tự và không chứa khoảng trắng");
    //     if(errorpassword!= '') {console.log(errorpassword);}
    // }else {
    //     login(userName,password)
    // }

    clearInput();
  };
  return (
    <div className="body">
      <div className="body-up">
        <p className="body-up__Main-text">Comeback to go on</p>
        <h1 className="body-up__sub-text">Your Travel Planning</h1>
      </div>
      <div className="body-down">
        <img className="icon icon-robot" src={iconRobot}></img>
        <img className="icon icon-mobile" src={iconMobile}></img>
        <img className="icon icon-drone" src={iconDrone}></img>
        <img className="icon icon-book" src={iconBook}></img>
        <img className="icon icon-science" src={iconScience}></img>
        <img className="icon qr-code" src={qrCode} />
        <p className="text-download">Scan to download app</p>
      </div>
      <div className="body-img__container">
        <img className="login-img" src={earthImg} alt="Earth-img" />
      </div>
      <div className="login__container">
        <div className="login__container-header">
          <p className="header-text">Welcome back to</p>
          <div className="header-link-container">
            <Link className="header-link-home" to="/">
              TravelPlan
            </Link>
          </div>
          <div className="clear"></div>
          <h2 className="header-sign-text">Login</h2>
        </div>
        <div className="login-form">
          <div className="login-form__element">
            <input
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
              required
              placeholder="Username:"
            />
          </div>
          <div className="login-form__element">
            <input
              value={password}
              type="password"
              onChange={(e) => setPassword(e.target.value)}
              required
              placeholder="Password:"
            />
          </div>
          <div className="login-form-reset">
            <Link className="reset-pass">Forget password ?</Link>
          </div>
          <button onClick={handleSubmit} className="login-form-btn">
            Log in{" "}
          </button>
          <div className="to-sign-up">
            <p>
              Bạn chưa có tài khoản?
              <Link to="/regis" className="link-sign-up">
                Sign up
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

export default Login;
