package com.serviceworker.repository;

import com.serviceworker.model.User;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUsername(String username);
	
}