package com.chatapp.request;

public class UserSignupRequest {
	
	private String userName;
	private String name;
	private String email;
	private String mobile;
	private String password;
	private String otp;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public UserSignupRequest(String userName, String name, String email, String mobile, String password, String otp) {
		super();
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.otp = otp;
	}
	public UserSignupRequest() {
		super();
	}
	
}
