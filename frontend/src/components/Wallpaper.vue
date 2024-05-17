<script setup></script>

<template>
  <div class="wrapper">
    <div class="wave-1"></div>
    <div class="wave-2"></div>
    <div class="wave-3"></div>
  </div>
</template>

<style scoped>
body {
  background: #333;
}

.wrapper {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 5px;
  background-image: linear-gradient(to top, #accbee 100%, #e7f0fd 0%);
  overflow: hidden;
  z-index: -1;
}

.wave-1 {
  width: 200vw;
  height: 400vh;
  position: fixed;
  top: 290vh;
  left: 50%;
  margin-left: -100%;
  margin-top: -100%;
  border-radius: 35%;
  background: rgba(255, 255, 255, 0.3);
  animation: wave 60s infinite linear;
}

.wave-2 {
  width: 200vw;
  height: 400vh;
  position: fixed;
  top: 280vh;
  left: 50%;
  margin-left: -100%;
  margin-top: -100%;
  border-radius: 35%;
  background: rgba(255, 255, 255, 0.3);
  animation: wave 54s infinite linear;
}

.wave-3 {
  width: 200vw;
  height: 400vh;
  position: fixed;
  top: 275vh;
  left: 50%;
  margin-left: -100%;
  margin-top: -100%;
  border-radius: 35%;
  background: rgba(255, 255, 255, 0.3);
  animation: wave 45s infinite linear;
}

@keyframes wave {
  from {
    transform: rotate(0deg);
  }
  from {
    transform: rotate(360deg);
  }
}
</style>
