<script setup>
import BoardArticle from "@/components/board/item/BoardArticle.vue";

import { useBoardStore } from "@/stores/board";
import { storeToRefs } from "pinia";

const { board } = storeToRefs(useBoardStore());
</script>

<template>
  <div>
    <BoardArticle :article="board" />
    <!-- <BoardComment :comments="comments" /> -->
  </div>
</template>

<style scoped></style>
