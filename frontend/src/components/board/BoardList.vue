<script setup>
import BoardListItem from "@/components/board/item/BoardListItem.vue";
import BoardSearchItem from "@/components/board/item/BoardSearchItem.vue";
import GlassButton from "@/components/common/GlassButton.vue";

import { onMounted } from "vue";

import { useBoardStore } from "@/stores/board";
import { storeToRefs } from "pinia";

const useBoard = useBoardStore();
const { boardList, search, page } = storeToRefs(useBoard);
const { fetchBoardList, fetchSearchBoardList } = useBoard;

const fetch = async () => {
  if (search.value.key === "" || search.value.word === "") {
    await fetchBoardList(page.value, 10);
  } else {
    await fetchSearchBoardList(page.value, 10);
  }
};

onMounted(fetch);

const goNext = () => {
  page.value++;
  fetch();
};

const goPrev = () => {
  page.value--;
  fetch();
};
</script>

<template>
  <div>
    <BoardListItem :page="page" :search="search" />
    <BoardSearchItem />
    <!-- pagination -->
    <div class="pagination">
      <GlassButton v-if="page > 1" @click="goPrev">
        <template v-slot:content>
          <i class="bi bi-chevron-left"></i>
        </template>
      </GlassButton>
      <div class="page">{{ page }}</div>
      <GlassButton v-if="boardList.length === 10" @click="goNext">
        <template v-slot:content>
          <i class="bi bi-chevron-right"></i>
        </template>
      </GlassButton>
    </div>
  </div>
</template>

<style scoped>
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  align-items: center;
}

.page {
  margin: 0 1rem;
}
</style>
