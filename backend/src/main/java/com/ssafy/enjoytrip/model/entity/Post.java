package com.ssafy.enjoytrip.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private int postID;

    @Column(name = "UserID", length = 10, nullable = false)
    private String userID;

    @Column(name = "Title", length = 255, nullable = false)
    private String title;

    @Column(name = "Content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "photo", columnDefinition = "LONGTEXT")
    private String photo;

    @Column(name = "create_time_stamp", nullable = false)
    private Timestamp createTimeStamp;

    @Column(name = "update_time_stamp")
    private Timestamp updateTimeStamp;

    @ManyToOne
    @JoinColumn(name = "post_type_id", nullable = false)
    private PostType postType;

    @ManyToOne
    @JoinColumn(name = "content_id", nullable = true)
    private AttractionInfo contentId;

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", userID='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", photo='" + photo + '\'' +
                ", createTimeStamp=" + createTimeStamp +
                ", updateTimeStamp=" + updateTimeStamp +
                ", postType=" + postType +
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

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public AttractionInfo getContentId() {
        return contentId;
    }

    public void setContentId(AttractionInfo contentId) {
        this.contentId = contentId;
    }

    public Post() {
    }

    public Post(int postID, String userID, String title, String content, String photo, Timestamp createTimeStamp, Timestamp updateTimeStamp, PostType postType, AttractionInfo contentId) {
        this.postID = postID;
        this.userID = userID;
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.createTimeStamp = createTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
        this.postType = postType;
        this.contentId = contentId;
    }
}
