import { ref } from "vue";
import { defineStore } from "pinia";
import {
  getSido,
  getGugun,
  getAttractions,
  getRecommendation,
  getAttractionByContentID,
} from "@/api/search";
import Swal from "sweetalert2";

export const useSearchStore = defineStore("search", () => {
  const sido = ref(null);
  const gugun = ref(null);
  const type = ref(null);
  const keyword = ref(null);
  const focus = ref({ lat: 37.56682, lng: 126.97865, level: 8 });

  const sidoNames = ref([]);
  const gugunNames = ref([]);
  const typeNames = ref([
    "관광지",
    "문화시설",
    "행사",
    "여행코스",
    "레포츠",
    "숙박",
    "쇼핑",
    "음식점",
  ]);

  const sidoData = ref([]);
  const gugunData = ref([]);
  const typeData = ref([
    { typeName: "관광지", typeCode: 12 },
    { typeName: "문화시설", typeCode: 14 },
    { typeName: "행사", typeCode: 15 },
    { typeName: "여행코스", typeCode: 25 },
    { typeName: "레포츠", typeCode: 28 },
    { typeName: "숙박", typeCode: 32 },
    { typeName: "쇼핑", typeCode: 38 },
    { typeName: "음식점", typeCode: 39 },
  ]);
  const resultData = ref([]);

  const recommendedData = ref([]);

  // Actions
  const fetchSido = async () => {
    try {
      const response = await getSido();
      if (response.status === 200) {
        sidoData.value = response.data;
        sidoNames.value = sidoData.value.map((item) => item.sidoName);
        return "success";
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "시/도 정보를 불러오는데 실패했습니다.",
      });
      return "fail";
    }
  };

  const fetchGugun = async () => {
    let sidoCode;
    for (const item of sidoData.value) {
      if (item.sidoName === sido.value) {
        sidoCode = item.sidoCode;
        break;
      }
    }

    try {
      const response = await getGugun(sidoCode);
      if (response.status === 200) {
        gugunData.value = response.data;
        gugunNames.value = gugunData.value.map((item) => item.gugunName);
        return "success";
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "구/군 정보를 불러오는데 실패했습니다.",
      });
      return "fail";
    }
  };

  const search = async () => {
    let sidoCode = null;
    sidoData.value.forEach((item) => {
      if (item.sidoName === sido.value) {
        sidoCode = item.sidoCode;
      }
    });
    let gugunCode = null;
    gugunData.value.forEach((item) => {
      if (item.gugunName === gugun.value) {
        gugunCode = item.gugunCode;
      }
    });
    let typeCode = null;
    typeData.value.forEach((item) => {
      if (item.typeName === type.value) {
        typeCode = item.typeCode;
      }
    });
    try {
      const response = await getAttractions(sidoCode, gugunCode, typeCode, keyword.value);
      if (response.status === 200) {
        resultData.value = response.data;
        return "success";
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "검색 결과를 불러오는데 실패했습니다.",
      });
      return "fail";
    }
  };

  const fetchRecommendation = async (keyWord, contentTypeId) => {
    try {
      const response = await getRecommendation(keyWord, contentTypeId);
      if (response.status === 200) {
        recommendedData.value = response.data;
        return "success";
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      return "fail";
    }
  };

  const getAttraction = async (contentId) => {
    try {
      const response = await getAttractionByContentID(contentId);
      if (response.status === 200) {
        return response.data;
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      return null;
    }
  };

  const flush = () => {
    sido.value = null;
    gugun.value = null;
    type.value = null;
    keyword.value = null;

    resultData.value = [];
    recommendedData.value = [];
    focus.value = { lat: 37.56682, lng: 126.97865, level: 8 };
  };

  return {
    sido,
    gugun,
    type,
    keyword,
    focus,

    sidoNames,
    gugunNames,
    typeNames,

    sidoData,
    gugunData,
    typeData,
    resultData,
    recommendedData,

    fetchSido,
    fetchGugun,
    fetchRecommendation,
    getAttraction,

    search,
    flush,
  };
});
