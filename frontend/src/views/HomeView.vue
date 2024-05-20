<script setup>
import Card from "@/components/common/Card.vue";
import GlassButton from "@/components/common/GlassButton.vue";

import { useRouteStore } from "@/stores/route";
import { useSearchStore } from "@/stores/search";

import { useRouter } from "vue-router";

const router = useRouter();

const goTravel = () => {
  useRouteStore().flush();
  useSearchStore().flush();
  router.push({ name: "travel" });
};

const goBoard = () => {
  router.push({ name: "board" });
};

const goCourse = () => {
  router.push({ name: "course" });
};
</script>

<template>
  <div class="row">
    <Card>
      <template v-slot:content>
        <div>
          <h2>여행지 찾으러 가기</h2>
          <p>다양한 방법으로 여행지를 검색하고, 나만의 코스를 만들자!</p>
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
          <p>내가 생성한 코스를 관리하고, 다른 사용자들의 코스도 구경 해 보자!</p>
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
          <p>다양한 게시판을 통해 여행 정보를 공유하고, 소통하자!</p>
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
</template>

<style scoped></style>
