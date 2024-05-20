package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    public User() {
    }

    @Id
    @Column(name = "id", length = 10, nullable = false)
    private String id;

    @Column(name = "pw", length = 100)
    private String pw;

    @Column(name = "name", length = 10)
    private String name;

    @Column(name = "salt", length = 20)
    private String salt;

    @Column(name = "token", length = 1000)
    private String token;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", salt='" + salt + '\'' +
                ", token='" + token + '\'' +
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String id, String pw, String name, String salt, String token) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.salt = salt;
        this.token = token;
    }
}
