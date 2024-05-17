package com.ssafy.enjoytrip.model.dto;

import java.util.List;

public class TripPlanWithPlaces {
    private int id;
    private String name;
    private String userId;
    private List<AttractionInfo> places;

    @Override
    public String toString() {
        return "TripPlanWithPlaces{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", places=" + places +
                '}';
    }

    public TripPlanWithPlaces() {
    }

    public TripPlanWithPlaces(int id, String name, String userId, List<AttractionInfo> places) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.places = places;
    }
// Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<AttractionInfo> getPlaces() {
        return places;
    }

    public void setPlaces(List<AttractionInfo> places) {
        this.places = places;
    }
}
