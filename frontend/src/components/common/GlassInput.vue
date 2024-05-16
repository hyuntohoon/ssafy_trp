<script setup>
defineProps({
  placeHolder: String,
  value: String,
  onInput: Function,
});
</script>

<template>
  <input class="glass-input" :placeholder="placeHolder" :value="value" @input="onInput" />
</template>

<style scoped>
input {
  padding: 0.5rem 1rem;
  border-radius: 30px;
  color: white;
  font-weight: 100;
  font-size: 1rem;
  background-color: rgba(0, 0, 0, 0.5);
  border: none;
  width: 100%;
}

input::placeholder {
  color: white;
}

input:enabled {
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
}
</style>
