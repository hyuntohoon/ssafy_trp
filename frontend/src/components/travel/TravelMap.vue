<script setup>
import { ref, watch } from "vue";
import { KakaoMap, KakaoMapMarker } from "vue3-kakao-maps";

import { useSearchStore } from "@/stores/search";
import { storeToRefs } from "pinia";

const searchStore = useSearchStore();
const { resultData, focus } = storeToRefs(searchStore);

const currentFocus = ref(null);

const mouseOver = (marker) => {
  currentFocus.value = marker;
};

const mouseOut = () => {
  currentFocus.value = null;
};

// check resultData change, if change, update focus with avg lat, lng
watch(resultData, () => {
  if (resultData.value.length > 0) {
    let minLat = resultData.value[0].latitude;
    let maxLat = resultData.value[0].latitude;
    let minLng = resultData.value[0].longitude;
    let maxLng = resultData.value[0].longitude;
    let level = 8;
    const distance = Math.sqrt(Math.pow(maxLat - minLat, 2) + Math.pow(maxLng - minLng, 2));
    if (distance < 0.1) focus.value.level = 8;
    else if (distance < 0.5) focus.value.level = 7;
    else if (distance < 1) focus.value.level = 6;
    else if (distance < 2) focus.value.level = 5;
    else if (distance < 4) focus.value.level = 4;
    else if (distance < 8) focus.value.level = 3;
    else if (distance < 16) focus.value.level = 2;
    else if (distance < 32) focus.value.level = 1;
    const lat =
      resultData.value.reduce((acc, cur) => acc + cur.latitude, 0) / resultData.value.length;
    const lng =
      resultData.value.reduce((acc, cur) => acc + cur.longitude, 0) / resultData.value.length;
    // get proper level by distance
    resultData.value.forEach((result) => {
      if (result.latitude < minLat) minLat = result.latitude;
      if (result.latitude > maxLat) maxLat = result.latitude;
      if (result.longitude < minLng) minLng = result.longitude;
      if (result.longitude > maxLng) maxLng = result.longitude;
    });
    focus.value = { lat, lng, level };
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
  </KakaoMap>
</template>

<style scoped>
.map-wrap {
  border-radius: 10px;
  margin-right: 1rem;
}
</style>
