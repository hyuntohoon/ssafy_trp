package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attraction_detail")
public class AttractionDetail {

    @Id
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "cat1", length = 3)
    private String cat1;

    @Column(name = "cat2", length = 5)
    private String cat2;

    @Column(name = "cat3", length = 9)
    private String cat3;

    @Column(name = "created_time", length = 14)
    private String createdTime;

    @Column(name = "modified_time", length = 14)
    private String modifiedTime;

    @Column(name = "booktour", length = 5)
    private String booktour;

    @Override
    public String toString() {
        return "AttractionDetail{" +
                "contentId=" + contentId +
                ", cat1='" + cat1 + '\'' +
                ", cat2='" + cat2 + '\'' +
                ", cat3='" + cat3 + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", booktour='" + booktour + '\'' +
                '}';
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getBooktour() {
        return booktour;
    }

    public void setBooktour(String booktour) {
        this.booktour = booktour;
    }

    public AttractionDetail() {
    }

    public AttractionDetail(Integer contentId, String cat1, String cat2, String cat3, String createdTime, String modifiedTime, String booktour) {
        this.contentId = contentId;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.booktour = booktour;
    }
}
