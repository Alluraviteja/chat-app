package com.chatapp.response;

import java.util.List;

public class UserFriendsMappingDetailsListResponse extends RestResponse {

	private List<UserDetailsResponse> userFriendsList;

	public List<UserDetailsResponse> getUserFriendsList() {
		return userFriendsList;
	}

	public void setUserFriendsList(List<UserDetailsResponse> userFriendsList) {
		this.userFriendsList = userFriendsList;
	}

	public UserFriendsMappingDetailsListResponse(String status, String message, Integer responseCode,
			List<UserDetailsResponse> userFriendsList) {
		super(status, message, responseCode);
		this.userFriendsList = userFriendsList;
	}

	public UserFriendsMappingDetailsListResponse() {
	}
	
}
