<script setup>
defineProps({
  course: Object,
});

import { useRouteStore } from "@/stores/route";
const routeStore = useRouteStore();

import { storeToRefs } from "pinia";
const { route } = storeToRefs(routeStore);
</script>

<template>
  <div
    class="data-wrap"
    :style="{
      backgroundColor:
        route.tripPlan.id === course.tripPlan.id ? 'var(--secondary-color-light-5)' : '#fff',
    }">
    <h5>{{ course.tripPlan.name }}</h5>
    <span>생성자 : {{ course.tripPlan.userId }}</span>
  </div>
</template>

<style scoped>
.data-wrap {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 1rem 0.5rem;
  padding: 0.7rem 1rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 0.5rem;
}

button {
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 5px;
  color: #fff;
  cursor: pointer;
}

button#map {
  background-color: var(--primary-color);
}

button#edit {
  background-color: var(--secondary-color);
}

button#delete {
  background-color: var(--accent-color);
}

button:hover {
  filter: brightness(1.1);
}

button:active {
  filter: brightness(0.9);
}

button i {
  font-size: 1.2rem;
}
</style>
