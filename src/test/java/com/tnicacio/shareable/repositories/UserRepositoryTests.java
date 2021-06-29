package com.tnicacio.shareable.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.tnicacio.shareable.entities.User;

@DataJpaTest
public class UserRepositoryTests {
	
	private long existingId;
	private long nonExistingId;
	private long countTotalUsers;

	private UserRepository userRepository;
	
	@Autowired
	public UserRepositoryTests(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalUsers = 4L;
	}
	
	@Test
	public void findAllShouldReturnPageWhenHasPageableParameter() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<User> page = userRepository.findAll(pageable);
		
		Assertions.assertNotNull(page);
	}
	
	@Test
	public void findAllShouldReturnPageWithAllUsersUsersWhenUserCountIsLessThanPageSize() {
		Pageable pageable = PageRequest.of(0, 12);
		Page<User> page = userRepository.findAll(pageable);
		
		Assertions.assertNotNull(page);
		Assertions.assertEquals(countTotalUsers, page.getContent().size());
	}
	
	@Test
	public void findAllShouldReturnPageWithThreeUsersWhenHasPageableWithPageSizeEqualsToThree() {
		Pageable pageable = PageRequest.of(0, 3);
		Page<User> page = userRepository.findAll(pageable);
		
		Assertions.assertNotNull(page);
		Assertions.assertEquals(3, page.getContent().size());
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalUserWhenIdExists() {
		
		Optional<User> result = userRepository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalUserWhenIdDoesNotExists() {
		
		Optional<User> result = userRepository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
