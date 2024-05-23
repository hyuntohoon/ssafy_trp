package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

public class UpdatePostDTO {
    private int postID;
    private String userID;
    private String title;
    private String content;
    private String photo;
    private Timestamp createTimeStamp;
    private Timestamp updateTimeStamp;
    private Integer contentId;

    @Override
    public String toString() {
        return "UpdatePostDTO{" +
                "postID=" + postID +
                ", userID='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", photo='" + photo + '\'' +
                ", createTimeStamp=" + createTimeStamp +
                ", updateTimeStamp=" + updateTimeStamp +
                ", contentId=" + contentId +
                '}';
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Timestamp getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Timestamp createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public Timestamp getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Timestamp updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public UpdatePostDTO() {
    }

    public UpdatePostDTO(int postID, String userID, String title, String content, String photo, Timestamp createTimeStamp, Timestamp updateTimeStamp, Integer contentId) {
        this.postID = postID;
        this.userID = userID;
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.createTimeStamp = createTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.contentId = contentId;
    }
}
