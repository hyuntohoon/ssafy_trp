import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { setAuth } from "@/utils/http-commons";

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
          return false;
        }
      } catch (error) {
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
          return false;
        }
      } catch (error) {
        return false;
      }
    };

    const doSignUp = async () => {
      console.log("signUp");
      if (pw.value !== pwCheck.value) {
        return false;
      }

      try {
        const response = await signUp(id.value, pw.value, name.value);
        if (response.status === 200) {
          flush();
          return true;
        } else {
          return false;
        }
      } catch (error) {
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
          return false;
        }
      } catch (error) {
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
          return false;
        }
      } catch (error) {
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
