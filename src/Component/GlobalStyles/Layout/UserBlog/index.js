import { useEffect, useState } from "react";
import { Router, useHistory } from "react-router-dom";
import { Link } from "react-router-dom";
import axios from "axios";
import "./userblog.scss";
import avatar from "./img/Avater.png";
import contentImg from "./img/contentImg.png";
const iconBlogPost = {
  locationIcon: require("./img/ep_location.png"),
  imgIcon: require("./img/foundation_photo.png"),
  vidIcon: require("./img/Group.png"),
  starIcon: require("./img/starIcon.png"),
  cmtIcon: require("./img/cmtIcon.png"),
};
const userName = "Nguyen huynh duyen";
const userAvt = avatar;
const userDataBlog = [
  {
    avtar: avatar,
    name: "Nguyen huynh duyen",
    data: {
      datePost: "27/8/2023",
      locationPost: "Sai Gon",
      content:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Malesuada id dolor neq ue euismod proin ut nunc.",
      contentImg: [contentImg, contentImg, contentImg],
      contentVid: [],
      numLike: 3.5,
      numComment: "2k",
    },
  },
  {
    avtar: avatar,
    name: "Nguyen huynh duyen",
    data: {
      datePost: "27/8/2023",
      locationPost: "Sai Gon",
      content:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Malesuada id dolor neq ue euismod proin ut nunc.",
      contentImg: [contentImg, contentImg, contentImg],
      contentVid: [],
      numLike: 3.5,
      numComment: "2k",
    },
  },
  {
    avtar: avatar,
    name: "Nguyen huynh duyen",
    data: {
      datePost: "27/8/2023",
      locationPost: "Sai Gon",
      content:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Malesuada id dolor neq ue euismod proin ut nunc.",
      contentImg: [contentImg, contentImg, contentImg],
      contentVid: [],
      numLike: 3.5,
      numComment: "2k",
    },
  },
];
function UserBlog() {
  const [apiData, setApiData] = useState(userDataBlog);
  const [blogContent, setBlogContent] = useState("");
  const [images, setImages] = useState([]);
  const [selectedVideos, setSelectedVideos] = useState([]);

  function getPostition(position) {
    const { latitude, longitude } = position.coords;
    console.log(latitude);
    console.log(longitude);
  }
  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(getPostition);
    } else {
      console.log("Not supported");
    }
  }

  const handleImageBlogChange = (event) => {
    const selectedImages = Array.from(event.target.files).slice(0, 3);
    console.log("image", selectedImages);

    selectedImages.forEach((selectedImage) => {
      selectedImage.preview = URL.createObjectURL(selectedImage);
    });
    setImages(selectedImages);
  };

  useEffect(() => {
    return () => {
      images.forEach((image) => URL.revokeObjectURL(image.preview));
    };
  }, [images]);

  function handleVideoBlogChange(event) {
    const files = event.target.files;
    const videosArray = Array.from(files);
    setSelectedVideos(videosArray);
  }

  function handlePostUserBlog() {
    const newBlog = {
      avtar: userAvt,
      name: userName,
      data: {
        datePost: new Date().toLocaleDateString(),
        locationPost: "Sai Gon",
        content: blogContent,
        contentImg: images,
        contentVid: selectedVideos,
        numLike: 2,
        numComment: 1,
      },
    };
    setApiData((prev) => [...prev, newBlog]);
    setBlogContent("");
    setImages([]);
    setSelectedVideos([]);
  }

  return (
    <div className="user-blog">
      <div className="user-blog-post">
        <div className="user-blog-post__text">
          <div className="user-blog-post-avt">
            <img src={avatar} />
          </div>
          <textarea
            value={blogContent}
            onChange={(e) => setBlogContent(e.target.value)}
            className="user-blog-post-content"
            placeholder="Hãy viết blog của bạn nào !"
          />
        </div>
        <div className="user-blog-post-btn">
          <div className="user-blog-btn-input">
            <div className="user-blog-btn-location">
              <img src={iconBlogPost.locationIcon} />
              <button onClick={getLocation}>Địa điểm</button>
            </div>
            <div className="user-blog-btn-img">
              <img src={iconBlogPost.imgIcon} />
              <input
                multiple
                type="file"
                className="post-input__img"
                onChange={handleImageBlogChange}
                id="user-blog-post-input-img"
                accept="image/*"
                style={{ display: "none" }}
              />
              <div className="user-blog-input-lable-wrap">
                <label
                  className="user-blog-input-label"
                  for="user-blog-post-input-img"
                >
                  Hình ảnh
                </label>
              </div>
            </div>
            <div className="user-blog-btn-video">
              <img src={iconBlogPost.vidIcon} />
              <input
                multiple
                type="file"
                className="user-blog-post-input__video"
                onChange={handleVideoBlogChange}
                id="user-blog-post-input-video"
                accept="video/*"
                style={{ display: "none" }}
              />
              <div className="user-blog-input-lable-wrap">
                <label
                  className="user-blog-input-label"
                  for="user-blog-post-input-video"
                >
                  Video
                </label>
              </div>
            </div>
          </div>
          <div className="user-blog-btn__post">
            <button onClick={handlePostUserBlog}>Post</button>
          </div>
        </div>
      </div>
      <div className="user-blog-posted">
        {apiData.map((blog) => (
          <div className="user-blog-posted-item">
            <div className="user-blog-posted-item-heading">
              <div className="user-blog-posted-profile">
                <img className="posted-profile-avt" src={blog.avtar} />
                <div className="posted-profile-info">
                  <h5 className="posted-profile-info__name">{blog.name}</h5>
                  <div className="posted-profile-info__location">
                    <ul>
                      <li>{blog.data.datePost}</li>
                      <li>{blog.data.locationPost}</li>
                    </ul>
                  </div>
                </div>
              </div>
              <button className="posted-item-btn__edit">&#8942;</button>
            </div>
            <div className="user-blog-posted-item-content">
              <p className="posted-item-content-text">{blog.data.content}</p>
              <div cclassName="posted-item-content-img">
                {blog.data.contentImg.map((img) => (
                  <img src={img} />
                ))}
              </div>
            </div>
            <div className="user-blog-posted-item-interact">
              <div className="posted-item-interact__star">
                <img src={iconBlogPost.starIcon} />
                <p>{blog.data.numLike}</p>
              </div>
              <div className="posted-item-interact__cmt">
                <img src={iconBlogPost.cmtIcon} />
                <p>{blog.data.numComment}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default UserBlog;
