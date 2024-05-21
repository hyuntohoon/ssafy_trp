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
    <div class="row-wrap">
      <!-- if data has firstImage, show it -->
      <img src="https://placekitten.com/100/100" alt="place" v-if="!data.firstImage" />
      <img :src="data.firstImage" alt="place" v-else />
      <div style="width: 100%">
        <h5>{{ data.title }}</h5>
        <span>{{ data.addr1 }} {{ data.addr2 }}</span>
        <slot name="actions"></slot>
      </div>
    </div>
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

.row-wrap {
  display: flex;
  flex-direction: row;
}

img {
  width: 100px;
  height: 100px;
  object-fit: fill;
  border-radius: 10px;
  margin-right: 1rem;
}
</style>
