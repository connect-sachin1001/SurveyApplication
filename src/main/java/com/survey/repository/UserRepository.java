package com.survey.repository;

import org.springframework.data.repository.CrudRepository;
import com.survey.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	
}
