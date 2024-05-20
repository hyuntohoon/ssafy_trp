package com.ssafy.enjoytrip.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(mappedBy = "tripPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TripPlanPlace> places;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "trip_plan_users",
            joinColumns = @JoinColumn(name = "trip_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @Override
    public String toString() {
        return "TripPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", places=" + places +
                ", users=" + users +
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

    public List<TripPlanPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<TripPlanPlace> places) {
        this.places = places;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public TripPlan() {
    }

    public TripPlan(Integer id, String name, String userId, List<TripPlanPlace> places, List<User> users) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.places = places;
        this.users = users;
    }
}
