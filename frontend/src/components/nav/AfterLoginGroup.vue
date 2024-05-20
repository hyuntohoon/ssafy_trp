<script setup>
import GlassButton from "@/components/common/GlassButton.vue";
</script>

<template>
  <div class="group-wrap">
    <GlassButton text="마이페이지" :onClick="() => $emit('loginGroupEvent', 'mypage')">
      <template v-slot:content> 마이페이지 </template>
    </GlassButton>
    <GlassButton
      :onClick="() => $emit('loginGroupEvent', 'logout')"
      :style="'margin: 0 0 0 0.5rem'">
      <template v-slot:content> 로그아웃 </template>
    </GlassButton>
  </div>
</template>

<style scoped>
.group-wrap {
  display: flex;
  flex-direction: row;
}
</style>
