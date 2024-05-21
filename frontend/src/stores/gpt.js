import { ref } from "vue";
import { defineStore } from "pinia";

import { postBotChat } from "@/api/gpt";

export const useGptStore = defineStore("gpt", () => {
  const gpt = ref({
    prompt: "",
    response: "",
  });
  const isLoading = ref(false);

  // Action
  const setPrompt = (prompt) => {
    gpt.value.prompt = prompt;
  };

  const getResponse = async () => {
    try {
      isLoading.value = true;
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

  const flush = () => {
    gpt.value.prompt = "";
    gpt.value.response = "";
  };

  return {
    gpt,
    isLoading,

    setPrompt,
    getResponse,
    flush,
  };
});
