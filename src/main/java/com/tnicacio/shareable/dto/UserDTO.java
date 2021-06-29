package com.tnicacio.shareable.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.tnicacio.shareable.entities.User;

public class UserDTO {

	private Long id;
	
	@NotBlank(message = "Required Field")
	private String name;
	
	@NotBlank(message = "Required Field")
	@Email(message = "Please insert a valid email")
	private String email;
	
	@NotBlank(message = "Required Field")
	private String password;
	
	private String description;
	private Instant createdAt;
	private Instant updatedAt;
	private Instant inactivatedAt;
	private Boolean isActive;
	
	private final Set<RoleDTO> roles = new HashSet<>();
	private final Set<KnowledgeDTO> knowledges = new HashSet<>();
	private final Set<SessionDTO> sessions = new HashSet<>();
	
	public UserDTO() {
	}

	public UserDTO(Long id, String name, String email, String password, String description, Instant createdAt,
			Instant updatedAt, Instant inactivatedAt, Boolean isActive) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.inactivatedAt = inactivatedAt;
		this.isActive = isActive;
	}
	
	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
		entity.getKnowledges().forEach(knowledge -> this.knowledges.add(new KnowledgeDTO(knowledge)));
		entity.getSessions().forEach(session -> this.sessions.add(new SessionDTO(session)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Instant getInactivatedAt() {
		return inactivatedAt;
	}

	public void setInactivatedAt(Instant inactivatedAt) {
		this.inactivatedAt = inactivatedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public Set<KnowledgeDTO> getKnowledges() {
		return knowledges;
	}

	public Set<SessionDTO> getSessions() {
		return sessions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
