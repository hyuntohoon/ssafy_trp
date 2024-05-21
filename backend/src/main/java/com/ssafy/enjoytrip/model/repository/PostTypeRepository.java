package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTypeRepository extends JpaRepository<PostType, Integer> {
}
