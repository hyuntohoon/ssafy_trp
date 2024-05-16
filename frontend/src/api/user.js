import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const signUp = (id, pw, name) => {
  return axios.post("/user", JSON.stringify({ id, pw, name }));
};

export const changePassword = (pw) => {
  return axios.put("/user", JSON.stringify({ pw }));
};

export const withdraw = () => {
  return axios.delete("/user");
};

export const signIn = (id, pw) => {
  return axios.post("/user/signin", { id, pw });
};

export const signOut = () => {
  return axios.get("/user/signout");
};
