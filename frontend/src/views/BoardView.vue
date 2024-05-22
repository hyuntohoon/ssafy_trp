<script setup>
import { useRouter } from "vue-router";

import { useBoardStore } from "@/stores/board";
const useBoard = useBoardStore();

import { storeToRefs } from "pinia";
const { board } = storeToRefs(useBoard);
const { removeBoard } = useBoard;

import Swal from "sweetalert2";

import Navigator from "@/components/Navigator.vue";
import GlassButton from "@/components/common/GlassButton.vue";

const router = useRouter();

const goCreate = () => {
  router.push({
    name: "board-create",
  });
};

const remove = async () => {
  const targetId = board.value.postID;

  Swal.fire({
    title: "게시글을 삭제하시겠습니까?",
    text: "삭제된 게시글은 복구할 수 없습니다.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "삭제",
    cancelButtonText: "취소",
  }).then(async (result) => {
    if (result.isConfirmed) {
      const response = await removeBoard(targetId);
      if (response) {
        Swal.fire({
          title: "삭제 완료",
          text: "게시글이 삭제되었습니다.",
          icon: "success",
          confirmButtonText: "확인",
        });
        router.replace({
          name: "board-list",
        });
      } else {
        Swal.fire({
          title: "삭제 실패",
          text: "게시글 삭제에 실패했습니다.",
          icon: "error",
          confirmButtonText: "확인",
        });
      }
    }
  });
};

// const goEdit = () => {
//   router.push({
//     name: "board-edit",
//     params: {
//       id: router.currentRoute.value.params.id,
//     },
//   });
// };

// const remove = () => {
//   const success = (response) => {
//     if (response.status !== 204) {
//       alert("문제가 발생했습니다.");
//       console.log(response.status);
//       return;
//     } else {
//       alert("삭제되었습니다.");
//       router.replace({
//         name: "board-list",
//       });
//     }
//   };
//   const fail = (error) => {
//     alert("문제가 발생했습니다 : " + error);
//   };
//   const id = router.currentRoute.value.params.id;

//   const answer = confirm("삭제하시겠습니까?");
//   if (!answer) {
//     return;
//   }
//   deleteBoard(userId.value, id, success, fail);
// };
</script>

<template>
  <div>
    <Navigator>
      <template v-slot:title>
        <h3>게시판</h3>
      </template>
      <template v-slot:actions>
        <GlassButton @click="goCreate" v-if="router.currentRoute.value.name === `board-list`">
          <template v-slot:content>
            <i class="bi bi-pencil"></i>&nbsp;
            <span>글쓰기</span>
          </template>
        </GlassButton>
        <div v-if="router.currentRoute.value.name === `board-detail`">
          <GlassButton @click="goEdit">
            <template v-slot:content>
              <i class="bi bi-pencil"></i>&nbsp;
              <span>수정</span>
            </template>
          </GlassButton>
          <span style="margin-right: 1rem"></span>
          <GlassButton @click="remove">
            <template v-slot:content>
              <i class="bi bi-trash"></i>&nbsp;
              <span>삭제</span>
            </template>
          </GlassButton>
        </div>
      </template>
    </Navigator>
    <router-view v-slot="{ Component }">
      <transition name="scale" mode="out-in">
        <component :is="Component" />
      </transition>
    </router-view>
  </div>
</template>

<style scoped>
.scale-enter-active,
.scale-leave-active {
  transition: all 0.3s ease;
}

.scale-enter-from,
.scale-leave-to {
  opacity: 0;
  transform: scale(0.9);
}
</style>
