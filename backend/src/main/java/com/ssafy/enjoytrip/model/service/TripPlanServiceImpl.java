package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.*;
import com.ssafy.enjoytrip.model.entity.*;
import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import com.ssafy.enjoytrip.model.entity.TripPlan;
import com.ssafy.enjoytrip.model.entity.User;
import com.ssafy.enjoytrip.model.repository.AttractionInfoRepository;
import com.ssafy.enjoytrip.model.repository.TripPlanRepository;
import com.ssafy.enjoytrip.model.repository.TripPlanPlaceRepository;
import com.ssafy.enjoytrip.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public TripPlan createTripPlan(String name, String userId, List<Integer> attractionIds, Date tripDate) {
		// TripPlan을 생성하고 저장합니다.
		TripPlan tripPlan = new TripPlan();
		tripPlan.setName(name);
		tripPlan.setUserId(userId);
		tripPlan.setTripDate(tripDate);
		tripPlanRepository.save(tripPlan);

		// TripPlan에 장소를 추가합니다.
		addPlacesToTripPlan(tripPlan, attractionIds);
		return tripPlan;
	}

	@Override
	@Transactional
	public void inviteUserToTripPlan(Integer tripPlanId, String userId) {
		TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
				.orElseThrow(() -> new RuntimeException("TripPlan not found"));

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		tripPlan.getUsers().add(user);
		tripPlanRepository.save(tripPlan);
	}

	@Override
	@Transactional
	public boolean updateTripPlan(int id, TripPlanRequest tripPlanRequest) {
		Optional<TripPlan> optionalTripPlan = tripPlanRepository.findById(id);
		if (optionalTripPlan.isPresent()) {
			TripPlan tripPlan = optionalTripPlan.get();
			tripPlan.setName(tripPlanRequest.getName());
			tripPlan.setUserId(tripPlanRequest.getUserId());
			tripPlan.setTripDate(tripPlanRequest.getTripDate());
			tripPlanRepository.save(tripPlan);
			// 기존 장소를 삭제합니다.
			tripPlanPlaceRepository.deleteByTripPlanId(id);

			// 새로운 장소를 추가합니다.
			List<Integer> newAttractionIds = tripPlanRequest.getAttractionIds();
			int order = 0; // 장소 순서를 설정하는 변수
			tripPlan.setId(id);
			for (Integer attractionId : newAttractionIds) {
				TripPlanPlace newTripPlanPlace = new TripPlanPlace();
				newTripPlanPlace.setTripPlan(tripPlan);
				AttractionInfo attractionInfo = attractionInfoRepository.findById(attractionId)
						.orElseThrow(() -> new IllegalArgumentException("Invalid attraction ID: " + attractionId));
				newTripPlanPlace.setAttractionInfo(attractionInfo);
				newTripPlanPlace.setOrder(order++);
				tripPlanPlaceRepository.save(newTripPlanPlace);
			}

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

	public List<PlaceDTO> getTripPlansByUserId(String userId) {
		List<TripPlan> tripPlans = tripPlanRepository.findByUserId(userId);

		return tripPlans.stream().flatMap(tripPlan -> tripPlan.getPlaces().stream().map(place -> {
			TripPlanDTO tripPlanDTO = new TripPlanDTO();
			tripPlanDTO.setId(tripPlan.getId());
			tripPlanDTO.setName(tripPlan.getName());
			tripPlanDTO.setUserId(tripPlan.getUserId());

			PlaceDTO placeDTO = new PlaceDTO();
			placeDTO.setTripPlan(tripPlanDTO);
			placeDTO.setAttractionInfo(place.getAttractionInfo());
			placeDTO.setOrder(place.getOrder());
			placeDTO.setTripDate(tripPlan.getTripDate());
			return placeDTO;
		})).collect(Collectors.toList());
	}

	private void addPlacesToTripPlan(TripPlan tripPlan, List<Integer> attractionIds) {
		// 각 attractionId에 대해 TripPlanPlace를 생성하고 저장합니다.
		for (int order = 0; order < attractionIds.size(); order++) {
			int attractionId = attractionIds.get(order);
			Optional<AttractionInfo> attractionInfo = attractionInfoRepository.findById(attractionId);
			if (attractionInfo.isPresent()) {
				// TripPlanPlaceId를 생성하고 TripPlanPlace 엔티티를 생성합니다.
				TripPlanPlaceId id = new TripPlanPlaceId(tripPlan.getId(), attractionId);
				TripPlanPlace tripPlanPlace = new TripPlanPlace(id, tripPlan, attractionInfo.get(), order);
				tripPlanPlaceRepository.save(tripPlanPlace);
			}
		}
	}

	public List<PlaceDTO> getTripPlanPlaces(Integer tripPlanId) {
		TripPlan tripPlan = tripPlanRepository.findById(tripPlanId).orElseThrow(() -> new RuntimeException("TripPlan not found"));

		TripPlanDTO tripPlanDTO = new TripPlanDTO();
		tripPlanDTO.setId(tripPlan.getId());
		tripPlanDTO.setName(tripPlan.getName());
		tripPlanDTO.setUserId(tripPlan.getUserId());
		return tripPlan.getPlaces().stream().map(place -> {
			PlaceDTO placeDTO = new PlaceDTO();
			placeDTO.setTripPlan(tripPlanDTO);
			placeDTO.setAttractionInfo(place.getAttractionInfo());
			placeDTO.setOrder(place.getOrder());
			placeDTO.setTripDate(tripPlan.getTripDate());
			return placeDTO;
		}).collect(Collectors.toList());
	}
}
