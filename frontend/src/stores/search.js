import { ref } from "vue";
import { defineStore } from "pinia";
import { getSido, getGugun, getAttractions } from "@/api/search";

export const useSearchStore = defineStore(
  "search",
  () => {
    const sido = ref(null);
    const gugun = ref(null);
    const type = ref(null);
    const keyword = ref(null);

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

    // Actions
    const setSido = (name) => {
      sido.value = name;
    };

    const setGugun = (name) => {
      gugun.value = name;
    };

    const setType = (name) => {
      type.value = name;
    };

    const setKeyWord = (word) => {
      keyword.value = word;
    };

    const fetchSido = async () => {
      try {
        const response = await getSido();
        if (response.status === 200) {
          sidoData.value = response.data;
          return sidoData.value.map((item) => item.sidoName);
        } else {
          throw new Error(response.status);
        }
      } catch (error) {
        console.error(error);
        return [];
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
          return gugunData.value.map((item) => item.gugunName);
        } else {
          throw new Error(response.status);
        }
      } catch (error) {
        console.error(error);
        return [];
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
        console.log(sidoCode, gugunCode, typeCode, keyword.value);
        const response = await getAttractions(sidoCode, gugunCode, typeCode, keyword.value);
        console.log(response);
        if (response.status === 200) {
          console.log(response.data);
          return response.data;
        } else {
          throw new Error(response.status);
        }
      } catch (error) {
        console.error(error);
        return [];
      }
    };

    const flush = () => {
      sido.value = null;
      gugun.value = null;
      type.value = null;
      keyword.value = null;
    };

    return {
      sido,
      gugun,
      type,
      keyword,

      typeNames,

      sidoData,
      gugunData,
      typeData,

      fetchSido,
      fetchGugun,

      setSido,
      setGugun,
      setType,
      setKeyWord,

      search,
      flush,
    };
  },
  {
    persist: true,
  }
);
