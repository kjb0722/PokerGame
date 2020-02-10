package com.poker.gui;

import javax.swing.JFrame;

import com.poker.game.GamePlay;

public class GameGui extends JFrame {
	static final int WIDTH = 1000;
	static final int HEIGHT = 700;
	private Board board;
	private MenuPanel menu;
	private GamePlay play;

	public Board getBoard() {
		return board;
	}

	public MenuPanel getMenu() {
		return menu;
	}

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

	private void init() {
		board = new Board(this);
		add(board);

		menu = new MenuPanel(this);
		add(menu);

		setTitle("포커 게임");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
