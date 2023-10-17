package com.chatapp.response;

import java.util.List;

import com.chatapp.dto.UserChatDto;

public class UserChatResponse extends RestResponse {
	
	private String senderName;
	
	private Long senderId;
	
	private String recieverName;
	
	private Long recieverId;
	
	List<UserChatDto> userChat;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public Long getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Long recieverId) {
		this.recieverId = recieverId;
	}

	public List<UserChatDto> getUserChat() {
		return userChat;
	}

	public void setUserChat(List<UserChatDto> userChat) {
		this.userChat = userChat;
	}
	
}
