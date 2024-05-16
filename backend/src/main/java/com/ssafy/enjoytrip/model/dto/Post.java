package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

public class Post {
    private Integer postId;
    private String userId;
    private String title;
    private String content;
    private Timestamp createTimeStamp;
    private Timestamp updateTimeStamp;
    @Override
    public String toString() {
        return "Post [postId=" + postId + ", userId=" + userId + ", title=" + title + ", content=" + content
                + ", createTimeStamp=" + createTimeStamp + ", updateTimeStamp=" + updateTimeStamp + "]";
    }
    public Post() {
    }
    public Post(Integer postId, String userId, String title, String content, Timestamp createTimeStamp,
                Timestamp updateTimeStamp) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createTimeStamp = createTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
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
}
