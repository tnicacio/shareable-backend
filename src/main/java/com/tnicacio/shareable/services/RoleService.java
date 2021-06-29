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

import com.tnicacio.shareable.dto.RoleDTO;
import com.tnicacio.shareable.entities.Role;
import com.tnicacio.shareable.repositories.RoleRepository;
import com.tnicacio.shareable.services.exceptions.DatabaseException;
import com.tnicacio.shareable.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {

	private RoleRepository repository;
	
	@Autowired
	public RoleService(RoleRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
	public Page<RoleDTO> findAll(Pageable pageable) {
		Page<Role> list = repository.findAll(pageable);
		return list.map(role -> new RoleDTO(role));
	}
	
	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {
		Optional<Role> optionalEntity = repository.findById(id);
		Role entity = optionalEntity.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new RoleDTO(entity);
	}

	@Transactional
	public RoleDTO insert(RoleDTO dto) {
		Role entity = new Role();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new RoleDTO(entity);
	}

	@Transactional
	public RoleDTO update(Long id, RoleDTO dto) {
		try {
			Role entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new RoleDTO(entity);
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
	
	private void copyDtoToEntity(RoleDTO dto, Role entity) {
		entity.setAuthority(dto.getAuthority());
	}
}
