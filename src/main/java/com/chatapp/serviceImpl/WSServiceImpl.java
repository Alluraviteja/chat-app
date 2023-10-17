package com.chatapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.chatapp.response.MessageResponse;
import com.chatapp.service.WSService;

@Service
public class WSServiceImpl implements WSService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationServiceImpl notificationService;

    @Autowired
    public WSServiceImpl(SimpMessagingTemplate messagingTemplate, NotificationServiceImpl notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyAllUsers(final String message) {
        MessageResponse response = new MessageResponse(message);
        notificationService.sendGlobalNotification();

        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message) {
        MessageResponse response = new MessageResponse(message);

        notificationService.sendPrivateNotification(id);
        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
    }
    
}
