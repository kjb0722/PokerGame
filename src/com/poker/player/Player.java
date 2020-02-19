package com.poker.player;

import java.util.ArrayList;

import com.poker.game.Card;

public class Player {
	String name;
	int money;
	ArrayList<Card> card;

	public Player(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<Card> getArrayCard() {
		return card;
	}

	public void setArrayCard(ArrayList<Card> card) {
		this.card = card;
	}

	public Card getCard(int index) {
		return card.get(index);
	}
	
	public void setCard(Card card) {
		this.card.add(card);
	}
}
