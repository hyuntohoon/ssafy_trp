<script setup>
import GlassInput from "@/components/common/GlassInput.vue";
import GlassButton from "@/components/common/GlassButton.vue";

import { useSearchStore } from "@/stores/search";
const storeSearch = useSearchStore();

const { keyWord, setKeyWord, search } = storeSearch;

const input = (e) => {
  setKeyWord(e.target.value);
};
</script>

<template>
  <div class="input-wrap">
    <GlassInput
      style="margin-right: 0.5rem"
      :value="keyWord"
      @input="input"
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
