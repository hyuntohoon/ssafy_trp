package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attraction_description")
public class AttractionDescription {

    @Id
    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "homepage", length = 100)
    private String homepage;

    @Column(name = "overview", length = 10000)
    private String overview;

    @Column(name = "telname", length = 45)
    private String telname;

    public AttractionDescription() {
    }

    @Override
    public String toString() {
        return "AttractionDescription{" +
                "contentId=" + contentId +
                ", homepage='" + homepage + '\'' +
                ", overview='" + overview + '\'' +
                ", telname='" + telname + '\'' +
                '}';
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTelname() {
        return telname;
    }

    public void setTelname(String telname) {
        this.telname = telname;
    }

    public AttractionDescription(Integer contentId, String homepage, String overview, String telname) {
        this.contentId = contentId;
        this.homepage = homepage;
        this.overview = overview;
        this.telname = telname;
    }
}
