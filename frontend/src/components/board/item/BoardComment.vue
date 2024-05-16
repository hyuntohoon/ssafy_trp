<script setup>
const props = defineProps({
  comments: Array,
});
</script>

<template>
  <div class="main-wrap">
    <div class="comment-wrap" v-for="comment in comments" :key="comment.id">
      <div class="info-wrap">
        <div class="info">
          <span style="margin-right: 1rem">작성자 : {{ comment.writer }}</span>
          <span style="margin-right: 1rem">작성일 : {{ comment.date }}</span>
        </div>
      </div>
      <div class="content-wrap">
        <p>{{ comment.content }}</p>
      </div>
      <div
        v-if="comment.id !== comments[comments.length - 1].id"
        style="width: 100%; height: 1px; background-color: rgba(0, 0, 0, 0.1)"
      ></div>
    </div>
  </div>
</template>

<style scoped>
.main-wrap {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  border-radius: 1rem;
  padding: 1rem;
  margin-bottom: 1rem;
}

.comment-wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-self: start;
  padding: 0.5rem;
}

.info {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 0.8rem;
}

.content-wrap {
  width: 100%;
  padding: 1rem;
}
</style>