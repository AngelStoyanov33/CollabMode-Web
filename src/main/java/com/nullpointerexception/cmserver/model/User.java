package com.nullpointerexception.cmserver.model;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="id")
	 private int id;
	 
	 @Column(name="fullName", nullable=false)
	 private String fullName;
	

	@Column(name="email", nullable=false)
	 private String email;
	 
	 @Column(name="password", nullable=false)
	 private String password;
	 
	 @Column(name="newsletterStatus", nullable=false)
	 private boolean newsletterStatus;
	 
	 //TODO: Column: is_owner_of_team bool, default 0
	 //TODO: Column: team_id int, primary key
	 
	 public User(String fullName, String email, String password, String newsletterStatus) {
		 this.fullName = fullName;
		 this.email = email;
		 this.password = password;
		 this.newsletterStatus = ((newsletterStatus.equals("true")) ? true : false);
	 }
	 public User() {}
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isNewsletterStatus() {
		return newsletterStatus;
	}

	public void setNewsletterStatus(boolean newsletterStatus) {
		this.newsletterStatus = newsletterStatus;
	}
	
}
