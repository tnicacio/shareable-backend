package com.tnicacio.shareable.entities.pk;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tnicacio.shareable.entities.SharedSession;
import com.tnicacio.shareable.entities.User;

public class UserSessionPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "shared_session_id")
	private SharedSession sharedSession;
	
	public UserSessionPK() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SharedSession getSharedSession() {
		return sharedSession;
	}

	public void setSharedSession(SharedSession sharedSession) {
		this.sharedSession = sharedSession;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sharedSession == null) ? 0 : sharedSession.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserSessionPK other = (UserSessionPK) obj;
		if (sharedSession == null) {
			if (other.sharedSession != null)
				return false;
		} else if (!sharedSession.equals(other.sharedSession))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
