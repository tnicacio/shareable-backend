package com.tnicacio.shareable.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.shareable.entities.User;

public interface  UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
}
