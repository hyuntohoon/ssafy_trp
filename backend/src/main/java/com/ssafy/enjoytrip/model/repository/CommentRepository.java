package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
