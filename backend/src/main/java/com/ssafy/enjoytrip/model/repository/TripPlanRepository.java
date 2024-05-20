package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.TripPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripPlanRepository extends JpaRepository<TripPlan, Integer> {
    List<TripPlan> findByUserId(String userId);
}
