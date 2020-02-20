package com.poker.emun;

public enum RaiseType {
	Half("하프"), Die("다이"), Check("체크");

	private final String value;

	RaiseType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
