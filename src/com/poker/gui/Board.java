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
import com.poker.player.Player;

public class Board extends JPanel {
	public static final String PLAYER_NAME = "PLAYER1";
	public static final String COMPUTER_NAME = "COMPUTER";

	private int playerDefaultMoney = 100000;
	private int computerDefaultMoney = 100000;
	private int betDefaultMoney = 1000;

	private final int panelWidth = GameGui.WIDTH - 400; // 패널
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

	private final int txtComputerCardX = 30; // 카드 표시
	private final int txtComputerCardY = 5;
	private final int txtCardWidth = 90;
	private final int txtCardHeight = 20;
	private final int txtPlayerCardX = 30;
	private final int txtPlayerCardY = panelHeight - 225;
	private final int btnRaiseX = 80;
	private final int btnRaiseGapX = 100;
	private final int btnRaiseY = panelHeight / 2 + 50;
	private final int btnRaiseWidth = 80;
	private final int btnRaiseHeight = 30;

	private final int lblComputerRankX = 30; // 컴퓨터 족보
	private final int lblComputerRankY = 198;
	private final int lblComputerRankWidth = 250;
	private final int lblComputerRankHeight = 50;

	private final int lblPlayerRankX = 260; // 플레이어 족보
	private final int lblPlayerRankY = 453;
	private final int lblPlayerRankWidth = 250;
	private final int lblPlayerRankHeight = 50;

	private final int lblComputerMoneyWidth = 200; // 컴퓨터 돈
	private final int lblComputerMoneyHeight = 50;
	private final int lblComputerMoneyX = 550;
	private final int lblComputerMoneyY = 50;
	private final int txtComputerMoneyX = 550;
	private final int txtComputerMoneyY = 90;
	private final int txtComputerMoneyWidth = 100;
	private final int txtComputerMoneyHeight = 30;
	private final int lblComputerMoneyUnitX = 660;
	private final int lblComputerMoneyUnitY = 80;
	private final int lblComputerMoneyUnitWidth = 50;
	private final int lblComputerMoneyUnitHeight = 50;
	private final int lblComputerBetMoneyX = 550;
	private final int lblComputerBetMoneyY = 90;
	private final int lblComputerBetMoneyWidth = 100;
	private final int lblComputerBetMoneyHeight = 100;

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
	private final int lblPlayerBetMoneyX = 550;
	private final int lblPlayerBetMoneyY = 480;
	private final int lblPlayerBetMoneyWidth = 100;
	private final int lblPlayerBetMoneyHeight = 100;

	private final int lblPlateWidth = 100; // 판 돈
	private final int lblPlateHeight = 100;
	private final int lblPlateX = 250;
	private final int lblPlateY = 250;
	private final int txtPlateX = 300;
	private final int txtPlateY = 250;
	private final int txtPlateWidth = 200;
	private final int txtPlateHeight = 100;

	private final int lblHalfWidth = 100; // 하프 라벨
	private final int lblHalfHeight = 100;
	private final int lblHalfX = 15;
	private final int lblHalfY = 330;
	private final int txtHalfWidth = 80; // 하프 금액
	private final int txtHalfHeight = 20;
	private final int txtHalfX = 80;
	private final int txtHalfY = 370;

	private final int lblComputerResultX = 520; // 컴퓨터 게임 결과
	private final int lblComputerResultY = 170;
	private final int lblComputerResultWidth = 170;
	private final int lblComputerResultHeight = 70;
	private final int lblPlayerResultX = 520; // 플레이어 게임 결과
	private final int lblPlayerResultY = 430;
	private final int lblPlayerResultWidth = 170;
	private final int lblPlayerResultHeight = 70;

	private JLabel[] lblComputerCard;
	private JLabel[] lblPlayerCard;
	private JTextField txtComputerCard;
	private JTextField txtPlayerCard;
	private JButton[] btnRaise;
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
	private JLabel lblHalf;
	private JTextField txtHalf;
	private JLabel lblComputerRank;
	private JLabel lblPlayerRank;
	private JLabel lblComputerBetMoney;
	private JLabel lblPlayerBetMoney;
	private JLabel lblComputerResult;
	private JLabel lblPlayerResult;

	private GameGui gui;
	private Player[] player;

	public Board(GameGui gui) {
		this.gui = gui;
		init();
	}

	public int getComputerDefaultMoney() {
		return computerDefaultMoney;
	}

	public int getPlayerDefaultMoney() {
		return playerDefaultMoney;
	}

	public Player[] getPlayer() {
		return player;
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

	public JLabel[] getLblComputerCard() {
		return lblComputerCard;
	}

	public JLabel[] getLblPlayerCard() {
		return lblPlayerCard;
	}

	public void resetPlayerMoney() {
		this.txtPlayerMoney.setText(df.format(playerDefaultMoney));
		this.txtComputerMoney.setText(df.format(computerDefaultMoney));
	}

	public void resetBoard() {
		resetCardLbl();
		resetRank();
		resetResult();
		resetCardTxt();
		txtPlate.setText(("0"));
	}

	private void resetCardTxt() {
		txtComputerCard.setBackground(Color.white);
		txtPlayerCard.setBackground(Color.white);
	}

	public void resetResult() {
		lblPlayerResult.setText("");
		lblComputerResult.setText("");
	}

	private void resetRank() {
		lblPlayerRank.setText("");
		lblComputerRank.setText("");
	}

	public void resetCardLbl() {
		for (int i = 0; i < lblComputerCard.length; i++) {
			lblComputerCard[i].setName("");
			lblComputerCard[i].setIcon(null);
		}
		for (int i = 0; i < lblPlayerCard.length; i++) {
			lblPlayerCard[i].setName("");
			lblPlayerCard[i].setIcon(null);
		}
	}

	public void btnRaiseEnable(boolean enable) {
		for(int i=0;i<btnRaise.length;i++) {
			btnRaise[i].setEnabled(enable);
		}
	}

	private void init() {
		setBounds(0, 0, panelWidth, panelHeight);
		setBackground(Color.getHSBColor(60, 91, 0));
		setLayout(null);
		df = new DecimalFormat("#,###");

		resetPlayer();

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
		txtPlate.setFont(new Font("돋움", Font.BOLD, 20));
		txtPlate.setBackground(Color.darkGray);
		txtPlate.setHorizontalAlignment(JTextField.CENTER);
		add(txtPlate);
	}

	private void createComputerComponent() {
		lblComputerCard = new JLabel[7];
		jpComputerCard = new JLayeredPane();
		jpComputerCard.setBorder(new LineBorder(Color.white, 3));
		jpComputerCard.setBounds(jpComputerCardX, jpComputerCardY, jpComputerWidth, jpComputerHeight);
		add(jpComputerCard);
		for (int i = 0; i < lblComputerCard.length; i++) {
			lblComputerCard[i] = new JLabel();
			lblComputerCard[i].setBounds(lblCardX + (lblCardGapX * i), lblCardY, lblCardWidth, lblCardHeight);
			jpComputerCard.add(lblComputerCard[i], new Integer(i));
		}

		txtComputerCard = new JTextField();
		txtComputerCard.setBounds(txtComputerCardX, txtComputerCardY, txtCardWidth, txtCardHeight);
		txtComputerCard.setText("컴퓨터 카드");
		txtComputerCard.setHorizontalAlignment(JTextField.CENTER);
		txtComputerCard.setFont(new Font("돋움", Font.BOLD, 12));
		txtComputerCard.setEditable(false);
		add(txtComputerCard);

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

		// 전 베팅 금액
		lblComputerBetMoney = new JLabel();
		lblComputerBetMoney.setBounds(lblComputerBetMoneyX, lblComputerBetMoneyY, lblComputerBetMoneyWidth,
				lblComputerBetMoneyHeight);
		lblComputerBetMoney.setFont(new Font("돋움", Font.BOLD, 14));
		lblComputerBetMoney.setForeground(Color.white);
		add(lblComputerBetMoney);

		// 하프 금액
		lblHalf = new JLabel();
		lblHalf.setText("하프 금액:");
		lblHalf.setBounds(lblHalfX, lblHalfY, lblHalfWidth, lblHalfHeight);
		lblHalf.setForeground(Color.white);
		lblHalf.setFont(new Font("돋움", Font.BOLD, 12));
		add(lblHalf);
		txtHalf = new JTextField();
		txtHalf.setText("0");
		txtHalf.setBounds(txtHalfX, txtHalfY, txtHalfWidth, txtHalfHeight);
		txtHalf.setHorizontalAlignment(JTextField.CENTER);
		txtHalf.setEditable(false);
		add(txtHalf);

		// 족보
		lblComputerRank = new JLabel();
		lblComputerRank.setBounds(lblComputerRankX, lblComputerRankY, lblComputerRankWidth, lblComputerRankHeight);
		lblComputerRank.setFont(new Font("seria", Font.BOLD, 20));
		lblComputerRank.setForeground(Color.white);
		lblComputerRank.setBorder(new LineBorder(Color.white, 2));
		lblComputerRank.setHorizontalAlignment(JTextField.CENTER);
		add(lblComputerRank);

		// 게임 결과
		lblComputerResult = new JLabel();
		lblComputerResult.setBounds(lblComputerResultX, lblComputerResultY, lblComputerResultWidth,
				lblComputerResultHeight);
		lblComputerResult.setFont(new Font("seria", Font.BOLD, 20));
		lblComputerResult.setForeground(Color.red);
		lblComputerResult.setHorizontalAlignment(JTextField.CENTER);
		lblComputerResult.setBorder(new LineBorder(Color.white));
		add(lblComputerResult);
	}

	public JLabel getLblHalf() {
		return lblHalf;
	}

	public void setLblHalf(JLabel lblHalf) {
		this.lblHalf = lblHalf;
	}

	public JTextField getTxtHalf() {
		return txtHalf;
	}

	public void setTxtHalf(String money) {
		txtHalf.setText(money);
	}

	private void createPlayerComponent() {
		lblPlayerCard = new JLabel[7];
		jpPlayerCard = new JLayeredPane();
		jpPlayerCard.setBorder(new LineBorder(Color.white, 3));
		jpPlayerCard.setBounds(jpPlayerCardX, jpPlayerCardY, jpPlayerWidth, jpPlayerHeight);
		add(jpPlayerCard);
		for (int i = 0; i < lblPlayerCard.length; i++) {
			lblPlayerCard[i] = new JLabel();
			lblPlayerCard[i].setBounds(lblCardX + (lblCardGapX * i), lblCardY, lblCardWidth, lblCardHeight);
			jpPlayerCard.add(lblPlayerCard[i], new Integer(i));
		}

		txtPlayerCard = new JTextField();
		txtPlayerCard.setBounds(txtPlayerCardX, txtPlayerCardY, txtCardWidth, txtCardHeight);
		txtPlayerCard.setText("플레이어 카드");
		txtPlayerCard.setHorizontalAlignment(JTextField.CENTER);
		txtPlayerCard.setFont(new Font("돋움", Font.BOLD, 12));
		txtPlayerCard.setEditable(false);
		add(txtPlayerCard);

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

		// 전 베팅 금액
		lblPlayerBetMoney = new JLabel();
		lblPlayerBetMoney.setBounds(lblPlayerBetMoneyX, lblPlayerBetMoneyY, lblPlayerBetMoneyWidth,
				lblPlayerBetMoneyHeight);
		lblPlayerBetMoney.setFont(new Font("돋움", Font.BOLD, 14));
		lblPlayerBetMoney.setForeground(Color.white);
		add(lblPlayerBetMoney);

		// 족보
		lblPlayerRank = new JLabel();
		lblPlayerRank.setBounds(lblPlayerRankX, lblPlayerRankY, lblPlayerRankWidth, lblPlayerRankHeight);
		lblPlayerRank.setFont(new Font("seria", Font.BOLD, 20));
		lblPlayerRank.setForeground(Color.white);
		lblPlayerRank.setBorder(new LineBorder(Color.white, 2));
		lblPlayerRank.setHorizontalAlignment(JTextField.CENTER);
		add(lblPlayerRank);

		// 게임 결과
		lblPlayerResult = new JLabel();
		lblPlayerResult.setBounds(lblPlayerResultX, lblPlayerResultY, lblPlayerResultWidth, lblPlayerResultHeight);
		lblPlayerResult.setFont(new Font("seria", Font.BOLD, 20));
		lblPlayerResult.setForeground(Color.red);
		lblPlayerResult.setHorizontalAlignment(JTextField.CENTER);
		lblPlayerResult.setBorder(new LineBorder(Color.white));
		add(lblPlayerResult);

//		하프	전체 판돈의 절반, 즉 50% 금액을 베팅합니다.
//		다이	새로 베팅하지 않고, 이번 판을 포기합니다.
//		체크	머니를 베팅하지 않고 다음 카드를 받습니다.
//		Half("하프"), Die("다이"), Check("체크");
//			0			1			2
		btnRaise = new JButton[3];
		for (int i = 0; i < btnRaise.length; i++) {
			String btnName = "";
			btnRaise[i] = new JButton();
			if (i == RaiseType.Half.ordinal()) {
				btnName = RaiseType.Half.getValue();
			} else if (i == RaiseType.Die.ordinal()) {
				btnName = RaiseType.Die.getValue();
			} else if (i == RaiseType.Check.ordinal()) {
				btnName = RaiseType.Check.getValue();
			}

			btnRaise[i].setText(btnName);
			btnRaise[i].setBounds(btnRaiseX + (i * btnRaiseGapX), btnRaiseY, btnRaiseWidth, btnRaiseHeight);
			btnRaise[i].setEnabled(false);
			btnRaise[i].addActionListener(listener);
			add(btnRaise[i]);
		}
	}

	private void createBtnListener() {
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String raiseType = e.getActionCommand();
				if (raiseType.equals(RaiseType.Half.getValue())) {
					gui.raiseHalf();
				} else if (raiseType.equals(RaiseType.Die.getValue())) {
					gui.raiseDie();
				} else if (raiseType.equals(RaiseType.Check.getValue())) {
					gui.raiseCheck();
				}
			}
		};
	}

	public void resetPlayer() {
		player = new Player[2];
		player[0] = new Player(PLAYER_NAME, playerDefaultMoney);
		player[1] = new Player(COMPUTER_NAME, computerDefaultMoney);
	}

	public void setMoneyInit() {
		txtPlate.setText("0");
		txtHalf.setText("0");
	}

	public void setlblPlayerRank(String text) {
		lblPlayerRank.setText(text);
	}

	public void setLblComputerRank(String text) {
		lblComputerRank.setText(text);
	}

	public void turnChange(Player player) {
		if (player.getName().equals(PLAYER_NAME)) {
			jpPlayerCard.setBorder(new LineBorder(Color.green, 3));
			lblPlayerRank.setBorder(new LineBorder(Color.green));
			txtPlayerCard.setBackground(Color.green);
			jpComputerCard.setBorder(new LineBorder(Color.white, 3));
			lblComputerRank.setBorder(new LineBorder(Color.white));
			txtComputerCard.setBackground(Color.white);
		} else {
			jpPlayerCard.setBorder(new LineBorder(Color.white, 3));
			lblPlayerRank.setBorder(new LineBorder(Color.white));
			txtPlayerCard.setBackground(Color.white);
			jpComputerCard.setBorder(new LineBorder(Color.green, 3));
			lblComputerRank.setBorder(new LineBorder(Color.green));
			txtComputerCard.setBackground(Color.green);
		}
	}
	
	public void winnerDisplay(Player player) {
		if (player.getName().equals(PLAYER_NAME)) {
			jpPlayerCard.setBorder(new LineBorder(Color.red, 3));
			lblPlayerRank.setBorder(new LineBorder(Color.red));
			txtPlayerCard.setBackground(Color.red);
		} else {
			jpComputerCard.setBorder(new LineBorder(Color.red, 3));
			lblComputerRank.setBorder(new LineBorder(Color.red));
			txtComputerCard.setBackground(Color.red);
		}
	}

	public void resetTurn() {
		jpPlayerCard.setBorder(new LineBorder(Color.white, 3));
		lblPlayerRank.setBorder(new LineBorder(Color.white));
		jpComputerCard.setBorder(new LineBorder(Color.white, 3));
		lblComputerRank.setBorder(new LineBorder(Color.white));
	}

	public void setLblPlayerBetMoney(String betMoney) {
		lblPlayerBetMoney.setText(betMoney);
	}

	public void setLblComputerBetMoney(String betMoney) {
		lblComputerBetMoney.setText(betMoney);
	}

	public void setLblPlayerResult(String text) {
		lblPlayerResult.setText(text);
	}

	public void setLblComputerResult(String text) {
		lblComputerResult.setText(text);
	}
}
