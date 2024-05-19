package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private Integer postId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @Column(name = "Content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "CreateTimeStamp", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTimeStamp;

    @Column(name = "UpdateTimeStamp", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updateTimeStamp;

    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "photo", columnDefinition = "LONGTEXT")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "content_id", insertable = false, updatable = false)
    private AttractionInfo attractionInfo;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public AttractionInfo getAttractionInfo() {
        return attractionInfo;
    }

    public void setAttractionInfo(AttractionInfo attractionInfo) {
        this.attractionInfo = attractionInfo;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTimeStamp=" + createTimeStamp +
                ", updateTimeStamp=" + updateTimeStamp +
                ", contentId=" + contentId +
                ", photo='" + photo + '\'' +
                ", attractionInfo=" + attractionInfo +
                '}';
    }

    public Post() {
    }

    public Post(Integer postId, User user, String title, String content, Timestamp createTimeStamp, Timestamp updateTimeStamp, Integer contentId, String photo) {
        this.postId = postId;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createTimeStamp = createTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.contentId = contentId;
        this.photo = photo;
    }
}
