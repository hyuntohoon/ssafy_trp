package com.ssafy.enjoytrip.model.repository;

import com.ssafy.enjoytrip.model.entity.TripPlanPlace;
import com.ssafy.enjoytrip.model.entity.TripPlanPlaceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripPlanPlaceRepository extends JpaRepository<TripPlanPlace, TripPlanPlaceId> {
    List<TripPlanPlace> findByTripPlanId(Integer tripPlanId);
    void deleteByTripPlanId(Integer tripPlanId);
}

