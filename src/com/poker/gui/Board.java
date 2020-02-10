package com.poker.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private final int btnRaiseWidth = 80;
	private final int btnRaiseHeight = 30;
	private GameGui gui;
	private JButton[] computerBtn;
	private JButton[] playerBtn;
	private JTextField computerCardLabel;
	private JTextField playerCardLabel;
	private JButton[] raiseBtn;

	public Board(GameGui gui) {
		this.gui = gui;
		init();
	}

	public JButton[] getComputerBtn() {
		return computerBtn;
	}

	public JButton[] getPlayerBtn() {
		return playerBtn;
	}

	public JButton[] getRaiseBtn() {
		return raiseBtn;
	}

	public void resetBoard() {
		resetCardBtn();
		resetRaiseBtn();
	}

	public void resetCardBtn() {
		for (int i = 0; i < computerBtn.length; i++) {
			computerBtn[i].setText("");
			computerBtn[i].setIcon(null);
		}
		for (int i = 0; i < playerBtn.length; i++) {
			playerBtn[i].setText("");
			playerBtn[i].setIcon(null);
		}
	}

	public void resetRaiseBtn() {
		for (int i = 0; i < raiseBtn.length; i++) {
			raiseBtn[i].setEnabled(false);
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
			computerBtn[i].setBounds(btnComputerLocationX + (btnWidth * i), btnComputerLocationY, btnWidth, btnHeight);
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
			playerBtn[i].setBounds(btnPlayerLocationX + (btnWidth * i), btnPlayerLocationY, btnWidth, btnHeight);
			add(playerBtn[i]);
		}

		playerCardLabel = new JTextField();
		playerCardLabel.setBounds((this.getWidth() / 2) - 40, this.getHeight() - 225, labelCardWidth, labelCardHeight);
		playerCardLabel.setText("플레이어 카드");
		playerCardLabel.setHorizontalAlignment(JTextField.CENTER);
		playerCardLabel.setEnabled(false);
		add(playerCardLabel);

//		콜	앞 사람의 베팅 금액과 동일한 베팅 금액을 겁니다.
//		삥	삥	기본 판돈만 베팅합니다.(보스만 가능)
//		따당	앞사람이 베팅 한 금액의 2배를 베팅합니다.
//		하프	전체 판돈의 절반, 즉 50% 금액을 베팅합니다.
//		다이	새로 베팅하지 않고, 이번 판을 포기합니다.
//		체크	머니를 베팅하지 않고 다음 카드를 받습니다.(보스만 가능)
		raiseBtn = new JButton[6];
		for (int i = 0; i < raiseBtn.length; i++) {
			String btnName = "";
			switch (i) {
			case 0:
				btnName = "콜";
				break;
			case 1:
				btnName = "삥";
				break;
			case 2:
				btnName = "따당";
				break;
			case 3:
				btnName = "하프";
				break;
			case 4:
				btnName = "다이";
				break;
			case 5:
				btnName = "체크";
				break;
			}
			raiseBtn[i] = new JButton();
			raiseBtn[i].setText(btnName);
			raiseBtn[i].setBounds(80 + (i * 100), this.getHeight() / 2 + 50, btnRaiseWidth, btnRaiseHeight);
			raiseBtn[i].setEnabled(false);
			raiseBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gui.cardSpread(true, 1);
				}
			});
			add(raiseBtn[i]);
		}
	}
}
