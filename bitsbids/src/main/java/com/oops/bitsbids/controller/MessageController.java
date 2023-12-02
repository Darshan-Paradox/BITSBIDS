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
import com.oops.bitsbids.model.Message;
import com.oops.bitsbids.repository.MessageRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.lang.*;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private MessageRepository messageRepository;

	public MessageController() {}

	@CrossOrigin
	@GetMapping("/chat/{postId}")
	public ResponseEntity<?> getChatByPost(@PathVariable("postId") Long postId) {
		List<Message> response = messageRepository.findByPost(postId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/chat")
	public ResponseEntity<?> getChatByUser(@AuthenticationPrincipal OidcUser user) {

		User currentUser = userRepository.findByEmail(user.getEmail());
		List<Message> allMessage = messageRepository.findByUser(currentUser.getId());

		Map<Tuple, List<Message>> groupedMessage = allMessage.stream().collect(Collectors.groupingBy( message -> new Tuple(message.getPost(), message.getBidderFromServer()) ));

		List<List<Message>> response = new ArrayList<List<Message>>(groupedMessage.values());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/chat/{id}")
	public ResponseEntity<?> createMessage(@AuthenticationPrincipal OidcUser user, @PathVariable("id") Long id, @RequestBody Message message) {
		Message lastSent = messageRepository.findById(id).get();

		message.setOwner(lastSent.getOwnerFromServer());
		message.setBidder(lastSent.getBidderFromServer());

		if (lastSent.getBidderFromServer() == userRepository.findByEmail(user.getEmail())) {
			message.setDirection(false);
		} else {
			message.setDirection(true);
		}

		return new ResponseEntity<>(messageRepository.save(message), HttpStatus.CREATED);
	}

	@PostMapping("/chat/new/{id}")
	public ResponseEntity<?> createNewMessage(@AuthenticationPrincipal OidcUser user, @PathVariable("id") Long id, @RequestBody Message message) {
		Post post = postRepository.findById(id).get();
		message.setBidder(userRepository.findByEmail(user.getEmail()));
		message.setOwner(post.getUserFromServer());
		message.setDirection(false);

		return new ResponseEntity<>(messageRepository.save(message), HttpStatus.CREATED);
	}

}

record Tuple (Post post, User bidder) {}
