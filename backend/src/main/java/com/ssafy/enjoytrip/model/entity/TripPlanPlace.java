package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;

@Table(name = "trip_plan_place")
@Entity
public class TripPlanPlace {

    @EmbeddedId
    private TripPlanPlaceId id;

    @ManyToOne
    @MapsId("tripPlanId")
    @JoinColumn(name = "tripPlanId")
    private TripPlan tripPlan;

    @ManyToOne
    @MapsId("contentId")
    @JoinColumn(name = "content_id")
    private AttractionInfo attractionInfo;

    @Column(name = "`order`")
    private Integer order;

    public TripPlanPlace() {}

    public TripPlanPlace(TripPlanPlaceId id, TripPlan tripPlan, AttractionInfo attractionInfo, Integer order) {
        this.id = id;
        this.tripPlan = tripPlan;
        this.attractionInfo = attractionInfo;
        this.order = order;
    }

    // Getters and setters

    public TripPlanPlaceId getId() {
        return id;
    }

    public void setId(TripPlanPlaceId id) {
        this.id = id;
    }

    public TripPlan getTripPlan() {
        return tripPlan;
    }

    public void setTripPlan(TripPlan tripPlan) {
        this.tripPlan = tripPlan;
    }

    public AttractionInfo getAttractionInfo() {
        return attractionInfo;
    }

    public void setAttractionInfo(AttractionInfo attractionInfo) {
        this.attractionInfo = attractionInfo;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
