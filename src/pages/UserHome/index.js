import { useEffect, useState } from "react";
import { Router, useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
import axios from "axios";
import "./userhome.scss";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import avatar from "./img/Avater.png";
import UserBlog from "../../Component/GlobalStyles/Layout/UserBlog";
const icon = {
  icon1: require("./img/icon1.png"),
  icon2: require("./img/icon2.png"),
  icon3: require("./img/icon3.png"),
};
const userProfile = [
  {
    avatar: avatar,
    name: "Nguyen huynh duyen",
    nickName: "#huyenduyencutevai",
  },
];

function UserHome() {
  const [userData, setUserData] = useState(userProfile[0]); //null khi ket noi db
  console.log(userData.name);
  // useEffect(() => {
  //    axios.get('') //api
  //     .then(response =>setUserData(response.data))
  //     .catch(error =>console.log(error));
  // },[])
  // if (!userData) {
  //     // Hiển thị thông báo hoặc spinner trong quá trình tải dữ liệu
  //     return <div>Loading...</div>;
  //  }
  return (
    <div className="User">
      <div className="user-heading">
        <Header />
      </div>
      <div className="user-app">
        <div className="user-app-profile">
          <div className="user-app-profile-info">
            <div className="user-app-profile-avt">
              <img src={userData.avatar} />
            </div>
            <div className="user-app-profile-name">
              <h4 className="profile-name__full">{userData.name}</h4>
              <p className="profile-name__nick">{userData.nickName}</p>
            </div>
          </div>

          <div className="user-app-profile-icon">
            <img className="profile-icon1" src={icon.icon1} />
            <img className="profile-icon2" src={icon.icon2} />
            <img className="profile-icon3" src={icon.icon3} />
          </div>
        </div>
        <div className="user-app-blog">
          <div className="app-blog-posting">
            <UserBlog />
          </div>
        </div>
      </div>
      <div className="user-footing">
        <Footer />
      </div>
    </div>
  );
}

export default UserHome;
