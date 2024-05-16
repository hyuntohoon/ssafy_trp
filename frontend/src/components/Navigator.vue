<script setup>
import GlassButton from "@/components/common/GlassButton.vue";

import { useRouter } from "vue-router";

const router = useRouter();

const goBack = () => {
  router.go(-1);
};
</script>

<template>
  <div class="nav-wrapper">
    <div class="nav-content">
      <GlassButton :onClick="goBack">
        <template v-slot:content>
          <i class="bi bi-arrow-left"></i>
        </template>
      </GlassButton>
      <div style="margin-left: 1rem"></div>
      <slot name="title"></slot>
      <div style="margin-left: 1rem"></div>
    </div>

    <slot name="actions"></slot>
  </div>
</template>

<style scoped>
.nav-wrapper {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 1rem;
}
.nav-content {
  display: flex;
  flex-direction: row;
  padding: 0.5rem;
  justify-content: space-around;
  align-items: center;
  border-radius: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(20px);
  max-width: fit-content;
}
</style>