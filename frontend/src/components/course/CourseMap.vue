<script setup>
import { ref, watch } from "vue";
import { KakaoMap, KakaoMapMarker, KakaoMapPolyline } from "vue3-kakao-maps";

import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const routeStore = useRouteStore();
const { route, mobilityRoutes } = storeToRefs(routeStore);
const { route: routeData } = routeStore;

const currentFocus = ref(null);
const focus = ref({ lat: 37.5665, lng: 126.978, level: 8 });

const mouseOver = (marker) => {
  currentFocus.value = marker;
};

const mouseOut = () => {
  currentFocus.value = null;
};

watch(routeData, () => {
  if (route.value.places.length === 0) {
    focus.value = { lat: 37.5665, lng: 126.978, level: 8 };
    return;
  }
  let max = { lat: Number.MIN_SAFE_INTEGER, lng: Number.MIN_SAFE_INTEGER };
  let min = { lat: Number.MAX_SAFE_INTEGER, lng: Number.MAX_SAFE_INTEGER };
  for (const place of route.value.places) {
    max.lat = Math.max(max.lat, place.latitude);
    max.lng = Math.max(max.lng, place.longitude);
    min.lat = Math.min(min.lat, place.latitude);
    min.lng = Math.min(min.lng, place.longitude);
  }
  // calc level based on the distance between max and min
  focus.value = {
    lat: (max.lat + min.lat) / 2,
    lng: (max.lng + min.lng) / 2,
    level: 8,
  };
});
</script>

<template>
  <KakaoMap
    class="map-wrap"
    width="60vw"
    height="70vh"
    :lat="focus.lat"
    :lng="focus.lng"
    :level="focus.level"
    :draggable="true">
    <KakaoMapMarker
      v-for="result in route.places"
      :key="result.id"
      :lat="result.latitude"
      :lng="result.longitude"
      :title="result.title"
      :image="{
        imageSrc: 'https://ssafy.b-cdn.net/markers/' + result.contentTypeId + '.png',
        imageWidth: 30,
        imageHeight: 30,
        imageOption: {},
      }"
      :infoWindow="{
        content: `<div
          style='display: block;
          background: #50627F;
          color: #fff;
          line-height:22px;
          min-width: 200px;
          min-height: 50px;
          align-items: center;
          display: flex;
          border-radius:4px;
          margin: -10px;
          padding:0px 10px;'
        >
          <h4>${result.title}</h4>
        </div>`,
        visible: currentFocus === result.contentId,
      }"
      @mouseOverKakaoMapMarker="mouseOver(result.contentId)"
      @mouseOutKakaoMapMarker="mouseOut" />
    <KakaoMapPolyline
      :latLngList="mobilityRoutes"
      :endArrow="true"
      :strokeWeight="6"
      :strokeOpacity="1" />
  </KakaoMap>
</template>

<style scoped>
.map-wrap {
  border-radius: 10px;
  margin-right: 1rem;
}
</style>
