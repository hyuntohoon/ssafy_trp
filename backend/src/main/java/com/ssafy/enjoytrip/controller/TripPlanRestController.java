package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import com.ssafy.enjoytrip.model.dto.AttractionInfo;
import com.ssafy.enjoytrip.model.service.TripPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tripplan")
public class TripPlanRestController {

    private final TripPlanService tripPlanService;

    @Autowired
    public TripPlanRestController(TripPlanService tripPlanService) {
        this.tripPlanService = tripPlanService;
    }

    @GetMapping("/{id}")
    public TripPlanWithPlaces getTripPlanById(@PathVariable int id) {
        return tripPlanService.getTripPlanById(id);
    }

    @GetMapping("/user/{userId}")
    public List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(@PathVariable String userId) {
        return tripPlanService.getTripPlansWithPlacesByUserId(userId);
    }

    @PostMapping
    public TripPlan setTripPlan(@RequestBody TripPlanRequest tripPlanRequest) {
        return tripPlanService.setTripPlan(tripPlanRequest.getName(), tripPlanRequest.getUserId(), tripPlanRequest.getAttractionIds());
    }

    @PutMapping("/{id}")
    public boolean updateTripPlan(@PathVariable int id, @RequestBody TripPlanRequest tripPlanRequest) {
        return tripPlanService.updateTripPlan(id, tripPlanRequest);
    }

    @DeleteMapping("/{id}")
    public boolean delTripPlan(@PathVariable int id) {
        return tripPlanService.delTripPlan(id);
    }
}