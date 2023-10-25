import React, { useState } from "react";
import "./Categorys.scss";
import rectangle from "./Rectangle.png";
import { Link } from "react-router-dom";
const categories = [
  {
    id: 1,
    img: rectangle,
    name: "Beach1",
  },
  {
    id: 2,
    img: rectangle,
    name: "Beach2",
  },
  {
    id: 3,
    img: rectangle,
    name: "Beach3",
  },
  {
    id: 4,
    img: rectangle,
    name: "Beach4",
  },
  {
    id: 5,
    img: rectangle,
    name: "Beach5",
  },
  {
    id: 6,
    img: rectangle,
    name: "Beach6",
  },
  {
    id: 7,
    img: rectangle,
    name: "Beach7",
  },
  {
    id: 8,
    img: rectangle,
    name: "Beach8",
  },
  {
    id: 9,
    img: rectangle,
    name: "van duy hoang 21511200311",
  },
  {
    id: 10,
    img: rectangle,
    name: "Beach10",
  },
  {
    id: 11,
    img: rectangle,
    name: "Beach11",
  },
  {
    id: 12,
    img: rectangle,
    name: "Beach12",
  },
  {
    id: 13,
    img: rectangle,
    name: "Beach14",
  },
  {
    id: 15,
    img: rectangle,
    name: "Beach15",
  },
];

function Categorys() {
  const [indexCategorys, setIndexCategorys] = useState(0);

  const [startIndex, setStartIndex] = useState(0);

  const prevCategories = () => {
    setStartIndex((prevIndex) => {
      console.log('prev');
      return prevIndex === 0 ? categories.length - 7 : prevIndex - 7;
    });
  };
  const nextCategories = () => {
    setStartIndex((prevIndex) => {
      console.log('next');
      return prevIndex >= categories.length - 7 ? 0 : prevIndex + 7;
    });
  };
  const displayCategories = categories.slice(startIndex, startIndex + 7);
  return (
    <div className="category-component">
      <div className="header">
        <div className="header-left">
          <h2 className="header-left-text-main">Categories</h2>
          <p className="header-left-text-sub">
            Here are lots of interesting destinations to visit, but don’t be
            confused—they’re already grouped by category.
          </p>
        </div>
        <div className="header-right">
          <button className="button-prev" onClick={prevCategories}>
            {" "}
            {"<"}{" "}
          </button>
          <button className="button-next" onClick={nextCategories}>
            {" "}
            {">"}{" "}
          </button>
        </div>
      </div>
      <div className="content-category">
        <div className="category-items">
          {categories.slice(startIndex, startIndex + 7).map((category) => {
            return (
              <Link key={category.id} className="category-item">
                <img className="category-item-img" src={category.img} />
                <p className="category-item-img-text">Visit</p>
                <span className="category-item-name">
                  {category.name}
                </span>
              </Link>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default Categorys;
