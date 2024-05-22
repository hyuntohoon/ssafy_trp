import { ref } from "vue";
import { defineStore } from "pinia";

import { getBoardList, searchBoardList } from "@/api/board";

export const useBoardStore = defineStore("board", () => {
  const boardList = ref([]);
  const board = ref({});
  const page = ref(1);
  const search = ref({
    key: "",
    word: "",
  });

  const fetchBoardList = async (page, pageSize) => {
    const response = await getBoardList(page, pageSize);
    boardList.value = response.data;
  };

  const fetchSearchBoardList = async (page, pageSize) => {
    let { key, word } = search.value;
    if (key === "제목") key = "title";
    if (key === "내용") key = "content";
    if (key === "작성자") key = "writer";
    const response = await searchBoardList(key, word, page, pageSize, 1);
    boardList.value = response.data;
  };

  return {
    boardList,
    board,

    page,

    search,

    fetchBoardList,
    fetchSearchBoardList,
  };
});
