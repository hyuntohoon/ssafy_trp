<script setup>
import PlaceCard from "@/components/common/PlaceCard.vue";
import Swal from "sweetalert2";

import { useRouter } from "vue-router";
const router = useRouter();

import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const routeStore = useRouteStore();
const { route } = routeStore;
const routeData = storeToRefs(routeStore).route;

import { useSearchStore } from "@/stores/search";
import { watch } from "vue";
const searchStore = useSearchStore();

const { recommendedData, focus } = storeToRefs(searchStore);
const { fetchRecommendation } = searchStore;

const moveFocus = (data) => {
  focus.value = { lat: data.latitude, lng: data.longitude, level: 3 };
};

const addPlace = (data) => {
  const index = recommendedData.value.findIndex((place) => place.contentId === data.contentId);
  routeData.value.places.push(recommendedData.value[index]);

  // remove the place from the recommended list
  recommendedData.value.splice(index, 1);
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

watch(route, async (newValue) => {
  recommendedData.value = [];
  if (newValue.places.length <= 1) {
    return;
  }
  let keywordCount = {};
  let contentIdCount = {};
  for (const place of newValue.places) {
    const keywords = place.addr1.split(" ");
    for (let i = 1; i < keywords.length; i++) {
      if (keywordCount[keywords[i]]) {
        keywordCount[keywords[i]]++;
      } else {
        keywordCount[keywords[i]] = 1;
      }
    }
    if (contentIdCount[place.contentTypeId]) {
      contentIdCount[place.contentTypeId]++;
    } else {
      contentIdCount[place.contentTypeId] = 1;
    }
  }

  // get the most frequent keyword and contentId
  let maxKeyword = "";
  let maxKeywordCount = 0;
  let maxContentId = "";
  let maxContentIdCount = 0;
  for (const [key, value] of Object.entries(keywordCount)) {
    if (value > maxKeywordCount) {
      maxKeyword = key;
      maxKeywordCount = value;
    }
  }
  for (const [key, value] of Object.entries(contentIdCount)) {
    if (value > maxContentIdCount) {
      maxContentId = key;
      maxContentIdCount = value;
    }
  }

  // fetch recommendations based on the most frequent keyword and contentId
  await fetchRecommendation(maxKeyword, maxContentId);

  // remove the places that are already in the course
  for (const place of newValue.places) {
    const index = recommendedData.value.findIndex(
      (element) => element.contentId === place.contentId
    );
    if (index !== -1) {
      recommendedData.value.splice(index, 1);
    }
  }
});
</script>

<template>
  <div id="wrap">
    <h2>가볼만한 장소</h2>
    <hr />
    <h5 v-if="recommendedData.length === 0">여행 장소를 2곳 이상 선택 해 주세요!</h5>
    <div v-else>
      <div v-for="place in recommendedData" :key="place.id">
        <PlaceCard :data="place">
          <template v-slot:actions>
            <div style="display: flex; justify-content: end; align-items: end">
              <div class="button-wrap">
                <button @click="moveFocus(place)">
                  <i class="bi bi-geo-alt"></i>
                </button>
                <button @click="goWrite(place)">
                  <i class="bi bi-heart"></i>
                </button>
                <button @click="addPlace(place)">
                  <i class="bi bi-plus-circle"></i>
                </button>
              </div>
            </div>
          </template>
        </PlaceCard>
      </div>
    </div>
  </div>
</template>

<style scoped>
#wrap {
  margin-top: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  margin-right: 1rem;
  border-radius: 10px;
  overflow-y: auto;
  overflow-x: hidden;
  width: 40vw;
  height: 100%;
}

button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  font-size: 1.5rem;
}

button:hover {
  color: var(--accent-color);
  transition: color 0.5s;
}

button:active {
  color: var(--accent-color-dark-8);
}

.button-wrap {
  display: flex;
  justify-content: end;
}
</style>
