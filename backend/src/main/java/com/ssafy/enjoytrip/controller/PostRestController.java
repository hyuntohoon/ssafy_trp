package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.model.dto.Post;
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
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        System.out.println(post);
        boolean result = postService.createPost(post);
        if (result)
            return ResponseEntity.ok("createdPost");
        else
            return ResponseEntity.notFound().build();
    }

    // 게시글 ID로 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable String postId) {
        Post post = postService.getPost(postId);
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
    public ResponseEntity<?> updatePost(@RequestBody Post post, @PathVariable String postId) {
        // post.set
        System.out.println(post);
        post.setPostId(Integer.parseInt(postId));
        boolean result = postService.updatePost(post);
        if (result) {
            return ResponseEntity.ok("updatedPost");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable String postId) {

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
            @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println(UserID);
        List<Post> posts = postService.searchPosts(title, UserID, content, page, pageSize);
        return ResponseEntity.ok(posts);
    }
}
