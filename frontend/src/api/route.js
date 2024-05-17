import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const postTripPlan = (name, userId, attractionIds) => {
  return axios.post("/tripplan", JSON.stringify({ name, userId, attractionIds }));
};
