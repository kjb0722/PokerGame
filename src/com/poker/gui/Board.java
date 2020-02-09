package com.poker.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Board extends JPanel {
	private final int btnWidth = 100;
	private final int btnHeight = 150;
	private final int btnComputerLocationX = 30;
	private final int btnComputerLocationY = 30;
	private final int btnPlayerLocationX = 30;
	private final int btnPlayerLocationY = 500;
	private final int labelCardWidth = 90;
	private final int labelCardHeight = 20;
	private JButton[] computerBtn;
	private JButton[] playerBtn;
	private JTextField computerCardLabel;
	private JTextField playerCardLabel;

	public Board() {
		init();
	}

	public JButton[] getComputerBtn() {
		return computerBtn;
	}

	public JButton[] getPlayerBtn() {
		return playerBtn;
	}

	public void resetBoard() {
		for (int i = 0; i < computerBtn.length; i++) {
			computerBtn[i].setText("");
			playerBtn[i].setText("");
		}
	}

	private void init() {
		setBounds(0, 0, GameGui.WIDTH - 250, GameGui.HEIGHT);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		CreateComputerComponent();
		CreatePlayerComponent();
	}

	private void CreateComputerComponent() {
		computerBtn = new JButton[7];

		for (int i = 0; i < computerBtn.length; i++) {
			computerBtn[i] = new JButton();
			computerBtn[i].setSize(btnWidth, btnHeight);
			computerBtn[i].setLocation(btnComputerLocationX + (btnWidth * i), btnComputerLocationY);
			computerBtn[i].setEnabled(false);
			add(computerBtn[i]);
		}

		computerCardLabel = new JTextField();
		computerCardLabel.setBounds((this.getWidth() / 2) - 40, 5, labelCardWidth, labelCardHeight);
		computerCardLabel.setText("컴퓨터 카드");
		computerCardLabel.setHorizontalAlignment(JTextField.CENTER);
		computerCardLabel.setEnabled(false);
		add(computerCardLabel);
	}

	private void CreatePlayerComponent() {
		playerBtn = new JButton[7];

		for (int i = 0; i < playerBtn.length; i++) {
			playerBtn[i] = new JButton();
			playerBtn[i].setSize(btnWidth, btnHeight);
			playerBtn[i].setLocation(btnPlayerLocationX + (btnWidth * i), btnPlayerLocationY);
			playerBtn[i].setEnabled(false);
			add(playerBtn[i]);
		}
		
		playerCardLabel = new JTextField();
		playerCardLabel.setBounds((this.getWidth() / 2) - 40, this.getHeight() - 225, labelCardWidth, labelCardHeight);
		playerCardLabel.setText("플레이어 카드");
		playerCardLabel.setHorizontalAlignment(JTextField.CENTER);
		playerCardLabel.setEnabled(false);
		add(playerCardLabel);
	}
}
