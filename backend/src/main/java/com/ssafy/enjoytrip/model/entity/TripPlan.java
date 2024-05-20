package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trip_plan")
public class TripPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "user_id", nullable = false, length = 255)
    private String userId;

    @Override
    public String toString() {
        return "TripPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public TripPlan() {
    }

    public TripPlan(Integer id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
