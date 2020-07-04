package com.survey.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.survey.entities.User;

public interface LoginRepository extends CrudRepository<User, Integer> {

	@Query("from User u where u.email = :email and password = :password")
	public User getUser(String email, String password);

}
