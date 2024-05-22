import { ref } from "vue";
import { defineStore } from "pinia";

import { postBotChat, getAutoPlan } from "@/api/gpt";

export const useGptStore = defineStore("gpt", () => {
  const gpt = ref({
    prompt: "",
    response: "",
  });
  const autoPlan = ref(null);
  const isLoading = ref(false);

  // Action
  const setPrompt = (prompt) => {
    gpt.value.prompt = prompt;
  };

  const getResponse = async () => {
    try {
      const response = await postBotChat(gpt.value.prompt);
      if (response.status === 200) {
        gpt.value.response = response.data;
        isLoading.value = false;
        return "success";
      } else {
        isLoading.value = false;
        throw new Error(response.status);
      }
    } catch (error) {
      isLoading.value = false;
      return "fail";
    }
  };

  const getPlan = async (prompt) => {
    isLoading.value = true;
    try {
      const response = await getAutoPlan(prompt);
      if (response.status === 200) {
        autoPlan.value = response.data;
        isLoading.value = false;
        return "success";
      } else {
        isLoading.value = false;
        throw new Error(response.status);
      }
    } catch (error) {
      isLoading.value = false;
      return "fail";
    }
  };

  const flush = () => {
    gpt.value.prompt = "";
    gpt.value.response = "";
    autoPlan.value = null;
  };

  return {
    gpt,
    isLoading,
    autoPlan,

    setPrompt,
    getResponse,
    getPlan,

    flush,
  };
});
