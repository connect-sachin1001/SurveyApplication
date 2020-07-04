package com.survey.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.entities.User;
import com.survey.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	private final static String STATUS = "status";
	@GetMapping
	public ResponseEntity<Object> fetchAll() {

		Map<String, Object> result = new HashMap<>();
		result = userService.fetchAllUsers();

		switch (result.get(STATUS).toString()) {
		case "200":
			return ResponseEntity.status(HttpStatus.CREATED).body(result.get("data"));
		default:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> fetchById(@PathVariable int id) {

		Map<String, Object> result = new HashMap<>();
		result = userService.fetchUserById(id);
		switch (result.get(STATUS).toString()) {
		case "200":
			return ResponseEntity.status(HttpStatus.OK).body(result.get("data"));
		default:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody User user) {

		Map<String, Object> result = new HashMap<>();
		result = userService.createUser(user);

		switch (result.get(STATUS).toString()) {
		case "201":
			return ResponseEntity.status(HttpStatus.CREATED).body((User) result.get("data"));
		case "400":
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		default:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}

}
