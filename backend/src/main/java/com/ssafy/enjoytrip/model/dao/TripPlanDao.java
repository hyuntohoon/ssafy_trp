package com.ssafy.enjoytrip.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.model.dto.TripPlan;

@Mapper
public interface TripPlanDao {

	public boolean setTripPlan(TripPlan tripPlan);

	public List<TripPlan> getTripPlan(String userid);

	public boolean delTripPlan(int id);

	public boolean updateTripPlan(TripPlan tripPlan);

}
