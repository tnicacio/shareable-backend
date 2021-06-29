package com.tnicacio.shareable.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.shareable.entities.Role;

public interface  RoleRepository extends JpaRepository<Role, Long> {
	
}
