<script setup>
import Navigator from "@/components/Navigator.vue";
import GlassSelect from "@/components/common/GlassSelect.vue";
import GlassInput from "@/components/common/GlassInput.vue";
import GlassButton from "@/components/common/GlassButton.vue";
import { KakaoMap } from "vue3-kakao-maps";
import { ref } from "vue";
import { getSido, getGugun } from "@/api/area";

const coordinate = {
  lat: 37.566826,
  lng: 126.9786567,
};

const sido = ref("");
const gugun = ref("");
const type = ref("");
const keyWord = ref("");

const data = ref({
  sido: [],
  gugun: [],
  type: [],
});

const success = (response) => {
  if (response.status !== 200) {
    alert("문제가 발생했습니다.");
    return;
  } else {
    response.data.forEach((item) => {
      data.value.sido.push(item.sidoName);
    });
  }
};
const fail = (error) => {
  alert("문제가 발생했습니다 : " + error);
};

getSido(success, fail);

const getGugun = (sidoCode) => {
  const success = (response) => {
    if (response.status !== 200) {
      alert("문제가 발생했습니다.");
      return;
    } else {
      response.data.forEach((item) => {
        data.value.gugun.push(item.gugunName);
      });
    }
  };
};
</script>

<template>
  <div>
    <Navigator>
      <template v-slot:title>
        <h3>여행지 찾기</h3>
      </template>
    </Navigator>
    <div class="row-wrap">
      <KakaoMap
        class="map-wrap"
        width="60vw"
        height="70vh"
        :lat="coordinate.lat"
        :lng="coordinate.lng"
        :draggable="true">
      </KakaoMap>
      <div>
        <div class="search-wrap">
          <div class="select-wrap">
            <GlassSelect
              style="margin-right: 0.5rem"
              placeHolder="시/도 선택"
              :value="sido"
              @change="sido = $event.target.value"
              :options="data.sido" />
            <GlassSelect
              style="margin-right: 0.5rem"
              placeHolder="구/군 선택"
              :value="gugun"
              @change="gugun = $event.target.value"
              :options="data.gugun" />
            <GlassSelect
              style="margin-right: 0.5rem"
              placeHolder="타입 선택"
              :value="type"
              @change="type = $event.target.value"
              :options="data.type" />
          </div>
          <div class="input-wrap">
            <GlassInput
              style="margin-right: 0.5rem"
              :value="keyWord"
              @input="keyWord = $event.target.value"
              placeholder="검색어를 입력하세요" />
            <GlassButton :isColored="true">
              <template v-slot:content>
                <i class="bi bi-search"></i>
              </template>
            </GlassButton>
          </div>
        </div>
        <div class="search-result">
          <h3>검색 결과</h3>
          <div>
            <p>검색 해주세용</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.row-wrap {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
}
.map-wrap {
  border-radius: 10px;
  margin-right: 1rem;
}

.search-wrap {
  display: flex;
  width: 25vw;
  flex-direction: column;
  justify-content: center;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  border-radius: 10px;
}

.select-wrap {
  margin-bottom: 1rem;
}

.input-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
}

.search-result {
  margin-top: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  border-radius: 10px;
}
</style>
