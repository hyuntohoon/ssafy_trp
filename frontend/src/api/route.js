import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

const kakao = "9bb115ef822cc39d4f4d132938c8471c";

export const postTripPlan = (name, userId, attractionIds, tripDate) => {
  return axios.post("/tripplan", JSON.stringify({ name, userId, attractionIds, tripDate }));
};

export const getTripPlans = (userId) => {
  return axios.get(`/tripplan/tripPlans/places?userId=${userId}`);
};

export const putTripPlan = (tripPlanId, name, userId, attractionIds, tripDate) => {
  return axios.put(
    `/tripplan/${tripPlanId}`,
    JSON.stringify({ name, userId, attractionIds, tripDate })
  );
};

export const deleteTripPlan = (tripPlanId) => {
  return axios.delete(`/tripplan/${tripPlanId}`);
};

export const getMobilityRoutes = (origin, destination) => {
  let url = "https://apis-navi.kakaomobility.com/v1/directions";
  url += `?origin=${origin}`;
  url += `&destination=${destination}`;
  const headers = {
    Authorization: `KakaoAK ${kakao}`,
  };
  return axios.get(url, { headers });
};
