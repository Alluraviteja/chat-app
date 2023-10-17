package com.chatapp.request;

public class AddFriendRequest {

	private Long newUsernameId;
	private Long currentUsernameId;
	public Long getNewUsernameId() {
		return newUsernameId;
	}
	public void setNewUsernameId(Long newUsernameId) {
		this.newUsernameId = newUsernameId;
	}
	public Long getCurrentUsernameId() {
		return currentUsernameId;
	}
	public void setCurrentUsernameId(Long currentUsernameId) {
		this.currentUsernameId = currentUsernameId;
	}
	public AddFriendRequest(Long newUsernameId, Long currentUsernameId) {
		super();
		this.newUsernameId = newUsernameId;
		this.currentUsernameId = currentUsernameId;
	}
	public AddFriendRequest() {
		super();
	}
	
}
