package com.ssafy.enjoytrip.model.dto;

import com.ssafy.enjoytrip.model.entity.TripPlan;
import com.ssafy.enjoytrip.model.entity.TripPlanPlace;

import java.util.List;

public class TripPlanWithPlaces {
    private TripPlan tripPlan;
    private List<TripPlanPlace> places;

    @Override
    public String toString() {
        return "TripPlanWithPlaces{" +
                "tripPlan=" + tripPlan +
                ", places=" + places +
                '}';
    }

    public TripPlan getTripPlan() {
        return tripPlan;
    }

    public void setTripPlan(TripPlan tripPlan) {
        this.tripPlan = tripPlan;
    }

    public List<TripPlanPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<TripPlanPlace> places) {
        this.places = places;
    }

    public TripPlanWithPlaces() {
    }

    public TripPlanWithPlaces(TripPlan tripPlan, List<TripPlanPlace> places) {
        this.tripPlan = tripPlan;
        this.places = places;
    }
}
