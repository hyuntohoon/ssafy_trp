package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import com.ssafy.enjoytrip.model.service.TripPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tripplan")
public class TripPlanRestController {

    private final TripPlanService tripPlanService;

    @Autowired
    public TripPlanRestController(TripPlanService tripPlanService) {
        this.tripPlanService = tripPlanService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripPlanWithPlaces> getTripPlanById(@PathVariable int id) {
        TripPlanWithPlaces tripPlan = tripPlanService.getTripPlanById(id);
        if (tripPlan == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(tripPlan);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TripPlanWithPlaces>> getTripPlansWithPlacesByUserId(@PathVariable String userId) {
        List<TripPlanWithPlaces> tripPlans = tripPlanService.getTripPlansWithPlacesByUserId(userId);
        if (tripPlans == null || tripPlans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(tripPlans);
    }

    @PostMapping
    public ResponseEntity<TripPlan> setTripPlan(@RequestBody TripPlanRequest tripPlanRequest) {
        TripPlan tripPlan = tripPlanService.setTripPlan(tripPlanRequest.getName(), tripPlanRequest.getUserId(), tripPlanRequest.getAttractionIds());
        if (tripPlan == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tripPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTripPlan(@PathVariable int id, @RequestBody TripPlanRequest tripPlanRequest) {
        boolean updated = tripPlanService.updateTripPlan(id, tripPlanRequest);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public boolean delTripPlan(@PathVariable int id) {
        return tripPlanService.delTripPlan(id);
    }
}