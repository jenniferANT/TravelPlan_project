import axios from "axios";
import { loginFailed, loginStart, loginSuccess, logoutFailed, logoutStart, logoutSuccess, registerFailed, registerStart, registerSuccess } from "./authSlice";
const baseUrl = " http://localhost:8081/api";
export const loginUser = async(user,dispatch, navigate) => {
    dispatch(loginStart());
    try {
        const res = await axios.post(baseUrl+"/v1/auth/login",user)
        dispatch(loginSuccess(res.data));
        console.log(res.data());
        navigate("/");
    }catch(err) {
        dispatch(loginFailed());
    }
}

export const registerUser = async (user,dispatch,navigate) =>{
    dispatch(registerStart());
    try {
        await axios.post(baseUrl+"/v1/auth/register",user);
        dispatch(registerSuccess());
        console.log("success");
        navigate("/login")
    }catch(err) {
        dispatch(registerFailed());
    }
}

export const logOut = async (dispatch, navigate) => {
    dispatch(logoutStart());
    try {
        //await axios.post(baseUrl + "v1/auth/logout");
        dispatch(logoutSuccess());
        navigate("/login")
    }catch(err) {
        dispatch(logoutFailed());
    }
}