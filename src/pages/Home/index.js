import { Router, useHistory } from "react-router-dom";
import { Link } from "react-router-dom";

import "./home.scss";
import logo from "./img/Union.png";
import airplane from "./img/airplane.png";
import earthSvg from "./img/earth.svg";
import image from "./img/Image.png";
import map from "./img/map.png";
import search from "./img/search.png";

import mainImg from "./img/mainImg.png";
import subImgCus from "./img/sub-img-cus.png";
import subImgDes from "./img/sub-img-des.png";

import aboutAward from "./img/aboutAward.png";
import aboutJourney from "./img/aboutJourney.png";
import aboutExperience from "./img/aboutExperience.png";

import feedbackCus from "./img/feedback-cus.png";
import android1 from "./img/android1.png";
import android2 from "./img/android2.png";
import android3 from "./img/android3.png";
import avadev from "./img/image 580.png";
import avacustomer from "./img/avacustomer.png";

import signUpContainer from "./img/sign-up-container.png";
import intersect from "./img/Intersect.png";
import receiveIcon from "./img/receive-input.png";



import DefaultLayout from "../../Component/GlobalStyles/Layout/DefaultLayout";
import Regis from "../Login";
import Login from "../Regis";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer"
import Categorys from "../../Component/GlobalStyles/Layout/Categorys";
import AboutUs from "../../Component/GlobalStyles/Layout/AboutUs";
import PopularDestinations from "../../Component/GlobalStyles/Layout/PopularDestination";
import TopDestination from "../../Component/GlobalStyles/Layout/TopDestination";
function Home() {
  return (
    <div className="content">
      <Header/>
      <div className="intro">
        <div className="intro-left">
          <h1 className="intro-left-main font">
            Discover the Best Travel Plaining
          </h1>
          <p className="intro-left-sub">
            Plan and look for your perfect trip with expert advice, travel tips,
            destination information and inspiration from us
          </p>
          <div className="intro-left-container">
            <button className="intro-left-btn">
              <Link className="font left-btn__link" to="/planing">
                Start your trip
              </Link>
              <img src={search}></img>
            </button>
          </div>
        </div>
        <div className="intro-right">
          <img src={image}></img>
          <img className="position-absolute intro-right-imgMap" src={map}></img>
          <img
            className="position-absolute intro-right-imgEarth"
            src={earthSvg}
          ></img>
        </div>
      </div>
      <Categorys />
      <div className="about-us">
        <div className="about-us-left">
          <img className="left-main-img" src={mainImg} />
          <img className="left-sub-img-des" src={subImgDes} />
          <img className="left-sub-img-cus" src={subImgCus} />
        </div>
        <div className="about-us-right">
          <div className="about-us-right-up">
            <p>Our Experience</p>
            <h2>Our Stories Have Adventures</h2>
            <p>
              We are experienced in bringing adventures to stay their journey,
              with all outdoor destinations in the as country our specialties.
              Start your adventure now! Nature has already called you!
            </p>
          </div>
          <div className="about-us-right-down">
            <img className="" src={aboutJourney}></img>
            <img className="" src={aboutAward}></img>
            <img className="" src={aboutExperience}></img>
          </div>
        </div>
      </div>
      <PopularDestinations />
      <TopDestination />
      <div className="feed-back">
        <div className="feed-back-dev">
          <h1 className="dev-heading">About Our Develop Team</h1>
          <div className="dev-content">
            <div className="dev-content-left">
              <img className="dev-ava" src={avadev} />
            </div>
            <div className="dev-content-right">
              <p className="main-text">Thanks for your supporting</p>
              <p className="sub-text">Try your best!</p>
              <img src={android1} />
              <img src={android2} />
              <img src={android3} />
            </div>
          </div>
        </div>
        <div className="feed-back-customer">
          <h1 className="customer-heading">What The Customers Said</h1>
          <div className="customer-content">
            <img className="ava-customer" src={avacustomer} />
            <img className="customer-content-feedback__img" src={feedbackCus} />
          </div>
        </div>
      </div>

      <div className="receive-email">
        <div className="receive-email-content">
          <h1 className="receive-email-heading">Sign up to our newsletter</h1>
          <p className="receive-email-title">
            Recieved latest news update, and many other things every week
          </p>
          <div className="receive-input-container">
            <input type="email" placeholder="Enter your email address" />
            <div className="receive-btn-container">
              <button className="receive-btn" src={receiveIcon}></button>
            </div>
          </div>
        </div>
      </div>
      <Footer/>
    </div>
  );
}

export default Home;
