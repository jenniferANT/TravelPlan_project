import axios from "axios";
import { Navigate } from "react-router-dom";
import { toast } from "react-toastify";

const loginApi = async (username, password, navigate) => {
  try {
    let res = await axios.post("http://localhost:8081/api/v1/auth/login", {
      username,
      password,
    });
    if (res) {
      localStorage.setItem("userCurrent", JSON.stringify(res.data));
    }

    toast.success("Login success!");
    navigate("/");
  } catch (err) {
    toast.error(err.response.data.message);
  }
};

const handleAddToCart = async (planId) => {

  console.log("id plan" + planId);
  let currentUser = JSON.parse(localStorage.getItem("userCurrent")) || null;
  if (currentUser !== null) {
    try {
      console.log("id plan 1");
      console.log( await axios.post(
        "http://localhost:8081/api/v1/cart",
        planId,
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `${currentUser.token}`,
          },
        }
      ));
      console.log("id plan 2");

      toast.success("Add cart succeed!");
    } catch (err) {
      toast.error(err.response.data.message);
    }
  }
};

export { loginApi, handleAddToCart };
