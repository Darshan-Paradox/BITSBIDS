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
import com.oops.bitsbids.model.Bid;
import com.oops.bitsbids.repository.BidRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

@RestController
@RequestMapping("/api")
public class BidController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BidRepository bidRepository;

	public BidController() {}

	@CrossOrigin
	@GetMapping("/bid/{postId}")
	public ResponseEntity<?> getBidByPost(@PathVariable("postId") Long postId) {
		List<Bid> response = bidRepository.findByPost(postId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@PostMapping("/bid")
	public ResponseEntity<?> createBid(@RequestBody Bid bid) {
		return new ResponseEntity<>(bidRepository.save(bid), HttpStatus.CREATED);
	}

}
