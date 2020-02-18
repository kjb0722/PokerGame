package com.poker.emun;

public enum CardHandType {
	�����(0), �����(1), �����(2), Ʈ����(3), ��Ʈ����Ʈ(4), ����ƾ(5), �÷���(6), Ǯ�Ͽ콺(7), ��ī��(8), ��Ʈ����Ʈ�÷���(9), �ξ⽺Ʈ����Ʈ�÷���(10);

	private final int number;

	CardHandType(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}
}
