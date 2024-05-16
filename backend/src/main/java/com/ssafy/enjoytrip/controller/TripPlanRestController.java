package com.ssafy.enjoytrip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.service.TripPlanService;

@RestController
@RequestMapping("/tripplan") 
public class TripPlanRestController {
	private TripPlanService tripPlanService;

	public TripPlanRestController(TripPlanService tripPlanService) {
		this.tripPlanService = tripPlanService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getTripPlan(@PathVariable String userId) {
		List<TripPlan> tripPlans = tripPlanService.getTripPlan(userId);
		if (!tripPlans.isEmpty()) {
			return ResponseEntity.ok(tripPlans); // 200
		} else {
			return ResponseEntity.noContent().build(); // 204
		}
	}

	@PostMapping("")
	public ResponseEntity<TripPlan> setTripPlan(@RequestBody TripPlan tripPlan) {
		boolean result = tripPlanService.setTripPlan(tripPlan);
		if (result) {
			return ResponseEntity.ok(tripPlan); // 200 OK with body
		} else {
			return ResponseEntity.noContent().build(); // 204 No Content
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTripPlan(@PathVariable int id, @RequestBody TripPlan tripPlan) {
		tripPlan.setId(id);
		boolean result = tripPlanService.updateTripPlan(tripPlan);
		if (result) {
			return ResponseEntity.ok(result); // 200
		} else {
			return ResponseEntity.noContent().build(); // 204
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delTripPlan(@PathVariable int id) {
		boolean result = tripPlanService.delTripPlan(id);
		if (result) {
			return ResponseEntity.ok(result); // 200
		} else {
			return ResponseEntity.noContent().build(); // 204
		}
	}
}
