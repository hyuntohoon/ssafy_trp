package com.ssafy.enjoytrip.model.dto;

public class UserCreateDTO {
    private String id;
    private String pw;
    private String name;

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserCreateDTO() {
    }

    public UserCreateDTO(String id, String pw, String name) {
        this.id = id;
        this.pw = pw;
        this.name = name;
    }
}
