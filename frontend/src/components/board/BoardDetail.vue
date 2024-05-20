<script setup>
import BoardArticle from "@/components/board/item/BoardArticle.vue";

import { ref } from "vue";
import { useRoute } from "vue-router";

import { getBoard } from "@/api/board";

const route = useRoute();

const id = ref(route.params.id);

const article = ref({
  user: {},
});

const success = (response) => {
  if (response.status !== 200) {
    alert("문제가 발생했습니다.");
    return;
  } else {
    article.value = response.data;
    article.value.date = new Date(article.value.createTimeStamp).toLocaleDateString();
  }
};
const fail = (error) => {
  alert("문제가 발생했습니다 : " + error);
};

getBoard(id.value, success, fail);

// const comments = ref([
//   {
//     id: 1,
//     writer: "작성자1",
//     content: "댓글1",
//     date: "2021-09-01",
//   },
//   {
//     id: 2,
//     writer: "작성자2",
//     content: "댓글2",
//     date: "2021-09-02",
//   },
//   {
//     id: 3,
//     writer: "작성자3",
//     content: "댓글3",
//     date: "2021-09-03",
//   },
// ]);
</script>

<template>
  <div>
    <BoardArticle :article="article" />
    <!-- <BoardComment :comments="comments" /> -->
  </div>
</template>

<style scoped></style>
