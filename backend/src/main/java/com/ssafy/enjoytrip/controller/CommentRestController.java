package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.entity.Comment;
import com.ssafy.enjoytrip.model.entity.User;
import com.ssafy.enjoytrip.model.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
    @Autowired
    private CommentService commentService;

     // CREATE: 새로운 댓글 추가
     @PostMapping
     public ResponseEntity<Comment> addComment(@RequestBody Comment comment,
     HttpSession session) {
     User user = (User) session.getAttribute("user");
     comment.setUser(user);
     Comment newComment = commentService.addComment(comment);

     return ResponseEntity.ok(newComment);
     }

     // READ: 모든 댓글 조회
     @GetMapping
     public ResponseEntity<List<Comment>> getAllComments() {
     List<Comment> comments = commentService.getAllComments();
     return ResponseEntity.ok(comments);
     }

     // READ: 특정 댓글 조회
     @GetMapping("/{id}")
     public ResponseEntity<Comment> getCommentById(@PathVariable("id") int
     commentId) {
     Comment comment = commentService.getCommentById(commentId);
     return comment != null ? ResponseEntity.ok(comment) :
     ResponseEntity.notFound().build();
     }

     // UPDATE: 댓글 수정
     @PutMapping("/{id}")
     public ResponseEntity<Comment> updateComment(@PathVariable("id") int commentId, @RequestBody Comment comment, HttpSession session) {
         User user = (User) session.getAttribute("user");
         Comment existingComment = commentService.getCommentById(commentId);

         if (existingComment == null || !user.equals(existingComment.getUser())) {
             return ResponseEntity.notFound().build();
         }

         Comment updatedComment = commentService.updateComment(commentId, comment);
         return updatedComment != null ? ResponseEntity.ok(updatedComment) : ResponseEntity.notFound().build();
     }

    // DELETE: 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int commentId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Comment comment = commentService.getCommentById(commentId);

        if (comment == null || !user.equals(comment.getUser())) {
            return ResponseEntity.notFound().build();
        }

        boolean isDeleted = commentService.deleteComment(commentId);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
