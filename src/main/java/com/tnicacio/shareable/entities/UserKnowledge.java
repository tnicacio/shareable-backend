package com.tnicacio.shareable.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tnicacio.shareable.entities.pk.UserKnowledgePK;

@Entity
@Table(name = "tb_user_knowledge")
public class UserKnowledge {

	@EmbeddedId
	private UserKnowledgePK id = new UserKnowledgePK();
	
	public UserKnowledge() {};
	
	public UserKnowledge(User user, Knowledge knowledge) {
		setUser(user);
		setKnowledge(knowledge);
	}

	public User getUser() {
		return id.getUser();
	}
	
	public void setUser(User user) {
		id.setUser(user);
	}
	
	public Knowledge getKnowledge() {
		return id.getKnowledge();
	}
	
	public void setKnowledge(Knowledge knowledge) {
		id.setKnowledge(knowledge);
	}
}
