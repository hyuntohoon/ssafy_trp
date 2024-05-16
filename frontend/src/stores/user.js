import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore(
  "user",
  () => {
    const userName = ref(null);
    const userId = ref(null);

    // Getters
    const isLoggedIn = computed(() => {
      return userName.value !== null;
    });

    const getUserName = computed(() => {
      return userName.value;
    });

    const getUserId = computed(() => {
      return userId.value;
    });

    return {
      userName,
      userId,
      isLoggedIn,
      getUserName,
      getUserId,
    };
  },
  {
    persist: true,
  }
);
