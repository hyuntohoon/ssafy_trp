<script setup>
import { KakaoMap } from "vue3-kakao-maps";

const coordinate = {
  lat: 37.566826,
  lng: 126.9786567,
};
</script>

<template>
  <KakaoMap
    class="map-wrap"
    width="60vw"
    height="70vh"
    :lat="coordinate.lat"
    :lng="coordinate.lng"
    :draggable="true">
  </KakaoMap>
</template>

<style scoped>
.map-wrap {
  border-radius: 10px;
  margin-right: 1rem;
}
</style>
