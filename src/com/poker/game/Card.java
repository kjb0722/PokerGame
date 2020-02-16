package com.poker.game;

public class Card {
	private String suit;
	private String number;
	private int numberOrder;

	Card(String suit, String number, int numberOrder) {
		this.suit = suit;
		this.number = number;
		this.numberOrder = numberOrder;
	}

	public String getSuit() {
		return suit;
	}

	public String getNumber() {
		return number;
	}
	
	public int getNumberOrder() {
		return numberOrder;
	}
}
