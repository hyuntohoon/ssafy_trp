<script setup>
import PlaceCard from "@/components/common/PlaceCard.vue";

import { useSearchStore } from "@/stores/search";
const searchStore = useSearchStore();

import { useRouteStore } from "@/stores/route";
const routeStore = useRouteStore();

import { storeToRefs } from "pinia";

const { resultData, focus } = storeToRefs(searchStore);
const { placeList } = storeToRefs(routeStore);

const moveFocus = (data) => {
  focus.value = { lat: data.latitude, lng: data.longitude, level: 3 };
};

const addPlace = (data) => {
  if (placeList.value.some((place) => place.contentId === data.contentId)) {
    return;
  }
  placeList.value.push(data);
};

const checkPlace = (data) => {
  return placeList.value.some((place) => place.contentId === data.contentId);
};
</script>

<template>
  <div class="search-result">
    <h2 v-if="resultData.length === 0">검색 결과가 없습니다.</h2>
    <PlaceCard
      v-for="result in resultData"
      :key="result.contentId"
      :data="result"
      :color="checkPlace(result)">
      <template v-slot:actions>
        <div class="button-wrap">
          <button @click="moveFocus(result)">
            <i class="bi bi-geo-alt"></i>
          </button>
          <button>
            <i class="bi bi-heart"></i>
          </button>
          <button @click="addPlace(result)">
            <i class="bi bi-plus-circle"></i>
          </button>
        </div>
      </template>
    </PlaceCard>
  </div>
</template>

<style scoped>
.search-result {
  margin-top: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  width: 25vw;
  border-radius: 10px;
  height: 54.7vh;
  overflow-y: auto;
  overflow-x: hidden;
}

button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  font-size: 1.5rem;
}

button:hover {
  color: var(--accent-color);
  transition: color 0.3s;
}

button:active {
  color: var(--accent-color-dark-8);
}

.button-wrap {
  display: flex;
  justify-content: end;
  margin-top: 1rem;
}
</style>
