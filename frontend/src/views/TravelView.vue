<script setup>
import TravelMap from "@/components/travel/TravelMap.vue";
import SearchArea from "@/components/travel/SearchArea.vue";
import SearchInput from "@/components/travel/SearchInput.vue";
import SearchResult from "@/components/travel/SearchResult.vue";
import TemporalCourse from "@/components/travel/TemporalCourse.vue";
import HotPlace from "@/components/travel/HotPlace.vue";

import Navigator from "@/components/Navigator.vue";
</script>

<template>
  <div>
    <Navigator>
      <template v-slot:title>
        <h3>여행지 찾기</h3>
      </template>
    </Navigator>
    <div class="row-wrap">
      <TravelMap />
      <div>
        <div class="search-wrap">
          <SearchArea />
          <SearchInput />
        </div>
        <SearchResult />
      </div>
    </div>
    <div class="row-wrap">
      <HotPlace />
      <TemporalCourse />
    </div>
  </div>
</template>

<style scoped>
.row-wrap {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
}

.search-wrap {
  display: flex;
  width: 25vw;
  flex-direction: column;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  border-radius: 10px;
}
</style>
