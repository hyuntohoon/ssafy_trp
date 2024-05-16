package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.TripPlan;

public interface TripPlanService {
	public boolean setTripPlan(TripPlan tripPlan);

	boolean delTripPlan(int id);

	boolean updateTripPlan(TripPlan tripPlan);

	List<TripPlan> getTripPlan(String userId);
}
