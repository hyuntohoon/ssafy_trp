import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const getSido = () => {
  return axios.get("/areasido/sido");
};

export const getGugun = (sido) => {
  return axios.get(`/areasido/gugun/${sido}`);
};

export const getAttractions = (sido, gugun, type, keyword) => {
  const params = {
    sidoCode: sido,
    gugunCode: gugun,
    type: type,
    keyword: keyword,
  };
  return axios.get("/attractions", { params });
};

export const getRecommendation = (keyWord, contentTypeId) => {
  return axios.get(`/attractions/recommended?keyword=${keyWord}&contentTypeId=${contentTypeId}`);
};

export const getAttractionByContentID = (contentId) => {
  return axios.get(`/attractions/id?id=${contentId}`);
};
