package com.poker.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poker.emun.RaiseType;

public class Board extends JPanel {
	private final int panelWidth = GameGui.WIDTH - 250;
	private final int panelHeight = GameGui.HEIGHT;
	private final int btnWidth = 100;
	private final int btnHeight = 150;
	private final int btnComputerX = 30;
	private final int btnComputerY = 30;
	private final int btnPlayerX = 30;
	private final int btnPlayerY = 500;
	private final int lblComputerCardX = (panelWidth / 2) - 40;
	private final int lblComputerCardY = 5;
	private final int lblCardWidth = 90;
	private final int lblCardHeight = 20;
	private final int lblPlayCardX = (panelWidth / 2) - 40;
	private final int lblPlayCardY = panelHeight - 225;
	private final int btnRaiseX = 80;
	private final int btnRaiseGapX = 100;
	private final int btnRaiseY = panelHeight / 2 + 50;
	private final int btnRaiseWidth = 80;
	private final int btnRaiseHeight = 30;
	private final int lblMoneyWidth = 200;
	private final int lblMoneyHeight = 200;
	private final int lblMoneyX = 150;
	private final int lblMoneyY = 250;
	private final int txtMoneyX = 350;
	private final int txtMoneyY = 335;
	private final int txtMoneyWidth = 250;
	private final int txtMoneyHeight = 30;
	private final int lblPlateWidth = 100;
	private final int lblPlateHeight = 100;
	private final int lblPlateX = 200;
	private final int lblPlateY = 200;
	private final int txtPlateX = 300;
	private final int txtPlateY = 200;
	private final int txtPlateWidth = 200;
	private final int txtPlateHeight = 100;
	private final int lblPlayerMoneyUnitX = 620;
	private final int lblPlayerMoneyUnitY = 250;
	private final int lblPlayerMoneyUnitWidth = 200;
	private final int lblPlayerMoneyUnitHeight = 200;

	private GameGui gui;
	private JButton[] computerBtn;
	private JButton[] playerBtn;
	private JTextField computerCardLabel;
	private JTextField playerCardLabel;
	private JButton[] raiseBtn;
	private JLabel lblPlayerMoney;
	private JTextField txtPlayerMoney;
	private JLabel lblPlayerMoneyUnit;
	private JLabel lblPlate;
	private JTextField txtPlate;
	private DecimalFormat df;
	private int playerDefaultMoney = 100000;
	private int betDefaultMoney = 1000;
	private ActionListener listener;

	public Board(GameGui gui) {
		this.gui = gui;
		init();
	}

	public int getBetDefaultMoney() {
		return betDefaultMoney;
	}

	public void setTxtPlayerMoney(String text) {
		txtPlayerMoney.setText(text);
	}

	public String getTxtPlayerMoney() {
		return txtPlayerMoney.getText();
	}

	public void setTxtPlate(String text) {
		txtPlate.setText(text);
	}

	public String getTxtPlate() {
		return txtPlate.getText();
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

	public void resetPlayerMoney() {
		this.txtPlayerMoney.setText(df.format(playerDefaultMoney));
	}

	public void resetBoard() {
		resetCardBtn();
		resetRaiseBtn();
		txtPlate.setText(("0"));
	}

	public void resetCardBtn() {
		for (int i = 0; i < computerBtn.length; i++) {
			computerBtn[i].setName("");
			computerBtn[i].setIcon(null);
		}
		for (int i = 0; i < playerBtn.length; i++) {
			playerBtn[i].setName("");
			playerBtn[i].setIcon(null);
		}
	}

	public void resetRaiseBtn() {
		for (int i = 0; i < raiseBtn.length; i++) {
			raiseBtn[i].setEnabled(false);
		}
	}

	private void init() {
		setBounds(0, 0, panelWidth, panelHeight);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		df = new DecimalFormat("#,###");
		createBtnListener();
		createComputerComponent();
		createPlayerComponent();
		createMoneyScore();
	}

	private void createMoneyScore() {
		// 판 돈 라벨
		lblPlate = new JLabel();
		lblPlate.setText("판 돈:");
		lblPlate.setBounds(lblPlateX, lblPlateY, lblPlateWidth, lblPlateHeight);
		lblPlate.setForeground(Color.white);
		lblPlate.setFont(new Font("돋움", Font.BOLD, 20));
		add(lblPlate);

		// 판 돈 텍스트
		txtPlate = new JTextField();
		txtPlate.setText(df.format(0));
		txtPlate.setEnabled(false);
		txtPlate.setBounds(txtPlateX, txtPlateY, txtPlateWidth, txtPlateHeight);
		txtPlate.setFont(new Font("돋움", Font.BOLD, 20));
		txtPlate.setBackground(Color.darkGray);
		txtPlate.setHorizontalAlignment(JTextField.CENTER);
		add(txtPlate);

		// 플레이어 보유 금액 라벨
		lblPlayerMoney = new JLabel();
		lblPlayerMoney.setText("현재 보유 금액 : ");
		lblPlayerMoney.setBounds(lblMoneyX, lblMoneyY, lblMoneyWidth, lblMoneyHeight);
		lblPlayerMoney.setForeground(Color.white);
		lblPlayerMoney.setFont(new Font("돋움", Font.BOLD, 20));
		add(lblPlayerMoney);

		// 플레이어 보유 금액 텍스트
		txtPlayerMoney = new JTextField();
		df = new DecimalFormat("#,###");
		txtPlayerMoney.setText(df.format(playerDefaultMoney));
		txtPlayerMoney.setEnabled(false);
		txtPlayerMoney.setBounds(txtMoneyX, txtMoneyY, txtMoneyWidth, txtMoneyHeight);
		txtPlayerMoney.setFont(new Font("돋움", Font.BOLD, 20));
		txtPlayerMoney.setBackground(Color.darkGray);
		txtPlayerMoney.setHorizontalAlignment(JTextField.CENTER);
		add(txtPlayerMoney);

		// "원"
		lblPlayerMoneyUnit = new JLabel();
		lblPlayerMoneyUnit.setText("원");
		lblPlayerMoneyUnit.setBounds(lblPlayerMoneyUnitX, lblPlayerMoneyUnitY, lblPlayerMoneyUnitHeight,
				lblPlayerMoneyUnitWidth);
		lblPlayerMoneyUnit.setFont(new Font("돋움", Font.BOLD, 20));
		lblPlayerMoneyUnit.setForeground(Color.white);
		add(lblPlayerMoneyUnit);
	}

	private void createComputerComponent() {
		computerBtn = new JButton[7];

		for (int i = 0; i < computerBtn.length; i++) {
			computerBtn[i] = new JButton();
			computerBtn[i].setBounds(btnComputerX + (btnWidth * i), btnComputerY, btnWidth, btnHeight);
			add(computerBtn[i]);
		}

		computerCardLabel = new JTextField();
		computerCardLabel.setBounds(lblComputerCardX, lblComputerCardY, lblCardWidth, lblCardHeight);
		computerCardLabel.setText("컴퓨터 카드");
		computerCardLabel.setHorizontalAlignment(JTextField.CENTER);
		computerCardLabel.setEnabled(false);
		add(computerCardLabel);
	}

	private void createPlayerComponent() {
		playerBtn = new JButton[7];
		for (int i = 0; i < playerBtn.length; i++) {
			playerBtn[i] = new JButton();
			playerBtn[i].setBounds(btnPlayerX + (btnWidth * i), btnPlayerY, btnWidth, btnHeight);
			add(playerBtn[i]);
		}

		playerCardLabel = new JTextField();
		playerCardLabel.setBounds(lblPlayCardX, lblPlayCardY, lblCardWidth, lblCardHeight);
		playerCardLabel.setText("플레이어 카드");
		playerCardLabel.setHorizontalAlignment(JTextField.CENTER);
		playerCardLabel.setEnabled(false);
		add(playerCardLabel);

//		하프	전체 판돈의 절반, 즉 50% 금액을 베팅합니다.
//		다이	새로 베팅하지 않고, 이번 판을 포기합니다.
//		체크	머니를 베팅하지 않고 다음 카드를 받습니다.(보스만 가능)
//		Half("하프"), Die("다이"), Check("체크");
//			0			1			2
		raiseBtn = new JButton[3];
		for (int i = 0; i < raiseBtn.length; i++) {
			String btnName = "";
			raiseBtn[i] = new JButton();
			if (i == RaiseType.Half.ordinal()) {
				btnName = RaiseType.Half.value;
				raiseBtn[i].setName(RaiseType.Half.name());
			} else if (i == RaiseType.Die.ordinal()) {
				btnName = RaiseType.Die.value;
				raiseBtn[i].setName(RaiseType.Die.name());
			} else if (i == RaiseType.Check.ordinal()) {
				btnName = RaiseType.Check.value;
				raiseBtn[i].setName(RaiseType.Check.name());
			}

			raiseBtn[i].setText(btnName);
			raiseBtn[i].setBounds(btnRaiseX + (i * btnRaiseGapX), btnRaiseY, btnRaiseWidth, btnRaiseHeight);
			raiseBtn[i].setEnabled(false);
			raiseBtn[i].addActionListener(listener);
			add(raiseBtn[i]);
		}
	}

	private void createBtnListener() {
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String raiseType = e.getActionCommand();
				if (raiseType.equals(RaiseType.Half.value)) {
					gui.raiseHalf();
				} else if (raiseType.equals(RaiseType.Die.value)) {
					gui.raiseDie();
				} else if (raiseType.equals(RaiseType.Check.value)) {
					gui.raiseCheck();
				}
			}
		};
	}
}
