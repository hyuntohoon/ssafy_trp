<script setup>
import GlassButton from "@/components/common/GlassButton.vue";
</script>

<template>
  <div class="group-wrap">
    <GlassButton :onClick="() => $emit('loginGroupEvent', 'login')">
      <template v-slot:content> 로그인 </template>
    </GlassButton>
    <GlassButton
      :onClick="() => $emit('loginGroupEvent', 'register')"
      :style="'margin: 0 0 0 0.5rem'">
      <template v-slot:content> 회원가입 </template>
    </GlassButton>
  </div>
</template>

<style scoped>
.group-wrap {
  display: flex;
  flex-direction: row;
}
</style>
