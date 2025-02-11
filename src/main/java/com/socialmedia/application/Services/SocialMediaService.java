package com.socialmedia.application.Services;

import com.socialmedia.application.Entity.Post;
import com.socialmedia.application.Entity.User;
import com.socialmedia.application.Repo.PostRepository;
import com.socialmedia.application.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SocialMediaService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    public Post createPost(Long userId, String content) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");

        Post post = new Post();
        post.setUser(userOpt.get());
        post.setContent(content);

        return postRepository.save(post);
    }


    public void deletePost(Long postId, Long userId) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty() || !postOpt.get().getUser().getId().equals(userId)) {
            throw new RuntimeException("Post not found or unauthorized");
        }
        postRepository.delete(postOpt.get());
    }


    public void followUser(Long followerId, Long followeeId) {
        if (followerId.equals(followeeId)) throw new RuntimeException("Cannot follow yourself");

        User follower = userRepository.findById(followerId).orElseThrow(() -> new RuntimeException("Follower not found"));
        User followee = userRepository.findById(followeeId).orElseThrow(() -> new RuntimeException("Followee not found"));

        followee.getFollowers().add(follower);
        userRepository.save(followee);
    }


    public void unfollowUser(Long followerId, Long followeeId) {
        User followee = userRepository.findById(followeeId).orElseThrow(() -> new RuntimeException("User not found"));
        User follower = userRepository.findById(followerId).orElseThrow(() -> new RuntimeException("User not found"));

        followee.getFollowers().remove(follower);
        userRepository.save(followee);
    }

    public Page<Post> getFeed(Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Set<Long> followeeIds = user.getFollowers().stream().map(User::getId).collect(Collectors.toSet());
        followeeIds.add(userId);

        return postRepository.findByUserIdInOrderByCreatedAtDesc(followeeIds.stream().toList(), PageRequest.of(page, size));
    }
}
