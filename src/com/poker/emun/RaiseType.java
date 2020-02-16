package com.poker.emun;

public enum RaiseType {
	Half("하프"), Die("다이"), Check("체크");

	public final String value;

	RaiseType(String value) {
		this.value = value;
	}
}
