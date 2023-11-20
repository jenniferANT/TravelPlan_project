import axios from "axios";
import { loginSuccess } from "./authSlice";

const axiosInstance = axios.create();

const reduxMiddleware = (store) => (next) => (action) => {
  // Kiểm tra action có phải là action setAccessToken không
  if (action.type === loginSuccess.type) {
    // Lấy AccessToken từ action và cấu hình trong header của axiosInstance
    const { accessToken } = action.payload;
    axiosInstance.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${accessToken}`;
  }

  // Tiếp tục action cho các middleware hoặc reducer khác
  return next(action);
};

export default reduxMiddleware;
