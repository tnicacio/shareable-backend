package com.tnicacio.shareable.entities.enums;

public enum SessionStatus {
	
	ONGOING(1),
	FINISHED(2),
	CANCELED(3);

	private int code;
	
	private SessionStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static SessionStatus valueOf(int code) {
		for (SessionStatus value : SessionStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Ivalid SharedSessionStatus code");
	}

}
