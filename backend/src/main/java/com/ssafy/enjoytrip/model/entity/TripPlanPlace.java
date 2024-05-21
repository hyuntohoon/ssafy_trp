package com.ssafy.enjoytrip.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "trip_plan_place")
@IdClass(TripPlanPlaceId.class)
public class TripPlanPlace {

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "trip_plan_id", insertable = false, updatable = false)
    private TripPlan tripPlan;

    @Id
    @ManyToOne
    @JoinColumn(name = "content_id", insertable = false, updatable = false)
    private AttractionInfo attractionInfo;

    @Column(name = "`order`")
    private Integer order;

    @Override
    public String toString() {
        return "TripPlanPlace{" +
                "tripPlan=" + tripPlan +
                ", attractionInfo=" + attractionInfo +
                ", order=" + order +
                '}';
    }

    public TripPlanPlace(TripPlanPlaceId id, TripPlan tripPlan, AttractionInfo attractionInfo, Integer order) {
        this.tripPlan = tripPlan;
        this.attractionInfo = attractionInfo;
        this.order = order;
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

    public TripPlanPlace() {
    }

    public TripPlanPlace(TripPlan tripPlan, AttractionInfo attractionInfo, Integer order) {
        this.tripPlan = tripPlan;
        this.attractionInfo = attractionInfo;
        this.order = order;
    }
}
