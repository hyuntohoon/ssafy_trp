//package com.ssafy.enjoytrip.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.enjoytrip.model.dto.Comment;
//import com.ssafy.enjoytrip.model.dto.User;
//import com.ssafy.enjoytrip.model.service.CommentService;
//import com.ssafy.enjoytrip.model.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Timestamp;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//class CommentRestControllerIntegrationTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(CommentRestControllerIntegrationTest.class);
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private CommentService commentService;
//
//    @Autowired
//    private UserService userService;
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
//
//        // 데이터베이스 초기화
//        commentService.deleteAllComments();
//        userService.deleteAllUsers();
//
//        // Mock user 설정
//        mockUser = new User();
//        mockUser.setId("ssafy");
//        mockUser = userService.addUser(mockUser); // SignUp
//
//        session = new MockHttpSession();
//        session.setAttribute("user", mockUser);
//    }
//
//    @Test
//    void testAddComment() throws Exception {
//        Comment comment = new Comment();
//        comment.setPostId(1);
//        comment.setUserId(mockUser.getId());
//        comment.setContent("Test content");
//        comment.setCreateTimeStamp(new Timestamp(System.currentTimeMillis()));
//        comment.setUpdateTimeStamp(new Timestamp(System.currentTimeMillis()));
//        comment.setLikes(0);
//
//        mockMvc.perform(post("/comment")
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(comment)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Test content"));
//    }
//
//    @Test
//    void testGetAllComments() throws Exception {
//        Comment comment1 = new Comment();
//        comment1.setPostId(1);
//        comment1.setUserId(mockUser.getId());
//        comment1.setContent("Test content 1");
//        commentService.addComment(comment1);
//
//        Comment comment2 = new Comment();
//        comment2.setPostId(2);
//        comment2.setUserId(mockUser.getId());
//        comment2.setContent("Test content 2");
//        commentService.addComment(comment2);
//
//        mockMvc.perform(get("/comment")
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].content").value("Test content 1"))
//                .andExpect(jsonPath("$[1].content").value("Test content 2"));
//    }
//
//    @Test
//    void testGetCommentById() throws Exception {
//        Comment comment = new Comment();
//        comment.setPostId(1);
//        comment.setUserId(mockUser.getId());
//        comment.setContent("Test content");
//        comment = commentService.addComment(comment);
//
//        mockMvc.perform(get("/comment/" + comment.getCommentId())
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Test content"));
//    }
//
//    @Test
//    void testUpdateComment() throws Exception {
//        logger.debug("Starting testUpdateComment");
//        Comment existingComment = new Comment();
//        existingComment.setPostId(1);
//        existingComment.setUserId(mockUser.getId());
//        existingComment.setContent("Old content");
//        existingComment = commentService.addComment(existingComment);
//
//        Comment updatedComment = new Comment();
//        updatedComment.setCommentId(existingComment.getCommentId());
//        updatedComment.setPostId(existingComment.getPostId());
//        updatedComment.setUserId(mockUser.getId());
//        updatedComment.setContent("Updated content");
//
//        logger.debug("Performing PUT request to /comment/{}", existingComment.getCommentId());
//        mockMvc.perform(put("/comment/" + existingComment.getCommentId())
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedComment)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content").value("Updated content"));
//    }
//
//    @Test
//    void testDeleteComment() throws Exception {
//        logger.debug("Starting testDeleteComment");
//
//        Comment existingComment = new Comment();
//        existingComment.setPostId(1);
//        existingComment.setUserId(mockUser.getId());
//        existingComment.setContent("Comment to be deleted");
//        existingComment = commentService.addComment(existingComment);
//
//        logger.debug("Performing DELETE request to /comment/{}", existingComment.getCommentId());
//        mockMvc.perform(delete("/comment/" + existingComment.getCommentId())
//                        .session(session)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//}
