package com.oops.bitsbids.controller;

import com.oops.bitsbids.model.User;
import com.oops.bitsbids.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.oops.bitsbids.model.User;
import com.oops.bitsbids.repository.UserRepository;
import com.oops.bitsbids.model.Post;
import com.oops.bitsbids.repository.PostRepository;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

    public PostController() {}

    @CrossOrigin
    @GetMapping("/post")
    public ResponseEntity<?> getPost(@AuthenticationPrincipal OidcUser user) {
        User currentUser = userRepository.findByEmail(user.getEmail());
        return new ResponseEntity<>(postRepository.findByUser(currentUser), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/post/50")
    public ResponseEntity<?> getPostByPage(@AuthenticationPrincipal OidcUser user) {

        //PAGINATION LOGIC
        Long pageSize = 50l;

        Long offset = 0l;

        User currentUser = userRepository.findByEmail(user.getEmail());

        List<Post> response = postRepository.findPostByIdLimits(offset, pageSize, currentUser.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/post/page/{page}")
    public ResponseEntity<?> getPostByPage(@AuthenticationPrincipal OidcUser user, @PathVariable("page") Long page) {

        //PAGINATION LOGIC
        Long pageSize = 3l;

        Long offset = (page - 1)*pageSize;

        User currentUser = userRepository.findByEmail(user.getEmail());

        List<Post> response = postRepository.findPostByIdLimits(offset, pageSize, currentUser.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/post/{id}")
    public ResponseEntity<?> freezePostById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postRepository.findById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/post/freeze/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        Post post = postRepository.findById(id).get();
        post.setDeadline(new Date());

        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/post", consumes = {"*/*"})
    public ResponseEntity<?> createPost(@RequestBody Post post) {

        if (post.getUserFromServer() == null)
            return new ResponseEntity<>(null, HttpStatus.OK);

        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

}
