<script setup>
import CourseItem from "@/components/course/CourseItem.vue";

import { onMounted } from "vue";

import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

import { useGptStore } from "@/stores/gpt";
const gptStore = useGptStore();
const { flush: flushGpt } = gptStore;

const routeStore = useRouteStore();
const { routeList } = storeToRefs(routeStore);
const { getRouteList, selectRoute } = routeStore;

onMounted(async () => {
  await getRouteList();
});

const select = (course) => {
  flushGpt();
  selectRoute(course);
};
</script>

<template>
  <div class="search-result">
    <h2 v-if="routeList.length === 0">저장된 코스가 없습니다.</h2>
    <CourseItem
      @click="select(course.tripPlan.id)"
      v-for="course in routeList"
      :key="course.tripPlan.id"
      :course="course" />
  </div>
</template>

<style scoped>
.search-result {
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  width: 25vw;
  border-radius: 10px;
  height: 70vh;
  overflow-y: auto;
  overflow-x: hidden;
}
</style>
