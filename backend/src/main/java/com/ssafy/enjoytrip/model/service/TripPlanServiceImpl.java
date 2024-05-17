package com.ssafy.enjoytrip.model.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.model.dao.TripPlanDao;
import com.ssafy.enjoytrip.model.dto.TripPlan;

@Service
public class TripPlanServiceImpl implements TripPlanService {

	private final TripPlanDao tripPlanDao;

	public TripPlanServiceImpl(TripPlanDao tripPlanDao) {
		this.tripPlanDao = tripPlanDao;
	}

	@Override
	@Transactional
	public Optional<TripPlanWithPlaces> getTripPlanById(int id) {
		List<TripPlanWithPlaces> tripPlans = tripPlanDao.getTripPlanById(id);
		if (tripPlans.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(tripPlans.get(0));
	}

	@Override
	@Transactional
	public List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(String userId) {
		return tripPlanDao.getTripPlansWithPlacesByUserId(userId);
	}

	@Override
	@Transactional
	public TripPlan setTripPlan(String name, String userId, List<Integer> attractionIds) {
		TripPlan tripPlan = new TripPlan();
		tripPlan.setName(name);
		tripPlan.setUserId(userId);
		tripPlanDao.setTripPlan(tripPlan);
		addPlacesToTripPlan(tripPlan.getId(), attractionIds);
		return tripPlan;
	}

	@Override
	@Transactional
	public boolean updateTripPlan(int id, TripPlanRequest tripPlanRequest) {
		tripPlanDao.updateTripPlan(id, tripPlanRequest.getName(), tripPlanRequest.getUserId());
		tripPlanDao.deletePlacesFromTripPlan(id);
		addPlacesToTripPlan(id, tripPlanRequest.getAttractionIds());
		return true;
	}

	@Override
	@Transactional
	public boolean delTripPlan(int id) {
		tripPlanDao.delTripPlan(id);
		return true;
	}

	private void addPlacesToTripPlan(int tripPlanId, List<Integer> attractionIds) {
		for (int order = 0; order < attractionIds.size(); order++) {
			int attractionId = attractionIds.get(order);
			tripPlanDao.addPlaceToTripPlan(tripPlanId, attractionId, order);
		}
	}
}
