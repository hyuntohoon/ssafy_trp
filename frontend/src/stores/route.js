import { ref, computed } from "vue";
import { defineStore, storeToRefs } from "pinia";

import { useUserStore } from "./user";
import { postTripPlan, getTripPlans } from "@/api/route";

export const useRouteStore = defineStore("route", () => {
  const route = ref({
    tripPlan: {},
    places: [],
  });
  const routeList = ref([]);

  const getLatLngList = computed(() => {
    return route.value.places.map((place) => {
      return {
        lat: place.latitude,
        lng: place.longitude,
      };
    });
  });

  // Action
  const selectRoute = (routeId) => {
    const selectedRoute = routeList.value.find((val) => val.tripPlan.id === routeId);
    route.value.tripPlan = selectedRoute.tripPlan;
    route.value.places = selectedRoute.places;
  };

  const postPlace = async (name) => {
    if (route.value.places.length === 0) {
      return;
    }

    const userStore = useUserStore();
    const { id } = storeToRefs(userStore);
    const userId = id.value;

    const places = [];
    for (const place of route.value.places) {
      places.push(place.contentId);
    }

    try {
      const response = await postTripPlan(name, userId, places);
      if (response.status === 201) {
        return true;
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      return false;
    }
  };

  const getRouteList = async () => {
    const userStore = useUserStore();
    const { id } = storeToRefs(userStore);
    const userId = id.value;

    try {
      const response = await getTripPlans(userId);
      if (response.status === 200) {
        routeList.value = refineRouteList(response.data);
        console.log(routeList.value);
        return true;
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      return false;
    }
  };

  const refineRouteList = (original) => {
    const map = {};
    for (const place of original) {
      let id = place.tripPlan.id;
      if (!map[id]) {
        map[id] = {
          tripPlan: place.tripPlan,
          places: [],
        };
      }
      map[id].places.push(place.attractionInfo);
    }

    const result = [];
    for (const key in map) {
      map[key].places.sort((a, b) => a.order - b.order);
      result.push(map[key]);
    }

    return result;
  };

  const flush = () => {
    route.value.places = [];
    route.value.tripPlan = {};
  };

  return {
    route,
    routeList,

    getLatLngList,

    selectRoute,
    postPlace,
    getRouteList,

    flush,
  };
});
