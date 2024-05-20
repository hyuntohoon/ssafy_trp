import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const postTripPlan = (name, userId, attractionIds) => {
  return axios.post("/tripplan", JSON.stringify({ name, userId, attractionIds }));
};

export const getTripPlans = (userId) => {
  return axios.get(`/tripplan/tripPlans/places?userId=${userId}`);
};

export const putTripPlan = (tripPlanId, name, userId, attractionIds) => {
  return axios.put(`/tripplan/${tripPlanId}`, JSON.stringify({ name, userId, attractionIds }));
};

export const deleteTripPlan = (tripPlanId) => {
  return axios.delete(`/tripplan/${tripPlanId}`);
};
