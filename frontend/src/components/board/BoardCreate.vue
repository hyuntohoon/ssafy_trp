<script setup>
import { ref, onMounted } from "vue";
import Swal from "sweetalert2";

import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const route = useRoute();

import GlassButton from "@/components/common/GlassButton.vue";
import GlassInput from "@/components/common/GlassInput.vue";
import GlassTextArea from "@/components/common/GlassTextArea.vue";

import { useUserStore } from "@/stores/user";
import { useBoardStore } from "@/stores/board";

import { storeToRefs } from "pinia";

const useUser = useUserStore();
const useBoard = useBoardStore();

const { id } = storeToRefs(useUser);
const { board } = storeToRefs(useBoard);

const query = route.query;

onMounted(() => {
  board.value = {
    title: "",
    content: "",
    photo: "[]",
    contentId: parseInt(query.contentId),
  };
});

const { writeBoard } = useBoard;

const tmpImages = ref([]);

const uploadImages = async (images) => {
  let urls = [];
  for (const image of images) {
    const formData = new FormData();
    const decoded = atob(image.split(",")[1]);
    const array = new Uint8Array(decoded.length);
    for (let i = 0; i < decoded.length; i++) {
      array[i] = decoded.charCodeAt(i);
    }
    const blob = new Blob([array], { type: "image/jpeg" });
    formData.append("image", blob);
    const res = await fetch("https://image.ssafy.picel.net", {
      method: "POST",
      body: formData,
    });
    const data = await res.json();
    urls.push(data.url);
  }
  return urls;
};

const upload = () => {
  if (tmpImages.value.length > 10) {
    alert("이미지는 최대 10개까지 업로드 가능합니다.");
    return;
  }

  const input = document.createElement("input");
  input.type = "file";
  input.accept = "image/*";
  input.multiple = true;
  input.onchange = (e) => {
    const files = e.target.files;
    for (const file of files) {
      const reader = new FileReader();
      reader.onload = (e) => {
        tmpImages.value.push(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };
  input.click();
};

const write = async () => {
  if (!board.value.title) {
    Swal.fire({
      icon: "error",
      title: "제목을 입력하세요",
      showConfirmButton: false,
      timer: 1500,
    });
    return;
  } else if (!board.value.content) {
    Swal.fire({
      icon: "error",
      title: "내용을 입력하세요",
      showConfirmButton: false,
      timer: 1500,
    });
    return;
  }
  Swal.fire({
    title: "게시글을 작성하시겠습니까?",
    showCancelButton: true,
    confirmButtonText: "네",
    cancelButtonText: "아니요",
    showLoaderOnConfirm: true,
    preConfirm: async () => {
      const urls = await uploadImages(tmpImages.value);
      board.value.photo = JSON.stringify(urls);
      const res = await writeBoard(id.value);
      console.log(res);
      if (res) {
        Swal.fire({
          icon: "success",
          title: "게시글이 작성되었습니다.",
          showConfirmButton: false,
          timer: 1500,
        });
        router.push({ name: "board-list" });
      } else {
        Swal.fire({
          icon: "error",
          title: "게시글 작성에 실패했습니다.",
          showConfirmButton: false,
          timer: 1500,
        });
      }
    },
  });
};
</script>

<template>
  <div class="main-wrap">
    <div style="text-align: end" v-if="query.contentId">
      <h2>
        {{ query.title }}
        후기 작성
      </h2>
    </div>
    <GlassInput
      placeHolder="제목을 입력하세요"
      :value="board.title"
      @input="board.title = $event.target.value" />
    <div style="margin-bottom: 1rem"></div>
    <GlassTextArea
      placeHolder="내용을 입력하세요"
      :value="board.content"
      @input="board.content = $event.target.value" />
    <div style="margin-bottom: 1rem"></div>
    <div class="upload-wrap">
      <button class="upload-btn" @click="upload">
        <i class="bi bi-plus-circle" style="font-size: 2rem"></i>
      </button>
      <div class="preview-wrap">
        <img v-for="image in tmpImages" :src="image" :key="image" />
      </div>
    </div>
    <GlassButton @click="write">
      <template v-slot:content>
        <i class="bi bi-pencil"></i>&nbsp;
        <span>글쓰기</span>
      </template>
    </GlassButton>
  </div>
</template>

<style scoped>
.main-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  border-radius: 1rem;
  padding: 1rem;
  margin-bottom: 1rem;
}

.upload-wrap {
  display: flex;
  align-items: start;
  justify-content: center;
  width: 100%;
  height: 100px;
  overflow-x: auto;
}

.preview-wrap {
  display: flex;
  align-items: start;
  justify-content: start;
  width: 100%;
  overflow-x: hidden;
  overflow-y: hidden;
}

.preview-wrap img {
  width: 80px;
  height: 80px;
  margin: 0.5rem;
  border-radius: 1rem;
  object-fit: contain;
}

.upload-btn {
  width: 100px;
  height: 100px;
  border-radius: 1rem;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
</style>
