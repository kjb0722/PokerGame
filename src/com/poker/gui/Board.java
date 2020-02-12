package com.poker.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private final int labelComputerCardX = (panelWidth / 2) - 40;
	private final int labelComputerCardY = 5;
	private final int labelCardWidth = 90;
	private final int labelCardHeight = 20;
	private final int labelPlayCardX = (panelWidth / 2) - 40;
	private final int labelPlayCardY = panelHeight - 225;
	private final int btnRaiseX = 80;
	private final int btnRaiseGapX = 100;
	private final int btnRaiseY = panelHeight / 2 + 50;
	private final int btnRaiseWidth = 80;
	private final int btnRaiseHeight = 30;
	private final int labelMoneyWidth = 200;
	private final int labelMoneyHeight = 200;
	private final int labelMoneyX = 220;
	private final int labelMoneyY = 250;
	private final int txtMoneyX = 400;
	private final int txtMoneyY = 335;
	private final int txtMoneyWidth = 150;
	private final int txtMoneyHeight = 30;

	private GameGui gui;
	private JButton[] computerBtn;
	private JButton[] playerBtn;
	private JTextField computerCardLabel;
	private JTextField playerCardLabel;
	private JButton[] raiseBtn;
	private JLabel lblMoney;
	private JTextField txtMoney;

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
		setBounds(0, 0, panelWidth, panelHeight);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		CreateComputerComponent();
		CreatePlayerComponent();
		CreateMoneyScore();
	}

	private void CreateMoneyScore() {
		lblMoney = new JLabel();
		lblMoney.setText("���� ���� �ݾ� : ");
		lblMoney.setBounds(labelMoneyX, labelMoneyY, labelMoneyWidth, labelMoneyHeight);
		lblMoney.setForeground(Color.white);
		lblMoney.setFont(new Font("����", Font.BOLD, 20));
		add(lblMoney);

		txtMoney = new JTextField();
		txtMoney.setText("0");
		txtMoney.setEnabled(false);
		txtMoney.setBounds(txtMoneyX, txtMoneyY, txtMoneyWidth, txtMoneyHeight);
		txtMoney.setEditable(false);
		txtMoney.setFont(new Font("����", Font.BOLD, 20));
		txtMoney.setBackground(Color.darkGray);
		txtMoney.setHorizontalAlignment(JTextField.CENTER);
		add(txtMoney);
	}

	private void CreateComputerComponent() {
		computerBtn = new JButton[7];

		for (int i = 0; i < computerBtn.length; i++) {
			computerBtn[i] = new JButton();
			computerBtn[i].setBounds(btnComputerX + (btnWidth * i), btnComputerY, btnWidth, btnHeight);
			computerBtn[i].setToolTipText("");
			add(computerBtn[i]);
		}

		computerCardLabel = new JTextField();
		computerCardLabel.setBounds(labelComputerCardX, labelComputerCardY, labelCardWidth, labelCardHeight);
		computerCardLabel.setText("��ǻ�� ī��");
		computerCardLabel.setHorizontalAlignment(JTextField.CENTER);
		computerCardLabel.setEnabled(false);
		add(computerCardLabel);
	}

	private void CreatePlayerComponent() {
		playerBtn = new JButton[7];
		for (int i = 0; i < playerBtn.length; i++) {
			playerBtn[i] = new JButton();
			playerBtn[i].setBounds(btnPlayerX + (btnWidth * i), btnPlayerY, btnWidth, btnHeight);
			playerBtn[i].setToolTipText("");
			add(playerBtn[i]);
		}

		playerCardLabel = new JTextField();
		playerCardLabel.setBounds(labelPlayCardX, labelPlayCardY, labelCardWidth, labelCardHeight);
		playerCardLabel.setText("�÷��̾� ī��");
		playerCardLabel.setHorizontalAlignment(JTextField.CENTER);
		playerCardLabel.setEnabled(false);
		add(playerCardLabel);

//		��	�� ����� ���� �ݾװ� ������ ���� �ݾ��� �̴ϴ�.
//		��	��	�⺻ �ǵ��� �����մϴ�.(������ ����)
//		����	�ջ���� ���� �� �ݾ��� 2�踦 �����մϴ�.
//		����	��ü �ǵ��� ����, �� 50% �ݾ��� �����մϴ�.
//		����	���� �������� �ʰ�, �̹� ���� �����մϴ�.
//		üũ	�Ӵϸ� �������� �ʰ� ���� ī�带 �޽��ϴ�.(������ ����)
//		Call("��"), Bbing("��"), Tadang("����"), Half("����"), Die("����"), Check("üũ");
//			0			1			2				3			4			5
		raiseBtn = new JButton[6];
		for (int i = 0; i < raiseBtn.length; i++) {
			String btnName = "";
			raiseBtn[i] = new JButton();
			if (i == RaiseType.Call.ordinal()) {
				btnName = RaiseType.Call.value;
				raiseBtn[i].setName(RaiseType.Call.name());
			} else if (i == RaiseType.Bbing.ordinal()) {
				btnName = RaiseType.Bbing.value;
				raiseBtn[i].setName(RaiseType.Bbing.name());
			} else if (i == RaiseType.Tadang.ordinal()) {
				btnName = RaiseType.Tadang.value;
				raiseBtn[i].setName(RaiseType.Tadang.name());
			} else if (i == RaiseType.Half.ordinal()) {
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
			raiseBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand() == RaiseType.Die.value) {
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
