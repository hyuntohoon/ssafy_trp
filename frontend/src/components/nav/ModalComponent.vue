<script setup></script>

<template>
  <div class="modal-wrap">
    <div class="modal">
      <slot name="modal-content"></slot>
      <slot name="modal-action"></slot>
    </div>
  </div>
</template>

<style scoped>
.modal-wrap {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  backdrop-filter: blur(3px);
  background-color: rgba(0, 0, 0, 0.5);
  padding: 20px;
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 20px;
  padding: 2rem 3rem;
  width: auto;
  height: auto;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  background-color: white;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}

#modal-content {
  margin-bottom: 20px;
}
</style>
