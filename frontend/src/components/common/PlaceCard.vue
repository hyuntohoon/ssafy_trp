<script setup>
defineProps({
  data: Object,
  color: Boolean,
});
</script>

<template>
  <!-- if color true, make data-wrap div color red -->
  <div
    class="data-wrap"
    :style="{ backgroundColor: color ? 'var(--secondary-color-light-5)' : '#fff' }">
    <h5>{{ data.title }}</h5>
    <span>{{ data.addr1 }} {{ data.addr2 }}</span>
    <slot name="actions"></slot>
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
}
</style>
