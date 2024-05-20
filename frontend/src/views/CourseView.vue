<script setup>
import Navigator from "@/components/Navigator.vue";
import CourseMap from "@/components/course/CourseMap.vue";
import CourseList from "@/components/course/CourseList.vue";
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
