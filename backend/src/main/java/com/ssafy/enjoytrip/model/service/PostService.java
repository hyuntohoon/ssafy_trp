package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.Post;

import java.util.List;

public interface PostService {
    public boolean createPost(Post post);

    public Post getPost(String postId);

    public boolean deletePost(String postId);

    public boolean updatePost(Post post);

    public List<Post> getAllPosts();

    public List<Post> getPosts(String order, int page, int numItems);
}
