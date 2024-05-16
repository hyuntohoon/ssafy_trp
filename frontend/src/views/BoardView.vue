<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

import { useUserStore } from "@/stores/user";
import { deleteBoard } from "@/api/board";

import Navigator from "@/components/Navigator.vue";
import GlassButton from "@/components/common/GlassButton.vue";

const router = useRouter();

const userStore = useUserStore();

const userId = ref(userStore.getUserId);

const goCreate = () => {
  router.push({
    name: "board-create",
  });
};

const goEdit = () => {
  router.push({
    name: "board-edit",
    params: {
      id: router.currentRoute.value.params.id,
    },
  });
};

const remove = () => {
  const success = (response) => {
    if (response.status !== 204) {
      alert("문제가 발생했습니다.");
      console.log(response.status);
      return;
    } else {
      alert("삭제되었습니다.");
      router.replace({
        name: "board-list",
      });
    }
  };
  const fail = (error) => {
    alert("문제가 발생했습니다 : " + error);
  };
  const id = router.currentRoute.value.params.id;

  const answer = confirm("삭제하시겠습니까?");
  if (!answer) {
    return;
  }
  deleteBoard(userId.value, id, success, fail);
};
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
    <RouterView />
  </div>
</template>

<style scoped></style>
