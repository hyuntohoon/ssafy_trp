package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;

public interface TripPlanService {
    public TripPlan setTripPlan(String name, String userId, List<Integer> attractionIds);

    boolean delTripPlan(int id);

    public boolean updateTripPlan(int id, TripPlanRequest tripPlanRequest);

    TripPlanWithPlaces getTripPlanById(int id);

    List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(String userId);
}
