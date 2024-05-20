package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GugunRepository extends JpaRepository<Gugun, Integer> {
    List<Gugun> findBySidoCode(Integer sidoCode);
}
