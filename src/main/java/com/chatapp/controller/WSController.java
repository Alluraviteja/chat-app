package com.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.chatapp.dto.MessageDto;
import com.chatapp.serviceImpl.WSServiceImpl;

@Controller
public class WSController {

    @Autowired
    private WSServiceImpl service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final MessageDto message) {
        service.notifyAllUsers(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@RequestHeader("Authorization") String authorizationToken,
    							   @PathVariable final String id,
                                   @RequestBody final MessageDto message) {
        service.notifyUser(id, message.getMessageContent());
    }
}
