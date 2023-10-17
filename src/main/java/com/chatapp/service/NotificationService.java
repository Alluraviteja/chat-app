package com.chatapp.service;

public interface NotificationService {

	public void sendGlobalNotification();

    public void sendPrivateNotification(final String userId);
	
}
