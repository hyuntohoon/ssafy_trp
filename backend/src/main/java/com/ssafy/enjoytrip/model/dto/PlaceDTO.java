package com.ssafy.enjoytrip.model.dto;

import com.ssafy.enjoytrip.model.entity.AttractionInfo;

import java.util.Date;

public class PlaceDTO {
    private TripPlanDTO tripPlan;
    private AttractionInfo attractionInfo;
    private int order;
    private Date tripDate;

    // Getters and Setters
    public TripPlanDTO getTripPlan() {
        return tripPlan;
    }

    public void setTripPlan(TripPlanDTO tripPlan) {
        this.tripPlan = tripPlan;
    }

    public AttractionInfo getAttractionInfo() {
        return attractionInfo;
    }

    public void setAttractionInfo(AttractionInfo attractionInfo) {
        this.attractionInfo = attractionInfo;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }
}
