package com.poker.emun;

public enum RaiseType {
	Call("콜"), Bbing("삥"), Tadang("따당"), Half("하프"), Die("다이"), Check("체크");

	public final String value;

	RaiseType(String value) {
		this.value = value;
	}
}
