import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const getSido = (success, fail) => {
  axios.get("/areasido/sido").then(success).catch(fail);
};

export const getGugun = (sido, success, fail) => {
  axios.get(`/areasido/gugun/${sido}`).then(success).catch(fail);
};
