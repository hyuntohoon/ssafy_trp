<script setup>
import GlassInput from "@/components/common/GlassInput.vue";
import GlassButton from "@/components/common/GlassButton.vue";

import { useSearchStore } from "@/stores/search";
import { storeToRefs } from "pinia";
const storeSearch = useSearchStore();

const { keyword } = storeToRefs(storeSearch);
const { search } = storeSearch;
</script>

<template>
  <div class="input-wrap">
    <GlassInput
      style="margin-right: 0.5rem"
      :value="keyword"
      @input="keyword = $event.target.value"
      placeholder="검색어를 입력하세요" />
    <GlassButton :isColored="true" @click="search">
      <template v-slot:content>
        <i class="bi bi-search"></i>
      </template>
    </GlassButton>
  </div>
</template>

<style scoped>
.input-wrap {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
