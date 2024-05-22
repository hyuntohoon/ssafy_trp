<script setup>
import GlassButton from "@/components/common/GlassButton.vue";
import GlassInput from "@/components/common/GlassInput.vue";
import GlassSelect from "@/components/common/GlassSelect.vue";

import { useBoardStore } from "@/stores/board";
import { storeToRefs } from "pinia";

const useBoard = useBoardStore();
const { fetchBoardList, fetchSearchBoardList } = useBoard;
const { search, page } = storeToRefs(useBoard);

const fetch = async () => {
  page.value = 1;
  if (search.value.key === "" || search.value.word === "") {
    search.value.key = "";
    search.value.word = "";
    await fetchBoardList(page.value, 10);
  } else {
    await fetchSearchBoardList(page.value, 10);
  }
};
</script>

<template>
  <div>
    <GlassSelect
      placeHolder="검색 조건"
      :options="['제목', '작성자', '내용']"
      :value="search.key"
      @change="search.key = $event.target.value" />
    <span style="margin-left: 0.5rem"></span>
    <GlassInput
      placeHolder="검색어를 입력하세요"
      :value="search.word"
      @input="search.word = $event.target.value" />
    <span style="margin-left: 0.5rem"></span>
    <GlassButton @click="fetch()">
      <template v-slot:content>
        <i class="bi bi-search"></i>
      </template>
    </GlassButton>
  </div>
</template>

<style scoped>
div {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  backdrop-filter: blur(20px);
  padding: 0.5rem;
  border-radius: 1rem;
  margin-bottom: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
}
</style>
