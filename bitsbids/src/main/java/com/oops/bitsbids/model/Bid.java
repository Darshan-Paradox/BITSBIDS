package com.oops.bitsbids.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Bid {

	@Id
	private Long id;

	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Post post;

	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;

	private Long amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public Bid() {}

	public Bid(Post post, User user, Long amount) {
		this.post = post;
		this.user = user;
		this.amount = amount;
	}

}
