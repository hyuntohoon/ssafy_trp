package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.entity.Post;
import com.ssafy.enjoytrip.model.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public boolean createPost(Post post) {
        postRepository.save(post);
        return true;
    }

    @Override
    public Post getPost(Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        return post.orElse(null);
    }

    @Override
    public boolean deletePost(Integer postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePost(Post post) {
        if (postRepository.existsById(post.getPostId())) {
            postRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPosts(String order, int page, int numItems) {
        PageRequest pageRequest = PageRequest.of(page - 1, numItems, Sort.by("postId").ascending());
        if ("DESC".equalsIgnoreCase(order)) {
            pageRequest = PageRequest.of(page - 1, numItems, Sort.by("postId").descending());
        }
        return postRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Post> searchPosts(String title, String userId, String content, int page, int pageSize) {
        Specification<Post> spec = Specification.where(null);

        if (StringUtils.hasText(title)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }

        if (StringUtils.hasText(userId)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("user").get("id"), "%" + userId + "%"));
        }

        if (StringUtils.hasText(content)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("content"), "%" + content + "%"));
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createTimeStamp").descending());

        return postRepository.findAll(spec, pageRequest).getContent();
    }
}
