package com.poker.emun;

public enum CardHandType {
	노페어(0), 원페어(1), 투페어(2), 트리플(3), 스트레이트(4), 마운틴(5), 플러쉬(6), 풀하우스(7), 포카드(8), 스트레이트플러쉬(9), 로얄스트레이트플러쉬(10);

	private final int number;

	CardHandType(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}
}
