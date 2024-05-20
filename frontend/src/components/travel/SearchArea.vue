<script setup>
import { ref, onMounted } from "vue";
import GlassSelect from "@/components/common/GlassSelect.vue";

const errorMsg = ref("");

import { useSearchStore } from "@/stores/search";
const storeSearch = useSearchStore();
import { storeToRefs } from "pinia";

const { sido, sidoNames, gugunNames, gugun, type, typeNames } = storeToRefs(storeSearch);
const { fetchSido, fetchGugun, flush } = storeSearch;

onMounted(async () => {
  const res = await fetchSido();
  flush();
  if (!res) {
    errorMsg.value = "데이터를 불러오는데 실패했습니다.";
  } else {
    errorMsg.value = "";
  }
});

const changeSido = async (data) => {
  sido.value = data;
  const res = await fetchGugun();
  if (!res) {
    errorMsg.value = "데이터를 불러오는데 실패했습니다.";
  } else {
    errorMsg.value = "";
  }
};
</script>

<template>
  <div class="select-wrap">
    <GlassSelect
      style="margin-right: 0.5rem"
      placeHolder="시/도 선택"
      :value="sido"
      @change="changeSido($event.target.value)"
      :options="sidoNames" />
    <GlassSelect
      style="margin-right: 0.5rem"
      placeHolder="구/군 선택"
      :value="gugun"
      @change="gugun = $event.target.value"
      :options="gugunNames" />
    <GlassSelect
      style="margin-right: 0.5rem"
      placeHolder="타입 선택"
      :value="type"
      @change="type = $event.target.value"
      :options="typeNames" />
  </div>
</template>

<style scoped>
.select-wrap {
  margin-bottom: 1rem;
}
</style>
