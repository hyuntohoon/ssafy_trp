package com.ssafy.enjoytrip.model.dto;

import java.util.List;

public class TripPlanRequest {
    private String name;
    private String userId;
    private List<Integer> attractionIds;

    @Override
    public String toString() {
        return "TripPlanRequest{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", attractionIds=" + attractionIds +
                '}';
    }

    public TripPlanRequest(String name) {
        this.name = name;
    }

    public TripPlanRequest(String name, String userId, List<Integer> attractionIds) {
        this.name = name;
        this.userId = userId;
        this.attractionIds = attractionIds;
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
}