package com.poker.game;

import java.util.ArrayList;

import com.poker.emun.CardHandType;
import com.poker.emun.SuitType;
import com.poker.player.Player;

public class Rank {
	ArrayList<Card> hands;
	Player player;

	public void rankChecker(Player player) {
		this.player = player;
		this.hands = player.getArrayCard();

		// 정렬
		handSort();

		if (royalStraightFlush()) {
			player.setHand(CardHandType.로얄스트레이트플러쉬);
		} else if (straightFlush()) {
			player.setHand(CardHandType.스트레이트플러쉬);
		} else if (fourCard()) {
			player.setHand(CardHandType.포카드);
		} else if (fullHouse()) {
			player.setHand(CardHandType.풀하우스);
		} else if (flush()) {
			player.setHand(CardHandType.플러쉬);
		} else if (mountain()) {
			player.setHand(CardHandType.마운틴);
		} else if (straight()) {
			player.setHand(CardHandType.스트레이트);
		} else if (threePair()) {
			player.setHand(CardHandType.트리플);
		} else if (twoPair()) {
			player.setHand(CardHandType.투페어);
		} else if (pair() == 1) {
			player.setHand(CardHandType.원페어);
		} else {
			highCard();
			player.setHand(CardHandType.노페어);
		}
	}

	public int drawCheck(Player[] player) {
		CardHandType computerHand = player[0].getHand();
		CardHandType playerHand = player[1].getHand();

		// 로열 스트레이트 플러쉬, 스트레이트 플러쉬, 포카드, 풀하우스, 플러쉬, 마운틴
		// 무늬로 승패 결정
		// 나머지는 숫자로 승패 결정
		if (computerHand.getNumber() >= 5 && computerHand.getNumber() <= 10 && playerHand.getNumber() >= 5
				&& playerHand.getNumber() <= 10) {
			return player[0].getBestSuitNumber() > player[1].getBestSuitNumber() ? 0 : 1;
		} else {
			return player[0].getBestNumberOrder() > player[1].getBestNumberOrder() ? 0 : 1;
		}
	}

	// 카드 정렬
	private void handSort() {
		// Number 오름차순 정렬
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

		// Number가 같은 Suit 정렬
		for (int i = 0; i < hands.size(); i++) {
			for (int j = (i + 1); j < hands.size(); j++) {
				int handNum = hands.get(i).getNumberOrder();
				int handNum2 = hands.get(j).getNumberOrder();
				int handSuit = hands.get(i).getSuitType().getRank();
				int handSuit2 = hands.get(j).getSuitType().getRank();
				if (handNum == handNum2 && handSuit > handSuit2) {
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

	// 하이카드 : 어느 족보에도 해당하지 않는다면 가장 높은 숫자의 패
	private void highCard() {
		player.setBestCard(hands.get(hands.size() - 1));
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
					player.setBestCard(hands.get(j));
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
					player.setBestCard(hands.get(j));
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
				player.setBestCard(hands.get(i + 1));
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
				player.setBestCard(card);
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
					player.setBestCard(hands.get(j));
					return true;
				}
			}
		}

		return false;
	}

	// 풀하우스 -같은 숫자 3개(트리플)와 같은 숫자 2개(원페어)로 된 패
	private boolean fullHouse() {
		return pair() > 2 && threePair();
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
					player.setBestCard(hands.get(j));
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
					player.setBestCard(card);
				}
			}

			if (spadesCount >= 5 && startNumber >= 15) {
				return true;
			}
		}
		return false;
	}
}
