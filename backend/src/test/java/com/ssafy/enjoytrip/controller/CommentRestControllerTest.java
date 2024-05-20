package com.ssafy.enjoytrip.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.model.dto.Comment;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentRestController.class)
class CommentRestControllerTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(CommentRestControllerTest.class);
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CommentService commentService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private MockHttpSession session;
//    private User mockUser;
//
//    @BeforeEach
//    void setUp() {
//        logger.debug("Setting up mock user and session : ssafy");
//        mockUser = new User();
//        mockUser.setId("ssafy");
//        session = new MockHttpSession();
//        session.setAttribute("user", mockUser);
//    }
//
//    @Test
//    void testAddComment() throws Exception {
//        Comment comment = new Comment();
//        comment.setPostId(1);
//        comment.setContent("Test content");
//        comment.setCreateTimeStamp(new Timestamp(System.currentTimeMillis()));
//        comment.setUpdateTimeStamp(new Timestamp(System.currentTimeMillis()));
//        comment.setLikes(0);
//
//        when(commentService.addComment(any(Comment.class))).thenReturn(comment);
//
//        mockMvc.perform(post("/comment")
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(comment)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Test content"));
//
//        verify(commentService, times(1)).addComment(any(Comment.class));
//    }
//
//    @Test
//    void testGetAllComments() throws Exception {
//        Comment comment1 = new Comment();
//        comment1.setContent("Test content 1");
//
//        Comment comment2 = new Comment();
//        comment2.setContent("Test content 2");
//
//        List<Comment> comments = Arrays.asList(comment1, comment2);
//
//        when(commentService.getAllComments()).thenReturn(comments);
//        mockMvc.perform(get("/comment")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].content").value("Test content 1"))
//                .andExpect(jsonPath("$[1].content").value("Test content 2"));
//
//        verify(commentService, times(1)).getAllComments();
//    }
//
//    @Test
//    void testGetCommentById() throws Exception {
//        Comment comment = new Comment();
//        comment.setContent("Test content");
//
//        when(commentService.getCommentById(1)).thenReturn(comment);
//
//        mockMvc.perform(get("/comment/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Test content"));
//
//        verify(commentService, times(1)).getCommentById(1);
//    }
//
//    @Test
//    void testUpdateComment() throws Exception {
//        logger.debug("Starting testUpdateComment");
//        Comment existingComment = new Comment();
//        existingComment.setCommentId(1);
//        existingComment.setUserId("ssafy");
//        existingComment.setContent("Old content");
//
//        Comment updatedComment = new Comment();
//        updatedComment.setCommentId(1);
//        updatedComment.setUserId("ssafy");
//        updatedComment.setContent("Updated content");
//
//        logger.debug("Mocking commentService.getCommentById(1) to return existingComment");
//        when(commentService.getCommentById(1)).thenReturn(existingComment);
//
//        logger.debug("Mocking commentService.updateComment(1, updatedComment) to return updatedComment");
//        when(commentService.updateComment(eq(1), any(Comment.class))).thenReturn(updatedComment);
//
//        logger.debug("Performing PUT request to /comment/1");
//        mockMvc.perform(put("/comment/1")
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedComment)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Updated content"));
//
//        logger.debug("Verifying commentService interactions");
//        verify(commentService, times(1)).getCommentById(1);
//        verify(commentService, times(1)).updateComment(eq(1), any(Comment.class));
//    }
//
//
//    @Test
//    void testDeleteComment() throws Exception {
//        logger.debug("Starting testDeleteComment");
//
//        Comment existingComment = new Comment();
//        existingComment.setCommentId(1);
//        existingComment.setUserId("ssafy");
//        existingComment.setContent("Comment to be deleted");
//        logger.debug("Existing comment: {}", existingComment);
//
//        when(commentService.getCommentById(1)).thenReturn(existingComment);
//        when(commentService.deleteComment(1)).thenReturn(true);
//
//        mockMvc.perform(delete("/comment/1")
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//
//        verify(commentService, times(1)).getCommentById(1);
//        verify(commentService, times(1)).deleteComment(1);
//    }
}
