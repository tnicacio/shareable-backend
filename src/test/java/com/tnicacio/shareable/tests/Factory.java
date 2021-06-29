package com.tnicacio.shareable.tests;

import java.time.Instant;

import com.tnicacio.shareable.dto.UserDTO;
import com.tnicacio.shareable.entities.Knowledge;
import com.tnicacio.shareable.entities.Role;
import com.tnicacio.shareable.entities.Session;
import com.tnicacio.shareable.entities.User;
import com.tnicacio.shareable.entities.enums.SharedSessionStatus;

public class Factory {
	
	public static User createUser() {
		return new User(null, "John Doe", "jaydoe@email.com", "image-url", "123456", "A great guy", Instant.now(), null, null, true);
	}
	
	public static UserDTO createUserDTO() {
		return new UserDTO(createUser());
	}
	
	public static User createAdmin() {
		User user = createUser();
		user.getRoles().add(new Role(null, "ROLE_ADMIN"));
		return user;
	}
	
	public static UserDTO createAdminDTO() {
		return new UserDTO(createAdmin());
	}
	
	public static User createOperator() {
		User user = createUser();
		user.getRoles().add(new Role(null, "ROLE_OPERATOR"));
		return user;
	}
	
	public static UserDTO createOperatorDTO() {
		return new UserDTO(createOperator());
	}
	
	public static Session createSession() {
		return new Session(null, SharedSessionStatus.ONGOING);
	}
	
	public static Knowledge createKnowledge() {
		return new Knowledge(null, "Swimming");
	}

}
