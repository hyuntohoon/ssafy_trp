package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gugun")
public class Gugun {
    @Id
    @Column(name = "gugun_code")
    private Integer gugunCode;

    @Column(name = "gugun_name", length = 30)
    private String gugunName;

    @Column(name = "sido_code")
    private Integer sidoCode;

    @Override
    public String toString() {
        return "Gugun{" +
                "gugunCode=" + gugunCode +
                ", gugunName='" + gugunName + '\'' +
                ", sidoCode=" + sidoCode +
                '}';
    }

    public Integer getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(Integer gugunCode) {
        this.gugunCode = gugunCode;
    }

    public String getGugunName() {
        return gugunName;
    }

    public void setGugunName(String gugunName) {
        this.gugunName = gugunName;
    }

    public Integer getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(Integer sidoCode) {
        this.sidoCode = sidoCode;
    }

    public Gugun() {
    }

    public Gugun(Integer gugunCode, String gugunName, Integer sidoCode) {
        this.gugunCode = gugunCode;
        this.gugunName = gugunName;
        this.sidoCode = sidoCode;
    }
}
