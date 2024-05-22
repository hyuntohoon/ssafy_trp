// eslint-disable-next-line vue/multi-word-component-names
<script setup></script>

<template>
  <div class="col-lg-4" style="padding: 10px">
    <div
      class="blur-box"
      style="
        align-items: flex-start;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
      ">
      <slot name="content"></slot>
      <slot name="action"></slot>
    </div>
  </div>
</template>

<style scoped>
.blur-box {
  backdrop-filter: blur(10px);
  background-color: rgba(255, 255, 255, 0.296);
  border-radius: 20px;
  margin-top: 2vh;
  padding: 20px;
  animation: slide-fade-in-from-bottom 0.5s ease-in-out 0s 1 normal forwards running;
}
</style>
