package com.tnicacio.shareable.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tnicacio.shareable.dto.KnowledgeDTO;
import com.tnicacio.shareable.entities.Knowledge;
import com.tnicacio.shareable.repositories.KnowledgeRepository;
import com.tnicacio.shareable.services.exceptions.DatabaseException;
import com.tnicacio.shareable.services.exceptions.ResourceNotFoundException;

@Service
public class KnowledgeService {

	private KnowledgeRepository repository;
	
	@Autowired
	public KnowledgeService(KnowledgeRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public Page<KnowledgeDTO> findAll(Pageable pageable) {
		Page<Knowledge> list = repository.findAll(pageable);
		return list.map(knowledge -> new KnowledgeDTO(knowledge));
	}
	
	@Transactional(readOnly = true)
	public KnowledgeDTO findById(Long id) {
		Optional<Knowledge> optionalEntity = repository.findById(id);
		Knowledge entity = optionalEntity.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new KnowledgeDTO(entity);
	}

	@Transactional
	public KnowledgeDTO insert(KnowledgeDTO dto) {
		Knowledge entity = new Knowledge();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new KnowledgeDTO(entity);
	}

	@Transactional
	public KnowledgeDTO update(Long id, KnowledgeDTO dto) {
		try {
			Knowledge entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new KnowledgeDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);					
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}		
	}
	
	private void copyDtoToEntity(KnowledgeDTO dto, Knowledge entity) {
		entity.setName(dto.getName());
	}
}
