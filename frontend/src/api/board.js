import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const getBoardList = async (page, pageSize) => {
  return await axios.get("/post", { params: { page, pageSize } });
};

export const searchBoardList = async (key, value, page, pageSize, postTypeId) => {
  const params = {
    [key]: value,
    page,
    pageSize,
    postTypeId,
  };
  return await axios.get("/post/search", { params });
};

export const postBoard = async (params) => {
  return await axios.post("/post", params);
};

export const deleteBoard = async (postId) => {
  return await axios.delete(`/post/${postId}`);
};

export const putBoard = async (postId, params) => {
  return await axios.put(`/post/${postId}`, params);
};
