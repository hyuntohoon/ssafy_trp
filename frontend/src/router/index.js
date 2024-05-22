import { createRouter, createWebHistory } from "vue-router";
import Swal from "sweetalert2";

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
import { storeToRefs } from "pinia";

const onlyAuthUser = (to, from, next) => {
  const { token } = storeToRefs(useUserStore());
  if (!token.value) {
    Swal.fire({
      icon: "warning",
      title: "로그인이 필요합니다.",
      text: "상단의 로그인 버튼을 눌러주세요.",
    });
    next({ name: "home" });
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
      beforeEnter: onlyAuthUser,
    },
    {
      path: "/course",
      name: "course",
      component: CourseView,
      beforeEnter: onlyAuthUser,
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
      redirect: { name: "board-list" },
      beforeEnter: onlyAuthUser,
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
        },
        {
          path: "detail",
          name: "board-detail",
          component: BoardDetail,
        },
        {
          path: "result",
          name: "board-result",
          component: BoardResult,
        },
        {
          path: "edit",
          name: "board-edit",
          component: BoardEdit,
        },
      ],
    },
  ],
});

export default router;
