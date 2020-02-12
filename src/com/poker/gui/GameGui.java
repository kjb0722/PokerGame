package com.poker.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

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
		menu.resetMenu();
	}

	public void cardSpread(boolean bet, int count) {
		play.cardSpread(bet, count);
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

	public JButton[] getComputerBtn() {
		return board.getComputerBtn();
	}

	public JButton[] getPlayerBtn() {
		return board.getPlayerBtn();
	}

	public JButton[] getRaiseBtn() {
		return board.getRaiseBtn();
	}

	public String getNoticeText() {
		return menu.getNoticeText();
	}

	public void setNoticeText(String string) {
		menu.setNoticeText(string);
	}

	public String rankCheck(String[] hand) {
		return rank.Checker(hand);
	}
	
	public Board getBoard() {
		return board;
	}
}
