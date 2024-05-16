package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dao.CommentDao;
import com.ssafy.enjoytrip.model.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dto.User;

/**
 * CommentServiceImpl
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.getAllComments();
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentDao.getCommentById(commentId);
    }

    @Override
    public Comment updateComment(int commentId, Comment comment) {
        return commentDao.updateComment(commentId, comment);
    }

    @Override
    public boolean deleteComment(int commentId) {
        return commentDao.deleteComment(commentId);
    }

    @Profile("test")
    @Override
    public void deleteAllComments() {
        commentDao.deleteAllComments();
    }
}