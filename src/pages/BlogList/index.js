import { Router, useHistory, Link } from "react-router-dom";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import "./bloglist.scss";
import { useState } from "react";

import vietnam from "./img/pic1.png";
import bangkok from "./img/pic2.png";
const blogFavs = ["nghi duong", "kham pha", "vui choi"];

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
const blogDestinations = [
  {
    categoryId: 1,
    name: "London1",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London2",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London3",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London4",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London5",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London5.1",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "nghi duong",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London6",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London7",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London8",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London9",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London10",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 1,
    name: "London11",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok1",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok2",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok3",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok4",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok5",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok6",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "kham pha",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok7",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok8",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok9",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok10",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok11",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
  {
    categoryId: 2,
    name: "Bangkok12",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
    fav: "vui choi",
    detail:
      "Hạ Long Bay is a UNESCO World Heritage Site and popular travel destination in Quảng Ninh Province, Vietnam. Halong Bay is a beautiful natural wonder in northern Vietnam near the Chinese border. The Bay is dotted with 1,600 limestone islands and islets and covers an area of over 1,500 sqkm.",
  },
];
function BlogList() {
  const [blogList, setBlogList] = useState(blogCategories[0].categoryName);
  const [blogFav, setBlogFav] = useState(blogFavs[0]);
  const [blogListId, setBlogListId] = useState(blogCategories[0].categoryId);
  const handleChangeBlogList = (e) => {
    blogCategories.find((category) => {
      if (e.target.value === category.categoryName) {
        setBlogList(e.target.value);
        setBlogListId(category.categoryId);
      }
    });
  };

  const handleChangeBlogFav = (e) => {
    setBlogFav(e.target.value);
  };
  const filteredBlog = blogDestinations.filter(
    (destination) =>
      destination.categoryId === blogListId && destination.fav === blogFav
  );
  return (
    <div className="blog-list">
      <Header />
      <div className="blog-list-category-container">
        <div className="blog-list-category__select">
          <label for="select">Categories:</label>
          <select
            className="blog-list-category__selection blog-list__selection"
            value={blogList}
            onChange={handleChangeBlogList}
          >
            {blogCategories.map((category) => (
              <option key={category.categoryId} value={category.categoryName}>
                {category.categoryName}
              </option>
            ))}
          </select>
          <select
            className="blog-list-fav__selection blog-list__selection"
            value={blogFav}
            onChange={handleChangeBlogFav}
          >
            {blogFavs.map((fav) => (
              <option key={fav} value={fav}>
                {fav}
              </option>
            ))}
          </select>
        </div>
      </div>
      <div className="blog-list-main">
        {filteredBlog.map((blogDestination, index) => (
          <div className="blog-list-row">
            <div className="blog-list-row-left">
              <img
                className="blog-list-row-left__img"
                src={blogDestination.imgSrc}
              />
              <p className="blog-list-row-left__rate">
                {blogDestination.point}
              </p>
              <h4 className="blog-list-row-left__name">
                {blogDestination.name}
              </h4>
              <h5 className="blog-list-row-left__sub">
                {blogDestination.content}
              </h5>
            </div>
            <div className="blog-list-row-detail">
              <h3 className="blog-list-row-detail__heading">
                {blogDestination.name}
              </h3>
              <p className="blog-list-row-detail__content">
                {blogDestination.detail}
              </p>
            </div>
          </div>
        ))}
      </div>
      <Footer />
    </div>
  );
}

export default BlogList;
