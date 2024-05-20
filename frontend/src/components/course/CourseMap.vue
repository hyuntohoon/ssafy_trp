<script setup>
import { ref, watch } from "vue";
import { KakaoMap, KakaoMapMarker, KakaoMapPolyline } from "vue3-kakao-maps";

import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const routeStore = useRouteStore();
const { getLatLngList, route } = storeToRefs(routeStore);

const currentFocus = ref(null);
const focus = ref({ lat: 37.5665, lng: 126.978, level: 8 });

const mouseOver = (marker) => {
  currentFocus.value = marker;
};

const mouseOut = () => {
  currentFocus.value = null;
};

watch(getLatLngList, () => {
  let midLat = 0;
  let midLng = 0;
  let max = { lat: -90, lng: -180 };
  let min = { lat: 90, lng: 180 };
  for (let i = 0; i < getLatLngList.value.length; i++) {
    console.log(getLatLngList.value[i]);
    midLat += getLatLngList.value[i].lat;
    midLng += getLatLngList.value[i].lng;
    max.lat = Math.max(max.lat, getLatLngList.value[i].lat);
    max.lng = Math.max(max.lng, getLatLngList.value[i].lng);
    min.lat = Math.min(min.lat, getLatLngList.value[i].lat);
    min.lng = Math.min(min.lng, getLatLngList.value[i].lng);
  }
  focus.value.lat = midLat / getLatLngList.value.length;
  focus.value.lng = midLng / getLatLngList.value.length;

  const distance = Math.max(max.lat - min.lat, max.lng - min.lng);

  console.log(distance);
  // distance 0.1989699099999953 == 9
  // distance 0.026144900000005578 == 6

  if (distance < 0.03) {
    focus.value.level = 6;
  } else if (distance < 0.05) {
    focus.value.level = 7;
  } else if (distance < 0.1) {
    focus.value.level = 8;
  } else if (distance < 0.2) {
    focus.value.level = 9;
  } else {
    focus.value.level = 10;
  }
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
      :latLngList="getLatLngList"
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
