package com.oops.bitsbids.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User owner;

	@ManyToOne
	private User bidder;

	@ManyToOne(cascade=CascadeType.REMOVE)
	private Post post;

	private boolean direction;
	private String text;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public Message() {}

	public Message(User owner, User bidder, Post post, boolean direction, String text, Date created) {
		this.owner = owner;
		this.bidder = bidder;
		this.post = post;
		this.direction = direction;
		this.text = text;
		this.created = created;
	}

	public Long getId() {
		return this.id;
	}
	public User getOwner() {
		Date currentTime = new Date();
		if (this.post.getDeadline().before(currentTime)) {
			return this.owner;
		}
		return null;
	}
	public User getBidder() {
		Date currentTime = new Date();
		if (this.post.getDeadline().before(currentTime)) {
			return this.bidder;
		}
		return null;
	}

	@JsonIgnore
	public User getOwnerFromServer() {
		return this.owner;
	}
	@JsonIgnore
	public User getBidderFromServer() {
		return this.bidder;
	}

	public Post getPost() {
		return this.post;
	}
	public boolean getDirection() {
		return this.direction;
	}
	public String getText() {
		return this.text;
	}
	public Date getCreated() {
		return this.created;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	public void setBidder(User bidder) {
		this.bidder = bidder;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
