package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.PostRequestDTO;
import com.ssafy.enjoytrip.model.dto.UpdatePostDTO;
import com.ssafy.enjoytrip.model.entity.Post;
import com.ssafy.enjoytrip.model.service.PostService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        System.out.println(postRequestDTO);
        boolean result = postService.createPost(postRequestDTO);
        if (result)
            return ResponseEntity.ok("createdPost");
        else
            return ResponseEntity.notFound().build();
    }

    // 게시글 ID로 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable int postId) {
        System.out.println(postId);
        Post post = postService.getPostById(postId);
        System.out.println(post);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // // 모든 게시글 조회
    // @GetMapping
    // public ResponseEntity<List<?>> getAllPosts() {
    // List<Post> posts = postService.getAllPosts();
    // return ResponseEntity.ok(posts);
    // }

    // 게시글 업데이트
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable String postId, @RequestBody UpdatePostDTO updatePostDTO) {
        updatePostDTO.setPostID(Integer.parseInt(postId));
        boolean result = postService.updatePost(updatePostDTO);
        if (result) {
            return ResponseEntity.ok("updatedPost");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {

        boolean isDeleted = postService.deletePost(postId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false, defaultValue = "DESC") String order,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Post> posts = postService.getPosts(order, page, pageSize);
        return ResponseEntity.ok(posts);
    }

    // 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String UserID,
            @RequestParam(required = false) String content,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = true) int postTypeId) {
        List<Post> posts = postService.searchPosts(title, UserID, content, page, pageSize,postTypeId);
        return ResponseEntity.ok(posts);
    }
}
