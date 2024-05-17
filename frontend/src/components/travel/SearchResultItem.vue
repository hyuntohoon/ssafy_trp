<script setup>
const props = defineProps({
  result: Object,
});

import { useSearchStore } from "@/stores/search";
const searchStore = useSearchStore();

import { storeToRefs } from "pinia";
const { focus } = storeToRefs(searchStore);

const moveFocus = () => {
  focus.value = { lat: props.result.latitude, lng: props.result.longitude, level: 3 };
};
</script>

<template>
  <div class="result-wrap" @click="moveFocus">
    <h5>{{ result.title }}</h5>
    <span>{{ result.addr1 }} {{ result.addr2 }}</span>
    <div class="button-wrap">
      <button>
        <!-- information icon -->
        <i class="bi bi-info-circle"></i>
      </button>
      <button>
        <i class="bi bi-heart"></i>
      </button>
      <button>
        <i class="bi bi-plus-circle"></i>
      </button>
    </div>
  </div>
</template>

<style scoped>
.result-wrap {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 0.5rem;
  padding: 0.7rem 1rem;
  width: 22vw;
  border-radius: 10px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

button {
  background-color: #fff;
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
