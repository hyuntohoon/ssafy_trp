import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

// export const getSido = (success, fail) => {
//   axios.get("/areasido/sido").then(success).catch(fail);
// };

// export const getGugun = (sido, success, fail) => {
//   axios.get(`/areasido/gugun/${sido}`).then(success).catch(fail);
// };

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
