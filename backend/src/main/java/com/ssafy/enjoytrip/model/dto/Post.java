package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

public class Post {
    private Integer postId;
    private String userId;
    private String title;
    private String content;
    private Timestamp createTimeStamp;
    private Timestamp updateTimeStamp;
    private int content_id;
    private String photo;

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTimeStamp=" + createTimeStamp +
                ", updateTimeStamp=" + updateTimeStamp +
                ", content_id=" + content_id +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Post() {
    }

    public Post(Integer postId, String userId, String title, String content, Timestamp createTimeStamp, Timestamp updateTimeStamp, int content_id, String photo) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createTimeStamp = createTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.content_id = content_id;
        this.photo = photo;
    }
}
