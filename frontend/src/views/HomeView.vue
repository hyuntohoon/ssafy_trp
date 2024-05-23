<script setup>
import Card from "@/components/common/Card.vue";
import GlassButton from "@/components/common/GlassButton.vue";
import ImageAutoSlider from "@/components/common/ImageAutoSlider.vue";

import { useRouteStore } from "@/stores/route";
import { useSearchStore } from "@/stores/search";
import { useGptStore } from "@/stores/gpt";

import { useRouter } from "vue-router";

const router = useRouter();

const goTravel = () => {
  useRouteStore().flush();
  useSearchStore().flush();
  useGptStore().flush();
  router.push({ name: "travel" });
};

const goBoard = () => {
  router.push({ name: "board" });
};

const goCourse = () => {
  useGptStore().flush();
  router.push({ name: "course" });
};
</script>

<template>
  <div>
    <ImageAutoSlider
      :images="[
        '/src/assets/banners/image1.png',
        '/src/assets/banners/image2.png',
        '/src/assets/banners/image3.png',
      ]" />
    <div class="row">
      <Card>
        <template v-slot:content>
          <div>
            <h2>여행지 찾으러 가기</h2>
            <p>여행지를 검색하고, 나만의 코스를 만들자!</p>
          </div>
        </template>
        <template v-slot:action>
          <GlassButton :style="'width: 3rem; height: 3rem'" :onClick="goTravel">
            <template v-slot:content>
              <i class="bi bi-map"></i>
            </template>
          </GlassButton>
        </template>
      </Card>

      <Card>
        <template v-slot:content>
          <div>
            <h2>코스 탐색하기</h2>
            <p>코스를 관리하고, AI에게 평가도 받아보자!</p>
          </div>
        </template>
        <template v-slot:action>
          <GlassButton :style="'width: 3rem; height: 3rem'" :onClick="goCourse">
            <template v-slot:content>
              <i class="bi bi-geo-alt"></i>
            </template>
          </GlassButton>
        </template>
      </Card>
      <Card>
        <template v-slot:content>
          <div>
            <h2>게시판 가기</h2>
            <p>여행지 사진과 함께 이야기를 나눠보자!</p>
          </div>
        </template>
        <template v-slot:action>
          <GlassButton :style="'width: 3rem; height: 3rem'" :onClick="goBoard">
            <template v-slot:content>
              <i class="bi bi-chat-left"></i>
            </template>
          </GlassButton>
        </template>
      </Card>
    </div>
  </div>
</template>

<style scoped></style>
