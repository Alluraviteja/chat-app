package com.chatapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chatapp.request.AddFriendRequest;
import com.chatapp.request.AuthRequest;
import com.chatapp.request.UserSignupRequest;
import com.chatapp.service.UserService;
import com.chatapp.serviceImpl.WSServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WSServiceImpl service;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		log.info("UserController || login");
		return userService.loginService(authRequest);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody UserSignupRequest userSignupRequest) {
		log.info("UserController || userSignup");
		return userService.userSignupService(userSignupRequest);
	}
	
	@PostMapping("/setPassword")
	public ResponseEntity<?> setPassword(@RequestBody UserSignupRequest authRequest) {
		log.info("UserController || setPassword");
		return userService.setPasswordService(authRequest);
	}
	
	@PostMapping("/searchByUsername")
	public ResponseEntity<?> searchByUsername(@RequestBody UserSignupRequest authRequest) {
		log.info("UserController || searchByUsername");
		return userService.searchByUsernameService(authRequest);
	}
	
	@GetMapping("/fetchFriendsByUsername")
	public ResponseEntity<?> fetchFriendsByUsername(@RequestHeader("Authorization") String authorizationToken) {
		log.info("UserController || fetchFriendsByUsername");
		return userService.fetchFriendsByUsernameService(authorizationToken);
	}
	
	@PostMapping("/addFriendsUsername")
	public ResponseEntity<?> addFriendsUsername(@RequestHeader("Authorization") String authorizationToken, 
									@RequestBody AddFriendRequest addFriendRequest) {
		log.info("UserController || addFriendsUsername");
		return userService.addFriendsUsername(authorizationToken, addFriendRequest);
	}
	
}
