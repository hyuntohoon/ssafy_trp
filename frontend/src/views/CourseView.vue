<script setup>
import Navigator from "@/components/Navigator.vue";
import CourseMap from "@/components/course/CourseMap.vue";
import CourseList from "@/components/course/CourseList.vue";
import CourseDetail from "@/components/course/CourseDetail.vue";
import CourseGpt from "@/components/course/CourseGpt.vue";
</script>

<template>
  <div>
    <Navigator>
      <template v-slot:title>
        <h3>코스 탐색</h3>
      </template>
    </Navigator>
    <div class="row-wrap">
      <CourseMap />
      <CourseList />
    </div>
    <CourseDetail />
    <CourseGpt />
  </div>
</template>

<style scoped>
.row-wrap {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
}
</style>
