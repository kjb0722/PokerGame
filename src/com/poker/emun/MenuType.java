package com.poker.emun;

public enum MenuType {
	start(0), reset(1), exit(2);

	public final int number;

	MenuType(int number) {
		this.number = number;
	}

	public int getNumber() {
		return this.number;
	}
}
 