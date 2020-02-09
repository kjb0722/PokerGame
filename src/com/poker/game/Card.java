package com.poker.game;

public class Card {
	private String suit;
	private String number;

	Card(String suit, String number) {
		this.suit = suit;
		this.number = number;
	}

	public String getSuit() {
		return suit;
	}

	public String getNumber() {
		return number;
	}
}
