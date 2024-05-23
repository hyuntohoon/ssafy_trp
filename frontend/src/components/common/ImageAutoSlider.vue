<script setup>
import { defineProps, ref, onMounted } from "vue";

const props = defineProps({
  images: Array,
});

// auto slide with time interval
const current = ref(0);
const interval = ref(null);

onMounted(() => {
  interval.value = setInterval(() => {
    current.value = current.value === props.images.length - 1 ? 0 : current.value + 1;
  }, 5000);
});
</script>

<template>
  <div class="slider-wrap">
    <div class="image-wrap" v-for="(image, idx) in images" :key="idx">
      <img :src="image" :style="{ transform: `translateX(-${current * 100}%)` }" />
    </div>
  </div>
</template>

<style scoped>
.slider-wrap {
  width: 100%;
  overflow: hidden;
  display: flex;
  border-radius: 1rem;
}

.image-wrap {
  width: 70vw;
  display: flex;
  transition: transform 1s ease-in-out;
  border-radius: 1rem;
}

img {
  width: 68vw;
  object-fit: fill;
  transition: transform 1s ease-in-out;
  border-radius: 1rem;
}
</style>
