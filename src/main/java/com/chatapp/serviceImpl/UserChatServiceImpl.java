package com.chatapp.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatapp.dao.UserChatRepository;
import com.chatapp.dao.UserFriendsMappingRepository;
import com.chatapp.dao.UserRepository;
import com.chatapp.dto.UserChatDto;
import com.chatapp.entity.User;
import com.chatapp.entity.UserChat;
import com.chatapp.entity.UserFriendsMapping;
import com.chatapp.request.UserChatRequest;
import com.chatapp.response.UserChatResponse;
import com.chatapp.service.UserChatService;
import com.chatapp.util.Constants;

@Service
public class UserChatServiceImpl implements UserChatService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserChatRepository userChatRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserFriendsMappingRepository userFriendsMappingRepo;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public ResponseEntity<?> sendMessageService(String authorizationToken, UserChatRequest userChatRequest) {
		
		UserChatResponse userChatResponse = new UserChatResponse();
		
		try {
			
			String userId = tokenService.getUserIdFromToken(authorizationToken);
			
			DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			User userSenderDetails = new User();
			User userRecieverDetails = new User();
			UserChat userChat = new UserChat();
			
			userSenderDetails.setId(Long.valueOf(userId));
			userRecieverDetails.setId(userChatRequest.getRecieverUserId());
			
			Optional<UserFriendsMapping> userFriendMapping =
					userFriendsMappingRepo.findByUserMainAndUserFriend(userSenderDetails, userRecieverDetails);
			
			userChat.setUserFriendMapping(userFriendMapping.get());
			userChat.setMessage(userChatRequest.getMessage());
			userChat.setCreatedDate(dateTimeFormat.format(date));
			
			userChatRepo.save(userChat);
			
			userChatResponse.setStatus(Constants.API_SUCCESS_STATUS);
			userChatResponse.setResponseCode(HttpServletResponse.SC_OK);
			userChatResponse.setMessage("User sent message successfully");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			log.error(e.getMessage());
			
			userChatResponse.setStatus(Constants.API_FAIL_STATUS);
			userChatResponse.setMessage("Please try after some time");
			userChatResponse.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(userChatResponse);
		
	}

	@Override
	public ResponseEntity<?> fetchMessagesService(String authorizationToken, UserChatRequest userChatRequest) {
		
		UserChatResponse userChatResponse = new UserChatResponse();
		
		try {
			
			DateFormat originalDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat convertedDateTimeFormat = new SimpleDateFormat("HH:mm");
			
			String userId = tokenService.getUserIdFromToken(authorizationToken);
			
			ArrayList<UserChatDto> userChatDtoList = new ArrayList<>();
			UserChatDto userChatDto;
			
			User senderUser = userRepo.getOne(Long.valueOf(userId));
			User recieverUser = userRepo.getOne(userChatRequest.getRecieverUserId());
			
			List<UserChat> userChatList = userChatRepo.findMessagesBySenderAndMessanger(Long.valueOf(userId), userChatRequest.getRecieverUserId());
			
			for(UserChat userChat : userChatList) {
				
				userChatDto = new UserChatDto();
				
				if(senderUser.getId().equals(userChat.getUserFriendMapping().getUserMain().getId())) {
					userChatDto.setName(userChat.getUserFriendMapping().getUserMain().getName());
					userChatDto.setSenderChat(true);
				} else {
					userChatDto.setName(userChat.getUserFriendMapping().getUserFriend().getName());
					userChatDto.setSenderChat(false);
				}
				userChatDto.setMessage(userChat.getMessage());
				userChatDto.setCreatedDate(convertedDateTimeFormat.format(originalDateTimeFormat.parse(userChat.getCreatedDate())));
				
				userChatDtoList.add(userChatDto);
				
			}
			
			userChatResponse.setSenderName(senderUser.getUsername());
			userChatResponse.setSenderId(senderUser.getId());
			userChatResponse.setRecieverName(recieverUser.getUsername());
			userChatResponse.setRecieverId(recieverUser.getId());
			userChatResponse.setUserChat(userChatDtoList);
			
			userChatResponse.setStatus(Constants.API_SUCCESS_STATUS);
			userChatResponse.setResponseCode(HttpServletResponse.SC_OK);
			userChatResponse.setMessage("Messages fetched successfully");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			log.error(e.getMessage());
			
			userChatResponse.setStatus(Constants.API_FAIL_STATUS);
			userChatResponse.setMessage("Please try after some time");
			userChatResponse.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(userChatResponse);
		
	}
	
}
