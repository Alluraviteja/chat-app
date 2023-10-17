package com.chatapp.request;

public class UserChatRequest {

	private Long senderUserId;
	
	private Long recieverUserId;
	
	private String message;

	public Long getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(Long senderUserId) {
		this.senderUserId = senderUserId;
	}

	public Long getRecieverUserId() {
		return recieverUserId;
	}

	public void setRecieverUserId(Long recieverUserId) {
		this.recieverUserId = recieverUserId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
