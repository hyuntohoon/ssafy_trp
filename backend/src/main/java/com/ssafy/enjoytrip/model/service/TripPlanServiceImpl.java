package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import com.ssafy.enjoytrip.model.entity.TripPlan;
import com.ssafy.enjoytrip.model.entity.TripPlanPlace;
import com.ssafy.enjoytrip.model.entity.TripPlanPlaceId;
import com.ssafy.enjoytrip.model.repository.AttractionInfoRepository;
import com.ssafy.enjoytrip.model.repository.TripPlanRepository;
import com.ssafy.enjoytrip.model.repository.TripPlanPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripPlanServiceImpl implements TripPlanService {

	@Autowired
	private TripPlanRepository tripPlanRepository;

	@Autowired
	private TripPlanPlaceRepository tripPlanPlaceRepository;

	@Autowired
	private AttractionInfoRepository attractionInfoRepository;

	@Override
	@Transactional
	public TripPlan createTripPlan(String name, String userId, List<Integer> attractionIds) {
		TripPlan tripPlan = new TripPlan();
		tripPlan.setName(name);
		tripPlan.setUserId(userId);
		tripPlanRepository.save(tripPlan);
		addPlacesToTripPlan(tripPlan, attractionIds);
		return tripPlan;
	}

	@Override
	@Transactional
	public boolean updateTripPlan(int id, TripPlanRequest tripPlanRequest) {
		Optional<TripPlan> optionalTripPlan = tripPlanRepository.findById(id);
		if (optionalTripPlan.isPresent()) {
			TripPlan tripPlan = optionalTripPlan.get();
			tripPlan.setName(tripPlanRequest.getName());
			tripPlan.setUserId(tripPlanRequest.getUserId());
			tripPlanRepository.save(tripPlan);

			tripPlanPlaceRepository.deleteByTripPlanId(id);
			addPlacesToTripPlan(tripPlan, tripPlanRequest.getAttractionIds());
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean deleteTripPlan(int id) {
		if (tripPlanRepository.existsById(id)) {
			tripPlanPlaceRepository.deleteByTripPlanId(id);
			tripPlanRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TripPlanWithPlaces> getTripPlanById(int id) {
		Optional<TripPlan> tripPlan = tripPlanRepository.findById(id);
		if (tripPlan.isPresent()) {
			List<TripPlanPlace> places = tripPlanPlaceRepository.findByTripPlanId(id);
			return Optional.of(new TripPlanWithPlaces(tripPlan.get(), places));
		}
		return Optional.empty();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(String userId) {
		List<TripPlan> tripPlans = tripPlanRepository.findByUserId(userId);
		return tripPlans.stream().map(tripPlan -> {
			List<TripPlanPlace> places = tripPlanPlaceRepository.findByTripPlanId(tripPlan.getId());
			return new TripPlanWithPlaces(tripPlan, places);
		}).collect(Collectors.toList());
	}

	private void addPlacesToTripPlan(TripPlan tripPlan, List<Integer> attractionIds) {
		for (int order = 0; order < attractionIds.size(); order++) {
			int attractionId = attractionIds.get(order);
			Optional<AttractionInfo> attractionInfo = attractionInfoRepository.findById(attractionId);
			if (attractionInfo.isPresent()) {
				TripPlanPlaceId id = new TripPlanPlaceId(tripPlan.getId(), attractionId);
				TripPlanPlace tripPlanPlace = new TripPlanPlace(id, tripPlan, attractionInfo.get(), order);
				tripPlanPlaceRepository.save(tripPlanPlace);
			}
		}
	}
}
