<script setup>
import ImageSlider from "@/components/common/ImageSlider.vue";
import PlaceCard from "@/components/common/PlaceCard.vue";

const props = defineProps({
  article: Object,
});

let images = [];
try {
  images = JSON.parse(props.article.photo);
} catch (error) {
  images = [];
}

import { useSearchStore } from "@/stores/search";
import { ref, onMounted } from "vue";
const useSearch = useSearchStore();

const { getAttraction } = useSearch;

const place = ref(null);

onMounted(async () => {
  if (props.article.contentId !== null) {
    place.value = await getAttraction(props.article.contentId.contentId);
    console.log(place.value);
  }
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
      <ImageSlider
        v-if="images !== null && images.length > 0"
        :images="images"
        style="margin-right: 2rem" />
      <div class="column-wrap">
        <PlaceCard v-if="place !== null" :data="place" style="margin: 0" />
        <pre style="padding: 1rem; white-space: pre-wrap; word-wrap: break-word">{{
          article.content
        }}</pre>
      </div>
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
  display: flex;
  flex-direction: row;
  align-items: flex-start;
}

img {
  width: 300px;
  height: 300px;
  height: auto;
  margin-bottom: 1rem;
}
</style>
