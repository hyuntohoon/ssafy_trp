<script setup>
import { useRouter } from "vue-router";

import { useUserStore } from "@/stores/user";

import AfterLoginGroup from "@/components/nav/AfterLoginGroup.vue";
import BeforeLoginGroup from "@/components/nav/BeforeLoginGroup.vue";

const userStore = useUserStore();
const router = useRouter();

import { storeToRefs } from "pinia";

const { isLoggedIn } = storeToRefs(userStore);

const emit = defineEmits(["headerNavEvent"]);

const event = async (data) => {
  if (data !== "logout") {
    emit("headerNavEvent", data);
  } else {
    console.log("logout");
    await userStore.doSignOut();
  }
};

const returnHome = () => {
  router.replace("/");
};
</script>

<template>
  <div class="blur-box header-box">
    <span class="logo" @click="returnHome">뭉게뭉게</span>
    <div>
      <BeforeLoginGroup v-if="!isLoggedIn" @loginGroupEvent="event" />
      <AfterLoginGroup v-else @loginGroupEvent="event" />
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Gugi&display=swap");

.blur-box {
  backdrop-filter: blur(10px);
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 20px;
  margin: 2vh 0;
  padding: 20px;
  animation: slide-fade-in-from-bottom 0.5s ease-in-out 0s 1 normal forwards running;
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
  font-family: "Gugi", cursive;
  cursor: pointer;
}
</style>
