package com.poker.game;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.poker.emun.CardHandType;
import com.poker.emun.NumberType;
import com.poker.emun.RaiseType;
import com.poker.emun.SuitType;
import com.poker.gui.GameGui;

public class GamePlay {
	private ArrayList<Card> cards;
	private ArrayList<Card> computerCard;
	private ArrayList<Card> playerCard;
	private GameGui gui;
	private JButton[] btnComputer;
	private JButton[] btnPlayer;
	private JButton[] btnRaise;
	private DecimalFormat df;

	public GamePlay(GameGui gui) {
		this.gui = gui;
		this.btnComputer = gui.getComputerBtn();
		this.btnPlayer = gui.getPlayerBtn();
		this.btnRaise = gui.getRaiseBtn();

		cards = new ArrayList<Card>();
		computerCard = new ArrayList<Card>();
		playerCard = new ArrayList<Card>();
		df = new DecimalFormat("#,###");
	}

	private void shuffle() {
		Collections.shuffle(cards);
	}

	private void createCards() {
		cards.clear();
		for (SuitType suits : SuitType.values()) {
			String suit = suits.name();
			for (NumberType numbers : NumberType.values()) {
				cards.add(new Card(suit, numbers.name(), numbers.getNumber()));
			}
		}
	}

	public void gamePlay() {
		JOptionPane.showMessageDialog(gui.getBoard(), "���� ����");

		// ī�� ����
		createCards();

		// ī�� ����
		shuffle();

		// ���� �ʱ�ȭ
		gui.resetBoard();

		// ���� ī�� �ʱ�ȭ
		computerCard.clear();
		playerCard.clear();

		// ī�� ������(Ƚ��)
		cardSpread(3);

		// �⺻ �� �� ����
		betDefaultMoney();

		// ���� ��ư Ȱ��ȭ
		btnRaiseEnable();
	}

	private void betDefaultMoney() {
		int betDefaultMoney = gui.getBetDefaultMoney();
		betPlayer(betDefaultMoney);
		betComputer(betDefaultMoney);
	}

	public void betComputer(int betMoney) {
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));

		plateMoney += betMoney;
		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtNotice("��ǻ�Ͱ� " + betMoney + "���� �����߽��ϴ�.");
	}

	private void betPlayer(int betMoney) {
		// �� ��, �÷��̾� ���� �ݾ�
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""));

		playerMoney -= betMoney;
		plateMoney += betMoney;

		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtPlayerMoney(df.format(playerMoney));
		gui.setTxtNotice("�÷��̾ " + betMoney + "���� �����߽��ϴ�.");
	}

	public void bet(String betType) {
		if (betType.equals(RaiseType.Half.value)) {
			cardSpread(1);
			int plateHalfMoney = Integer.parseInt(gui.getTxtPlate().replace(",", "")) / 2;
			betPlayer(plateHalfMoney);
			plateHalfMoney = Integer.parseInt(gui.getTxtPlate().replace(",", "")) / 2;
			betComputer(plateHalfMoney);
		} else if (betType.equals(RaiseType.Check.value)) {
			cardSpread(1);
		}
	}

	private void btnRaiseEnable() {
		for (int i = 0; i < btnRaise.length; i++) {
			btnRaise[i].setEnabled(true);
		}
	}

	private void btnImageInsert(JButton btn, String fileName) {
		btn.setIcon(new ImageIcon("img/" + fileName + ".png"));
	}

	private void btnImageInsert(JButton btn, String suit, int number) {
		btn.setIcon(new ImageIcon("img/" + suit + "-" + number + ".png"));
	}

	public void cardSpread(int count) {
		// ���� �ѷ��� ī���� index
		int spreadCard = getSpreadCardIndex();

		for (int i = spreadCard; i < spreadCard + count; i++) {
			Card computerCard = cards.get(0);
			this.computerCard.add(computerCard);

			// ��ǻ�ʹ� �ι�°, ����°, ������ ī�常 ����
			if (i == 1 || i == 2 || i == (btnPlayer.length - 1)) {
				btnImageInsert(btnComputer[i], computerCard.getSuit(), computerCard.getNumberOrder());
			} else {
				btnImageInsert(btnComputer[i], "back");
			}
			btnComputer[i].setName(computerCard.getSuit() + "-" + computerCard.getNumberOrder());
			cards.remove(0);

			Card playerCard = cards.get(0);
			this.playerCard.add(playerCard);
			btnImageInsert(btnPlayer[i], playerCard.getSuit(), playerCard.getNumberOrder());
			btnPlayer[i].setName(playerCard.getSuit() + "-" + playerCard.getNumberOrder());
			cards.remove(0);
		}
	}

	public void lastCardCheck() {
		int spreadCard = getSpreadCardIndex();
		if (spreadCard == -1) {
			gui.setTxtNotice("������ ī�带 ���Ƚ��ϴ�.");

			// ������ ��ǻ�� ī�� �̹��� ���̰�
			computerCardOpen();

			// ���� ��ư �ʱ�ȭ
			gui.resetRaiseBtn();

			// ���� Ȯ��
			cardHandCheck();

			// �÷��̾� �⺻ ���� �ݾ��� �ִ��� Ȯ��
			if (playMoneyCheck(gui.getBetDefaultMoney())) {
				// ���� ���� ����
				gamePlay();
			} else {
				JOptionPane.showMessageDialog(gui.getBoard(), "�� �̻� ������ �Ͻ� �� �����ϴ�.");
			}
		} else {
			gui.setTxtNotice("ī�带 �����ϴ�.");
		}
	}

	private boolean playMoneyCheck(int betMoney) {
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""));
		if (playerMoney < betMoney) {
			return false;
		}
		return true;
	}

	private void computerCardOpen() {
		for (JButton btn : btnComputer) {
			btn.setIcon(new ImageIcon("img/" + btn.getName() + ".png"));
		}
	}

	private void cardHandCheck() {
		CardHandType computerHandType = gui.rankCheck(computerCard);
		CardHandType playerHandType = gui.rankCheck(playerCard);

		String winner = "";

		if (computerHandType.getNumber() == playerHandType.getNumber()) {
			Card computerBestHand = gui.tieCheck(computerCard, computerHandType);
			Card playerBestHand = gui.tieCheck(playerCard, playerHandType);
			winner = "���º�";
		}

		if (computerHandType.getNumber() > playerHandType.getNumber()) {
			winner = "��ǻ�� ��";
		} else if (computerHandType.getNumber() < playerHandType.getNumber()) {
			winner = "�÷��̾� ��";
			takeMoney();
		}

		JOptionPane.showMessageDialog(gui.getBoard(),
				"��ǻ�� ��:" + computerHandType.name() + "\n�÷��̾� ��:" + playerHandType.name() + "\n" + winner);
		gui.setTxtNotice("[" + winner + "]");
	}

	private int getSpreadCardIndex() {
		for (int i = 0; i < btnComputer.length; i++) {
			if (btnComputer[i].getName() == null || btnComputer[i].getName().equals("")) {
				return i;
			}
		}

		return -1;
	}

	private void takeMoney() {
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""));
		gui.setTxtPlayerMoney(df.format(plateMoney + playerMoney));
	}

	public void raiseHalf() {
		bet(RaiseType.Half.value);
		lastCardCheck();
	}

	public void raiseDie() {
		gamePlay();
	}

	public void raiseCheck() {
		gui.bet(RaiseType.Check.value);
		gui.lastCardCheck();
	}
}
