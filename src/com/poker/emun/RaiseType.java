package com.poker.emun;

public enum RaiseType {
	Half("����"), Die("����"), Check("üũ");

	private final String value;

	RaiseType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
