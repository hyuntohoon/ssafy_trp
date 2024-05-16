<script setup>
import { ref } from "vue";
import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";

const coordinate = {
  lat: 37.566826,
  lng: 126.9786567,
};

const markers = ref([
  {
    lat: 37.566826,
    lng: 126.9786567,
    title: "서울특별시청",
  },
  {
    lat: 37.566826,
    lng: 126.9786567,
    title: "서울특별시청",
  },
  {
    lat: 37.566826,
    lng: 126.9786567,
    title: "서울특별시청",
  },
  {
    lat: 37.566826,
    lng: 126.9786567,
    title: "서울특별시청",
  },
  {
    lat: 37.566826,
    lng: 126.9786567,
    title: "서울특별시청",
  },
]);
</script>

<template>
  <KakaoMap
    class="map-wrap"
    width="60vw"
    height="70vh"
    :lat="coordinate.lat"
    :lng="coordinate.lng"
    :draggable="true">
    <KakaoMapMarker
      v-for="(marker, index) in markers"
      :key="index"
      :lat="marker.lat"
      :lng="marker.lng"></KakaoMapMarker>
  </KakaoMap>
</template>

<style scoped>
.map-wrap {
  border-radius: 10px;
  margin-right: 1rem;
}
</style>
