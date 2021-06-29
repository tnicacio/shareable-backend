package com.tnicacio.shareable.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tnicacio.shareable.dto.UserDTO;
import com.tnicacio.shareable.entities.Knowledge;
import com.tnicacio.shareable.entities.Role;
import com.tnicacio.shareable.entities.Session;
import com.tnicacio.shareable.entities.User;
import com.tnicacio.shareable.repositories.KnowledgeRepository;
import com.tnicacio.shareable.repositories.RoleRepository;
import com.tnicacio.shareable.repositories.SessionRepository;
import com.tnicacio.shareable.repositories.UserRepository;
import com.tnicacio.shareable.services.exceptions.DatabaseException;
import com.tnicacio.shareable.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private UserRepository repository;
	private RoleRepository roleRepository;
	private KnowledgeRepository knowledgeRepository;
	private SessionRepository sessionRepository;
	
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository repository, RoleRepository roleRepository,
			KnowledgeRepository knowledgeRepository, SessionRepository sessionRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.roleRepository = roleRepository;
		this.knowledgeRepository = knowledgeRepository;
		this.sessionRepository = sessionRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(user -> new UserDTO(user));
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> optionalEntity = repository.findById(id);
		User entity = optionalEntity.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			User entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);
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
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setDescription(dto.getDescription());
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setUpdatedAt(dto.getUpdatedAt());
		entity.setInactivatedAt(dto.getInactivatedAt());
		entity.setIsActive(dto.getIsActive());
		
		entity.getRoles().clear();
		entity.getKnowledges().clear();
		entity.getSessions().clear();

		dto.getRoles().forEach(roleDTO -> {
			Role role = roleRepository.getOne(roleDTO.getId());
			entity.getRoles().add(role);
		});
		dto.getKnowledges().forEach(knowledgeDTO -> {
			Knowledge knowledge = knowledgeRepository.getOne(knowledgeDTO.getId());
			entity.getKnowledges().add(knowledge);
		});
		dto.getSessions().forEach(sessionDTO -> {
			Session session = sessionRepository.getOne(sessionDTO.getId());
			entity.getSessions().add(session);
		});
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User found: " + username);
		return user;
	}
}
