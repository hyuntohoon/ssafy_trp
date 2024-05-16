<script setup>
import { ref, watch } from "vue";
import { useRouter } from "vue-router";

import { useUserStore } from "@/stores/user";
import { signOut } from "@/api/user";

import AfterLoginGroup from "@/components/nav/AfterLoginGroup.vue";
import BeforeLoginGroup from "@/components/nav/BeforeLoginGroup.vue";

const userStore = useUserStore();
const router = useRouter();

const isLoggedIn = ref(userStore.isLoggedIn);

watch(
  () => userStore.isLoggedIn,
  (value) => {
    isLoggedIn.value = value;
  }
);

const emit = defineEmits(["headerNavEvent"]);

const event = (data) => {
  if (data !== "logout") {
    emit("headerNavEvent", data);
  } else {
    const success = (response) => {
      if (response.status !== 200) {
        alert("문제가 발생했습니다.");
        return;
      } else {
        userStore.userId = null;
        userStore.userName = null;
      }
    };
    const fail = (error) => {
      alert("문제가 발생했습니다 : " + error);
    };
    signOut(success, fail);
  }
};

const returnHome = () => {
  router.replace("/");
};
</script>

<template>
  <div class="blur-box header-box">
    <span class="logo" @click="returnHome">Enjoy Trip</span>
    <div>
      <BeforeLoginGroup v-if="!isLoggedIn" @loginGroupEvent="event" />
      <AfterLoginGroup v-else @loginGroupEvent="event" />
    </div>
  </div>
</template>

<style scoped>
.blur-box {
  backdrop-filter: blur(10px);
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  margin: 2vh 0;
  padding: 20px;
  animation: slide-fade-in-from-bottom 0.5s ease-in-out 0s 1 normal forwards
    running;
}
.header-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-top: 5px;
  padding-bottom: 5px;
  width: 100%;
}
.logo {
  font-size: 2rem;
  font-weight: 700;
  text-decoration: none;
}
</style>