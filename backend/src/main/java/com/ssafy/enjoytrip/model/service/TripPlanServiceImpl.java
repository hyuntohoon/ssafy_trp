package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.AttractionInfo;
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
	@Transactional(readOnly = true)
	public TripPlanWithPlaces getTripPlanById(int id) {
		return tripPlanDao.getTripPlanById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(String userId) {
		return tripPlanDao.getTripPlansWithPlacesByUserId(userId);
	}

	@Override
	@Transactional
	public TripPlan setTripPlan(String name, String userId, List<Integer> attractionIds) {
		TripPlan tripPlan = new TripPlan();
		tripPlan.setName(name);
		tripPlan.setUserId(userId);

		// TripPlan 저장
		tripPlanDao.setTripPlan(tripPlan);
		int tripPlanId = tripPlan.getId(); // 새로 생성된 TripPlan의 ID

		for (Integer attractionId : attractionIds) {
			tripPlanDao.addPlaceToTripPlan(tripPlanId, attractionId);
		}
		return tripPlan;
	}

	@Override
	@Transactional
	public boolean updateTripPlan(TripPlan tripPlan) {
		return false;
	}

	@Override
	@Transactional
	public boolean delTripPlan(int id) {
		return false;
	}
}
