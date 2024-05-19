package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sido")
public class Sido {
    @Id
    @Column(name = "sido_code")
    private int sidoCode;

    @Column(name = "sido_name", length = 30)
    private String sidoName;

    @Override
    public String toString() {
        return "Sido{" +
                "sidoCode=" + sidoCode +
                ", sidoName='" + sidoName + '\'' +
                '}';
    }

    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) {
        this.sidoCode = sidoCode;
    }

    public String getSidoName() {
        return sidoName;
    }

    public void setSidoName(String sidoName) {
        this.sidoName = sidoName;
    }

    public Sido() {
    }

    public Sido(int sidoCode, String sidoName) {
        this.sidoCode = sidoCode;
        this.sidoName = sidoName;
    }
}
