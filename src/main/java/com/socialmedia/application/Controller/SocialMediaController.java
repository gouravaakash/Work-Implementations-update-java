package com.socialmedia.application.Controller;

import com.socialmedia.application.Entity.Post;
import com.socialmedia.application.Services.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/social")
public class SocialMediaController {

    @Autowired
    private SocialMediaService socialMediaService;


    @PostMapping("/posts/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable Long userId, @RequestBody String content) {
        Post createdPost = socialMediaService.createPost(userId, content);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }


    @DeleteMapping("/posts/{userId}/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        socialMediaService.deletePost(postId, userId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


    @PostMapping("/follow/{followerId}/{followeeId}")
    public ResponseEntity<String> followUser(@PathVariable Long followerId, @PathVariable Long followeeId) {
        socialMediaService.followUser(followerId, followeeId);
        return ResponseEntity.status(HttpStatus.OK).body("User followed successfully");
    }


    @PostMapping("/unfollow/{followerId}/{followeeId}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long followerId, @PathVariable Long followeeId) {
        socialMediaService.unfollowUser(followerId, followeeId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


    @GetMapping("/feed/{userId}")
    public ResponseEntity<Page<Post>> getFeed(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Post> feed = socialMediaService.getFeed(userId, page, size);
        return ResponseEntity.ok(feed);
    }
}
