package com.tnicacio.shareable.dto;

import java.time.Instant;

import com.tnicacio.shareable.entities.Session;

public class SessionDTO {

	private Long id;
	private Instant createdAt;
	private Instant updatedAt;
	private Integer status;
	
	public SessionDTO() {}

	public SessionDTO(Long id, Instant createdAt, Instant updatedAt, Integer status) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
	}

	public SessionDTO(Session entity) {
		id = entity.getId();
		createdAt = entity.getCreatedAt();
		updatedAt = entity.getUpdatedAt();
		status = entity.getStatus().getCode();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		SessionDTO other = (SessionDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
