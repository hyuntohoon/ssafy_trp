package com.ssafy.enjoytrip.model.dao;

import com.ssafy.enjoytrip.model.dto.Comment;
//import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface CommentDao {
    Comment addComment(Comment comment);

    List<Comment> getAllComments();

    Comment getCommentById(int commentId);

    boolean deleteComment(int commentId);

    Comment updateComment(int commentId, Comment comment);

    void deleteAllComments();
}
