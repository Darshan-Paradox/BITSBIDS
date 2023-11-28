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
    public ResponseEntity<?> getPostById() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }



    @CrossOrigin
    @GetMapping("/post/page/{page}")
    public ResponseEntity<?> getPostByPage(@PathVariable("page") Long page) {

        //PAGINATION LOGIC
        Long pageSize = 10l;
        Long size = postRepository.count();

        Long begin, end;

        begin = pageSize*(page - 1) + 1;
        end = pageSize*page;

        if (begin < 1) begin = 1l;
        if (end > size) end = size;

        begin = size - begin + 1;
        end = size - end + 1;

        Long tmp = begin;
        begin = end;
        end = tmp;

        List<Post> response = postRepository.getPostByIdLimits(begin, end);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postRepository.findById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/post", consumes = {"*/*"})
    public ResponseEntity<?> getPostByPage(@RequestBody Post post) {
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

}
