import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const signUp = (id, pw, name) => {
  return axios.post("/user/signup", JSON.stringify({ id, pw, name }));
};

export const changePassword = (userId, newPassword) => {
  return axios.put("/user/password", JSON.stringify({ userId, newPassword }));
};

export const signIn = (id, pw) => {
  return axios.post("/user/signin", { id, pw });
};

export const signOut = () => {
  return axios.get("/user/signout");
};
