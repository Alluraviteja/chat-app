package com.chatapp.controller;

import com.chatapp.dto.MessageDto;
import com.chatapp.response.MessageResponse;
import com.chatapp.serviceImpl.NotificationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {
    
	@Autowired
    private NotificationServiceImpl notificationService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public MessageResponse getMessage(final MessageDto message) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendGlobalNotification();
        return new MessageResponse(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public MessageResponse getPrivateMessage(final MessageDto message,
                                             final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendPrivateNotification(principal.getName());
        return new MessageResponse(HtmlUtils.htmlEscape(
                "Sending private message to user " + principal.getName() + ": "
                        + message.getMessageContent())
        );
    }
}
