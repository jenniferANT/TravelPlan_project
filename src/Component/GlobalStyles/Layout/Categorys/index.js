import React, { useEffect, useState } from "react";
import "./Categorys.scss";
import { Link } from "react-router-dom";

function Categorys() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8081/api/v1/category/category-child/name?name=area")
      .then((res) => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setCategories(result);
        },
        // Lưu ý: việc xử lý lỗi ở đây rất quan trọng
        // thay vì khối Catch() để chúng ta không nuốt
        // ngoại lệ từ các lỗi thực tế trong các thành phần.
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  }, []);

  const [startIndex, setStartIndex] = useState(0);

  const prevCategories = () => {
    setStartIndex((prevIndex) => {
      console.log("prev");
      return prevIndex === 0 ? categories.length - 7 : prevIndex - 7;
    });
  };
  const nextCategories = () => {
    setStartIndex((prevIndex) => {
      console.log("next");
      return prevIndex >= categories.length - 7 ? 0 : prevIndex + 7;
    });
  };
  const displayCategories = categories.slice(startIndex, startIndex + 7);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
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
                  <div className="category-item-img">
                    <img src={category.imageCategory} />
                  </div>

                  <p className="category-item-img-text">Visit</p>
                  <span className="category-item-name">{category.name}</span>
                </Link>
              );
            })}
          </div>
        </div>
      </div>
    );
  }
}

export default Categorys;
