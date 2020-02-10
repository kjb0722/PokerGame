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
	private JButton[] computerBtn;
	private JButton[] playerBtn;
	private JButton[] raiseBtn;

	public GamePlay(GameGui gui) {
		this.board = gui.getBoard();
		this.menu = gui.getMenu();
		this.computerBtn = board.getComputerBtn();
		this.playerBtn = board.getPlayerBtn();
		this.raiseBtn = board.getRaiseBtn();

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
		for (int i = 0; i < count; i++) {
			Card computerCard = cards.get(i);
			computerBtn[i].setText("<html>" + computerCard.getSuit() + "<br>" + computerCard.getNumber() + "</html>");
			cards.remove(i);

			Card PlayerCard = cards.get(i);
			playerBtn[i].setText("<html>" + PlayerCard.getSuit() + "<br>" + PlayerCard.getNumber() + "</html>");
			cards.remove(i);
		}
		menu.setText("카드를 "+ count +"장씩 돌립니다.");
		
		if(bet) {
			menu.setText(menu.getText()+"\n베팅해주세요.");
		}
	}
}
