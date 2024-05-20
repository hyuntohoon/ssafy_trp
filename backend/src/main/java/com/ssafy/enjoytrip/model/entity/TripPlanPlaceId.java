package com.ssafy.enjoytrip.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class TripPlanPlaceId implements Serializable {
    private Integer tripPlan;
    private int attractionInfo;

    @Override
    public String toString() {
        return "TripPlanPlaceId{" +
                "tripPlan=" + tripPlan +
                ", attractionInfo=" + attractionInfo +
                '}';
    }

    public Integer getTripPlan() {
        return tripPlan;
    }

    public void setTripPlan(Integer tripPlan) {
        this.tripPlan = tripPlan;
    }

    public int getAttractionInfo() {
        return attractionInfo;
    }

    public void setAttractionInfo(int attractionInfo) {
        this.attractionInfo = attractionInfo;
    }

    public TripPlanPlaceId(Integer tripPlan, int attractionInfo) {
        this.tripPlan = tripPlan;
        this.attractionInfo = attractionInfo;
    }

    public TripPlanPlaceId() {
    }
}
