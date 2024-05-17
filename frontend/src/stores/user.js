import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { setAuth } from "@/utils/http-commons";
import Swal from "sweetalert2";

import { signIn, signOut, signUp, changePassword, withdraw } from "@/api/user";

export const useUserStore = defineStore(
  "user",
  () => {
    const id = ref(null);
    const pw = ref(null);
    const pwCheck = ref(null);
    const name = ref(null);
    const token = ref(null);

    // Getters
    const isLoggedIn = computed(() => token.value !== null);

    // Setters
    const setId = (value) => (id.value = value);
    const setPw = (value) => (pw.value = value);
    const setPwCheck = (value) => (pwCheck.value = value);
    const setName = (value) => (name.value = value);

    // Actions
    const doSignIn = async () => {
      try {
        const response = await signIn(id.value, pw.value);
        if (response.status === 200) {
          token.value = response.data;
          setAuth(token.value);
          pw.value = null;
          console.log(token.value);
          return true;
        } else {
          throw new Error();
        }
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "로그인 실패",
          text: "아이디와 비밀번호를 확인해주세요.",
        });
        return false;
      }
    };

    const doSignOut = async () => {
      try {
        const response = await signOut();
        if (response.status === 200) {
          flush();
          return true;
        } else {
          throw new Error();
        }
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "로그아웃 실패",
          text: "다시 시도해주세요.",
        });
        return false;
      }
    };

    const doSignUp = async () => {
      console.log("signUp");
      if (pw.value !== pwCheck.value) {
        Swal.fire({
          icon: "warning",
          title: "비밀번호가 일치하지 않습니다.",
          text: "다시 확인해주세요.",
        });
      }

      try {
        const response = await signUp(id.value, pw.value, name.value);
        if (response.status === 200) {
          flush();
          return true;
        } else {
          throw new Error();
        }
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "회원가입 실패",
          text: "다시 시도해주세요.",
        });
        return false;
      }
    };

    const doChangePassword = async () => {
      if (pw.value !== pwCheck.value) {
        return false;
      }

      try {
        const response = await changePassword(pw.value);
        if (response.status === 200) {
          flush();
          return true;
        } else {
          throw new Error();
        }
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "비밀번호 변경 실패",
          text: "다시 시도해주세요.",
        });
        return false;
      }
    };

    const doWithdraw = async () => {
      try {
        const response = await withdraw();
        if (response.status === 200) {
          flush();
          return true;
        } else {
          throw new Error();
        }
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "회원탈퇴 실패",
          text: "다시 시도해주세요.",
        });
        return false;
      }
    };

    const flush = () => {
      id.value = null;
      pw.value = null;
      name.value = null;
      token.value = null;
    };

    return {
      id,
      pw,
      pwCheck,
      name,
      token,

      isLoggedIn,

      setId,
      setPw,
      setPwCheck,
      setName,

      doSignIn,
      doSignOut,
      doSignUp,
      doChangePassword,
      doWithdraw,

      flush,
    };
  },
  {
    persist: true,
  }
);
