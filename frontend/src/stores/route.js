import { ref, computed } from "vue";
import { defineStore } from "pinia";

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

  return {
    placeList,
    getLatLngList,
  };
});
