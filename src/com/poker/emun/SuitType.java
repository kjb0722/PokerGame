package com.poker.emun;

public enum SuitType {
	clubs(0), diamonds(1), hearts(2), spades(3);

	private int rank;

	SuitType(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}
}
