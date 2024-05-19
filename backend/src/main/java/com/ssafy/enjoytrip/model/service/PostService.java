package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.Post;

import java.util.List;

public interface PostService {
    boolean createPost(Post post);

    Post getPost(Integer postId);

    boolean deletePost(Integer postId);

    boolean updatePost(Post post);

    List<Post> getAllPosts();

    List<Post> getPosts(String order, int page, int numItems);

    List<Post> searchPosts(String title, String userId, String content, int page, int pageSize);
}
