package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.service.TripPlanService;

import java.util.List;

@RestController
@RequestMapping("/tripplan")
public class TripPlanRestController {
    private TripPlanService tripPlanService;

    public TripPlanRestController(TripPlanService tripPlanService) {
        this.tripPlanService = tripPlanService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripPlanWithPlaces> getTripPlanById(@PathVariable int id) {
        TripPlanWithPlaces tripPlanWithPlaces = tripPlanService.getTripPlanById(id);
        if (tripPlanWithPlaces != null) {
            return ResponseEntity.ok(tripPlanWithPlaces);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TripPlanWithPlaces>> getTripPlansByUserId(@PathVariable String userId) {
        List<TripPlanWithPlaces> getTripPlansByUserId = tripPlanService.getTripPlansWithPlacesByUserId(userId);
        if (getTripPlansByUserId != null && !getTripPlansByUserId.isEmpty()) {
            return ResponseEntity.ok(getTripPlansByUserId);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<TripPlan> setTripPlan(@RequestBody TripPlanRequest tripPlanRequest) {
        TripPlan tripPlan = tripPlanService.setTripPlan(tripPlanRequest.getName(), tripPlanRequest.getUserId(), tripPlanRequest.getAttractionIds());
        if (tripPlan != null) {
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
