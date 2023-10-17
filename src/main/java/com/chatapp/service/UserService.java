package com.chatapp.service;

import org.springframework.http.ResponseEntity;

import com.chatapp.request.AddFriendRequest;
import com.chatapp.request.AuthRequest;
import com.chatapp.request.UserSignupRequest;

public interface UserService {

	ResponseEntity<?> loginService(AuthRequest authRequest);
	
	public ResponseEntity<?> userSignupService(UserSignupRequest userSignupRequest);
	
	public ResponseEntity<?> setPasswordService(UserSignupRequest userSignupRequest);

	ResponseEntity<?> searchByUsernameService(UserSignupRequest authRequest);

	ResponseEntity<?> addFriendsUsername(String authorizationToken, AddFriendRequest addFriendRequest);

	ResponseEntity<?> fetchFriendsByUsernameService(String authorizationToken);
	
}