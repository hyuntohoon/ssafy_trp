import { localAxios } from "@/utils/http-commons";

const axios = localAxios();

const appid = "9b777b0e8cc09624c486825532b1a2dd";

// export const getWeather = (lat, lon) => {
//   let url = `https://api.openweathermap.org/data/3.0/onecall?lat=${lat}&lon=${lon}&appid=${appid}`;
//   url = "https://proxy.cors.sh/" + url;
//   return axios.get(url, { headers: { "x-cors-api-key": "temp_ff36ce26a398c32bd713f5ebf2d14052" } });
// };

// export const getWeather = (lat, lon) => {
//   let url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${appid}&units=metric&lang=kr`;
//   url = "https://proxy.cors.sh/" + url;
//   return axios.get(url, { headers: { "x-cors-api-key": "temp_ff36ce26a398c32bd713f5ebf2d14052" } });
// };
