package com.tnicacio.shareable.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.shareable.entities.Knowledge;

public interface  KnowledgeRepository extends JpaRepository<Knowledge, Long> {
	
}
