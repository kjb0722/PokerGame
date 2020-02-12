package com.poker.game;

import java.util.ArrayList;

public class Rank {
	ArrayList<Card> hands;

	public String Checker(String[] hand) {
		String cardHand = "";
		hands = new ArrayList<Card>();
		for (int i = 0; i < hand.length; i++) {
			String[] handCard = hand[i].split("-");
			String suit = handCard[0];
			String number = handCard[1];
			hands.add(new Card(suit, number));
			System.out.println(suit + " " + number);
		}

		if (onePair()) {
			cardHand = "원페어";
		}

		if (cardHand.equals("")) {
			cardHand = "노 페어";
		}
		return cardHand;
	}

	private boolean onePair() {
		for (int i = 0; i < hands.size(); i++) {
			Card card = hands.get(i);
			for (int j = (i + 1); j < hands.size(); j++) {
				Card cards = hands.get(j);
				if (card.getNumber().equals(cards.getNumber())) {
					return true;
				}
			}
		}
		return false;
	}
}
