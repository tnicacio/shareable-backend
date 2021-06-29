package com.tnicacio.shareable.dto;

import java.io.Serializable;

import com.tnicacio.shareable.services.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

}
