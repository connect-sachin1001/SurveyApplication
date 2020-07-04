package com.survey.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.entities.User;
import com.survey.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	private final static String STATUS = "status";
	private final static String MESSAGE = "message";

	@PostMapping(value = "/auth")
	public ResponseEntity<Object> login(@RequestBody User user) {

		Map<String, Object> result = new HashMap<>();
		result = loginService.validateUser(user);

		switch (result.get(STATUS).toString()) {
		case "200":
			return ResponseEntity.ok((User) result.get("data"));

		case "400":
			return ResponseEntity.badRequest().body(result.get(MESSAGE));
		default:
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}
	}
}
