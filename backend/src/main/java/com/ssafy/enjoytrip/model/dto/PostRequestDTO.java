package com.ssafy.enjoytrip.model.dto;

public class PostRequestDTO {
    private String userId;
    private String title;
    private String content;
    private Integer postTypeId;
    private String Photo;
    private Integer contentId;

    @Override
    public String toString() {
        return "PostRequestDTO{" +
                "userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postTypeId=" + postTypeId +
                ", Photo='" + Photo + '\'' +
                ", contentId=" + contentId +
                '}';
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

    public Integer getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(Integer postTypeId) {
        this.postTypeId = postTypeId;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public PostRequestDTO() {
    }

    public PostRequestDTO(String userId, String title, String content, Integer postTypeId, String photo, Integer contentId) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.postTypeId = postTypeId;
        Photo = photo;
        this.contentId = contentId;
    }
}
