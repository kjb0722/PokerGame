package com.poker.gui;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.poker.emun.CardHandType;
import com.poker.game.Card;
import com.poker.game.GamePlay;
import com.poker.game.Rank;
import com.poker.player.Player;

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

	public void rankCheck(Player player) {
		rank.Checker(player);
	}

	public Board getBoard() {
		return board;
	}

	public void bet(String betType) {
		play.bet(betType);
	}

	public int getTxtPlate() {
		return Integer.parseInt(board.getTxtPlate().replace(",", ""));
	}

	public void setTxtPlate(String text) {
		board.setTxtPlate(text);
	}

	public int getTxtPlayerMoney() {
		return Integer.parseInt(board.getTxtPlayerMoney().replace(",", ""));
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

	public int getTxtComputerMoney() {
		return Integer.parseInt(board.getTxtComputerMoney().replace(",", ""));
	}

	public void setTxtComputerMoney(String text) {
		board.setTxtComputerMoney(text);
	}
	
	public Player[] getPlayer() {
		return board.getPlayer();
	}

	public void resetPlayer() {
		board.resetPlayer();
	}

	public int drawCheck(Player[] player) {
		return rank.drawCheck(player);
	}

	public int getComputerDefaultMoney() {
		return board.getComputerDefaultMoney();
	}

	public int getPlayerDefaultMoney() {
		return board.getPlayerDefaultMoney();
	}

	public void setTxtHalf(int money) {
		board.setTxtHalf(Integer.toString(money));
	}

	public void setMoneyInit() {
		board.setMoneyInit();
	}
}
