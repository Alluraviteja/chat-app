package com.chatapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="user_friends")
public class UserFriendsMapping {
	
	@Id
	@Column(name="user_freinds_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userFreindsId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userMain;
	
	@ManyToOne
	@JoinColumn(name = "friend_user_id")
	private User userFriend;
	
	@OneToMany(mappedBy = "userFriendMapping", cascade = {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private List<UserChat> userChat;

	public Integer getUserFreindsId() {
		return userFreindsId;
	}

	public void setUserFreindsId(Integer userFreindsId) {
		this.userFreindsId = userFreindsId;
	}

	public User getUserMain() {
		return userMain;
	}

	public void setUserMain(User userMain) {
		this.userMain = userMain;
	}

	public User getUserFriend() {
		return userFriend;
	}

	public void setUserFriend(User userFriend) {
		this.userFriend = userFriend;
	}

	public List<UserChat> getUserChat() {
		return userChat;
	}

	public void setUserChat(List<UserChat> userChat) {
		this.userChat = userChat;
	}

	public UserFriendsMapping(Integer userFreindsId, User userMain, User userFriend) {
		super();
		this.userFreindsId = userFreindsId;
		this.userMain = userMain;
		this.userFriend = userFriend;
	}

	public UserFriendsMapping() {
		super();
	}

}
