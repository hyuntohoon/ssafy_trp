package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> getAllComments();
    Comment getCommentById(int commentId);
    Comment updateComment(int commentId, Comment comment);
    boolean deleteComment(int commentId);
    void deleteAllComments();
}
