<script setup>
import GlassButton from "@/components/common/GlassButton.vue";
import GlassInput from "@/components/common/GlassInput.vue";
import GlassTextArea from "@/components/common/GlassTextArea.vue";

import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";

// import { getBoard, editBoard } from "@/api/board";
import { useUserStore } from "@/stores/user";

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

const userId = ref(userStore.getUserId);

const id = ref(route.params.id);

const article = ref({});

// const success = (response) => {
//   if (response.status !== 200) {
//     alert("문제가 발생했습니다.");
//     return;
//   } else {
//     article.value = response.data;
//   }
// };
// const fail = (error) => {
//   alert("문제가 발생했습니다 : " + error);
// };

// getBoard(id.value, success, fail);

// const edit = () => {
//   const success = (response) => {
//     if (response.status !== 200) {
//       alert("문제가 발생했습니다.");
//       console.log(response.status);
//       return;
//     } else {
//       router.replace({
//         name: "board-detail",
//         params: { id: id.value },
//       });
//     }
//   };
//   const fail = (error) => {
//     alert("문제가 발생했습니다 : " + error);
//     console.log(error);
//   };
//   editBoard(userId.value, id.value, article.value.title, article.value.content, success, fail);
// };
</script>

<template>
  <div class="main-wrap">
    <!-- write article -->
    <GlassInput
      placeHolder="제목을 입력하세요"
      :value="article.title"
      @input="article.title = $event.target.value" />
    <div style="margin-bottom: 1rem"></div>
    <GlassTextArea
      placeHolder="내용을 입력하세요"
      :value="article.content"
      @input="article.content = $event.target.value" />
    <div style="margin-bottom: 1rem"></div>
    <GlassButton @click="edit">
      <template v-slot:content>
        <i class="bi bi-pencil"></i>&nbsp;
        <span>수정하기</span>
      </template>
    </GlassButton>
  </div>
</template>

<style scoped>
.main-wrap {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  border-radius: 1rem;
  padding: 1rem;
  margin-bottom: 1rem;
}
</style>
