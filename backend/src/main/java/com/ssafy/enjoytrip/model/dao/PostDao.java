package com.ssafy.enjoytrip.model.dao;

import com.ssafy.enjoytrip.model.dto.Post;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.session.RowBounds;

import java.util.List;

//@Mapper
public interface PostDao {
    public boolean createPost(Post post);

    public Post getPost(String postId);

    public boolean deletePost(String postId);

    public boolean updatePost(Post post);

    public List<Post> getAllPosts();

//    public List<Post> findAllPosts(@Param("order") String order, RowBounds rowBounds);

    public List<Post> searchPosts(String title, String userID, String content, int offset, int pageSize);
}
