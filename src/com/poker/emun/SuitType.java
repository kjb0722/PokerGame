package com.poker.emun;

public enum SuitType {
	clubs(0), hearts(1), diamonds(2), spades(3);

	private int rank;

	SuitType(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}
}
