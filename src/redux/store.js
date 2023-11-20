import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice";
import reduxMiddleware from "./reduxMiddleware";

const store = configureStore({
  reducer: {
    auth: authReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(reduxMiddleware),
});

export default store;
