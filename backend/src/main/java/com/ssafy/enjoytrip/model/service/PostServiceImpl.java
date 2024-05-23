package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.PostRequestDTO;
import com.ssafy.enjoytrip.model.dto.UpdatePostDTO;
import com.ssafy.enjoytrip.model.entity.AttractionInfo;
import com.ssafy.enjoytrip.model.entity.Post;
import com.ssafy.enjoytrip.model.entity.PostType;
import com.ssafy.enjoytrip.model.repository.AttractionInfoRepository;
import com.ssafy.enjoytrip.model.repository.PostRepository;
import com.ssafy.enjoytrip.model.repository.PostTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AttractionInfoRepository attractionInfoRepository;

    @Autowired
    private PostTypeRepository postTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    public boolean createPost(PostRequestDTO postRequestDTO) {
        // DTO를 엔티티로 변환
        Post post = new Post();
        System.out.println(postRequestDTO.getPhoto());
        post.setUserID(postRequestDTO.getUserId());
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        post.setPhoto(postRequestDTO.getPhoto());
        post.setCreateTimeStamp(new Timestamp(System.currentTimeMillis()));
        post.setUpdateTimeStamp(new Timestamp(System.currentTimeMillis()));

        // contentId 설정
        if (postRequestDTO.getContentId() != null) {
            AttractionInfo attractionInfo = attractionInfoRepository.findById(postRequestDTO.getContentId())
                    .orElseThrow(() -> new RuntimeException("AttractionInfo not found"));
            post.setContentId(attractionInfo);
        }

        // PostType 설정
        PostType postType = postTypeRepository.findById(postRequestDTO.getPostTypeId())
                .orElseThrow(() -> new RuntimeException("PostType not found"));
        post.setPostType(postType);

        // 엔티티 저장
        postRepository.save(post);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public Post getPostById(Integer postId) {
        logger.info("Fetching post with ID: " + postId);
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            logger.info("Post found: " + post.get());
        } else {
            logger.warn("Post not found with ID: " + postId);
        }
        return post.orElse(null);
    }

    @Override
    public boolean deletePost(Integer postID) {
        if (postRepository.existsById(postID)) {
            postRepository.deleteById(postID);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updatePost(UpdatePostDTO updatePostDTO) {
        Optional<Post> optionalPost = postRepository.findById(updatePostDTO.getPostID());
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setUserID(updatePostDTO.getUserID());
            post.setTitle(updatePostDTO.getTitle());
            post.setContent(updatePostDTO.getContent());
            post.setPhoto(updatePostDTO.getPhoto());
            post.setCreateTimeStamp(updatePostDTO.getCreateTimeStamp());
            post.setUpdateTimeStamp(updatePostDTO.getUpdateTimeStamp());

            // contentId 설정
            if (updatePostDTO.getContentId() != null) {
                AttractionInfo attractionInfo = attractionInfoRepository.findById(updatePostDTO.getContentId())
                        .orElseThrow(() -> new RuntimeException("AttractionInfo not found"));
                post.setContentId(attractionInfo);
            } else {
                post.setContentId(null);
            }

            postRepository.save(post);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPosts(String order, int page, int numItems) {
        PageRequest pageRequest = PageRequest.of(page - 1, numItems, Sort.by("postID").ascending());
        if ("DESC".equalsIgnoreCase(order)) {
            pageRequest = PageRequest.of(page - 1, numItems, Sort.by("postID").descending());
        }
        return postRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Post> searchPosts(String title, String userId, String content, int page, int pageSize, int postTypeId) {
        Specification<Post> spec = Specification.where(null);

        if (StringUtils.hasText(title)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }

        if (StringUtils.hasText(userId)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("userID"), "%" + userId + "%"));
        }

        if (StringUtils.hasText(content)) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("content"), "%" + content + "%"));
        }

        if (postTypeId > 0) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("postType").get("id"), postTypeId));
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createTimeStamp").descending());

        return postRepository.findAll(spec, pageRequest).getContent();
    }
}
