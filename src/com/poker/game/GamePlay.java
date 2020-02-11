package com.poker.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.poker.gui.GameGui;

import javafx.geometry.HorizontalDirection;

public class GamePlay {
//	{"Hearts", "Clubs", "Spades", "Diamonds"};
//	{"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	String[] suits = { "hearts", "clubs", "spades", "diamonds" };
	String[] numbers = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace" };
	private ArrayList<Card> cards;
	private GameGui gui;
	private JButton[] computerBtn;
	private JButton[] playerBtn;
	private JButton[] raiseBtn;

	public GamePlay(GameGui gui) {
		this.gui = gui;
		this.computerBtn = gui.getComputerBtn();
		this.playerBtn = gui.getPlayerBtn();
		this.raiseBtn = gui.getRaiseBtn();

		cards = new ArrayList<Card>();
		createCards();
		shuffle();
	}

	private void shuffle() {
		Collections.shuffle(cards);
	}

	private void createCards() {
		for (int i = 0; i < suits.length; i++) {
			String suit = suits[i];
			for (int j = 0; j < numbers.length; j++) {
				cards.add(new Card(suit, numbers[j]));
			}
		}
	}

	public void gamePlay() {
		cardSpread(false, 3);
		raiseBtnEnable();
	}

	private void raiseBtnEnable() {
		for (int i = 0; i < raiseBtn.length; i++) {
			raiseBtn[i].setEnabled(true);
		}
	}

	public void cardSpread(boolean bet, int count) {
		int spreadCard = spreadCardCounting();

		for (int i = spreadCard; i < spreadCard + count; i++) {
			Card computerCard = cards.get(i);
			computerBtn[i].setToolTipText(computerCard.getSuit() + "-" + computerCard.getNumber());
			computerBtn[i].setIcon(
					new ImageIcon("img/" + computerCard.getNumber() + "_of_" + computerCard.getSuit() + ".png"));
			computerBtn[i].setHorizontalTextPosition(SwingConstants.CENTER);

			cards.remove(i);

			Card playerCard = cards.get(i);
			playerBtn[i].setToolTipText(playerCard.getSuit() + "-" + playerCard.getNumber());
			playerBtn[i]
					.setIcon(new ImageIcon("img/" + playerCard.getNumber() + "_of_" + playerCard.getSuit() + ".png"));
			cards.remove(i);
		}

		if (spreadCard == (playerBtn.length - 1)) {
			gui.setNoticeText(gui.getNoticeText() + "������ ī�带 ���Ƚ��ϴ�.\n");
			gui.resetRaiseBtn();
		} else {
			gui.setNoticeText(gui.getNoticeText() + "ī�带 " + count + "�徿 �����ϴ�.\n");
		}

		if (bet) {
			gui.setNoticeText(gui.getNoticeText() + "�������ּ���.\n");
		}
	}

	private int spreadCardCounting() {
		for (int i = 0; i < computerBtn.length; i++) {
			if (computerBtn[i].getToolTipText().equals("")) {
				return i;
			}
		}

		return -1;
	}
}