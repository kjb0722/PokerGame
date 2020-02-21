package com.poker.emun;

public enum MenuType {
	START(0), RESET(1), EXIT(2);

	private final int number;

	MenuType(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}
}
