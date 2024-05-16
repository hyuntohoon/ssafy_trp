<script setup>
import GlassButton from "@/components/common/GlassButton.vue";
import GlassInput from "@/components/common/GlassInput.vue";
import GlassSelect from "@/components/common/GlassSelect.vue";

import { ref } from "vue";

const search = ref("");
const selected = ref("");

const onChange = (event) => {
  selected.value = event.target.value;
  console.log(selected.value);
};

const onInput = (event) => {
  search.value = event.target.value;
  console.log(search.value);
};
</script>

<template>
  <div>
    <GlassSelect
      placeHolder="검색 조건"
      :options="['제목', '작성자']"
      :value="selected"
      @change="onChange"
    />
    <span style="margin-left: 0.5rem"></span>
    <GlassInput
      placeHolder="검색어를 입력하세요"
      :value="search"
      @input="onInput"
    />
    <span style="margin-left: 0.5rem"></span>
    <GlassButton>
      <template v-slot:content>
        <i class="bi bi-search"></i>
      </template>
    </GlassButton>
  </div>
</template>

<style scoped>
div {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  backdrop-filter: blur(20px);
  padding: 0.5rem;
  border-radius: 1rem;
  margin-bottom: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
}
</style>