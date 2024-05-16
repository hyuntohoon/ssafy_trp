package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dao.PostDao;
import com.ssafy.enjoytrip.model.dao.TripPlanDao;
import com.ssafy.enjoytrip.model.dto.Post;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private PostDao postDao;

    public PostServiceImpl(PostDao postDao){
        this.postDao = postDao;
    }

    @Override
    public boolean createPost(Post post) {
        return postDao.createPost(post);
    }

    @Override
    public Post getPost(String postId) {
        return postDao.getPost(postId);
    }

    @Override
    public boolean deletePost(String postId) {
        return postDao.deletePost(postId);
    }

    @Override
    public boolean updatePost(Post post) {
        return postDao.updatePost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }

    public List<Post> getPosts(String order, int page, int numItems) {
        int offset = (page - 1) * numItems;
        RowBounds rowBounds = new RowBounds(offset, numItems);
        return postDao.findAllPosts(order, rowBounds);
    }

    @Override
    public List<Post> searchPosts(String title, String UserID, String content, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        System.out.println(UserID);
        return postDao.searchPosts(title, UserID, content, offset, pageSize);
    }
}
