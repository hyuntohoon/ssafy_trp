<script setup>
import { ref } from "vue";
import GlassSelect from "@/components/common/GlassSelect.vue";
import { useSearchStore } from "@/stores/search";
const storeSearch = useSearchStore();
const { sido, setSido, fetchSido, gugun, setGugun, fetchGugun, type, typeNames, setType, flush } =
  storeSearch;

const sidoOptions = ref([]);
const gugunOptions = ref([]);

sidoOptions.value = await fetchSido();
flush();

const changeSido = async (data) => {
  setSido(data);
  const res = await fetchGugun();
  gugunOptions.value = res;
};

const changeGugun = (data) => {
  setGugun(data);
};

const changeType = (data) => {
  setType(data);
};
</script>

<template>
  <div class="select-wrap">
    <GlassSelect
      style="margin-right: 0.5rem"
      placeHolder="시/도 선택"
      :value="sido"
      @change="changeSido($event.target.value)"
      :options="sidoOptions" />
    <GlassSelect
      style="margin-right: 0.5rem"
      placeHolder="구/군 선택"
      :value="gugun"
      @change="changeGugun($event.target.value)"
      :options="gugunOptions" />
    <GlassSelect
      style="margin-right: 0.5rem"
      placeHolder="타입 선택"
      :value="type"
      @change="changeType($event.target.value)"
      :options="typeNames" />
  </div>
</template>

<style scoped>
.select-wrap {
  margin-bottom: 1rem;
}
</style>
