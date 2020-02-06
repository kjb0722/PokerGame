package com.poker.gui;

import javax.swing.JFrame;

public class GameGui extends JFrame{
	static final int width = 600;
	static final int height = 600;
	Board board;
	public GameGui(){
		init();
		
		board = new Board();
		add(board);
	}
	private void init() {
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
