package com.nullpointerexception.cmserver.model;

import javax.persistence.CascadeType; 
import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	 
//	 @OneToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
//	 @JoinColumn(name = "teamId", referencedColumnName = "id", nullable = true)
	 @Column(name="teamId", nullable=true)
	 private int teamID;
	 
	 @Column(name="isOwner", nullable=true)
	 private boolean isOwner = false; //default value
	 
	 
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
	
	public int getTeamID() {
		return teamID;
	}
	
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	
	public boolean isOwner() {
		return isOwner;
	}
	
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	
}
