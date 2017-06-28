package com.orainteractive.simplechat.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.orainteractive.simplechat.constant.SimpleChatConstant;
import com.orainteractive.simplechat.json.deserialize.ChatDeserializer;
import com.orainteractive.simplechat.json.deserialize.DateDeserializer;
import com.orainteractive.simplechat.json.deserialize.UserDeserializer;

@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonDeserialize(using = UserDeserializer.class)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "chat_id")
	@JsonDeserialize(using = ChatDeserializer.class)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	private Chat chat;
	@NotBlank
	private String message;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = SimpleChatConstant.DATE_PATTERN)
	@JsonDeserialize(using = DateDeserializer.class)
	private Date postedDate;

	public ChatMessage() {
	}

	public ChatMessage(Long id, User user, Chat chat, String message, Date postedDate) {
		this.id = id;
		this.user = user;
		this.chat = chat;
		this.message = message;
		this.postedDate = postedDate;
	}

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

	@Override
	public String toString() {
		return String.format("[id = %tl, user = %s , chat_id = %d , message = %s]", this.id,
				this.user == null ? "" : this.user.getUsername(), 
				this.chat == null ? "" : this.chat.getId(),
				this.message);
	}
}
