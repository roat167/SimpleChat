package com.orainteractive.simplechat.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "users")
public class User {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Size(min = 4)
	@Column(unique = true, length = 50)
	private String username;
	@NotBlank
	@Column(length = 50)
	private String password;
	@NotBlank
	@Column(length = 50)
	private String firstName;
	@NotBlank
	@Column(length = 50)
	private String lastName;
	@NotBlank
	@Email
	@Column(length = 100)
	private String email;
	
	@OneToMany(mappedBy="owner")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	//@JsonDeserialize(using = ChatListDeserializer.class)
	private List<Chat> chats = new ArrayList<Chat>();

	public User() {
	}
	
	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String username, String password, String firstName, String lastName, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Chat> getChats() {
		return chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}
}
