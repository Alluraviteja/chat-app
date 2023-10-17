package com.chatapp.response;

public class LoginResponse extends RestResponse{
	
	private String token;
	private Boolean isNewUser;
	private String name;
	private String mobile;
	private String roleName;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsNewUser() {
		return isNewUser;
	}
	public void setIsNewUser(Boolean isNewUser) {
		this.isNewUser = isNewUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
