package com.tnicacio.shareable.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.shareable.entities.Session;

public interface  SessionRepository extends JpaRepository<Session, Long> {
	
}
