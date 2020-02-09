package com.poker.game;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JTextArea;

import com.poker.gui.Board;
import com.poker.gui.GameGui;
import com.poker.gui.MenuPanel;

public class GamePlay {
//	{"Hearts", "Clubs", "Spades", "Diamonds"};
//	{"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	String[] suits = { "Hearts", "Clubs", "Spades", "Diamonds" };
	String[] numbers = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
			"King", "Ace" };
	private ArrayList<Card> cards;
	private Board board;
	private MenuPanel menu;
	private JButton[] ComputerBtn;
	private JButton[] PlayerBtn;

	public GamePlay(GameGui gui) {
		this.board = gui.getBoard();
		this.menu = gui.getMenu();
		this.ComputerBtn = board.getComputerBtn();
		this.PlayerBtn = board.getPlayerBtn();

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
		firstCard();
	}

	private void firstCard() {
		for (int i = 0; i < 3; i++) {
			Card computerCard = cards.get(i);
			ComputerBtn[i].setText("<html>" + computerCard.getSuit() + "<br>" + computerCard.getNumber() + "</html>");
			cards.remove(i);

			Card PlayerCard = cards.get(i);
			PlayerBtn[i].setText("<html>" + PlayerCard.getSuit() + "<br>" + PlayerCard.getNumber() + "</html>");
			cards.remove(i);
		}
		menu.setText("카드를 3장씩 돌립니다.");
	}
}
