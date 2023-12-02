import { useEffect, useRef, useState } from "react";
import { Router, useHistory } from "react-router-dom";
import Select from "react-select";
import { Link } from "react-router-dom";
import axios from "axios";
import "./userhome.scss";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import avatar from "./img/Avater.png";
import UserBlog from "../../Component/GlobalStyles/Layout/UserBlog";
import { flushSync } from "react-dom";
const icon = {
  icon1: require("./img/icon1.png"),
  icon2: require("./img/icon2.png"),
  icon3: require("./img/icon3.png"),
  locationI: require("./img/location.png"),
  videoI: require("./img/video.png"),
  imageI: require("./img/img.png"),
  tagI: require("./img/tag.png"),
  postI: require("./img/post.png"),
  moneyI: require("./img/price.png"),
  addressI: require("./img/address.png"),
  websiteI: require("./img/website.png"),
  timeI: require("./img/time.png"),
  phoneI: require("./img/phone.png"),
  pointI: require("./img/point.png"),
  countI: require("./img/count.png"),
  inputI: require("./img/input.png"),
};
const userProfile = [
  {
    avatar: avatar,
    name: "Nguyen huynh duyen",
    nickName: "#huyenduyencutevai",
  },
];
const blogData = [{}];
function UserHome() {
  const [title, setTitle] = useState("Thích Ca Phật Đài");
  const [address, setAddress] = useState("abc xyz qua n12");
  const [beginDate, setBeginDate] = useState("7h30");
  const [endDate, setEndDate] = useState("21h00");
  const [description, setDescription] = useState(
    "Thích Ca Phật Đài là một quần thể kiến trúc Phật giáo lớn, nằm trên sườn phía Bắc của Núi Lớn, thành phố Vũng Tàu, tỉnh Bà Rịa – Vũng Tàu. Nơi đây là một điểm tham quan du lịch và tín ngưỡng nổi tiếng, thu hút hàng triệu lượt khách đến tham quan mỗi năm. "
  );
  const [emLink, setEmLink] = useState("");
  const [linkMap, setLinkMap] = useState("");
  const [maxTime, setMaxTime] = useState("");
  const [minTime, setMinTime] = useState("");
  const [phone, setPhone] = useState("0123456789");
  const [price, setPrice] = useState("4000000");
  const [webName, setWebName] = useState("Website cua jeni");
  const [webLink, setWebLink] = useState("abc.com.vn");
  const [tag, setTag] = useState("");
  const [point, setPoint] = useState(0);
  const [count, setCount] = useState(0);
  const [comment, setComment] = useState('')
  const [imgComment,setImgComment] = useState([]);

  const [showPopUp, setShowPopUp] = useState(false);
  const popUpref = useRef(null);
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

  const listCategories = [
    {
      id: 1,
      name: "phieu luu",
    },
    {
      id: 2,
      name: "tham hiem",
    },
  ];

  const options = listCategories.map((item) => {
    return {
      value: item.id,
      label: item.name,
    };
  });

  function handleTogglePopUp() {
    if (showPopUp) {
      setShowPopUp(false);
    } else {
      setShowPopUp(true);
    }
  }
  useEffect(() => {
    setShowPopUp(false);
  }, []);

  function handleChangeAddress(e) {
    setAddress(e.target.value);
  }
  function handleChangeBeginDate(e) {
    setBeginDate(e.target.value);
  }
  function handleChangeEndDate(e) {
    setEndDate(e.target.value);
  }
  function handleChangeDes(e) {
    setDescription(e.target.value);
  }
  function handleChangeEmbeddedLink(e) {
    setEmLink(e.target.value);
  }
  function handleChangeLinkMap(e) {
    setLinkMap(e.target.value);
  }
  function handleChangeMaxTime(e) {
    setMaxTime(e.target.value);
  }
  function handleChangeMinTime(e) {
    setMinTime(e.target.value);
  }
  function handleChangePhone(e) {
    setPhone(e.target.value);
  }
  function handleChangePrice(e) {
    setPrice(e.target.value);
  }
  function handleChangeTitle(e) {
    setTitle(e.target.value);
  }
  function handleChangeWebName(e) {
    setWebName(e.target.value);
  }
  function handleChangeLinkWeb(e) {
    setWebLink(e.target.value);
  }
  function handleChangeTag(e) {
    setTag((preTag) => [...preTag, e.target.value]);
  }
  function handleCommentChange(e) {
    setComment(e.target.value)
  }


  function handlePostUserComment() {
    const newComment = {
      newAvartar: avatar,
      newName: userData.name,
      newComment: comment,
      newImg: imgComment
    }
    
  }



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
            <div className="app-blog-posting-row1">
              <img src={userData.avatar} />
              <div
                className="blog-posting-btn__create"
                onClick={handleTogglePopUp}
              >
                <span>Creat place !</span>
              </div>
            </div>
            {showPopUp && (
              <div ref={popUpref} className="blog-posting-popup">
                <div className="blog-posting-popup__row1">
                  <div className="popup__row1__col1">
                    <textarea
                      placeholder="Title"
                      className=" reset-resize"
                      onChange={handleChangeTitle}
                    ></textarea>
                    <textarea
                      placeholder="Phone Number"
                      className=" reset-resize"
                      onChange={handleChangePhone}
                    ></textarea>
                    <textarea
                      placeholder="Average Price"
                      className=" reset-resize"
                      onChange={handleChangePrice}
                    ></textarea>
                    <div className="">
                      <textarea
                        placeholder="Begin Date"
                        className="popup__row1__col1__date reset-resize"
                        onChange={handleChangeBeginDate}
                      ></textarea>
                      <textarea
                        placeholder="End Date"
                        className="popup__row1__col1__date reset-resize"
                        onChange={handleChangeEndDate}
                      ></textarea>
                    </div>
                  </div>
                  <div className="popup__row1__col2">
                    <textarea
                      placeholder="Description"
                      className="popup__description reset-resize"
                      onChange={handleChangeDes}
                    ></textarea>
                    <div className="popup__time-container">
                      <input type="checkbox" />
                      <h4>24/24</h4>
                      <textarea
                        placeholder="Min Time"
                        className="reset-resize"
                        onChange={handleChangeMinTime}
                      ></textarea>
                      <textarea
                        placeholder="Max Time"
                        className="reset-resize"
                        onChange={handleChangeMaxTime}
                      ></textarea>
                    </div>
                  </div>
                </div>
                <div className="blog-posting-popup__row2">
                  <div className="popup__row2__col1">
                    <textarea
                      placeholder="Address"
                      className="reset-resize"
                      onChange={handleChangeAddress}
                    ></textarea>
                    <textarea
                      placeholder="Link map"
                      className="reset-resize"
                      onChange={handleChangeLinkMap}
                    ></textarea>
                    <textarea
                      placeholder="Embedded Link"
                      className="reset-resize"
                      onChange={handleChangeEmbeddedLink}
                    ></textarea>
                  </div>
                  <div className="popup__row2__col2">
                    <textarea
                      placeholder="Web name"
                      className="reset-resize"
                      onChange={handleChangeWebName}
                    ></textarea>
                    <textarea
                      placeholder="Link web"
                      className="reset-resize"
                      onChange={handleChangeLinkWeb}
                    ></textarea>
                  </div>
                </div>
                <div className="blog-posting-popup__row3">
                  <Select options={options} isMulti />
                </div>
                <div className="blog-posting-popup__row4">
                  <label className="popup__row4__label" for="popup-input-image">
                    Picture
                    <img src={icon.imageI} />
                  </label>
                  <input
                    style={{ display: "none" }}
                    id="popup-input-image"
                    type="file"
                    accept="image/*"
                    multiple
                  />
                </div>
                <button className="blog-posting-popup-postBtn">
                  Post
                  <img src={icon.postI} />
                </button>
                <button
                  onClick={handleTogglePopUp}
                  className="blog-posting-popup-close"
                >
                  X
                </button>
              </div>
            )}
          </div>
          <div className="app-blog-posted">
            <div className="blog-posted-profile">
              <div className="blog-posted-profile-left">
                <div className="blog-posted-profile-left__col1">
                  {/* user avartar */}
                  <img src={userData.avatar} />
                </div>
                <div className="blog-posted-profile-left__col2">
                  {/* user name */}
                  <p>{userData.name}</p>
                  <div className="blog-posted-profile-left__col2-date">
                    <p className="profile-left__col2-date">27/08/2023</p>
                    <li className="profile-left__col2-location">Sài Gòn</li>
                  </div>
                </div>
              </div>
              <div className="blog-posted-profile-right">
                <button className="edit-button">&#8942;</button>
              </div>
            </div>
            <div className="blog-posted">
              <div className="blog-posted-content">
                <div className="blog-posted-content-left">
                  <h3>{title}</h3>
                  <p>{description}</p>
                </div>
                <div className="blog-posted-content-right">
                  <div>
                    <img src={icon.phoneI} />
                    <p>{phone}</p>
                  </div>
                  <div>
                    <img src={icon.moneyI} />
                    <p>{price}</p>
                  </div>
                  <div>
                    <img src={icon.timeI} />
                    <p>{`${beginDate} - ${endDate}`}</p>
                  </div>
                  <div className="posted-content-right-web">
                    <img src={icon.websiteI} />
                    <div>
                      <p>{webName}</p>
                      <p>{webLink}</p>
                    </div>
                  </div>
                  <div>
                    <img src={icon.addressI} />
                    <p>{address}</p>
                  </div>
                  <div>
                    <img src={icon.tagI} />
                    <p>{tag}</p>
                  </div>
                </div>
              </div>
              <div className="blog-posted-img">
                <img src={avatar} />
                <img src={avatar} />
                <img src={avatar} />
                <img src={avatar} />
              </div>
              <div className="blog-posted-rate">
                <div className="blog-posted-point">
                  <img src={icon.pointI} />
                  <p>{point}</p>
                </div>
                <div className="blog-posted-count">
                  <img src={icon.countI} />
                  <p>{count}</p>
                </div>
              </div>
            </div>
          </div>
          <div className="app-blog-comment">
            <h3>Commments</h3>
            <div className="app-blog-comment-profile">
              <img src={avatar} />
              <p>{userData.name}</p>
            </div>
            <div className="app-blog-comment-input">
              <textarea onChange={handleCommentChange} placeholder="Your comment"></textarea>
            </div>
            <div className="app-blog-comment-btn">
              <label for="input-comment">
                <img src={icon.inputI} />
              </label>
              <input
                style={{ display: "none" }}
                id="input-comment"
                type="file"
                multiple
              />
              <button onClick={handlePostUserComment} className="app-blog-comment-btn__post">Post</button>
            </div>
          </div>
          <div className="app-blog-watch">
            <div className="app-blog-comment-profile">
              <img src={avatar} />
              <p>{userData.name}</p>
            </div>
            <div className="app-blog-comment-input">
              {/* cac comment */}
              <p>abcxỹ0c0ádsads</p>
              {/* time up comment */}
              <p className="app-blog-comment-time">5 mins ago</p>
            </div>
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
