package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
@Entity
@Table(name = "attraction_info")
public class AttractionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private int contentId;

    @Column(name = "sido_code")
    private Integer sidoCode;

    @Column(name = "gugun_code")
    private Integer gugunCode;
    // 다른 컬럼들
    @Column(name = "addr1")
    private String addr1;

    @Column(name = "addr2")
    private String addr2;

    @Column(name = "content_type_id")
    private int contentTypeId;

    @Column(name = "first_image")
    private String firstImage;

    @Column(name = "first_image2")
    private String firstImage2;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "mlevel", length = 2)
    private String mlevel;

    @Column(name = "readcount")
    private int readcount;

    @Column(name = "tel")
    private String tel;

    @Column(name = "title")
    private String title;

    @Column(name = "zipcode")
    private String zipcode;

    @Override
    public String toString() {
        return "AttractionInfo{" +
                "contentId=" + contentId +
                ", sidoCode=" + sidoCode +
                ", gugunCode=" + gugunCode +
                ", addr1='" + addr1 + '\'' +
                ", addr2='" + addr2 + '\'' +
                ", contentTypeId=" + contentTypeId +
                ", firstImage='" + firstImage + '\'' +
                ", firstImage2='" + firstImage2 + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", mlevel='" + mlevel + '\'' +
                ", readcount=" + readcount +
                ", tel='" + tel + '\'' +
                ", title='" + title + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public Integer getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(Integer sidoCode) {
        this.sidoCode = sidoCode;
    }

    public Integer getGugunCode() {
        return gugunCode;
    }

    public void setGugunCode(Integer gugunCode) {
        this.gugunCode = gugunCode;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getFirstImage2() {
        return firstImage2;
    }

    public void setFirstImage2(String firstImage2) {
        this.firstImage2 = firstImage2;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMlevel() {
        return mlevel;
    }

    public void setMlevel(String mlevel) {
        this.mlevel = mlevel;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public AttractionInfo() {
    }

    public AttractionInfo(int contentId, Integer sidoCode, Integer gugunCode, String addr1, String addr2, int contentTypeId, String firstImage, String firstImage2, double latitude, double longitude, String mlevel, int readcount, String tel, String title, String zipcode) {
        this.contentId = contentId;
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.contentTypeId = contentTypeId;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mlevel = mlevel;
        this.readcount = readcount;
        this.tel = tel;
        this.title = title;
        this.zipcode = zipcode;
    }
}