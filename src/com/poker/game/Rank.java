package com.poker.game;

import java.util.ArrayList;

import javax.swing.JButton;

public class Rank {
	ArrayList<Card> hands;
	public String Checker(String[] hand) {
		hands = new ArrayList<Card>();
		for(int i=0; i<hand.length; i++) {
			String[] handCard = hand[i].split("-");
			String suit = handCard[0];
			String number = handCard[1];
			hands.add(new Card(suit, number));
		}
		
		if(StraightFlush()) {
			
		}
		
		return "";
	}
	private boolean StraightFlush() {
		
		return false;
	}
}
