import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const getBoardList = (success, fail) => {
  axios.get("/post").then(success).catch(fail);
};

export const getBoard = (postId, success, fail) => {
  axios.get(`/post/${postId}`).then(success).catch(fail);
};

export const writeBoard = (userId, title, content, success, fail) => {
  console.log(JSON.stringify({ userId, title, content }));
  axios
    .post("/post", JSON.stringify({ userId, title, content }))
    .then(success)
    .catch(fail);
};

export const editBoard = (userId, postId, title, content, success, fail) => {
  console.log(JSON.stringify({ userId, postId, title, content }));
  axios
    .put(`/post/${postId}`, JSON.stringify({ userId, title, content }))
    .then(success)
    .catch(fail);
};

export const deleteBoard = (userId, postId, success, fail) => {
  console.log({ userId });
  axios.delete(`/post/${postId}`, { userId }).then(success).catch(fail);
};
