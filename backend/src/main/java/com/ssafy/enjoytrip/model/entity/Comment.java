package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "PostID", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;



    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "CreateTimeStamp", nullable = false, updatable = false)
    private Timestamp createTimeStamp;

    @Column(name = "UpdateTimeStamp")
    private Timestamp updateTimeStamp;

    @Column(name = "likes")
    private Integer likes;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", post=" + post +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", createTimeStamp=" + createTimeStamp +
                ", updateTimeStamp=" + updateTimeStamp +
                ", likes=" + likes +
                '}';
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Comment() {
    }

    public Comment(Integer commentId, Post post, User user, String content, Timestamp createTimeStamp, Timestamp updateTimeStamp, Integer likes) {
        this.commentId = commentId;
        this.post = post;
        this.user = user;
        this.content = content;
        this.createTimeStamp = createTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.likes = likes;
    }
// Getters and Setters
}
