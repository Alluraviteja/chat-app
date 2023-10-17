package com.chatapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping(value = {"/login", "/"})
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = {"/signUp"})
	public String createAccount() {
		return "createAccount";
	}
	
	@RequestMapping(value = {"/forgotPassword"})
	public String forgotPassword() {
		return "forgotPassword";
	}
	
	@RequestMapping(value = {"/chat"})
	public String chat() {
		return "chat";
	}
	
	@RequestMapping(value = {"/chess"})
	public String chess() {
		return "chess";
	}
	
}
