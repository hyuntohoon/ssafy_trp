<script setup>
import HeaderNav from "@/components/HeaderNav.vue";
import Modal from "@/components/Modal.vue";
import Wallpaper from "@/components/Wallpaper.vue";
import Footer from "@/components/Footer.vue";

import { RouterView } from "vue-router";
import { ref } from "vue";

const modal = ref("");
const modalShow = ref(false);

const modalEvent = (data) => {
  modal.value = data;
  modalShow.value = !modalShow.value;
};

const modalClose = () => {
  modalShow.value = false;
};
</script>

<template>
  <div>
    <div class="container main">
      <HeaderNav @headerNavEvent="modalEvent" />
      <router-view v-slot="{ Component }">
        <transition name="scale" mode="out-in">
          <component :is="Component" />
        </transition>
      </router-view>
    </div>
    <Wallpaper />
    <!-- <Modal style="z-index: 1" v-if="modalShow" :modal="modal" @modalClose="modalClose" /> -->
    <transition name="fade">
      <Modal style="z-index: 1" v-if="modalShow" :modal="modal" @modalClose="modalClose" />
    </transition>
    <div style="height: 200px; width: 50vh"></div>
    <Footer />
  </div>
</template>

<style scoped>
h1 {
  font-size: 24px;
  font-weight: bold;
}

p {
  line-height: 1.5;
}

body {
  font-family: Arial, sans-serif;
  font-size: 16px;
  background-color: rgb(0, 0, 0);
  color: black;
  font-family: "IBM Plex Sans KR", sans-serif;
  min-height: 100vh;
  position: relative;
}

.main {
  overflow: hidden;
  min-height: 100vh;
  padding-bottom: 10vh;
}

.scale-enter-active,
.scale-leave-active {
  transition: all 0.3s ease;
}

.scale-enter-from,
.scale-leave-to {
  opacity: 0;
  transform: scale(0.9);
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
