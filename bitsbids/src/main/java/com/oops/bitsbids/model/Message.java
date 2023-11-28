package com.oops.bitsbids.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Message {

	@Id
	private Long id;

	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User owner;

	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User bidder;

	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Post post;

	private boolean direction;
	private String text;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

}
