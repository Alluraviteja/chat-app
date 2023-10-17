package com.chatapp.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatapp.dao.UserFriendsMappingRepository;
import com.chatapp.dao.UserRepository;
import com.chatapp.entity.Role;
import com.chatapp.entity.User;
import com.chatapp.entity.UserFriendsMapping;
import com.chatapp.entity.UserRoleMapping;
import com.chatapp.request.AddFriendRequest;
import com.chatapp.request.AuthRequest;
import com.chatapp.request.UserSignupRequest;
import com.chatapp.response.CommonResponse;
import com.chatapp.response.LoginResponse;
import com.chatapp.response.UserDetailsResponse;
import com.chatapp.response.UserFriendsMappingDetailsListResponse;
import com.chatapp.service.UserService;
import com.chatapp.util.Constants;
import com.chatapp.util.PasswordBcrypt;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserFriendsMappingRepository userFriendsMappingRepo;
	
	static Properties configProperty = new Properties();

	@Override
	public ResponseEntity<?> loginService(AuthRequest authRequest) {
		LoginResponse loginResponse = new LoginResponse();
		try {
			
			Optional<User> userDetails = userRepo.findByUsername(authRequest.getUsername());
			if(userDetails.isPresent()) {
				if(PasswordBcrypt.comparePassword(userDetails.get().getPassword(), authRequest.getPassword())) {
					
					loginResponse.setToken(tokenService.createToken(userDetails.get().getId(),userDetails.get().getUserRoleMapping().get(0).getRole().getId()));
					//loginResponse.setIsNewUser(userDetails.get().getIsNewUser());
					loginResponse.setName(userDetails.get().getUsername());
					loginResponse.setMobile(userDetails.get().getMobile());
					loginResponse.setRoleName(userDetails.get().getUserRoleMapping().get(0).getRole().getRoleName().name());
					loginResponse.setStatus(Constants.API_SUCCESS_STATUS);
					loginResponse.setResponseCode(HttpServletResponse.SC_OK);
					loginResponse.setMessage("Login successfully!");
					
				}else {
					loginResponse.setStatus(Constants.API_FAIL_STATUS);
					loginResponse.setMessage("Password is incorrect");
					loginResponse.setResponseCode(HttpServletResponse.SC_NOT_ACCEPTABLE);
				}
			}else {
				loginResponse.setStatus(Constants.API_FAIL_STATUS);
				loginResponse.setMessage("User doesn't exists");
				loginResponse.setResponseCode(HttpServletResponse.SC_NO_CONTENT);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			loginResponse.setStatus(Constants.API_FAIL_STATUS);
			loginResponse.setMessage("Please try after some time");
			loginResponse.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(loginResponse);
	}
	
	@Override
	public ResponseEntity<?> userSignupService(UserSignupRequest userSignupRequest) {
		
		CommonResponse commonResponse = new CommonResponse();
		
		try {
			
			log.info("Signup Request : " + userSignupRequest.toString());
			DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Optional<User> userDetails = userRepo.findByUsername(userSignupRequest.getUserName());
			
			if (userDetails.isPresent()) {
				
				commonResponse.setStatus(Constants.API_FAIL_STATUS);
				commonResponse.setMessage("User already exists");
				commonResponse.setResponseCode(HttpServletResponse.SC_CONFLICT);
				
			}else {
				
				User user = new User();
				
				user.setEmail(userSignupRequest.getEmail());
				user.setIsActive(true);
				user.setUsername(userSignupRequest.getUserName());
				
				//user.setIsNewUser(true);
				user.setUsername(userSignupRequest.getUserName());
				user.setMobile(userSignupRequest.getMobile());
				
				String pwd = PasswordBcrypt.encryptPassword(userSignupRequest.getPassword());
				user.setPassword(pwd);
				
				// user role mapping
				Role role = new Role();
				role.setId(Constants.ROLE_USER);
				
				List<UserRoleMapping> userRoleMappingList = new ArrayList<UserRoleMapping>();
				UserRoleMapping userRoleMapping = new UserRoleMapping();
				userRoleMapping.setUser(user);
				
				userRoleMapping.setRole(role);
				userRoleMappingList.add(userRoleMapping);
				user.setUserRoleMapping(userRoleMappingList);
				
				user.setCreatedDate(dateTimeFormat.format(date));
				userRepo.save(user);
				
				commonResponse.setStatus(Constants.API_SUCCESS_STATUS);
				commonResponse.setMessage("User signup created successfully!");
				commonResponse.setResponseCode(HttpServletResponse.SC_OK);
				
			}
		} catch (Exception e) {
			
			log.error("Signup Error :- "+e.getMessage());
			commonResponse.setStatus(Constants.API_FAIL_STATUS);
			commonResponse.setMessage("Please try after some time");
			commonResponse.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		}
		return ResponseEntity.ok(commonResponse);

	}

	@Override
	public ResponseEntity<?> setPasswordService(UserSignupRequest userSignupRequest) {
		LoginResponse loginResponse = new LoginResponse();
		try {
			
			Optional<User> userDetails = userRepo.findByEmail(userSignupRequest.getEmail());
			if(userDetails.isPresent()) {
				if(userSignupRequest.getOtp().equals("1234")) {
					
					String pwd = PasswordBcrypt.encryptPassword(userSignupRequest.getPassword());
					userDetails.get().setPassword(pwd);
					
					userRepo.save(userDetails.get());
					
					loginResponse.setStatus(Constants.API_SUCCESS_STATUS);
					loginResponse.setMessage("Reset password successfully");
					loginResponse.setResponseCode(HttpServletResponse.SC_OK);
					
				}else {
					loginResponse.setStatus(Constants.API_FAIL_STATUS);
					loginResponse.setMessage("Invalid OTP");
					loginResponse.setResponseCode(HttpServletResponse.SC_NOT_ACCEPTABLE);
				}
				
			}else {
				loginResponse.setStatus(Constants.API_FAIL_STATUS);
				loginResponse.setMessage("User not registered with the email");
				loginResponse.setResponseCode(HttpServletResponse.SC_NO_CONTENT);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			loginResponse.setStatus(Constants.API_FAIL_STATUS);
			loginResponse.setMessage("Please try after some time");
			loginResponse.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(loginResponse);
	}

	@Override
	public ResponseEntity<?> searchByUsernameService(UserSignupRequest authRequest) {

		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
		
		try {
			
			Optional<User> userDetails = userRepo.findByUsername(authRequest.getUserName());
			
			if (userDetails.isPresent()) {
				
				userDetailsResponse.setName(userDetails.get().getUsername());
				userDetailsResponse.setId(userDetails.get().getId());
				
				userDetailsResponse.setStatus(Constants.API_SUCCESS_STATUS);
				userDetailsResponse.setResponseCode(HttpServletResponse.SC_OK);
				userDetailsResponse.setMessage("User found");
				
			} else {
				
				userDetailsResponse.setStatus(Constants.API_FAIL_STATUS);
				userDetailsResponse.setMessage("User could not be found");
				userDetailsResponse.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			userDetailsResponse.setStatus(Constants.API_FAIL_STATUS);
			userDetailsResponse.setMessage("Please try after some time");
			userDetailsResponse.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(userDetailsResponse);
		
	}

	@Override
	public ResponseEntity<?> addFriendsUsername(String authorizationToken, AddFriendRequest addFriendRequest) {
		
		CommonResponse response = new CommonResponse();
		
		try {
			
			Optional<User> newFriendUserDetails = userRepo.findById(addFriendRequest.getNewUsernameId());
			
			if (newFriendUserDetails.isPresent()) {
				
				String userId = tokenService.getUserIdFromToken(authorizationToken);
				User userDetails = new User();
				userDetails.setId(Long.valueOf(userId));
				
				Optional<UserFriendsMapping> checkIfFriendExists = userFriendsMappingRepo.findByUserMainAndUserFriend(userDetails, newFriendUserDetails.get());
				
				if(checkIfFriendExists.isPresent()) {
					
					response.setStatus(Constants.API_FAIL_STATUS);
					response.setMessage("Friend already added");
					response.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					
				} else {
					
					UserFriendsMapping userFriends = new UserFriendsMapping();
					
					userFriends.setUserMain(userDetails);
					userFriends.setUserFriend(newFriendUserDetails.get());
					
					userFriendsMappingRepo.save(userFriends);
					
					userFriends = new UserFriendsMapping();
					
					userFriends.setUserMain(newFriendUserDetails.get());
					userFriends.setUserFriend(userDetails);
					
					userFriendsMappingRepo.save(userFriends);
					
					response.setStatus(Constants.API_SUCCESS_STATUS);
					response.setResponseCode(HttpServletResponse.SC_OK);
					response.setMessage("Successfully added as friend");
				
				}
				
			} else {
				
				response.setStatus(Constants.API_FAIL_STATUS);
				response.setMessage("Username could not be found");
				response.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				
			}

		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(Constants.API_FAIL_STATUS);
			response.setMessage("Please try after some time");
			response.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(response);
		
	}

	@Override
	public ResponseEntity<?> fetchFriendsByUsernameService(String authorizationToken) {
		
		UserFriendsMappingDetailsListResponse response = new UserFriendsMappingDetailsListResponse();
		
		try {
			
			String userId = tokenService.getUserIdFromToken(authorizationToken);
			User userDetails = new User();
			userDetails.setId(Long.valueOf(userId));
			
			List<UserFriendsMapping> userFriendsList = userFriendsMappingRepo.findByUserMain(userDetails);
			
			List<UserDetailsResponse> userFriendDetailsList = new ArrayList<>();
			
			UserDetailsResponse userFriendDetails;
			
			for(UserFriendsMapping userFriend : userFriendsList) {
				
				userFriendDetails = new UserDetailsResponse();
				
				userFriendDetails.setId(userFriend.getUserFriend().getId());
				userFriendDetails.setName(userFriend.getUserFriend().getUsername());
				
				userFriendDetailsList.add(userFriendDetails);
				
			}
			
			response.setUserFriendsList(userFriendDetailsList);
			response.setStatus(Constants.API_SUCCESS_STATUS);
			response.setResponseCode(HttpServletResponse.SC_OK);
			response.setMessage("User found");
			
		}catch(Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(Constants.API_FAIL_STATUS);
			response.setMessage("Please try after some time");
			response.setResponseCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(response);
		
	}
	
}
