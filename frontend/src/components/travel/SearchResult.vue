<script setup>
import Swal from "sweetalert2";
import PlaceCard from "@/components/common/PlaceCard.vue";

import { useRouter } from "vue-router";
const router = useRouter();

import { useSearchStore } from "@/stores/search";
const searchStore = useSearchStore();

import { useRouteStore } from "@/stores/route";
const routeStore = useRouteStore();

import { storeToRefs } from "pinia";

const { resultData, focus } = storeToRefs(searchStore);
const { route } = storeToRefs(routeStore);

const moveFocus = (data) => {
  focus.value = { lat: data.latitude, lng: data.longitude, level: 3 };
};

const addPlace = (data) => {
  if (route.value.places.some((place) => place.contentId === data.contentId)) {
    return;
  }
  route.value.places.push(data);
};

const removePlace = (data) => {
  const index = route.value.places.findIndex((place) => place.contentId === data.contentId);
  if (index === -1) {
    return;
  }
  route.value.places.splice(index, 1);
};

const checkPlace = (data) => {
  return route.value.places.some((place) => place.contentId === data.contentId);
};

const goWrite = (place) => {
  Swal.fire({
    title: "장소 후기 작성",
    text: "장소와 관련된 후기를 작성하러 이동하시겠습니까?",
    icon: "question",
    showCancelButton: true,
    confirmButtonText: "이동",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      router.push({
        name: "board-create",
        query: {
          contentId: place.contentId,
          title: place.title,
        },
      });
    }
  });
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
          <button @click="goWrite(result)">
            <i class="bi bi-heart"></i>
          </button>
          <button @click="addPlace(result)" v-if="!checkPlace(result)">
            <i class="bi bi-plus-circle"></i>
          </button>
          <button @click="removePlace(result)" v-else>
            <i class="bi bi-x-circle"></i>
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
