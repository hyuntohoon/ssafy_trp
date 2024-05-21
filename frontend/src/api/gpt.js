import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

export const postBotChat = (prompt) => {
  return axios.post(`/bot/chat`, { prompt });
};
