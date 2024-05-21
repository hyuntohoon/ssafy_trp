package com.ssafy.enjoytrip.model.dto;

import java.util.Date;
import java.util.List;

public class TripPlanRequest {
    private String name;
    private String userId;
    private List<Integer> attractionIds;
    private Date tripDate;

    @Override
    public String toString() {
        return "TripPlanRequest{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", attractionIds=" + attractionIds +
                ", tripDate=" + tripDate +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Integer> getAttractionIds() {
        return attractionIds;
    }

    public void setAttractionIds(List<Integer> attractionIds) {
        this.attractionIds = attractionIds;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public TripPlanRequest() {
    }

    public TripPlanRequest(String name, String userId, List<Integer> attractionIds, Date tripDate) {
        this.name = name;
        this.userId = userId;
        this.attractionIds = attractionIds;
        this.tripDate = tripDate;
    }
}