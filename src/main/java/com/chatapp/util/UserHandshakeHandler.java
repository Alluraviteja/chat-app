package com.chatapp.util;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.sun.security.auth.UserPrincipal;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    
	private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        
    	HttpHeaders headers = request.getHeaders();
        String authorizationHeader = headers.getFirst("Authorization");
        
    	System.out.println(authorizationHeader);
    	
    	final String randomId = UUID.randomUUID().toString();
        LOG.info("User with ID '{}' opened the page", randomId);

        return new UserPrincipal(randomId);
    }
    
}
