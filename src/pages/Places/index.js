import { Router, useHistory, Link, useParams } from "react-router-dom";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import "./blog.scss";
import vietnam from "./img/pic1.png";
import bangkok from "./img/pic2.png";
import map from "./img/Map.png";
import airplane from "./img/Airplane.png";
import React, { useEffect, useState } from "react";

import PostReview from "../../Component/GlobalStyles/Layout/PostReview";

const blogFav = ["nghi duong", "kham pha", "vui choi"];
const blogDestinations = [
  {
    categoryId: 1,
    name: "London1",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
  },
  {
    categoryId: 1,
    name: "London2",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
  },
  {
    categoryId: 1,
    name: "London3",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
  },
  {
    categoryId: 1,
    name: "London4",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
  },
  {
    categoryId: 1,
    name: "London5",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
  },
  {
    categoryId: 1,
    name: "London5.1",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
  },
  {
    categoryId: 1,
    name: "London6",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 1,
    name: "London7",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 1,
    name: "London8",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 1,
    name: "London9",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 1,
    name: "London10",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 1,
    name: "London11",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 2,
    name: "Bangkok1",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 2,
    name: "Bangkok2",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 2,
    name: "Bangkok3",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 2,
    name: "Bangkok4",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 2,
    name: "Bangkok5",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 2,
    name: "Bangkok6",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
  },
  {
    categoryId: 2,
    name: "Bangkok7",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
  },
  {
    categoryId: 2,
    name: "Bangkok8",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
  },
  {
    categoryId: 2,
    name: "Bangkok9",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
  },
  {
    categoryId: 2,
    name: "Bangkok10",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
  },
  {
    categoryId: 2,
    name: "Bangkok11",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
  },
  {
    categoryId: 2,
    name: "Bangkok12",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
  },
];
const blogCategories = [
  {
    categoryId: 1,
    categoryName: "London",
  },
  {
    categoryId: 2,
    categoryName: "BangKok",
  },
  {
    categoryId: 3,
    categoryName: "England",
  },
  {
    categoryId: 4,
    categoryName: "Italy",
  },
  {
    categoryId: 5,
    categoryName: "Singapore",
  },
];
function Places() {
  const params = useParams();
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [place, setPlace] = useState();

  useEffect(() => {
    if (params && params.id) {
      fetch(`http://localhost:8081/api/v1/places/${params.id}`)
        .then((res) => res.json())
        .then(
          (result) => {
            setIsLoaded(true);
            setPlace(result);
          },
          (error) => {
            setIsLoaded(true);
            setError(error);
          }
        );
    }
  }, []);

  const [desCateId, setDesCateId] = useState(blogCategories[0].categoryId);
  const [desCateName, setDesCateName] = useState(
    blogCategories[0].categoryName
  );
  const [favCate, setFavCate] = useState(blogFav[0]);
  const handleCategoryChange = (e) => {
    blogCategories.find((category) => {
      if (e.target.value === category.categoryName) {
        setDesCateId(category.categoryId);
        setDesCateName(category.categoryName);
      }
    });
  };

  const handleFavChange = (e) => {
    setFavCate(e.target.value);
  };

  const filteredDestination = blogDestinations.filter(
    (destination) =>
      destination.categoryId === desCateId && destination.fav === favCate
  );

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <div className="blog">
        <Header />
        <h1 className="blog-main">{place.title}</h1>
        <div className="blog-categories">
          <div className="blog-categories-container-selection">
            <h4>Categories:</h4>
            <select
              className="blog-selection-destination"
              value={desCateName}
              onChange={handleCategoryChange}
            >
              {blogCategories.map((category) => (
                <option key={category.categoryId} value={category.categoryName}>
                  {category.categoryName}
                </option>
              ))}
            </select>
            <div className="blog-categories-container-fav">
              <select
                className="blog-selection-fav"
                value={favCate}
                onChange={handleFavChange}
              >
                {blogFav.map((fav) => (
                  <option key={fav} value={fav}>
                    {fav}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <div className="blog-destination-container">
            {filteredDestination.map((destination, index) => {
              let itemImgClass = `destination blog-destination-item${index}`;
              return (
                <div key={destination.name} className={itemImgClass}>
                  <img src={destination.imgSrc} alt={destination.name} />
                  <div className="blog-destination-over">
                    <h3 className="blog-destination__name">
                      {destination.name}
                    </h3>
                    <p className="blog-destination__rate">
                      {destination.point}
                    </p>
                    <p className="blog-destination__content">
                      {destination.content}
                    </p>
                  </div>
                </div>
              );
            })}
          </div>
        </div>
        <div className="blog-post">
          <div className="blog-post-detail">
            <h2 className="blog-post-detail-content">
              Top things to do in {place.title}
            </h2>
            <p>{place.content}</p>
          </div>
          <img className="plane-img" src={airplane} />
          <img className="map-img" src={map} />
        </div>
        <div className="comment-container">
          <PostReview />
        </div>
        <Footer />
      </div>
    );
  }
}

export default Places;
