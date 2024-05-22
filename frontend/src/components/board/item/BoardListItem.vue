<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

import { useBoardStore } from "@/stores/board";
import { storeToRefs } from "pinia";

const useBoard = useBoardStore();
const { boardList, board } = storeToRefs(useBoard);

const goDetail = (idx) => {
  board.value = boardList.value[idx];
  router.push({
    name: "board-detail",
  });
};
</script>

<template>
  <div class="table-wrap">
    <table>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr class="items" v-for="(item, idx) in boardList" :key="item.id" @click="goDetail(idx)">
          <td>
            {{ item.postID }}
          </td>
          <td>{{ item.title }}</td>
          <td>{{ item.userID }}</td>
          <td>{{ new Date(item.createTimeStamp).toISOString().substring(0, 10) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
a {
  color: black;
  text-decoration: none;
}
.table-wrap {
  width: 100%;
  overflow-x: auto;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  border-radius: 1rem;
  margin-bottom: 1rem;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th {
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  padding: 0.7rem;
}
.items {
  cursor: pointer;
}
td {
  padding: 0.7rem;
}
tr:nth-child(even) {
  background-color: rgba(0, 0, 0, 0.1);
}
tr:hover {
  background-color: rgba(0, 0, 0, 0.2);
}
</style>
