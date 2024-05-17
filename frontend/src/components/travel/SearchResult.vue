<script setup>
import SearchResultItem from "@/components/travel/SearchResultItem.vue";
import { useSearchStore } from "@/stores/search";

const searchStore = useSearchStore();

import { storeToRefs } from "pinia";

const { resultData } = storeToRefs(searchStore);
</script>

<template>
  <div class="search-result">
    <h2 v-if="resultData.length === 0">검색 결과가 없습니다.</h2>
    <SearchResultItem v-for="data in resultData" :key="data.id" :result="data" />
    <!-- <ul>
      <li v-for="result in resultData" :key="result.id">
        <p>{{ result.addr1 }}</p>
        <p>{{ result.addr2 }}</p>
        <p>{{ result.zipcode }}</p>
        <p>{{ result.title }}</p>
        <p>{{ result.type }}</p>
        <p>{{ result.tel }}</p>
        <p>{{ result.latitude }}</p>
        <p>{{ result.longitude }}</p>
      </li>
    </ul> -->
  </div>
</template>

<style scoped>
.search-result {
  margin-top: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  border-radius: 10px;
  height: 54.7vh;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>
