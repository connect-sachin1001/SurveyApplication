package com.survey.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.entities.Role;
import com.survey.entities.User;
import com.survey.repository.RoleRepository;
import com.survey.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	private final static String STATUS = "status";
	private final static String MESSAGE = "message";

	public Map<String, Object> fetchAllUsers() {

		Map<String, Object> result = new HashMap<>();
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(u -> userList.add(u));

		result.put("data", userList);
		result.put(STATUS, 200);
		return result;
	}

	public Map<String, Object> fetchUserById(int id) {

		Map<String, Object> result = new HashMap<>();
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isPresent()) {
			User savedUser = userOpt.get();
			result.put("data", savedUser);
			result.put(STATUS, 200);
		}
		return result;
	}

	public Map<String, Object> createUser(User user) {
		Map<String, Object> result = new HashMap<>();

		if (user == null) {
			result.put(STATUS, 400);
			result.put(MESSAGE, "BAD REQUEST");
			return result;
		}

		User users = setUserObject(user);
		userRepository.save(users);

		Optional<User> userOpt = userRepository.findById(users.getId());
		if (userOpt.isPresent()) {
			User savedUser = userOpt.get();
			result.put("data", savedUser);
			result.put(STATUS, 201);
		}
		return result;
	}

	private User setUserObject(User user) {

		User users = new User();
		Optional<Role> optionalRoll = roleRepository.findById(user.getRoleId());
		if (optionalRoll.isPresent()) {
			users.setRole(optionalRoll.get());
		}
		users.setFirstName(user.getFirstName());
		users.setLastName(user.getLastName());
		users.setEmail(user.getEmail());
		users.setPassword(user.getPassword());
		users.setCreatedBy(user.getFirstName());
		users.setCreatedOn(new Date());
		users.setModifiedBy(user.getModifiedBy());
		users.setModifiedOn(new Date());

		return users;
	}

}
