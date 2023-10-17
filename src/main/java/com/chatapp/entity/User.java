package com.chatapp.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="user_ms")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "username")
	private String username;
	
	private String password;
	
	private String mobile;

	private String email;
	
	@Column(name="last_login_time")
	private Time loginTime;

	@Column(name="last_logout_time")
	private Time logoutTime;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="updated_date")
	private String updatedDate;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private List<UserRoleMapping> userRoleMapping;
	
	@OneToMany(mappedBy = "userMain", cascade = {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private List<UserFriendsMapping> userMainFriendsMapping;
	
	@OneToMany(mappedBy = "userFriend", cascade = {CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private List<UserFriendsMapping> userFriendsMapping;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Time getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Time loginTime) {
		this.loginTime = loginTime;
	}

	public Time getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Time logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<UserRoleMapping> getUserRoleMapping() {
		return userRoleMapping;
	}

	public void setUserRoleMapping(List<UserRoleMapping> userRoleMapping) {
		this.userRoleMapping = userRoleMapping;
	}

	public List<UserFriendsMapping> getUserMainFriendsMapping() {
		return userMainFriendsMapping;
	}

	public void setUserMainFriendsMapping(List<UserFriendsMapping> userMainFriendsMapping) {
		this.userMainFriendsMapping = userMainFriendsMapping;
	}

	public List<UserFriendsMapping> getUserFriendsMapping() {
		return userFriendsMapping;
	}

	public void setUserFriendsMapping(List<UserFriendsMapping> userFriendsMapping) {
		this.userFriendsMapping = userFriendsMapping;
	}

	public User() {
	}
	
}