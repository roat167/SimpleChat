package com.orainteractive.simplechat.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.orainteractive.simplechat.constant.SimpleChatConstant;

@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Chat chat;
	@NotBlank
	private String message;
	@NotBlank
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = SimpleChatConstant.DATE_PATTERN)
	private Date postedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
