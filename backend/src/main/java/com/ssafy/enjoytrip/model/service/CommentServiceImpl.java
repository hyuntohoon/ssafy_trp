package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.Comment;
import com.ssafy.enjoytrip.model.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(int commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.orElse(null);
    }

    @Override
    public Comment updateComment(int commentId, Comment commentDetails) {
        Comment comment = getCommentById(commentId);
        if (comment != null) {
            comment.setPost(commentDetails.getPost());
            comment.setUser(commentDetails.getUser());
            comment.setContent(commentDetails.getContent());
            comment.setUpdateTimeStamp(commentDetails.getUpdateTimeStamp());
            comment.setLikes(commentDetails.getLikes());
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public boolean deleteComment(int commentId) {
        if (commentRepository.existsById(commentId)) {
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

    @Profile("test")
    @Override
    public void deleteAllComments() {
        commentRepository.deleteAll();
    }
}
