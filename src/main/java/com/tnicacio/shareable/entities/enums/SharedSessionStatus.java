package com.tnicacio.shareable.entities.enums;

public enum SharedSessionStatus {
	
	ONGOING(1),
	FINISHED(2),
	CANCELED(3);

	private int code;
	
	private SharedSessionStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static SharedSessionStatus valueOf(int code) {
		for (SharedSessionStatus value : SharedSessionStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Ivalid SharedSessionStatus code");
	}

}
