package com.chatapp.service;

public interface WSService {

	public void notifyAllUsers(final String message);

    public void notifyUser(final String id, final String message);
	
}
