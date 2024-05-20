import { ref, computed } from "vue";
import { defineStore, storeToRefs } from "pinia";

import { useUserStore } from "./user";
import { postTripPlan, putTripPlan, getTripPlans, deleteTripPlan } from "@/api/route";

export const useRouteStore = defineStore("route", () => {
  const route = ref({
    tripPlan: {},
    places: [],
  });
  const routeList = ref([]);
  const isEditing = ref(false);

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

  const putPlace = async (name) => {
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
      const response = await putTripPlan(route.value.tripPlan.id, name, userId, places);
      if (response.status === 200) {
        isEditing.value = false;
        return true;
      } else {
        throw new Error(response.status);
      }
    } catch (error) {
      return false;
    }
  };

  const deletePlace = async () => {
    try {
      const response = await deleteTripPlan(route.value.tripPlan.id);
      if (response.status === 200) {
        // remove the selected route from the list
        const index = routeList.value.findIndex(
          (val) => val.tripPlan.id === route.value.tripPlan.id
        );
        routeList.value.splice(index, 1);

        route.value.places = [];
        route.value.tripPlan = {};
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
      map[id].places[map[id].places.length - 1].order = place.order;
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
    routeList.value = [];

    isEditing.value = false;
  };

  return {
    route,
    routeList,
    isEditing,

    getLatLngList,

    selectRoute,
    postPlace,
    putPlace,
    deletePlace,
    getRouteList,

    flush,
  };
});
