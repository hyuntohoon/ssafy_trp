<script setup></script>

<template>
  <!-- <div class="background-holder">
    <img src="@/assets/wallpaper.jpg" class="background-image" alt="travel" />
  </div> -->
  <div class="wrapper">
    <div class="wave"></div>
  </div>
</template>

<style scoped>
/* body {
  min-height: 100vh;
  position: relative;
} */
/* .background-image {
  position: absolute;
  top: 0;
  left: 50%;
  min-height: 100rem;
  max-height: 100vh;
  min-width: 100rem;
  max-width: 100vw;
  z-index: -1;
  transform: translateX(-50%);
  filter: brightness(0.9);
}

.background-holder {
  position: fixed;
  left: 50%;
  height: 100rem;
  top: 0;
  z-index: -1;
  min-height: 100vh;
  max-height: 100vh;
  min-width: 100vw;
  max-width: initial;
  overflow: hidden;
  transform: translateX(-50%);
} */

/* waving wallpaper with green-blue colors */
body {
  background: #333;
}

.wrapper {
  width: 100%;
  height: 100%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border-radius: 5px;
  background-image: linear-gradient(to top, #accbee 0%, #e7f0fd 100%);
  overflow: hidden;
  z-index: -1;
}

.wave {
  width: 200vw;
  height: 400vh;
  position: absolute;
  top: 290vh;
  left: 50%;
  margin-left: -100%;
  margin-top: -100%;
  border-radius: 35%;
  background: rgba(255, 255, 255, 0.75);
  animation: wave 40s infinite linear;
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
