package com.chatapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chatapp.request.UserChatRequest;
import com.chatapp.service.UserChatService;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/chat")
public class UserChatController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserChatService userChatService;
	
	@PostMapping("/sendChatMessage")
	public ResponseEntity<?> sendMessage(@RequestHeader("Authorization") String authorizationToken, 
			@RequestBody UserChatRequest userChatRequest) {
		
		log.info("UserChatController || sendMessage");
		return userChatService.sendMessageService(authorizationToken, userChatRequest);
		
	}
	
	@PostMapping("/fetchChatMessages")
	public ResponseEntity<?> fetchMessages(@RequestHeader("Authorization") String authorizationToken, 
			@RequestBody UserChatRequest userChatRequest) {
		
		log.info("UserChatController || fetchMessages");
		return userChatService.fetchMessagesService(authorizationToken, userChatRequest);
		
	}
	
}
