package com.ssafy.enjoytrip.model.dto;

import java.sql.Timestamp;

public class Comment {
    private int commentId;        // CommentID int AI PK
    private int postId;           // PostID int
    private String userId;        // UserID varchar(10)
    private String content;       // Content text
    private Timestamp createTimeStamp; // CreateTimeStamp timestamp
    private Timestamp updateTimeStamp; // UpdateTimeStamp timestamp
    private int likes;
    // Constructors
    public Comment() {
    }

    public Comment(int commentId, int postId, String userId, String content, Timestamp createTimeStamp, Timestamp updateTimeStamp, int likes) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createTimeStamp = createTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.likes = likes;
    }

    @Override
    public String toString() {

        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", createTimeStamp=" + createTimeStamp +
                ", updateTimeStamp=" + updateTimeStamp +
                ", likes=" + likes +
                '}';
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTimeStamp(Timestamp createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public void setUpdateTimeStamp(Timestamp updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreateTimeStamp() {
        return createTimeStamp;
    }

    public Timestamp getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public int getLikes() {
        return likes;
    }

}
