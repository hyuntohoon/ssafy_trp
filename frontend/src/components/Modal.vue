<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { ref } from "vue";

import { useUserStore } from "@/stores/user";

import GlassButton from "@/components/common/GlassButton.vue";
import ModalComponent from "@/components/nav/ModalComponent.vue";

const emit = defineEmits(["modalClose"]);
const userStore = useUserStore();

const {
  id,
  pw,
  pwCheck,
  name,
  // token,
  doChangePassword,
  // doWithdraw,
  setId,
  setPw,
  setPwCheck,
  setName,
} = userStore;

const isChangePassword = ref(false);

defineProps({
  modal: String,
});

const modalClose = () => {
  emit("modalClose");
};

const doSignIn = () => {
  userStore.doSignIn().then((res) => {
    if (res) {
      modalClose();
    }
  });
};

const doSignUp = () => {
  userStore.doSignUp().then((res) => {
    if (res) {
      modalClose();
    }
  });
};

const doUpdate = () => {
  doChangePassword().then((res) => {
    if (res) {
      modalClose();
    }
  });
};
</script>

<template>
  <ModalComponent>
    <template v-slot:modal-content>
      <div v-if="modal === 'login'">
        <h1 class="mb-3">로그인</h1>
        <div class="input-group mb-3">
          <input
            type="text"
            class="form-control"
            placeholder="아이디"
            :value="id"
            @change="setId($event.target.value)" />
        </div>
        <div class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            :value="pw"
            @change="setPw($event.target.value)" />
        </div>
      </div>

      <div v-else-if="modal === 'register'">
        <h1 class="mb-3">회원가입</h1>
        <div class="input-group mb-3">
          <input
            type="text"
            class="form-control"
            placeholder="아이디"
            :value="id"
            @change="setId($event.target.value)" />
        </div>
        <div class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            :value="pw"
            @change="setPw($event.target.value)" />
        </div>
        <div class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호 확인"
            :value="pwCheck"
            @change="setPwCheck($event.target.value)" />
        </div>
        <div class="input-group mb-3">
          <input
            type="text"
            class="form-control"
            placeholder="이름"
            :value="name"
            @change="setName($event.target.value)" />
        </div>
      </div>

      <div v-else-if="modal === 'mypage'">
        <h1 class="mb-3">마이페이지</h1>
        <div class="my-page-content">
          <span>아이디</span>
          <span>{{ id }}</span>
        </div>
        <div class="my-page-content">
          <span>비밀번호 변경</span>
          <a href="#" @click="isChangePassword = !isChangePassword">변경</a>
        </div>
        <div v-if="isChangePassword" class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            :value="pw"
            @change="setPw($event.target.value)" />
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호 확인"
            :value="pwCheck"
            @change="setPwCheck($event.target.value)" />
          <GlassButton :isColored="true" :onClick="doUpdate">
            <template v-slot:content>확인</template>
          </GlassButton>
        </div>
      </div>
    </template>

    <template v-slot:modal-action>
      <div v-if="modal === 'login'">
        <GlassButton :isColored="true" :onClick="doSignIn">
          <template v-slot:content>로그인</template>
        </GlassButton>
        <GlassButton :onClick="modalClose" style="margin: 0 0 0 0.5rem">
          <template v-slot:content>닫기</template>
        </GlassButton>
      </div>
      <div v-else-if="modal === 'register'">
        <GlassButton :isColored="true" :onClick="doSignUp">
          <template v-slot:content>회원가입</template>
        </GlassButton>
        <GlassButton :onClick="modalClose" style="margin: 0 0 0 0.5rem">
          <template v-slot:content>닫기</template>
        </GlassButton>
      </div>
      <div v-else-if="modal === 'mypage'">
        <GlassButton :onClick="modalClose">
          <template v-slot:content>닫기</template>
        </GlassButton>
      </div>
    </template>
  </ModalComponent>
</template>

<style scoped>
.input-group {
  width: 30rem;
}

.my-page-content {
  display: flex;
  width: 30rem;
  justify-content: space-between;
  margin-bottom: 1rem;
}

a {
  color: #3bb28a;
  text-decoration: underline;
}

@media (max-width: 768px) {
  .input-group {
    width: 80%;
  }

  .my-page-content {
    width: 80%;
  }
}
</style>
