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

import com.tnicacio.shareable.dto.SessionDTO;
import com.tnicacio.shareable.entities.Session;
import com.tnicacio.shareable.entities.enums.SharedSessionStatus;
import com.tnicacio.shareable.repositories.SessionRepository;
import com.tnicacio.shareable.services.exceptions.DatabaseException;
import com.tnicacio.shareable.services.exceptions.ResourceNotFoundException;

@Service
public class SessionService {

	private SessionRepository repository;
	
	@Autowired
	public SessionService(SessionRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public Page<SessionDTO> findAll(Pageable pageable) {
		Page<Session> list = repository.findAll(pageable);
		return list.map(session -> new SessionDTO(session));
	}
	
	@Transactional(readOnly = true)
	public SessionDTO findById(Long id) {
		Optional<Session> optionalEntity = repository.findById(id);
		Session entity = optionalEntity.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new SessionDTO(entity);
	}

	@Transactional
	public SessionDTO insert(SessionDTO dto) {
		Session entity = new Session();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new SessionDTO(entity);
	}

	@Transactional
	public SessionDTO update(Long id, SessionDTO dto) {
		try {
			Session entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new SessionDTO(entity);
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
	
	private void copyDtoToEntity(SessionDTO dto, Session entity) {
		entity.setStatus(SharedSessionStatus.valueOf( dto.getStatus()));
	}
}
