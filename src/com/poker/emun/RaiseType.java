package com.poker.emun;

public enum RaiseType {
	Call("��"), Bbing("��"), Tadang("����"), Half("����"), Die("����"), Check("üũ");

	public final String value;

	RaiseType(String value) {
		this.value = value;
	}
}
