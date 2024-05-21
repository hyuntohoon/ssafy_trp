package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.PlaceDTO;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlacesDTO;
import com.ssafy.enjoytrip.model.entity.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import com.ssafy.enjoytrip.model.service.TripPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<TripPlanWithPlaces> tripPlan = tripPlanService.getTripPlanById(id);
        if (tripPlan.isPresent()) {
            return ResponseEntity.ok(tripPlan.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<TripPlan> createTripPlan(@RequestBody TripPlanRequest tripPlanRequest) {
        TripPlan tripPlan = tripPlanService.createTripPlan(tripPlanRequest.getName(),tripPlanRequest.getUserId(),tripPlanRequest.getAttractionIds(),tripPlanRequest.getTripDate());
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
    public ResponseEntity<Void> deleteTripPlan(@PathVariable int id) {
        boolean deleted = tripPlanService.deleteTripPlan(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{tripPlanId}/invite")
    public ResponseEntity<?> inviteUserToTripPlan(@PathVariable Integer tripPlanId, @RequestParam String userId) {
        try {
            tripPlanService.inviteUserToTripPlan(tripPlanId, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/tripPlans/places")
    public List<PlaceDTO> getTripPlansByUserId(@RequestParam String userId) {
        return tripPlanService.getTripPlansByUserId(userId);
    }
}
