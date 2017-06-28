package com.orainteractive.simplechat.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.orainteractive.simplechat.constant.SimpleChatConstant;
import com.orainteractive.simplechat.json.deserialize.DateDeserializer;
import com.orainteractive.simplechat.json.deserialize.UserDeserializer;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String message;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonDeserialize(using = UserDeserializer.class)
	@NotNull(message = "Owner might be invalid or null")
	private User owner;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = SimpleChatConstant.DATE_PATTERN)
	@JsonDeserialize(using = DateDeserializer.class)
	private Date postedDate;
	@Transient
	private List<User> users;
	@OneToMany(mappedBy = "chat")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private List<ChatMessage> messages;

	public Chat() {
	}

	public Chat(Long id) {
		this.id = id;
	}

	public Chat(Long id, User owner, String message, Date postedDate) {
		this.id = id;
		this.owner = owner;
		this.message = message;
		this.postedDate = postedDate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getPostedDate() {
		return this.postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ChatMessage> getMessages() {
		return this.messages;
	}

	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}

}
