import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

const register = (userName, password) => {
  return axios.post(API_URL + "signup", {
    userName,
    password,
  });
};

const login = (userName, password) => {
  return axios
    .post(API_URL + "signin", {
      userName,
      password,
    })
    .then((response) => {
      if (response.data.token) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response;
    });
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const logout = () => {
  localStorage.removeItem("user");
};

export default {
    register,
    login,
    getCurrentUser,
    logout
  };