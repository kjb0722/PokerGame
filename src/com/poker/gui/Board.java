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

	private enum raiseType {
		Call("콜"), Bbing("삥"), Tadang("따당"), Half("하프"), Die("다이"), Check("체크");

		private final String value;

		raiseType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public void resetCardBtn() {
		for (int i = 0; i < computerBtn.length; i++) {
			computerBtn[i].setToolTipText("");
			computerBtn[i].setIcon(null);
		}
		for (int i = 0; i < playerBtn.length; i++) {
			playerBtn[i].setToolTipText("");
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
			computerBtn[i].setToolTipText("");
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
			playerBtn[i].setToolTipText("");
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
			raiseBtn[i] = new JButton();
			if (i == raiseType.Call.ordinal()) {
				btnName = raiseType.Call.value;
				raiseBtn[i].setName(raiseType.Call.name());
			} else if (i == raiseType.Bbing.ordinal()) {
				btnName = raiseType.Bbing.value;
				raiseBtn[i].setName(raiseType.Bbing.name());
			} else if (i == raiseType.Tadang.ordinal()) {
				btnName = raiseType.Tadang.value;
				raiseBtn[i].setName(raiseType.Tadang.name());
			} else if (i == raiseType.Half.ordinal()) {
				btnName = raiseType.Half.value;
				raiseBtn[i].setName(raiseType.Half.name());
			} else if (i == raiseType.Die.ordinal()) {
				btnName = raiseType.Die.value;
				raiseBtn[i].setName(raiseType.Die.name());
			} else if (i == raiseType.Check.ordinal()) {
				btnName = raiseType.Check.value;
				raiseBtn[i].setName(raiseType.Check.name());
			}

			raiseBtn[i].setText(btnName);
			raiseBtn[i].setBounds(80 + (i * 100), this.getHeight() / 2 + 50, btnRaiseWidth, btnRaiseHeight);
			raiseBtn[i].setEnabled(false);
			raiseBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand() == raiseType.Die.value) {
						gui.resetBoard();
					} else {
						gui.cardSpread(true, 1);
					}
				}
			});
			add(raiseBtn[i]);
		}
	}
}
