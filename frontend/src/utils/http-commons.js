import axios from "axios";

// axios.defaults.withCredentials = true;

const { VITE_API_BASE_URL } = import.meta.env;

let instance = axios.create({
  baseURL: VITE_API_BASE_URL,
  headers: {
    "Content-Type": "application/json;charset=urf-8",
  },
});

const localAxios = () => {
  return instance;
};

const setAuth = (token) => {
  instance.defaults.headers.common["Authorization"] = `Bearer ${token}`;
};

export { localAxios, setAuth };
