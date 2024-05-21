import axios from "axios";

const proxy = "https://proxy.cors.sh/";
const url = "https://api.open-meteo.com/v1/forecast";
const key = "temp_711cd64c8d914013ff5f0ca7728832a1";

// timezone=Asia%2FTokyo&forecast_days=16
export const getWeather = async (lat, lon) => {
  const params = {
    latitude: lat,
    longitude: lon,
    daily: "temperature_2m_max,temperature_2m_min,precipitation_sum",
    timezone: "Asia/Tokyo",
    forecast_days: 16,
  };

  const response = await axios.get(proxy + url, {
    params: params,
    headers: {
      "x-cors-api-key": key,
    },
  });

  return response.data;
};
