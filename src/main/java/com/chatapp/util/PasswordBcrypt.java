package com.chatapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordBcrypt {
	
	public static boolean comparePassword(String password,String requestPassword){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		boolean pass; 
		try{
			pass = passwordEncoder.matches(requestPassword,password);
		}catch(Exception e){
			pass= false;
		}
		return pass;
	}
	
	public static String encryptPassword(String requestPassword){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String pass; 
		try{
			pass = passwordEncoder.encode(requestPassword);
		}catch(Exception e){
			pass= null;
		}
		return pass;
	}
	
	public static void main(String args[]) {
		System.out.println(PasswordBcrypt.encryptPassword("Ravi@1234"));
	}

}
