package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;

import java.util.List;
import java.util.Optional;

public interface TripPlanService {
    TripPlan createTripPlan(String name, String userId, List<Integer> attractionIds);

    boolean deleteTripPlan(int id);

    boolean updateTripPlan(int id, TripPlanRequest tripPlanRequest);

    Optional<TripPlanWithPlaces> getTripPlanById(int id);

    List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(String userId);
}
