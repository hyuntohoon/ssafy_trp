package com.ssafy.enjoytrip.model.dto;

public class Gugun {

  private int gugunCode;
  private String gugunName;
  private int sidoCode;

  @Override
  public String toString() {
    return "Gugun{" +
            "gugunCode=" + gugunCode +
            ", gugunName='" + gugunName + '\'' +
            ", sidoCode=" + sidoCode +
            '}';
  }

  public int getGugunCode() {
    return gugunCode;
  }

  public void setGugunCode(int gugunCode) {
    this.gugunCode = gugunCode;
  }

  public String getGugunName() {
    return gugunName;
  }

  public void setGugunName(String gugunName) {
    this.gugunName = gugunName;
  }

  public int getSidoCode() {
    return sidoCode;
  }

  public void setSidoCode(int sidoCode) {
    this.sidoCode = sidoCode;
  }

  public Gugun(int gugunCode, String gugunName, int sidoCode) {
    this.gugunCode = gugunCode;
    this.gugunName = gugunName;
    this.sidoCode = sidoCode;
  }
}
