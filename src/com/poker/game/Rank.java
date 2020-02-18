package com.poker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import com.poker.emun.CardHandType;
import com.poker.emun.SuitType;

public class Rank {
	ArrayList<Card> hands;

	public CardHandType Checker(ArrayList<Card> handCard) {
		hands = handCard;

		// 정렬
		handSort();

		if (royalStraightFlush()) {
			return CardHandType.로얄스트레이트플러쉬;
		} else if (straightFlush()) {
			return CardHandType.스트레이트플러쉬;
		} else if (fourCard()) {
			return CardHandType.포카드;
		} else if (fullHouse()) {
			return CardHandType.풀하우스;
		} else if (flush()) {
			return CardHandType.플러쉬;
		} else if (mountain()) {
			return CardHandType.마운틴;
		} else if (straight()) {
			return CardHandType.스트레이트;
		} else if (threePair()) {
			return CardHandType.트리플;
		} else if (twoPair()) {
			return CardHandType.투페어;
		} else if (pair() == 1) {
			return CardHandType.원페어;
		} else {
			return CardHandType.노페어;
		}
	}

	// 카드 정렬
	private void handSort() {
		for (int i = 0; i < hands.size(); i++) {
			for (int j = (i + 1); j < hands.size(); j++) {
				int hand = hands.get(i).getNumberOrder();
				int hand2 = hands.get(j).getNumberOrder();
				if (hand > hand2) {
					Card tempCard = hands.get(i);
					Card tempCard2 = hands.get(j);
					hands.remove(j);
					hands.add(j, tempCard);
					hands.remove(i);
					hands.add(i, tempCard2);
				}
			}
		}
	}

	// 원 페어 -5장의 카드 중에서 2장의 숫자가 같은 패
	private int pair() {
		int count = 0;
		for (int i = 0; i < hands.size(); i++) {
			int num1 = hands.get(i).getNumberOrder();
			for (int j = (i + 1); j < hands.size(); j++) {
				int num2 = hands.get(j).getNumberOrder();
				if (num1 == num2) {
					count++;
					break;
				}
			}
		}
		return count;
	}

	// 투 페어 -같은 숫자 두 개(원페어)가 두 쌍이 있는 패
	private boolean twoPair() {
		return pair() >= 2;
	}

	// 트리플 -5장의 카드 중에서 3장의 숫자가 같은 패
	private boolean threePair() {
		for (int i = 0; i < hands.size(); i++) {
			int count = 0;
			int num1 = hands.get(i).getNumberOrder();
			for (int j = 0; j < hands.size(); j++) {
				int num2 = hands.get(j).getNumberOrder();
				if (num1 == num2) {
					count++;
				}
				if (count >= 3) {
					return true;
				}
			}
		}
		return false;
	}

	// 스트레이트 -모든 무늬가 같지는 않고 카드 5장의 숫자가 연달아 있는 패
	private boolean straight() {
		int count = 0;
		for (int i = 0; i < hands.size(); i++) {
			if (i + 1 >= hands.size()) {
				break;
			}
			if (hands.get(i).getNumberOrder() + 1 == hands.get(i + 1).getNumberOrder()) {
				count++;
			}
			if (count >= 5) {
				return true;
			}
		}
		return false;
	}

	// 마운틴 - 모든 무늬가 같지는 않고 10, J, Q, K, A가 연달아 있는 패
	private boolean mountain() {
		int startNumber = 10;
		for (Card card : hands) {
			if (card.getNumberOrder() == startNumber) {
				startNumber++;
			}

			if (startNumber >= 15) {
				return true;
			}
		}
		return false;
	}

	// 플러쉬 -카드 5장 모두 무늬가 같은 패
	private boolean flush() {
		for (int i = 0; i < hands.size(); i++) {
			int count = 0;
			for (int j = 0; j < hands.size(); j++) {
				if (hands.get(i).getSuit().equals(hands.get(j).getSuit())) {
					count++;
				}
				if (count >= 5) {
					return true;
				}
			}
		}

		return false;
	}

	// 풀하우스 -같은 숫자 3개(트리플)와 같은 숫자 2개(원페어)로 된 패
	private boolean fullHouse() {
		return threePair() && pair() > 2;
	}

	// 포카드(포카) -같은 숫자의 카드 4장으로 되어 있는 패
	private boolean fourCard() {
		for (int i = 0; i < hands.size(); i++) {
			int count = 0;
			int num1 = hands.get(i).getNumberOrder();
			for (int j = 0; j < hands.size(); j++) {
				int num2 = hands.get(j).getNumberOrder();
				if (num1 == num2) {
					count++;
				}
				if (count >= 4) {
					return true;
				}
			}
		}
		return false;
	}

	// 스트레이트 플러쉬(스티플) -카드 5장이 모두 무늬가 같으면서 숫자가 연달아 있는 패
	private boolean straightFlush() {
		return flush() && straight();
	}

	// 로얄 스트레이트 플러쉬 -카드 5장이 모두 무늬가 같으면서 10,J,Q,K,A 연달아 있는 패
	private boolean royalStraightFlush() {
		int startNumber = 10;
		if (flush()) {
			int spadesCount = 0;
			for (Card card : hands) {
				if (card.getSuit().equals(SuitType.spades.name()) && card.getNumberOrder() == startNumber) {
					startNumber++;
					spadesCount++;
				}
			}

			if (spadesCount >= 5 && startNumber >= 5) {
				return true;
			}
		}
		return false;
	}
}
