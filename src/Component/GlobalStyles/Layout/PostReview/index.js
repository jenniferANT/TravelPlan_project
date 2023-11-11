import React, { useEffect, useState } from "react";
import "./postreview.scss";
import { Link } from "react-router-dom";
import { FaStar } from "react-icons/fa";
import { render } from "@testing-library/react";
import avt from "./img/avt.png";
import summitBtn from "./img/summitBtn.png";
import addPic from "./img/inputPicture.png";
import reviewImg from "./img/reviewImg.png";
import userAvt from "./img/userAvt.png";
import categoryImg from "./img/categoryImg.png";
const reviewPost = [
  {
    userAvt: userAvt,
    userName: "Екатерина Лужецкая",
    userSub: "месяц назад",
    rate: 5,
    reviewImg: [reviewImg, reviewImg, reviewImg],
    previewContent:
      "Заказывала у ребят разработку интернет-магазина. Что могу сказать, я очень довольная, магазин сделали под ключ сразу с базовыми настройками для SEO (пока не планирую продвигать) но уже будет возможность это делать.Рекомендую, цена, качество и коммуникация на 100%.",
  },
  {
    userAvt: userAvt,
    userName: "Екатерина Лужецкая",
    userSub: "месяц назад",
    rate: 4,
    reviewImg: [reviewImg, reviewImg, reviewImg],
    previewContent:
      "Заказывала у ребят разработку интернет-магазина. Что могу сказать, я очень довольная, магазин сделали под ключ сразу с базовыми настройками для SEO (пока не планирую продвигать) но уже будет возможность это делать.Рекомендую, цена, качество и коммуникация на 100%.",
  },
  {
    userAvt: userAvt,
    userName: "Екатерина Лужецкая",
    userSub: "месяц назад",
    rate: 3,
    reviewImg: [reviewImg, reviewImg, reviewImg],
    previewContent:
      "Заказывала у ребят разработку интернет-магазина. Что могу сказать, я очень довольная, магазин сделали под ключ сразу с базовыми настройками для SEO (пока не планирую продвигать) но уже будет возможность это делать.Рекомендую, цена, качество и коммуникация на 100%.",
  },
];
const name = "van duy hoang"

function PostReview() {
  const [rate, setRate] = useState(0);
  const [comment, setComment] = useState("");
  const [images, setImages] = useState([]);
  const [reviewData, setReviewData] = useState(reviewPost);
  const [newReview, setNewReview] =  useState({})
  const [newPost,setNewPost] = useState({})
  

  console.log(reviewData);

  //handle
  const handleRatingChange = (value) => {
    setRate(value);
  };

  const handleCommentChange = (e) => {
    console.log(e.target.value);
    setComment(e.target.value);
  };

  const handleImageChange = (event) => {
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
  const handleSummitReview = () => {
    const newPost = {
      userAvt: avt,
      userName: name,
      userSub: "just now",
      rate: rate,
      reviewImg: images.map((image) => image.preview),
      previewContent: comment
    };
  
    setReviewData(prev => [...prev, newPost]);
    setRate(0);
    setComment("");
    setImages([]);

  };



  //stars
  const renderStart = () => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(
        <li key={i} onClick={() => handleRatingChange(i)}>
          {i <= rate ? <FaStar width="40px" color="#ffc107" /> : <FaStar />}
        </li>
      );
    }
    return stars;
  };
  const getStart = (rate) => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(
        <li key={i}>
          {i <= rate ? <FaStar width="20px" color="#ffc107" /> : <FaStar />}
        </li>
      );
    }
    return stars;
  };



  return (
    <div className="review">
      <div className="review-post">
        <h3 className="review-post-title">Add your rate and reviews !</h3>
        <div className="review-post-start">
          <ul className="review-post-start-list">{renderStart()}</ul>
        </div>
        <div className="review-post-comment">
          <div className="review-post-comment-avt">
            <img src={avt} />
          </div>
          <div className="review-post-comment-input">
            <textarea
              placeholder="Add a comment"
              value={comment}
              onChange={handleCommentChange}
              className="post-input__text"
            />
          </div>
        </div>
        <div className="post-comment-btn-container">
          <input
            multiple
            type="file"
            className="post-input__img"
            onChange={handleImageChange}
            id="input-img"
            accept="image/*"
          />
          <div className="input-lable-wrap">
            <label className="input-label" for="input-img">
              Add Picture
              <img className="input-icon" src={addPic} />
            </label>
          </div>
          <button onClick={handleSummitReview} className="review-post-btn">
            <p>Post</p>
            <img className="summit-icon" src={summitBtn} />
          </button>
        </div>
        <div className="preview-image-container">
          {images.length > 0 && (
            <div className="preview-images">
              {images.map((image, index) => (
                <img
                  className="preview-image-item"
                  key={index}
                  src={image.preview}
                  alt={`Preview ${index + 1}`}
                />
              ))}
            </div>
          )}
        </div>
      </div>
      <div className="review-watch">
        <div className="review-watch-destination">
          <div className="review-watch-destination-heading">
            <img className="review-watch-destination-avt" src={categoryImg} />
            <div className="review-watch-destination-category">
              <h3>Ha Long Bay</h3>
              <p className="review-watch-destination-update">
                last update on 25/09/2022
              </p>
            </div>
          </div>
          <div className="review-watch-destination-items">
            {reviewData.map((post, index) => (
              <div className="review-watch-destination-item">
                <div className="review-watch-destination-item__left">
                  <div className="review-watch-item-info">
                    <img
                      className="watch-item-info-avt"
                      src={post.userAvt}
                      alt={post.userAvt}
                    />
                    <div className="watch-item-info-user">
                      <h4 className="watch-item-info-name">{post.userName}</h4>
                      <p className="watch-item-info-sub">{post.userSub}</p>
                      <ul className="watch-item-info-rate">
                        {getStart(post.rate)}
                      </ul>
                    </div>
                  </div>
                  <div className="review-watch-item-content">
                    <p className="">{post.previewContent}</p>
                  </div>
                </div>
                <div className="review-watch-destination-item__right">
                  {post.reviewImg && post.reviewImg.map((postImg) => (
                    <img src={postImg} />
                  ))}
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostReview;
