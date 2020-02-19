package com.poker.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.poker.emun.CardHandType;
import com.poker.game.Card;
import com.poker.game.GamePlay;
import com.poker.game.Rank;

public class GameGui extends JFrame {
	static final int WIDTH = 1000;
	static final int HEIGHT = 700;
	private Board board;
	private MenuPanel menu;
	private GamePlay play;
	private Rank rank;

	public GameGui() {
		init();
	}

	public void gameRun() {
		play = new GamePlay(this);
		play.gamePlay();
	}

	public void resetBoard() {
		board.resetBoard();
	}

	public void cardSpread(int count) {
		play.cardSpread(count);
	}

	public void resetRaiseBtn() {
		board.resetRaiseBtn();
	}

	private void init() {
		board = new Board(this);
		add(board);

		menu = new MenuPanel(this);
		add(menu);

		rank = new Rank();

		setTitle("포커 게임");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void resetPlayerMoney() {
		board.resetPlayerMoney();
	}

	public JLabel[] getComputerCardLbl() {
		return board.getComputerCardLbl();
	}

	public JLabel[] getPlayerCardLbl() {
		return board.getPlayerCardLbl();
	}

	public JButton[] getRaiseBtn() {
		return board.getRaiseBtn();
	}

	public String getTxtNotice() {
		return menu.getTxtNotice();
	}

	public void setTxtNotice(String text) {
		menu.setTxtNotice(text);
	}

	public CardHandType rankCheck(ArrayList<Card> card) {
		return rank.Checker(card);
	}

	public Board getBoard() {
		return board;
	}

	public void bet(String betType) {
		play.bet(betType);
	}

	public String getTxtPlate() {
		return board.getTxtPlate();
	}

	public void setTxtPlate(String text) {
		board.setTxtPlate(text);
	}

	public String getTxtPlayerMoney() {
		return board.getTxtPlayerMoney();
	}

	public void setTxtPlayerMoney(String text) {
		board.setTxtPlayerMoney(text);
	}

	public int getBetDefaultMoney() {
		return board.getBetDefaultMoney();
	}

	public void lastCardCheck() {
		play.lastCardCheck();
	}

	public void raiseHalf() {
		play.raiseHalf();
	}

	public void raiseDie() {
		play.raiseDie();
	}

	public void resetCardBtn() {
		board.resetCardBtn();
	}

	public void raiseCheck() {
		play.raiseCheck();
	}

	public String getTxtComputerMoney() {
		return board.getTxtComputerMoney();
	}

	public void setTxtComputerMoney(String text) {
		board.setTxtComputerMoney(text);
	}
}
