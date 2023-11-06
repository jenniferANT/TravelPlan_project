import React, { useState } from "react";
import "./PopularDestination.scss";

import { Link } from "react-router-dom";
import popularImg from "./popularDes.png";
const popularDestinations = [
  {
    id: 1,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 2,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 3,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 4,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 5,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 6,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 7,
    img: popularImg,
    descript: "Beach7",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 8,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 9,
    img: popularImg,
    descript: "van duy hoang 21511200311",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 10,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 11,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 12,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 13,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
  {
    id: 14,
    img: popularImg,
    descript: "Mountai Hiking Tour",
    price: "400.000",
    subDescript:
      "Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour, Mountai Hiking Tour ",
  },
];

function PopularDestinations() {
  const [indexCategorys, setIndexCategorys] = useState(0);

  const [startIndex, setStartIndex] = useState(0);

  const prevCategories = () => {
    setStartIndex((prevIndex) => {
      let newIndex = prevIndex - 4;
      if(newIndex < 0) {
          newIndex = popularDestinations.length - (popularDestinations.length % 4);
      }
      return newIndex
      
    });
  };
  const nextCategories = () => {
    setStartIndex((prevIndex) => {
     let newIndex = prevIndex + 4;
     if(newIndex >= popularDestinations.length) {
        return 0;
     }
     if(newIndex === popularDestinations.length - 1) {
        newIndex = 0;
     }
     return newIndex;
    });
  };
  const displayCategories = popularDestinations.slice(
    startIndex,
    startIndex + 4
  );
  return (
    <div className="popularDestination-component">
      <div className="header">
        <div className="header-left">
          <h2 className="header-left-text-main">Find Popular Destinations</h2>
          <p className="header-left-text-sub"></p>
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
      <div className="content-popularDestination">
        <div className="popularDestination-items">
          {popularDestinations
            .slice(startIndex, startIndex + 4)
            .map((popularDestination) => {
              return (
                <div
                  key={popularDestination.id}
                  className="popularDestination-item"
                >
                  <img
                    className="popularDestination-item-img"
                    src={popularDestination.img}
                  />
                  <span className="popularDestination-item-descript">
                    {popularDestination.descript}
                  </span>
                    <p className="popularDestination-sub-Descript">
                      {popularDestination.subDescript}
                    </p>
                  <div className="popularDestination-booking-container">
                    <h5 className="popularDestination-price">
                      {popularDestination.price}
                    </h5>
                      <p>VND /Person</p>
                    <button className="popularDestination-booking-btn">Book Now</button>
                  </div>
                </div>
              );
            })}
        </div>
      </div>
    </div>
  );
}

export default PopularDestinations;
