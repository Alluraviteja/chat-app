package com.chatapp.dto;

public class UserChatDto {
    
	private String senderName;
	
	private String recieverName;
	
	private String name;
	
	private String message;
	
	private String createdDate;
	
	private boolean isSenderChat;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isSenderChat() {
		return isSenderChat;
	}

	public void setSenderChat(boolean isSenderChat) {
		this.isSenderChat = isSenderChat;
	}
	
}
