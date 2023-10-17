package com.chatapp.service;

import org.springframework.http.ResponseEntity;

import com.chatapp.request.UserChatRequest;

public interface UserChatService {

	ResponseEntity<?> sendMessageService(String authorizationToken, UserChatRequest userChatRequest);

	ResponseEntity<?> fetchMessagesService(String authorizationToken, UserChatRequest userChatRequest);

}
