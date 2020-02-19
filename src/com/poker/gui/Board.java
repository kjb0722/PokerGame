package com.poker.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.poker.emun.RaiseType;

public class Board extends JPanel {
	private final int panelWidth = GameGui.WIDTH - 250; // 패널
	private final int panelHeight = GameGui.HEIGHT;

	private final int lblCardWidth = 100; // 카드
	private final int lblCardHeight = 150;
	private final int lblCardX = 10;
	private final int lblCardY = 10;
	private final int lblCardGapX = 60;
	private final int jpComputerCardX = 30;
	private final int jpComputerCardY = 30;
	private final int jpComputerWidth = 480;
	private final int jpComputerHeight = 170;
	private final int jpPlayerCardX = 30;
	private final int jpPlayerCardY = 500;
	private final int jpPlayerWidth = 480;
	private final int jpPlayerHeight = 170;

	private final int txtComputerCardX = 220; // 카드 표시
	private final int txtComputerCardY = 5;
	private final int txtCardWidth = 90;
	private final int txtCardHeight = 20;
	private final int txtPlayerCardX = 220;
	private final int txtPlayerCardY = panelHeight - 225;
	private final int btnRaiseX = 80;
	private final int btnRaiseGapX = 100;
	private final int btnRaiseY = panelHeight / 2 + 50;
	private final int btnRaiseWidth = 80;
	private final int btnRaiseHeight = 30;

	private final int lblComputerMoneyWidth = 200; // 컴퓨터 돈
	private final int lblComputerMoneyHeight = 50;
	private final int lblComputerMoneyX = 550;
	private final int lblComputerMoneyY = 0;
	private final int txtComputerMoneyX = 550;
	private final int txtComputerMoneyY = 40;
	private final int txtComputerMoneyWidth = 100;
	private final int txtComputerMoneyHeight = 30;
	private final int lblComputerMoneyUnitX = 660;
	private final int lblComputerMoneyUnitY = 30;
	private final int lblComputerMoneyUnitWidth = 50;
	private final int lblComputerMoneyUnitHeight = 50;

	private final int lblPlayerMoneyWidth = 200; // 플레이어 돈
	private final int lblPlayerMoneyHeight = 50;
	private final int lblPlayerMoneyX = 550;
	private final int lblPlayerMoneyY = 530;
	private final int txtPlayerMoneyX = 550;
	private final int txtPlayerMoneyY = 570;
	private final int txtPlayerMoneyWidth = 100;
	private final int txtPlayerMoneyHeight = 30;
	private final int lblPlayerMoneyUnitX = 660;
	private final int lblPlayerMoneyUnitY = 560;
	private final int lblPlayerMoneyUnitWidth = 50;
	private final int lblPlayerMoneyUnitHeight = 50;

	private final int lblPlateWidth = 100; // 판 돈
	private final int lblPlateHeight = 100;
	private final int lblPlateX = 200;
	private final int lblPlateY = 250;
	private final int txtPlateX = 300;
	private final int txtPlateY = 250;
	private final int txtPlateWidth = 200;
	private final int txtPlateHeight = 100;
	private int playerDefaultMoney = 100000;
	private int computerDefaultMoney = 1000000;
	private int betDefaultMoney = 1000;

	private GameGui gui;
	private JLabel[] computerCardLbl;
	private JLabel[] playerCardLbl;
	private JTextField computerCardTxt;
	private JTextField playerCardTxt;
	private JButton[] raiseBtn;
	private JLabel lblComputerMoney;
	private JTextField txtComputerMoney;
	private JLabel lblComputerMoneyUnit;
	private JLabel lblPlayerMoney;
	private JTextField txtPlayerMoney;
	private JLabel lblPlayerMoneyUnit;
	private JLabel lblPlate;
	private JTextField txtPlate;
	private DecimalFormat df;
	private ActionListener listener;
	private JLayeredPane jpComputerCard;
	private JLayeredPane jpPlayerCard;

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

	public void setTxtComputerMoney(String text) {
		txtComputerMoney.setText(text);
	}

	public String getTxtComputerMoney() {
		return txtComputerMoney.getText();
	}

	public void setTxtPlate(String text) {
		txtPlate.setText(text);
	}

	public String getTxtPlate() {
		return txtPlate.getText();
	}

	public JLabel[] getComputerCardLbl() {
		return computerCardLbl;
	}

	public JLabel[] getPlayerCardLbl() {
		return playerCardLbl;
	}

	public JButton[] getRaiseBtn() {
		return raiseBtn;
	}

	public void resetPlayerMoney() {
		this.txtPlayerMoney.setText(df.format(playerDefaultMoney));
		this.txtComputerMoney.setText(df.format(computerDefaultMoney));
	}

	public void resetBoard() {
		resetCardBtn();
		resetRaiseBtn();
		txtPlate.setText(("0"));
	}

	public void resetCardBtn() {
		for (int i = 0; i < computerCardLbl.length; i++) {
			computerCardLbl[i].setName("");
			computerCardLbl[i].setIcon(null);
		}
		for (int i = 0; i < playerCardLbl.length; i++) {
			playerCardLbl[i].setName("");
			playerCardLbl[i].setIcon(null);
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
		lblPlate.setFont(new Font("돋움", Font.BOLD, 14));
		add(lblPlate);

		// 판 돈 텍스트
		txtPlate = new JTextField();
		txtPlate.setText(df.format(0));
		txtPlate.setEnabled(false);
		txtPlate.setBounds(txtPlateX, txtPlateY, txtPlateWidth, txtPlateHeight);
		txtPlate.setFont(new Font("돋움", Font.BOLD, 14));
		txtPlate.setBackground(Color.darkGray);
		txtPlate.setHorizontalAlignment(JTextField.CENTER);
		add(txtPlate);
	}

	private void createComputerComponent() {
		computerCardLbl = new JLabel[7];
		jpComputerCard = new JLayeredPane();
		jpComputerCard.setBorder(new LineBorder(Color.white, 3));
		jpComputerCard.setBounds(jpComputerCardX, jpComputerCardY, jpComputerWidth, jpComputerHeight);
		add(jpComputerCard);
		for (int i = 0; i < computerCardLbl.length; i++) {
			computerCardLbl[i] = new JLabel();
			computerCardLbl[i].setBounds(lblCardX + (lblCardGapX * i), lblCardY, lblCardWidth, lblCardHeight);
			jpComputerCard.add(computerCardLbl[i], new Integer(i));
		}

		computerCardTxt = new JTextField();
		computerCardTxt.setBounds(txtComputerCardX, txtComputerCardY, txtCardWidth, txtCardHeight);
		computerCardTxt.setText("컴퓨터 카드");
		computerCardTxt.setHorizontalAlignment(JTextField.CENTER);
		computerCardTxt.setEnabled(false);
		add(computerCardTxt);

		// 컴퓨터 보유 금액 라벨
		lblComputerMoney = new JLabel();
		lblComputerMoney.setText("현재 보유 금액");
		lblComputerMoney.setBounds(lblComputerMoneyX, lblComputerMoneyY, lblComputerMoneyWidth, lblComputerMoneyHeight);
		lblComputerMoney.setForeground(Color.white);
		lblComputerMoney.setFont(new Font("돋움", Font.BOLD, 14));
		add(lblComputerMoney);

		// 컴퓨터 보유 금액 텍스트
		txtComputerMoney = new JTextField();
		df = new DecimalFormat("#,###");
		txtComputerMoney.setText(df.format(computerDefaultMoney));
		txtComputerMoney.setEnabled(false);
		txtComputerMoney.setBounds(txtComputerMoneyX, txtComputerMoneyY, txtComputerMoneyWidth, txtComputerMoneyHeight);
		txtComputerMoney.setFont(new Font("돋움", Font.BOLD, 14));
		txtComputerMoney.setBackground(Color.darkGray);
		txtComputerMoney.setHorizontalAlignment(JTextField.CENTER);
		add(txtComputerMoney);

		// "원"
		lblComputerMoneyUnit = new JLabel();
		lblComputerMoneyUnit.setText("원");
		lblComputerMoneyUnit.setBounds(lblComputerMoneyUnitX, lblComputerMoneyUnitY, lblComputerMoneyUnitWidth,
				lblComputerMoneyUnitHeight);
		lblComputerMoneyUnit.setFont(new Font("돋움", Font.BOLD, 14));
		lblComputerMoneyUnit.setForeground(Color.white);
		add(lblComputerMoneyUnit);
	}

	private void createPlayerComponent() {
		playerCardLbl = new JLabel[7];
		jpPlayerCard = new JLayeredPane();
		jpPlayerCard.setBorder(new LineBorder(Color.white, 3));
		jpPlayerCard.setBounds(jpPlayerCardX, jpPlayerCardY, jpPlayerWidth, jpPlayerHeight);
		add(jpPlayerCard);
		for (int i = 0; i < playerCardLbl.length; i++) {
			playerCardLbl[i] = new JLabel();
			playerCardLbl[i].setBounds(lblCardX + (lblCardGapX * i), lblCardY, lblCardWidth, lblCardHeight);
			jpPlayerCard.add(playerCardLbl[i], new Integer(i));
		}

		playerCardTxt = new JTextField();
		playerCardTxt.setBounds(txtPlayerCardX, txtPlayerCardY, txtCardWidth, txtCardHeight);
		playerCardTxt.setText("플레이어 카드");
		playerCardTxt.setHorizontalAlignment(JTextField.CENTER);
		playerCardTxt.setEnabled(false);
		add(playerCardTxt);

		// 플레이어 보유 금액 라벨
		lblPlayerMoney = new JLabel();
		lblPlayerMoney.setText("현재 보유 금액");
		lblPlayerMoney.setBounds(lblPlayerMoneyX, lblPlayerMoneyY, lblPlayerMoneyWidth, lblPlayerMoneyHeight);
		lblPlayerMoney.setForeground(Color.white);
		lblPlayerMoney.setFont(new Font("돋움", Font.BOLD, 14));
		add(lblPlayerMoney);

		// 플레이어 보유 금액 텍스트
		txtPlayerMoney = new JTextField();
		df = new DecimalFormat("#,###");
		txtPlayerMoney.setText(df.format(playerDefaultMoney));
		txtPlayerMoney.setEnabled(false);
		txtPlayerMoney.setBounds(txtPlayerMoneyX, txtPlayerMoneyY, txtPlayerMoneyWidth, txtPlayerMoneyHeight);
		txtPlayerMoney.setFont(new Font("돋움", Font.BOLD, 14));
		txtPlayerMoney.setBackground(Color.darkGray);
		txtPlayerMoney.setHorizontalAlignment(JTextField.CENTER);
		add(txtPlayerMoney);

		// "원"
		lblPlayerMoneyUnit = new JLabel();
		lblPlayerMoneyUnit.setText("원");
		lblPlayerMoneyUnit.setBounds(lblPlayerMoneyUnitX, lblPlayerMoneyUnitY, lblPlayerMoneyUnitHeight,
				lblPlayerMoneyUnitWidth);
		lblPlayerMoneyUnit.setFont(new Font("돋움", Font.BOLD, 14));
		lblPlayerMoneyUnit.setForeground(Color.white);
		add(lblPlayerMoneyUnit);

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
