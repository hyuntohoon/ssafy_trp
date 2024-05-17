package com.ssafy.enjoytrip.model.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;

public interface TripPlanService {
    public TripPlan createTripPlan(String name, String userId, List<Integer> attractionIds);

    boolean deleteTripPlan(int id);

    public boolean updateTripPlan(int id, TripPlanRequest tripPlanRequest);

    Optional<TripPlanWithPlaces> getTripPlanById(int id);

    List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(String userId);
}
