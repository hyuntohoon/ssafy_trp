<script setup>
import { defineProps } from "vue";

defineProps({
  onClick: Function,
  style: String,
  isColored: Boolean,
});
</script>

<template>
  <button :class="isColored ? 'glass-btn-colored' : 'glass-btn'" :style="style" @click="onClick">
    <slot name="content"></slot>
  </button>
</template>

<style scoped>
button.glass-btn {
  padding: 0.5rem 1rem;
  border-radius: 30px;
  font-weight: bold;
  font-size: 1rem;
  color: white;
  background-color: rgba(0, 0, 0, 0.5);
  border: none;
}

/* hover animation */
button.glass-btn:hover {
  background-color: rgba(0, 0, 0, 0.7);
  transition: 0.3s;
}

button.glass-btn:active {
  background-color: rgba(0, 0, 0, 0.9);
  transition: 0.1s;
}

button.glass-btn-colored {
  padding: 0.5rem 1rem;
  border-radius: 30px;
  font-weight: bold;
  font-size: 1rem;
  background-color: rgba(105, 255, 218, 0.5);
  border: none;
}

/* hover animation */
button.glass-btn-colored:hover {
  background-color: rgba(105, 255, 218, 0.7);
  transition: 0.3s;
}

button.glass-btn-colored:active {
  background-color: rgba(105, 255, 218, 0.9);
  transition: 0.1s;
}
</style>
