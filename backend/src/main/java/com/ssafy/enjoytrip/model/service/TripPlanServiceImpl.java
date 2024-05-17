package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.AttractionInfo;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.TripPlanDao;
import com.ssafy.enjoytrip.model.dto.TripPlan;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TripPlanServiceImpl implements TripPlanService {

	private TripPlanDao tripPlanDao;

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
	public boolean setTripPlan(TripPlan tripPlan) {
		// TripPlan 저장
		int rowsAffected = tripPlanDao.setTripPlan(tripPlan);
		if (rowsAffected > 0) {
			// TripPlan과 관련된 장소 저장
			for (AttractionInfo attractionInfo : tripPlan.getPlaces()) {
				tripPlanDao.addPlaceToTripPlan(tripPlan.getId(), attractionInfo.getContentId());
			}
			return true;
		}
		return false;
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
