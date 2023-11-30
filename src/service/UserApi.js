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

  const currentUser = JSON.parse(localStorage.getItem("userCurrent")) || null;
  if (currentUser !== null) {
    const url = `http://localhost:8081/api/v1/cart?planId=${planId}`;
    try {
      await axios.post(
        url,
        {},
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `${currentUser.token}`,
          },
        }
      );

      toast.success("Add cart succeed!");
    } catch (err) {
      toast.error(err.response.data.message);
    }
  }
};

export { loginApi, handleAddToCart };
