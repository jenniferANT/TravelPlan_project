import { Router, useHistory, Link } from "react-router-dom";
import Header from "../../Component/GlobalStyles/Layout/DefaultLayout/Header";
import Footer from "../../Component/GlobalStyles/Layout/Footer";
import axios from "axios";
import "./bloglist.scss";
import { useEffect, useState } from "react";
import vietnam from "./img/pic1.png";
import bangkok from "./img/pic2.png";

function PlacesList() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [pageNo, setPageNo] = useState(1);
  const [totalPages, setTotalPages] = useState();
  const [places, setPlaces] = useState([]);
  const [last, setLast] = useState();
  const [numbers, setNumbers] = useState([1,2]);

  useEffect(() => {
    fetch(
      `http://localhost:8081/api/v1/places/get-all?pageNo=${
        pageNo - 1
      }&pageSize=15&sortBy=id&sortDir=asc`
    )
      .then((res) => res.json())
      .then(
        (result) => {
          setPlaces(result.content);
          setTotalPages(result.totalPages);
          setLast(result.last);

          const x = Array.from({ length: totalPages }, (_, index) => index + 1);
          setNumbers(x);
          setIsLoaded(true);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  }, [pageNo]);

  const handlePagination = (x) => {
    if (x === pageNo) {
      return;
    } else {
      setPageNo(x);
    }
  };

  const handleFirstPage = () => {
    if (pageNo === 1) {
      return;
    } else {
      setPageNo(1);
    }
  };

  const handleLastPage = () => {
    if (last) {
      return;
    } else {
      setPageNo(totalPages);
    }
  };

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <div className="blog-list">
        <Header />

        <div className="blog-list-main">
          {places?.map((place) => (
            <Link style={{textDecoration:'none', color:'black'}} key={place.id} to={`/places/${place.id}`}>
              <div className="blog-list-row">
                <div className="blog-list-row-left">
                  <img
                    className="blog-list-row-left__img"
                    src={place.imageUrl[0] || vietnam}
                    alt=""
                  />
                  <p className="blog-list-row-left__rate">{place.point}</p>
                </div>
                <div className="blog-list-row-detail">
                  <h3 className="blog-list-row-detail__heading">
                    {place.title}
                  </h3>
                  <p className="blog-list-row-detail__content">
                    Content: {place.description}
                  </p>
                </div>
              </div>
            </Link>
          ))}

          <div class="center">
            <div class="pagination">
              <Link onClick={() => handleFirstPage()}>&laquo;</Link>
              {numbers?.map((number) => (
                <Link
                  key={number}
                  onClick={() => handlePagination(number)}
                  style={
                    number === pageNo
                      ? {
                          backgroundColor: "var(--third-color)",
                          color: "white",
                          border: "1px solid var(--third-color)",
                        }
                      : {}
                  }
                >{`${number}`}</Link>
              ))}
              <Link onClick={() => handleLastPage()}>&raquo;</Link>
            </div>
          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

export default PlacesList;
