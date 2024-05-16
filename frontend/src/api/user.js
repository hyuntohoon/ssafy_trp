import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const signUp = (id, pw, name, success, fail) => {
  axios.post("/user", JSON.stringify({ id, pw, name })).then(success).catch(fail);
};

export const changePassword = (pw, success, fail) => {
  axios.put("/user", JSON.stringify({ pw })).then(success).catch(fail);
};

export const withdraw = (success, fail) => {
  axios.delete("/user").then(success).catch(fail);
};

export const signIn = (id, pw, success, fail) => {
  axios.post("/user/signin", JSON.stringify({ id, pw })).then(success).catch(fail);
};

export const signOut = (success, fail) => {
  axios.get("/user/signout").then(success).catch(fail);
};
