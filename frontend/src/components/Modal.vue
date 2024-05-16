<!-- eslint-disable vue/multi-word-component-names -->
<script setup>
import { ref } from "vue";

import { signIn, signUp, changePassword } from "@/api/user";
import { useUserStore } from "@/stores/user";

import GlassButton from "@/components/common/GlassButton.vue";
import ModalComponent from "@/components/nav/ModalComponent.vue";

const emit = defineEmits(["modalClose"]);
const userStore = useUserStore();

const isChangePassword = ref(false);

const signInData = ref({
  id: "",
  password: "",
});

const signUpData = ref({
  id: "",
  password: "",
  passwordCheck: "",
  name: "",
});

const changePasswordData = ref({
  password: "",
  passwordCheck: "",
});

defineProps({
  modal: String,
});

const modalClose = () => {
  emit("modalClose");
};

const login = () => {
  const success = (response) => {
    if (response.status !== 200) {
      alert("문제가 발생했습니다.");
      return;
    } else {
      userStore.userId = response.data.id;
      userStore.userName = response.data.name;
      modalClose();
    }
  };
  const fail = (error) => {
    alert("문제가 발생했습니다 : " + error);
  };
  signIn(signInData.value.id, signInData.value.password, success, fail);
};

const register = () => {
  if (signUpData.value.password !== signUpData.value.passwordCheck) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }
  const success = (response) => {
    if (response.status !== 200) {
      alert("문제가 발생했습니다.");
      return;
    } else {
      alert("회원가입이 완료되었습니다.");
      modalClose();
    }
  };
  const fail = (error) => {
    alert("문제가 발생했습니다 : " + error);
  };
  signUp(signUpData.value.id, signUpData.value.password, signUpData.value.name, success, fail);
};

const changePW = () => {
  const success = (response) => {
    if (response.status !== 200) {
      alert("문제가 발생했습니다.");
      return;
    } else {
      alert("비밀번호가 변경되었습니다.");
      modalClose();
    }
  };
  const fail = (error) => {
    alert("문제가 발생했습니다 : " + error);
  };
  changePassword(changePasswordData.value.password, success, fail);
};
</script>

<template>
  <ModalComponent>
    <template v-slot:modal-content>
      <div v-if="modal === 'login'">
        <h1 class="mb-3">로그인</h1>
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="아이디" v-model="signInData.id" />
        </div>
        <div class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            v-model="signInData.password" />
        </div>
      </div>
      <div v-else-if="modal === 'register'">
        <h1 class="mb-3">회원가입</h1>
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="아이디" v-model="signUpData.id" />
        </div>
        <div class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            v-model="signUpData.password" />
        </div>
        <div class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호 확인"
            v-model="signUpData.passwordCheck" />
        </div>
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="이름" v-model="signUpData.name" />
        </div>
      </div>
      <div v-else-if="modal === 'mypage'">
        <h1 class="mb-3">마이페이지</h1>
        <div class="my-page-content">
          <span>아이디</span>
          <span>{{ userStore.userId }}</span>
        </div>
        <div class="my-page-content">
          <span>이름</span>
          <span>{{ userStore.userName }}</span>
        </div>
        <!-- change password form that shows only text btn first, but if clicked, it expanded and shows input form and confirm button below -->
        <div class="my-page-content">
          <span>비밀번호 변경</span>
          <a href="#" @click="isChangePassword = !isChangePassword">변경</a>
        </div>
        <div v-if="isChangePassword" class="input-group mb-3">
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            v-model="changePasswordData.password" />
          <input
            type="password"
            class="form-control"
            placeholder="비밀번호 확인"
            v-model="changePasswordData.passwordCheck" />
          <GlassButton :isColored="true" :onClick="changePW">
            <template v-slot:content>확인</template>
          </GlassButton>
        </div>
      </div>
    </template>
    <template v-slot:modal-action>
      <div v-if="modal === 'login'">
        <GlassButton :isColored="true" :onClick="login">
          <template v-slot:content>로그인</template>
        </GlassButton>
        <GlassButton :onClick="modalClose" style="margin: 0 0 0 0.5rem">
          <template v-slot:content>닫기</template>
        </GlassButton>
      </div>
      <div v-else-if="modal === 'register'">
        <GlassButton :isColored="true" :onClick="register">
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
