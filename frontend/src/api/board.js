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
  console.log(params);
  return await axios.get("/post/search", { params });
};

export const postBoard = async (params) => {
  console.log(params);
  return await axios.post("/post", params);
};
