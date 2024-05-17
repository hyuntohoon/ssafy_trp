import { ref, computed } from "vue";
import { defineStore, storeToRefs } from "pinia";
import Swal from "sweetalert2";

import { useUserStore } from "./user";
import { postTripPlan } from "@/api/route";

export const useRouteStore = defineStore("route", () => {
  const placeList = ref([]);

  const getLatLngList = computed(() => {
    return placeList.value.map((place) => {
      return {
        lat: place.latitude,
        lng: place.longitude,
      };
    });
  });

  // Action
  const postPlace = async (name) => {
    if (placeList.value.length === 0) {
      return;
    }

    const userStore = useUserStore();
    const { id } = storeToRefs(userStore);
    const userId = id.value;

    const places = [];
    for (const place of placeList.value) {
      places.push(place.contentId);
    }

    try {
      const response = await postTripPlan(name, userId, places);
      if (response.status === 200) {
        return true;
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "여행 계획을 저장하는데 실패했습니다.",
      });
      return false;
    }
  };

  const flush = () => {
    placeList.value = [];
  };

  return {
    placeList,
    getLatLngList,

    postPlace,

    flush,
  };
});
