import React, { useEffect, useState } from "react";
import "./PopularDestination.scss";

import { Link } from "react-router-dom";
import popularImg from "./popularDes.png";
import userEvent from "@testing-library/user-event";


function PopularDestinations() {
  const [popularDes, setPopularDes] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);
  const [pageNo, setPageNo] = useState(0);

  useEffect(() => {
    fetch("http://localhost:8081/api/v1/places/get-all")
      .then((res) => res.json())
      .then((result) => {
        setIsLoaded(true);
        setPopularDes(result.content);
      },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  }, []);

  const prevCategories = () => {
    setPageNo((prevIndex) => {
      let newIndex = prevIndex - 4;
      if (newIndex < 0) {
        newIndex =
          popularDes.length - (popularDes.length % 4);
      }
      return newIndex;
    });
  };
  const nextCategories = () => {
    setPageNo((prevIndex) => {
      let newIndex = prevIndex + 4;
      if (newIndex >= popularDes.length) {
        return 0;
      }
      if (newIndex === popularDes.length - 1) {
        newIndex = 0;
      }
      return newIndex;
    });
  };
  const displayCategories = popularDes.slice(pageNo, pageNo + 4);

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
          {popularDes
            .slice(pageNo, pageNo + 4)
            .map((popularDestination) => {
              return (
                <div
                  key={popularDestination.id}
                  className="popularDestination-item"
                >
                  <div className="popularDestination-item-img">
                    {popularDestination.imageUrl.length === 0 ? (
                      <img style={{ maxWidth: '350px' }} src={popularImg} />
                    ) : (
                      <img style={{ maxWidth: '350px', minWidth: '350px' }} src={popularDestination.imageUrl[0]} />
                    )}
                  </div>

                  <span style={{minHeight:'92px', maxWidth:'80%'}} className="popularDestination-item-descript">
                    {popularDestination.title}
                  </span>
                  <p className="popularDestination-sub-Descript">
                    {popularDestination.description}
                  </p>
                  
                </div>
              );
            })}
        </div>
      </div>
    </div>
  );
}

export default PopularDestinations;
