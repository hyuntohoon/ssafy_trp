<script setup>
import { defineProps, ref } from "vue";

const props = defineProps({
  images: Array,
});

const current = ref(0);

const next = () => {
  current.value = current.value === props.images.length - 1 ? 0 : current.value + 1;
};

const prev = () => {
  current.value = current.value === 0 ? props.images.length - 1 : current.value - 1;
};

const go = (idx) => {
  current.value = idx;
};
</script>

<template>
  <div class="wrap">
    <div class="slider-wrap">
      <div class="image-wrap" v-for="(image, idx) in images" :key="idx">
        <img :src="image" :style="{ transform: `translateX(-${current * 100}%)` }" />
      </div>
    </div>
    <div class="dot-wrap">
      <button @click="prev">
        <i class="bi bi-chevron-left"></i>
      </button>
      <div
        class="dot"
        v-for="(image, idx) in images"
        :key="idx"
        :class="{ active: idx === current }"
        @click="go(idx)"></div>
      <button @click="next">
        <i class="bi bi-chevron-right"></i>
      </button>
    </div>
  </div>
</template>

<style scoped>
.wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 30vw;
  height: 50vh;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  border-radius: 1rem;
  padding: 1rem;
  margin-bottom: 1rem;
}

.slider-wrap {
  height: 90%;
  width: 29vw;
  overflow: hidden;
  display: flex;
}

.image-wrap {
  width: 29vw;
  height: 100%;
  display: flex;
  transition: transform 0.5s;
}

.dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.5);
  margin: 0.5rem;
  cursor: pointer;
}

.active {
  background-color: rgba(0, 0, 0, 0.8);
}

.dot-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  margin-top: 0.5rem;
}

button {
  background-color: transparent;
  color: rgba(0, 0, 0, 0.5);
  border: none;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

button:hover {
  background-color: rgba(0, 0, 0, 0.1);
  animation: fadeIn 0.5s forwards;
}

button:active {
  background-color: rgba(0, 0, 0, 0.3);
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    background-color: rgba(0, 0, 0, 0.1);
  }
}

img {
  width: 29vw;
  height: 100%;
  object-fit: contain;
  transition: transform 0.5s;
}
</style>
