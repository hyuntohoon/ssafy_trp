<script setup>
import { defineProps, ref } from "vue";

const props = defineProps({
  placeHolder: String,
  value: String,
  onChange: Function,
  options: Array,
});

const selected = ref(props.options[0]);
</script>

<template>
  <select class="glass-select" :value="value" @change="onChange">
    <option disabled value="">{{ placeHolder }}</option>
    <option v-for="option in options" :key="option" :value="option" :selected="option == selected">
      {{ option }}
    </option>
  </select>
</template>

<style scoped>
select.glass-select {
  padding: 0.5rem 0.5rem;
  border-radius: 30px;
  color: white;
  font-weight: 100;
  font-size: 1rem;
  background-color: rgba(0, 0, 0, 0.5);
  border: none;
}
</style>
