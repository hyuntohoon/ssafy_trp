<script setup>
import { ref, watch } from "vue";
import { KakaoMap, KakaoMapMarker, KakaoMapPolyline } from "vue3-kakao-maps";

import { useSearchStore } from "@/stores/search";
import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const { fetchMobilityRoutes } = useRouteStore();
const { mobilityRoutes } = storeToRefs(useRouteStore());

const searchStore = useSearchStore();
const routeStore = useRouteStore();
const { resultData, focus } = storeToRefs(searchStore);
const { route } = storeToRefs(routeStore);

const currentFocus = ref(null);

const mouseOver = (marker) => {
  currentFocus.value = marker;
};

const mouseOut = () => {
  currentFocus.value = null;
};

const calcFocus = () => {
  if (resultData.value.length === 0) {
    return;
  }
  let midLat = 0;
  let midLng = 0;
  let max = { lat: -90, lng: -180 };
  let min = { lat: 90, lng: 180 };
  for (let i = 0; i < resultData.value.length; i++) {
    midLat += resultData.value[i].latitude;
    midLng += resultData.value[i].longitude;
    max.lat = Math.max(max.lat, resultData.value[i].latitude);
    max.lng = Math.max(max.lng, resultData.value[i].longitude);
    min.lat = Math.min(min.lat, resultData.value[i].latitude);
    min.lng = Math.min(min.lng, resultData.value[i].longitude);
  }
  focus.value.lat = midLat / resultData.value.length;
  focus.value.lng = midLng / resultData.value.length;

  const distance = Math.max(max.lat - min.lat, max.lng - min.lng);

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

  focus.value.level = 8;
};

if (route !== null) {
  calcFocus();
}

// check resultData change, if change, update focus with avg lat, lng
watch(resultData, () => {
  calcFocus();
});

// fetch mobility routes
watch(routeStore.route, () => {
  fetchMobilityRoutes();
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
      v-for="result in resultData"
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
