package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TripPlanPlaceId implements Serializable {

    private Integer tripPlanId;
    private Integer contentId;

    public TripPlanPlaceId() {}

    public TripPlanPlaceId(Integer tripPlanId, Integer contentId) {
        this.tripPlanId = tripPlanId;
        this.contentId = contentId;
    }

    // Getters, setters, equals, and hashCode methods

    public Integer getTripPlanId() {
        return tripPlanId;
    }

    public void setTripPlanId(Integer tripPlanId) {
        this.tripPlanId = tripPlanId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripPlanPlaceId that = (TripPlanPlaceId) o;
        return Objects.equals(tripPlanId, that.tripPlanId) &&
                Objects.equals(contentId, that.contentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripPlanId, contentId);
    }
}
