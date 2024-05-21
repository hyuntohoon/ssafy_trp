<script setup>
import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const routeStore = useRouteStore();
const { route } = routeStore;

import { useSearchStore } from "@/stores/search";
import { watch } from "vue";
const searchStore = useSearchStore();

const { recommendedData } = storeToRefs(searchStore);
const { fetchRecommendation } = searchStore;

// watch(route, async (newValue) => {
//   if (newValue.places.length === 0) {
//     return;
//   }
//   console.log("update suggestions");
//   let keywordCount = {};
//   let contentIdCount = {};
//   for (const place of newValue.places) {
//     const keywords = place.split(" ");
//     for (let i = 1; i < keywords.length; i++) {
//       if (keywordCount[keywords[i]]) {
//         keywordCount[keywords[i]]++;
//       } else {
//         keywordCount[keywords[i]] = 1;
//       }
//     }
//     if (contentIdCount[place.contentId]) {
//       contentIdCount[place.contentId]++;
//     } else {
//       contentIdCount[place.contentId] = 1;
//     }
//   }

//   // get the most frequent keyword and contentId
//   let maxKeyword = "";
//   let maxKeywordCount = 0;
//   let maxContentId = "";
//   let maxContentIdCount = 0;
//   for (const [key, value] of Object.entries(keywordCount)) {
//     if (value > maxKeywordCount) {
//       maxKeyword = key;
//       maxKeywordCount = value;
//     }
//   }
//   for (const [key, value] of Object.entries(contentIdCount)) {
//     if (value > maxContentIdCount) {
//       maxContentId = key;
//       maxContentIdCount = value;
//     }
//   }

//   // fetch recommendations based on the most frequent keyword and contentId
//   await fetchRecommendation(maxKeyword, maxContentId);
// });
</script>

<template>
  <div id="wrap">
    <h2>가볼만한 장소</h2>
    <hr />
    <h5 v-if="recommendedData.length === 0">여행 장소를 선택 해 주세요!</h5>
    <div v-else>
      <div v-for="place in suggestions" :key="place.id">
        <h3>{{ place.name }}</h3>
        <p>{{ place.address }}</p>
        <p>{{ place.category }}</p>
        <p>{{ place.phone }}</p>
        <p>{{ place.description }}</p>
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
  width: 20vw;
  height: 100%;
}
</style>
