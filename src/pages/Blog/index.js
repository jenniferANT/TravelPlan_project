import { Router, useHistory, Link } from "react-router-dom";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import "./blog.scss";
import { useState } from "react";
import vietnam from "./img/pic1.png";
import bangkok from "./img/pic2.png";
import map from "./img/Map.png";
import airplane from "./img/Airplane.png";
import comment from "./img/Frame 12.png"

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
function Blog() {
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

  return (
    <div className="blog">
      <Header />
      <h1 className="blog-main">Ha Long Bay</h1>
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
                  <h3 className="blog-destination__name">{destination.name}</h3>
                  <p className="blog-destination__rate">{destination.point}</p>
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
        <div className="blog-post-title">
          <h1>
            Hạ Long Bay is a UNESCO World Heritage Site and popular travel
            destination in Quảng Ninh Province, Vietnam. Halong Bay is a
            beautiful natural wonder in northern Vietnam near the Chinese
            border. The Bay is dotted with 1,600 limestone islands and islets
            and covers an area of over 1,500 sqkm.
          </h1>
        </div>
        <div className="blog-post-detail">
          <h2 className="blog-post-detail-content">
            Top things to do in Ha Long Bay
          </h2>
          <h4>Cruise the bay</h4>
          <p>
            Nothing beats spending watching the sun set over the calm waters of
            Ha Long Bay, and waking up to a serene morning surrounded by karsts.
            Relaxing on a sun deck surrounded by magnificent panoramas is the
            highlight of many a trip.
          </p>
          <h4>Explore a floating village</h4>
          <p>
            Ha Long Bay is not just a UNESCO-listed site, it’s also home to a
            number of fishing communities who have lived on the water for
            centuries. Take a boat tour around a floating village for a glimpse
            of this rare and beautiful way of life.
          </p>
          <h4>Paddle out in a kayak</h4>
          <p>
            Even if you’re not an experienced kayaker, you can’t miss the chance
            to navigate your way around Ha Long’s gorgeous seascape. Just before
            sunset is an ideal time to kayak to see quiet lagoons and fishing
            boats up close.
          </p>
          <h4>Clamber into a cave</h4>
          <p>
            Beneath their rocks and jungled exteriors, many of Ha Long’s ancient
            karsts have been carved out by rain and water currents. Take a look
            inside these geological wonders on foot — some are a squeeze while
            others are enormous.
          </p>
          <h4>Try your hand at rock climbing</h4>
          <p>
            There are countless routes in the bay to keep climbing junkies
            entertained. Deep water soloing is becoming increasingly popular in
            beautiful Ha Long Bay and Lan Ha Bay, especially on Butterfly
            Island.{" "}
          </p>
          <h4>Ha Long Weather</h4>
          <p>
            Ha Long Bay’s climate can be cool with clear skies from September to
            November. Mist drifts in from December to March making the bay look
            all the more mysterious. April and May offer sunshine and a
            refreshing breeze, while the monsoon season can make visits
            unpredictable from June to August.
          </p>
          <h4>Ha Long Transport</h4>
          <p>
            Most visitors to Ha Long Bay opt for a packaged cruise, including
            transport to and from Hanoi. Independent travellers can take a bus
            to Ha Long City or Hai Phong and a taxi to the port. It’s also
            possible to charter a seaplane for a 45-minute ride direct from Noi
            Bai International Airport. Travelling Ha Long Bay is best by boat.
            Those looking for a bird's-eye view can book a 15-minute seaplane
            ride over the bay. In Ha Long City you can hire a taxi to get around
            town.
          </p>
          <p>
            From November 2021, Ha Long will open to an unlimited number of
            vaccinated tourists, who will be able to travel without quarantine
            requirements. Please check the current travel advisory for updates
            and application details.
          </p>
        </div>
        <img  className="plane-img" src={airplane}/>
        <img className="map-img" src={map}/>
      </div>
      <div className="comment-container">
        <PostReview/>
        
      </div>
      <Footer />
    </div>
  );
}

export default Blog;
