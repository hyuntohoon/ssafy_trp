import { createRouter, createWebHistory } from "vue-router";

import HomeView from "../views/HomeView.vue";

import TravelView from "@/views/TravelView.vue";
import CourseView from "@/views/CourseView.vue";

import BoardView from "@/views/BoardView.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardCreate from "@/components/board/BoardCreate.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import BoardResult from "@/components/board/BoardResult.vue";
import BoardEdit from "@/components/board/BoardEdit.vue";

import { useUserStore } from "@/stores/user";

const onlyAuthUser = (to, from, next) => {
  const userStore = useUserStore();
  if (!userStore.getAccessToken) {
    alert("로그인이 필요합니다.");
    return;
  }
  next();
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/travel",
      name: "travel",
      component: TravelView,
    },
    {
      path: "/course",
      name: "course",
      component: CourseView,
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
      redirect: { name: "board-list" },
      children: [
        {
          path: "list",
          name: "board-list",
          component: BoardList,
        },
        {
          path: "create",
          name: "board-create",
          component: BoardCreate,
          beforeEnter: onlyAuthUser,
        },
        {
          path: "detail/:id",
          name: "board-detail",
          component: BoardDetail,
        },
        {
          path: "result",
          name: "board-result",
          component: BoardResult,
        },
        {
          path: "edit/:id",
          name: "board-edit",
          component: BoardEdit,
          beforeEnter: onlyAuthUser,
        },
      ],
    },
  ],
});

export default router;
