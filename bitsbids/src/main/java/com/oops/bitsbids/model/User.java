package com.oops.bitsbids.model;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String username;
	private String email;
	private String icon;

	private Long coins;

	public User() {}

	public User(String username, String email, String icon) {
		this.username = username;
		this.email = email;
		this.icon = icon;

		this.coins = 0l;
	}

	public Long getId() {
		return this.id;
	}
	public String getUsername() {
		return this.username;
	}
	public String getEmail() {
		return this.email;
	}
	public String getIcon() {
		return this.icon;
	}
	public Long getCoins() {
		return this.coins;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setCoins(Long coins) {
		this.coins = coins;
	}

}
