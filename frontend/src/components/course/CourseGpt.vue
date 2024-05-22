<script setup>
import { useGptStore } from "@/stores/gpt";
const gptStore = useGptStore();

import { storeToRefs } from "pinia";
import { ref } from "vue";
const { gpt, isLoading } = storeToRefs(gptStore);

const loadingTexts = [
  "소라고동님에게 정중히 노크하는 중...",
  "소라고동님의 답변을 기다리는 중...",
  "이해하지 못한 부분을 다시 물어보는 중...",
  "모니터에 색칠하는 중...",
  "삶, 우주, 그리고 모든 것에 대한 해답을 찾는 중...",
  "Null Safe한 여정을 떠나는 중...",
  "진저에일 한 잔 마시는 중...",
];
const curIdx = ref(0);

setInterval(() => {
  // random
  let tmp;
  do {
    tmp = Math.floor(Math.random() * loadingTexts.length);
  } while (curIdx.value === tmp);
  curIdx.value = tmp;
}, 3000);
</script>

<template>
  <div>
    <div class="wrap" v-if="gpt.response !== ''">
      <h2>마법의 소라고동님의 답변</h2>
      <hr />
      <div v-html="gpt.response"></div>
    </div>
    <div v-if="isLoading" class="load-screen">
      <div class="load-items">
        <h2>
          {{ loadingTexts[curIdx] }}
        </h2>
        <div class="spinner-border" role="status">
          <span class="visually-hidden">Loading...</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.wrap {
  margin-top: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  border-radius: 10px;
  overflow-y: auto;
  overflow-x: hidden;
}

.load-screen {
  display: fixed;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.5);
  z-index: 1000;
  backdrop-filter: blur(10px);
}

.load-items {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
