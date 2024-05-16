<script setup>
defineProps({
  placeHolder: String,
  value: String,
  onInput: Function,
});
</script>

<template>
  <textarea
    class="glass-input"
    :placeholder="placeHolder"
    :value="value"
    @input="onInput"></textarea>
</template>

<style scoped>
textarea {
  padding: 0.5rem 1rem;
  border-radius: 1rem;
  margin-left: 0.5rem;
  color: white;
  font-weight: 100;
  font-size: 1rem;
  background-color: rgba(0, 0, 0, 0.5);
  border: none;
  width: 100%;
  height: 10rem;
  resize: none;
}

textarea::placeholder {
  color: white;
}

textarea:enabled {
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
}
</style>
