package com.ssafy.enjoytrip.model.dto;

public class UpdatePasswordRequest {
    private String userId;
    private String newPassword;

    // getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
