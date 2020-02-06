package com.poker.gui;

import java.awt.Color;

import javax.swing.JPanel;

public class Board extends JPanel{
	Board(){
		init();
	}
	private void init() {
		setSize(GameGui.width, GameGui.height);
		setLocation(0, 0);
		
	}
}
