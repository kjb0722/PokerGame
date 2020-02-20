package com.poker.gui;

import java.awt.Color;
import java.awt.Container;
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
	private int computerDefaultMoney = 1000000;
	private int betDefaultMoney = 1000;
	
	private final int panelWidth = GameGui.WIDTH - 250; // �г�
	private final int panelHeight = GameGui.HEIGHT;

	private final int lblCardWidth = 100; // ī��
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

	private final int txtComputerCardX = 220; // ī�� ǥ��
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

	private final int lblComputerMoneyWidth = 200; // ��ǻ�� ��
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

	private final int lblPlayerMoneyWidth = 200; // �÷��̾� ��
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

	private final int lblPlateWidth = 100; // �� ��
	private final int lblPlateHeight = 100;
	private final int lblPlateX = 200;
	private final int lblPlateY = 250;
	private final int txtPlateX = 300;
	private final int txtPlateY = 250;
	private final int txtPlateWidth = 200;
	private final int txtPlateHeight = 100;

	private final int lblHalfWidth = 100; // ���� ��
	private final int lblHalfHeight = 100;
	private final int lblHalfX = 15;
	private final int lblHalfY = 330;
	private final int txtHalfWidth = 80; // ���� �ݾ�
	private final int txtHalfHeight = 20;
	private final int txtHalfX = 80;
	private final int txtHalfY = 370;
	
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
	private JLabel lblHalf;
	private JTextField txtHalf;

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

		resetPlayer();

		createBtnListener();
		createComputerComponent();
		createPlayerComponent();
		createMoneyScore();
	}

	private void createMoneyScore() {
		// �� �� ��
		lblPlate = new JLabel();
		lblPlate.setText("�� ��:");
		lblPlate.setBounds(lblPlateX, lblPlateY, lblPlateWidth, lblPlateHeight);
		lblPlate.setForeground(Color.white);
		lblPlate.setFont(new Font("����", Font.BOLD, 14));
		add(lblPlate);

		// �� �� �ؽ�Ʈ
		txtPlate = new JTextField();
		txtPlate.setText(df.format(0));
		txtPlate.setEnabled(false);
		txtPlate.setBounds(txtPlateX, txtPlateY, txtPlateWidth, txtPlateHeight);
		txtPlate.setFont(new Font("����", Font.BOLD, 14));
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
		computerCardTxt.setText("��ǻ�� ī��");
		computerCardTxt.setHorizontalAlignment(JTextField.CENTER);
		computerCardTxt.setEnabled(false);
		add(computerCardTxt);

		// ��ǻ�� ���� �ݾ� ��
		lblComputerMoney = new JLabel();
		lblComputerMoney.setText("���� ���� �ݾ�");
		lblComputerMoney.setBounds(lblComputerMoneyX, lblComputerMoneyY, lblComputerMoneyWidth, lblComputerMoneyHeight);
		lblComputerMoney.setForeground(Color.white);
		lblComputerMoney.setFont(new Font("����", Font.BOLD, 14));
		add(lblComputerMoney);

		// ��ǻ�� ���� �ݾ� �ؽ�Ʈ
		txtComputerMoney = new JTextField();
		df = new DecimalFormat("#,###");
		txtComputerMoney.setText(df.format(computerDefaultMoney));
		txtComputerMoney.setEnabled(false);
		txtComputerMoney.setBounds(txtComputerMoneyX, txtComputerMoneyY, txtComputerMoneyWidth, txtComputerMoneyHeight);
		txtComputerMoney.setFont(new Font("����", Font.BOLD, 14));
		txtComputerMoney.setBackground(Color.darkGray);
		txtComputerMoney.setHorizontalAlignment(JTextField.CENTER);
		add(txtComputerMoney);

		// "��"
		lblComputerMoneyUnit = new JLabel();
		lblComputerMoneyUnit.setText("��");
		lblComputerMoneyUnit.setBounds(lblComputerMoneyUnitX, lblComputerMoneyUnitY, lblComputerMoneyUnitWidth,
				lblComputerMoneyUnitHeight);
		lblComputerMoneyUnit.setFont(new Font("����", Font.BOLD, 14));
		lblComputerMoneyUnit.setForeground(Color.white);
		add(lblComputerMoneyUnit);

		// ���� �ݾ�
		lblHalf = new JLabel();
		lblHalf.setText("���� �ݾ�:");
		lblHalf.setBounds(lblHalfX, lblHalfY, lblHalfWidth, lblHalfHeight);
		lblHalf.setForeground(Color.white);
		lblHalf.setFont(new Font("����", Font.BOLD, 12));
		add(lblHalf);
		txtHalf = new JTextField();
		txtHalf.setText("0");
		txtHalf.setBounds(txtHalfX, txtHalfY, txtHalfWidth, txtHalfHeight);
		txtHalf.setHorizontalAlignment(JTextField.CENTER);
		txtHalf.setEditable(false);
		add(txtHalf);
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
		playerCardTxt.setText("�÷��̾� ī��");
		playerCardTxt.setHorizontalAlignment(JTextField.CENTER);
		playerCardTxt.setEnabled(false);
		add(playerCardTxt);

		// �÷��̾� ���� �ݾ� ��
		lblPlayerMoney = new JLabel();
		lblPlayerMoney.setText("���� ���� �ݾ�");
		lblPlayerMoney.setBounds(lblPlayerMoneyX, lblPlayerMoneyY, lblPlayerMoneyWidth, lblPlayerMoneyHeight);
		lblPlayerMoney.setForeground(Color.white);
		lblPlayerMoney.setFont(new Font("����", Font.BOLD, 14));
		add(lblPlayerMoney);

		// �÷��̾� ���� �ݾ� �ؽ�Ʈ
		txtPlayerMoney = new JTextField();
		df = new DecimalFormat("#,###");
		txtPlayerMoney.setText(df.format(playerDefaultMoney));
		txtPlayerMoney.setEnabled(false);
		txtPlayerMoney.setBounds(txtPlayerMoneyX, txtPlayerMoneyY, txtPlayerMoneyWidth, txtPlayerMoneyHeight);
		txtPlayerMoney.setFont(new Font("����", Font.BOLD, 14));
		txtPlayerMoney.setBackground(Color.darkGray);
		txtPlayerMoney.setHorizontalAlignment(JTextField.CENTER);
		add(txtPlayerMoney);

		// "��"
		lblPlayerMoneyUnit = new JLabel();
		lblPlayerMoneyUnit.setText("��");
		lblPlayerMoneyUnit.setBounds(lblPlayerMoneyUnitX, lblPlayerMoneyUnitY, lblPlayerMoneyUnitHeight,
				lblPlayerMoneyUnitWidth);
		lblPlayerMoneyUnit.setFont(new Font("����", Font.BOLD, 14));
		lblPlayerMoneyUnit.setForeground(Color.white);
		add(lblPlayerMoneyUnit);

//		����	��ü �ǵ��� ����, �� 50% �ݾ��� �����մϴ�.
//		����	���� �������� �ʰ�, �̹� ���� �����մϴ�.
//		üũ	�Ӵϸ� �������� �ʰ� ���� ī�带 �޽��ϴ�.
//		Half("����"), Die("����"), Check("üũ");
//			0			1			2
		raiseBtn = new JButton[3];
		for (int i = 0; i < raiseBtn.length; i++) {
			String btnName = "";
			raiseBtn[i] = new JButton();
			if (i == RaiseType.Half.ordinal()) {
				btnName = RaiseType.Half.getValue();
				raiseBtn[i].setName(RaiseType.Half.name());
			} else if (i == RaiseType.Die.ordinal()) {
				btnName = RaiseType.Die.getValue();
				raiseBtn[i].setName(RaiseType.Die.name());
			} else if (i == RaiseType.Check.ordinal()) {
				btnName = RaiseType.Check.getValue();
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
}
