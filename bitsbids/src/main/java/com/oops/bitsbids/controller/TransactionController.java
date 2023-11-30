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
public class TransactionController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BidRepository bidRepository;

	@CrossOrigin
	@GetMapping("/payment/{postId}")
	public ResponseEntity<?> completePayment(@PathVariable("postId") Long postId) {
		List<Bid> bids = bidRepository.findByPost(postId);
		Bid winner = bids.get(0);

		if (!winner.getTransaction()) {
			Post post = postRepository.findById(postId).get();
			User owner = post.getUserFromServer();

			owner.setCoins(owner.getCoins() + winner.getAmount());

			User bidder = winner.getUserFromServer();
			bidder.setCoins(bidder.getCoins() - winner.getAmount());

			winner.setTransaction(true);
		}

		return new ResponseEntity<>("Payment done!", HttpStatus.OK);
	}
}
