package com.ssafy.enjoytrip.model.dto;

import java.util.List;

public class TripPlanWithPlacesDTO {
    private TripPlanDTO tripPlan;
    private List<PlaceDTO> places;

    // Getters and Setters
    public TripPlanDTO getTripPlan() {
        return tripPlan;
    }

    public void setTripPlan(TripPlanDTO tripPlan) {
        this.tripPlan = tripPlan;
    }

    public List<PlaceDTO> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceDTO> places) {
        this.places = places;
    }
}
