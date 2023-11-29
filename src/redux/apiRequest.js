import axios from "axios";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Cookies from "js-cookie";
import {
  loginFailed,
  loginStart,
  loginSuccess,
  registerFailed,
  registerStart,
  registerSuccess,
} from "./authSlice";

export const login = async (user, dispatch, navigate) => {
  dispatch(loginStart());
  try {
    const res = await axios.post(
      "http://localhost:8081/api/v1/auth/login",
      user
    );

    //store token in redux and r token in cookies
    dispatch(loginSuccess(res.data));
    Cookies.set("refreshToken", res.data.refreshToken, { expires: 30 });
    
    toast.success("Login success!");
    navigate("/");
  } catch (err) {
    dispatch(loginFailed());

    toast.error(err.response.data.message);
  }
};

export const registerUser = async (user, dispatch, navigate) => {
  dispatch(registerStart());
  try {
    await axios.post("http://localhost:8081/api/v1/auth/register", user);
    dispatch(registerSuccess());
    toast.success("Đăng ký thành công!");
    navigate("/login");
  } catch (err) {
    dispatch(registerFailed());
    toast.error(err.response.data.message);
  }
};
