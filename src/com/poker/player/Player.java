package com.poker.player;

import java.util.ArrayList;

import com.poker.emun.CardHandType;
import com.poker.game.Card;

public class Player {
	public final int CARD_TOTAL_COUNT = 7;

	private String name;
	private int money;
	private ArrayList<Card> card;
	private CardHandType hand;
	private Card bestCard;

	public Player(String name, int money) {
		this.name = name;
		this.money = money;

		this.card = new ArrayList<Card>();
	}

	public int getBestNumberOrder() {
		return bestCard.getNumberOrder();
	}

	public int getBestSuitNumber() {
		return bestCard.getSuitType().getRank();
	}

	public Card getBestCard() {
		return bestCard;
	}

	public void setBestCard(Card bestCard) {
		this.bestCard = bestCard;
	}

	public CardHandType getHand() {
		return hand;
	}

	public void setHand(CardHandType hand) {
		this.hand = hand;
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
