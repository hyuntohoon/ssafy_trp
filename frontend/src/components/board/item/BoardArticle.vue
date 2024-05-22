<script setup>
defineProps({
  article: Object,
});
</script>

<template>
  <div class="main-wrap">
    <div class="info-wrap">
      <h3>{{ article.title }}</h3>
      <div class="info">
        <span style="margin-right: 1rem">작성자 : {{ article.userID }}</span>
        <span style="margin-right: 1rem"
          >작성일 : {{ new Date(article.createTimeStamp).toLocaleDateString() }}</span
        >
        <span v-if="article.updateTimeStamp !== article.createTimeStamp"
          >수정일 : {{ new Date(article.updateTimeStamp).toLocaleDateString() }}</span
        >
      </div>
    </div>
    <div class="content-wrap">
      <img v-if="article.photo" :src="article.photo" alt="article photo" />
      <p>{{ article.content }}</p>
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

.info-wrap {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
  padding: 1rem;
  margin-bottom: 1rem;
}

.info {
  display: flex;
  align-items: center;
  justify-content: end;
  font-size: 0.8rem;
}

.content-wrap {
  width: 100%;
  padding: 1rem;
  margin-bottom: 1rem;
}
</style>
