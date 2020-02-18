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

		// ����
		handSort();

		if (royalStraightFlush()) {
			return CardHandType.�ξ⽺Ʈ����Ʈ�÷���;
		} else if (straightFlush()) {
			return CardHandType.��Ʈ����Ʈ�÷���;
		} else if (fourCard()) {
			return CardHandType.��ī��;
		} else if (fullHouse()) {
			return CardHandType.Ǯ�Ͽ콺;
		} else if (flush()) {
			return CardHandType.�÷���;
		} else if (mountain()) {
			return CardHandType.����ƾ;
		} else if (straight()) {
			return CardHandType.��Ʈ����Ʈ;
		} else if (threePair()) {
			return CardHandType.Ʈ����;
		} else if (twoPair()) {
			return CardHandType.�����;
		} else if (pair() == 1) {
			return CardHandType.�����;
		} else {
			return CardHandType.�����;
		}
	}

	// ī�� ����
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

	// �� ��� -5���� ī�� �߿��� 2���� ���ڰ� ���� ��
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

	// �� ��� -���� ���� �� ��(�����)�� �� ���� �ִ� ��
	private boolean twoPair() {
		return pair() >= 2;
	}

	// Ʈ���� -5���� ī�� �߿��� 3���� ���ڰ� ���� ��
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

	// ��Ʈ����Ʈ -��� ���̰� ������ �ʰ� ī�� 5���� ���ڰ� ���޾� �ִ� ��
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

	// ����ƾ - ��� ���̰� ������ �ʰ� 10, J, Q, K, A�� ���޾� �ִ� ��
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

	// �÷��� -ī�� 5�� ��� ���̰� ���� ��
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

	// Ǯ�Ͽ콺 -���� ���� 3��(Ʈ����)�� ���� ���� 2��(�����)�� �� ��
	private boolean fullHouse() {
		return threePair() && pair() > 2;
	}

	// ��ī��(��ī) -���� ������ ī�� 4������ �Ǿ� �ִ� ��
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

	// ��Ʈ����Ʈ �÷���(��Ƽ��) -ī�� 5���� ��� ���̰� �����鼭 ���ڰ� ���޾� �ִ� ��
	private boolean straightFlush() {
		return flush() && straight();
	}

	// �ξ� ��Ʈ����Ʈ �÷��� -ī�� 5���� ��� ���̰� �����鼭 10,J,Q,K,A ���޾� �ִ� ��
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
