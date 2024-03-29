package com.oops.bitsbids.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
public class Bid {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade=CascadeType.REMOVE)
	private Post post;

	@ManyToOne(cascade=CascadeType.REMOVE)
	private User user;

	private Long amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private boolean transacted;

	public Bid() {}

	public Bid(Post post, User user, Long amount) {
		this.post = post;
		this.user = user;
		this.amount = amount;
		this.created = new Date();
		this.transacted = false;
	}

	public Long getId() {
		return this.id;
	}
	public User getUser() {
		Date currentTime = new Date();
		if (this.post.getDeadline().before(currentTime)) {
			return this.user;
		}
		return null;
	}

	@JsonIgnore
	public User getUserFromServer() {
		return this.user;
	}
	@JsonIgnore
	public boolean getTransaction() {
		return this.transacted;
	}

	public Post getPost() {
		return this.post;
	}
	public Long getAmount() {
		return this.amount;
	}
	public Date getCreated() {
		return this.created;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setTransaction(boolean transacted) {
		this.transacted = transacted;
	}

}
