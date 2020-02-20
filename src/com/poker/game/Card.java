package com.poker.game;

import com.poker.emun.NumberType;
import com.poker.emun.SuitType;

public class Card {
	private SuitType suit;
	private NumberType number;

	Card(SuitType suit, NumberType number) {
		this.suit = suit;
		this.number = number;
	}
	
	public NumberType getNumberType() {
		return number;
	}
	
	public SuitType getSuitType() {
		return suit;
	}

	public String getSuit() {
		return suit.name();
	}

	public String getNumber() {
		return number.name();
	}

	public int getNumberOrder() {
		return number.getNumber();
	}
}
