import { ref } from "vue";
import { defineStore } from "pinia";

import { getBoardList, searchBoardList, postBoard, deleteBoard, putBoard } from "@/api/board";

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
    const response = await searchBoardList(key, word, page, pageSize, 2);
    boardList.value = response.data;
  };

  const writeBoard = async (userID) => {
    const params = {
      userId: userID,
      title: board.value.title,
      content: board.value.content,
      postTypeId: 2,
    };
    console.log(board.value.photo);
    if (board.value.photo) params.photo = board.value.photo;
    if (board.value.contentId) params.contentId = board.value.contentId;

    try {
      const response = await postBoard(params);
      console.log(response);

      if (response.status === 200) {
        return true;
      } else {
        throw new Error();
      }
    } catch (e) {
      return false;
    }
  };

  const removeBoard = async (postId) => {
    const response = await deleteBoard(postId);
    if (response.status === 204) {
      return true;
    } else {
      return false;
    }
  };

  const modifyBoard = async (userID, postId) => {
    const params = {
      userId: userID,
      title: board.value.title,
      content: board.value.content,
      postTypeId: 2,
    };
    if (board.value.photo) params.photo = board.value.photo;
    if (board.value.contentId) params.contentId = board.value.contentId;

    const response = await putBoard(postId, params);
    if (response.status === 200) {
      return true;
    } else {
      return false;
    }
  };

  return {
    boardList,
    board,

    page,

    search,

    fetchBoardList,
    fetchSearchBoardList,
    writeBoard,
    removeBoard,
    modifyBoard,
  };
});
