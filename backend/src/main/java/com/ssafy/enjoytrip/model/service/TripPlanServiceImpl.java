package com.ssafy.enjoytrip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.TripPlanDao;
import com.ssafy.enjoytrip.model.dto.TripPlan;

@Service
public class TripPlanServiceImpl implements TripPlanService {

	private TripPlanDao tripPlanDao;

	public TripPlanServiceImpl(TripPlanDao tripPlanDao) {
		this.tripPlanDao = tripPlanDao;
	}

	@Override
	public boolean setTripPlan(TripPlan tripPlan) {
		return tripPlanDao.setTripPlan(tripPlan);
	}

	@Override
	public List<TripPlan> getTripPlan(String userId) {
		return tripPlanDao.getTripPlan(userId);
	}

	@Override
	public boolean updateTripPlan(TripPlan tripPlan) {
		return tripPlanDao.updateTripPlan(tripPlan);
	}

	@Override
	public boolean delTripPlan(int id) {
		return delTripPlan(id);
	}

}
