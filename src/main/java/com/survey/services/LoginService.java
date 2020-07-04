package com.survey.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.entities.User;
import com.survey.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	private final static String STATUS = "status";
	private final static String MESSAGE = "message";
	
	public Map<String, Object> validateUser(User user) {
		
		Map<String, Object> result = new HashMap<>();
		String email = user.getEmail();
		String password = user.getPassword();
		User authUser = loginRepository.getUser(email, password);
		if(authUser == null) {
			
			result.put(STATUS, 400);
			result.put(MESSAGE, "User with the given id and password not found");
			return result;
		}
		
		result.put(STATUS, 200);
		result.put("data", authUser);
		return result;
	}
	
	
}
