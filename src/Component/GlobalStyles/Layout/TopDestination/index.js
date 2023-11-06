import React, { useEffect, useState } from "react";
import "./TopDestination.scss";
import { Link } from "react-router-dom";
import vietnam from "./img/pic1.png";
<<<<<<< HEAD
import bangkok from "./img/pic2.png";
import england from "./img/pic3.png";
import singapore from "./img/pic4.png";
import italy from "./img/pic5.png";
import rectangle from "../Categorys/Rectangle.png";
const destinations = [
  {
    categoryId: 1,
    name: "London1",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 1,
    name: "London2",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 1,
    name: "London3",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 1,
    name: "London4",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 1,
    name: "London5",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 1,
    name: "London6",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 1,
    name: "London7",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 1,
    name: "London8",
    imgSrc: vietnam,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok1",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok2",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok3",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok4",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok5",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok6",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok7",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
  },
  {
    categoryId: 2,
    name: "Bangkok8",
    imgSrc: bangkok,
    point: 3.5,
    content: "worefall",
=======
import bangkok from "./img/pic2.png"
import england from "./img/pic3.png"
import singapore from "./img/pic4.png"
import italy from "./img/pic5.png"
import rectangle from '../Categorys/Rectangle.png'
const destinations = [
  
  {
    categoryId:1,
    name:'London1',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:1,
    name:'London2',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:1,
    name:'London3',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:1,
    name:'London4',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:1,
    name:'London5',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:1,
    name:'London6',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:1,
    name:'London7',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:1,
    name:'London8',
    imgSrc:vietnam,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok1',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok2',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok3',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok4',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok5',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok6',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok7',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
  },
  {
    categoryId:2,
    name:'Bangkok8',
    imgSrc:bangkok,
    point:3.5,
    content:'worefall'
>>>>>>> 062b74f5846dd7cc9d5c50abb6f0d5a82ccaa7e5
  },
];
const categories = [
  {
<<<<<<< HEAD
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

function TopDestination() {
  const [categoryImages, setCategoryImages] = useState([]);
  const [destinationId, setDestinationId] = useState(1);

  useEffect(() => {
    if (destinationId) {
      const images = destinations
        .filter((destination) => destination.categoryId === destinationId)
        .map((destination) => destination.name);
      setCategoryImages(images);
    }
  }, [destinationId]);

  const handleDestinationOnClick = (categoryName) => {
    const category = categories.find(
      (category) => category.categoryName === categoryName
    );
    if (category) {
      setDestinationId(category.categoryId);
    } else {
      console.log("category not found");
    }
  };

=======
    categoryId:1,
    categoryName:"London"
  },
  {
    categoryId:2,
    categoryName:"BangKok"
  },
  {
    categoryId:3,
    categoryName:"England"
  },
  {
    categoryId:4,
    categoryName:"Italy"
  },
  {
    categoryId:5,
    categoryName:"Singapore"
  },
]

function TopDestination() {
  
  const [categoryImages, setCategoryImages] = useState([]);
  const [destinationId, setDestinationId] = useState(1);
  
  useEffect(() => {
    if(destinationId) {
      const images = destinations.filter((destination) => destination.categoryId===destinationId)
      .map((destination) => destination.name);
      setCategoryImages(images);
    }
  },[destinationId]);

  const handleDestinationOnClick = (categoryName) => {
    const category = categories.find((category) => category.categoryName === categoryName);
    if(category) {
      setDestinationId(category.categoryId)
    }else {
      console.log('category not found');
    }
  };
  
>>>>>>> 062b74f5846dd7cc9d5c50abb6f0d5a82ccaa7e5
  return (
    <div className="top-destination">
      <div className="header-top-destination">
        <h2 className="">Top Destination</h2>
        <p>Sost Brilliant reasons Entrada should be your one-stop-shop!</p>
        <div className="header-top-btn">
          {categories.map((category) => {
            return (
              <button
                className="destination-btn-item font"
                key={category.categoryName}
<<<<<<< HEAD
                onClick={() => handleDestinationOnClick(category.categoryName)}
=======
                onClick={()=> handleDestinationOnClick(category.categoryName)}
>>>>>>> 062b74f5846dd7cc9d5c50abb6f0d5a82ccaa7e5
              >
                {category.categoryName}
              </button>
            );
          })}
        </div>
      </div>
      <div className="content-top-destination">
<<<<<<< HEAD
        {categoryImages.map((name, index) => {
          const destination = destinations.find(
            (destination) => destination.name === name
          );
          let itemClass = `destination-item item${index}`;

          return (
            <div className={itemClass} key={index}>
              <img src={destination.imgSrc} alt={`Image ${index}`} />
              <h3>{destination.name}</h3>
              <p className="top-left-p">{destination.point}</p>
              <p className="bottom-left-p">{destination.content}</p>
            </div>
          );
        })}{" "}
=======
          {categoryImages.map((name,index) => {
            const destination = destinations.find((destination) => (destination.name===name));
            let itemClass = `destination-item item${index}`;
           
            return (
                
              <div className={itemClass} key={index}>
                <img src={destination.imgSrc} alt={`Image ${index}`}/>
                <h3>{destination.name}</h3>
                <p className="top-left-p">{destination.point}</p>
                <p className="bottom-left-p">{destination.content}</p>
              </div>
            );
          })}
>>>>>>> 062b74f5846dd7cc9d5c50abb6f0d5a82ccaa7e5
      </div>
    </div>
  );
}
export default TopDestination;
