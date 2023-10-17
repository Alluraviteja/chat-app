package com.chatapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_chat")
public class UserChat {

	@Id
	@Column(name="chat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer chatId;
	
	@ManyToOne
	@JoinColumn(name = "user_friends_id")
	private UserFriendsMapping userFriendMapping;
	
	@Column(name="message")
	private String message;
	
	@Column(name="created_date")
	private String createdDate;

	@Column(name="updated_date")
	private String updatedDate;

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public UserFriendsMapping getUserFriendMapping() {
		return userFriendMapping;
	}

	public void setUserFriendMapping(UserFriendsMapping userFriendMapping) {
		this.userFriendMapping = userFriendMapping;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
