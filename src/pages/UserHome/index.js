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
  const [comment, setComment] = useState("");
  const [imgComment, setImgComment] = useState([]);

  const [showPopUp, setShowPopUp] = useState(false);
  const popUpref = useRef(null);
  const [userData, setUserData] = useState(userProfile[0]); //null khi ket noi db

  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [pageNo, setPageNo] = useState(1);
  const [totalPages, setTotalPages] = useState();
  const [places, setPlaces] = useState([]);
  const [last, setLast] = useState();
  const [numbers, setNumbers] = useState();
  const currentUser = JSON.parse(localStorage.getItem("userCurrent")) || null;

  useEffect(() => {
    if (currentUser) {
      fetch(
        `http://localhost:8081/api/v1/places/my?pageNo=${
          pageNo - 1
        }&pageSize=15&sortBy=id&sortDir=asc`,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `${currentUser.token}`,
          },
        }
      )
        .then((res) => res.json())
        .then(
          (result) => {
            setPlaces(result.content);

            console.log(places);

            setTotalPages(result.totalPages);
            setLast(result.last);

            const x = Array.from(
              { length: totalPages },
              (_, index) => index + 1
            );
            setNumbers(x);
            setIsLoaded(true);
          },
          (error) => {
            setIsLoaded(true);
            setError(error);
          }
        );
    }
  }, [pageNo]);

  const handlePagination = (x) => {
    if (x === pageNo) {
      return;
    } else {
      setPageNo(x);
    }
  };

  const handleFirstPage = () => {
    if (pageNo === 1) {
      return;
    } else {
      setPageNo(1);
    }
  };

  const handleLastPage = () => {
    if (last) {
      return;
    } else {
      setPageNo(totalPages);
    }
  };

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
    setComment(e.target.value);
  }

  function handlePostUserComment() {
    const newComment = {
      newAvartar: avatar,
      newName: userData.name,
      newComment: comment,
      newImg: imgComment,
    };
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <>
        <Header />
        <div className="User">
          <div className="user-app">
            <div className="user-app-profile">
              <div className="user-app-profile-info">
                <div className="user-app-profile-avt">
                  <img src={currentUser.avatar} />
                </div>
                <div className="user-app-profile-name">
                  <h4 className="profile-name__full">{currentUser.name}</h4>
                  <p className="profile-name__nick">{currentUser.username}</p>
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
                  <img src={currentUser.avatar} />
                  <div
                    className="blog-posting-btn__create"
                    onClick={handleTogglePopUp}
                  >
                    <span>Creat place !</span>
                  </div>
                </div>

                {/* popup  */}
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
                      <label
                        className="popup__row4__label"
                        for="popup-input-image"
                      >
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

              {places?.map((place) => (
                <div className="app-blog-posted">
                  <div className="blog-posted-profile">
                    <div className="blog-posted-profile-left">
                      <div className="blog-posted-profile-left__col1">
                        {/* user avartar */}
                        <img src={currentUser.avatar} />
                      </div>
                      <div className="blog-posted-profile-left__col2">
                        {/* user name */}
                        <p>{currentUser.name}</p>
                        <div className="blog-posted-profile-left__col2-date">
                          <p className="profile-left__col2-date">
                            {place.createdAt}
                          </p>
                          <li className="profile-left__col2-location">
                            {place.addressDto.addressString}
                          </li>
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
                        <h3>{place.title}</h3>
                        <p>{place.description}</p>
                      </div>
                      <div className="blog-posted-content-right">
                        <div>
                          <img src={icon.phoneI} />
                          <p>{place.phoneNumber}</p>
                        </div>
                        <div>
                          <img src={icon.moneyI} />
                          <p>{place.cost}</p>
                        </div>
                        <div>
                          <img src={icon.timeI} />
                          <p>{`${place.beginDay} - ${place.endDay}`}</p>
                        </div>
                        <div className="posted-content-right-web">
                          <img src={icon.websiteI} />
                          <div>
                            <p>{place.link.url}</p>
                          </div>
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
                        <p>{place.point}</p>
                      </div>
                      <div className="blog-posted-count">
                        <img src={icon.countI} />
                        <p>{place.count}</p>
                      </div>
                    </div>
                  </div>
                </div>
              ))}

              <div class="center">
                <div class="pagination">
                  <Link onClick={() => handleFirstPage()}>&laquo;</Link>
                  {numbers?.map((number) => (
                    <Link
                      key={number}
                      onClick={() => handlePagination(number)}
                      style={
                        number === pageNo
                          ? {
                              backgroundColor: "var(--third-color)",
                              color: "white",
                              border: "1px solid var(--third-color)",
                            }
                          : {}
                      }
                    >{`${number}`}</Link>
                  ))}
                  <Link onClick={() => handleLastPage()}>&raquo;</Link>
                </div>
              </div>
            </div>
          </div>
        </div>

        <Footer />
      </>
    );
  }
}

export default UserHome;
