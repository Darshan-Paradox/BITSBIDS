package com.oops.bitsbids.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Date;
import java.util.Calendar;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	//@JsonIgnore
	private User user;

	private String title;
	private String description;

	private Long basePrice;
	@Temporal(TemporalType.TIMESTAMP)
	private Date deadline;

	private String multimedia;

	public Post() {}

	public Post(User user, String title, String description, Date deadline) {
		this.user = user;
		this.title= title;
		this.description= description;
		this.deadline= deadline;

		this.basePrice = 0l;
		this.multimedia = "";
	}

	public Post(User user, String title, String description, Long basePrice, Date deadline) {
		this.user = user;
		this.title= title;
		this.description= description;
		this.basePrice = basePrice;
		this.deadline= deadline;

		this.multimedia = "";
	}

	public Long getId() {
		return this.id;
	}
	public User getUser() {
		Date currentTime = new Date();
		if (this.deadline.before(currentTime)) {
			return this.user;
		}
		return null;
	}

	@JsonIgnore
	public User getUserFromServer()
	{
		return this.user;
	}

	public String getTitle() {
		return this.title;
	}
	public String getDescription() {
		return this.description;
	}
	public Long getBasePrice() {
		return this.basePrice;
	}
	public Date getDeadline() {
		return this.deadline;
	}
	public String getMultimedia() {
		return this.multimedia;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setBasePrice(Long basePrice) {
		this.basePrice = basePrice;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public void setMultimedia(String multimedia) {
		this.multimedia = multimedia;
	}
}
