//css
import "./assets/global.css";

import { createApp } from "vue";
import { createPinia } from "pinia";
import { useKakao } from "vue3-kakao-maps/@utils";

const { VITE_API_KAKAO_API_KEY } = import.meta.env;

console.log(import.meta.env);

import App from "./App.vue";
import router from "./router";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";

const app = createApp(App);
const pinia = createPinia();

import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
pinia.use(piniaPluginPersistedstate);

useKakao(VITE_API_KAKAO_API_KEY);

app.use(pinia);
app.use(router);

import { useUserStore } from "./stores/user";
const userStore = useUserStore();

import { storeToRefs } from "pinia";
import { setAuth } from "./utils/http-commons";
const { token } = storeToRefs(userStore);

setAuth(token.value);

app.mount("#app");
