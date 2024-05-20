<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { getBoardList } from "@/api/board";

const router = useRouter();

// const props = defineProps({
//   data: Array,
// });

const data = ref([]);

const success = (response) => {
  if (response.status !== 200) {
    alert("문제가 발생했습니다.");
    return;
  } else {
    data.value = response.data;
    data.value.forEach((item) => {
      item.date = new Date(item.createTimeStamp).toLocaleDateString();
    });
  }
};
const fail = (error) => {
  alert("문제가 발생했습니다 : " + error);
};

getBoardList(success, fail);

const goDetail = (id) => {
  router.push({
    name: "board-detail",
    params: { id },
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
        <tr class="items" v-for="item in data" :key="item.id" @click="goDetail(item.postId)">
          <td>
            {{ item.postId }}
          </td>
          <td>{{ item.title }}</td>
          <td>{{ item.userId }}</td>
          <td>{{ item.date }}</td>
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
