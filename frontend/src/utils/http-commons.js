import axios from "axios";

// axios.defaults.withCredentials = true;

const { VITE_API_BASE_URL } = import.meta.env;

const localAxios = () => {
  const instance = axios.create({
    baseURL: VITE_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=urf-8",
    },
  });

  return instance;
};

export { localAxios };
