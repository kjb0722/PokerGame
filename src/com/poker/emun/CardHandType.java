package com.poker.emun;

public enum CardHandType {
	노페어(0), 원페어(1), 투페어(2), 트리플(3), 스트레이트(4), 백스트레이트(5), 마운틴(6), 플러쉬(7), 풀하우스(8), 포카드(9), 스트레이트플러쉬(6), 백스트레이트플러쉬(7),
	로얄스트레이트플러쉬(8);

	public final int number;

	CardHandType(int number) {
		this.number = number;
	}
}
 