package com.poker.game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.poker.emun.CardHandType;
import com.poker.emun.NumberType;
import com.poker.emun.RaiseType;
import com.poker.emun.SuitType;
import com.poker.gui.GameGui;
import com.poker.player.Player;

public class GamePlay {
	private ArrayList<Card> cards;
	private ArrayList<Card> computerCard;
	private ArrayList<Card> playerCard;
	private GameGui gui;
	private JLabel[] computerCardLbl;
	private JLabel[] playerCardLbl;
	private JButton[] btnRaise;
	private DecimalFormat df;
	private Player[] player;

	public GamePlay(GameGui gui) {
		this.gui = gui;
		this.computerCardLbl = gui.getComputerCardLbl();
		this.playerCardLbl = gui.getPlayerCardLbl();
		this.btnRaise = gui.getRaiseBtn();

		cards = new ArrayList<Card>();
		computerCard = new ArrayList<Card>();
		playerCard = new ArrayList<Card>();
		df = new DecimalFormat("#,###");
		
		
		player[0] = new Player("player1", gui.getBetDefaultMoney());
		player[1] = new Player("player1", gui.getBetDefaultMoney());
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

		// ���� �ʱ�ȭ
		gui.resetBoard();

		// ���� ī�� �ʱ�ȭ
		computerCard.clear();
		playerCard.clear();

		// �⺻ �� �� ����
		betDefaultMoney();

		// ī�� ����
		createCards();

		// ī�� ����
		shuffle();

		// ī�� ������(Ƚ��)
		cardSpread(3);

		// ���� ��ư Ȱ��ȭ
		btnRaiseEnable();
	}

	private void betDefaultMoney() {
		int betDefaultMoney = gui.getBetDefaultMoney();
		betPlayer(betDefaultMoney);
		betComputer(betDefaultMoney);
	}

	public boolean betComputer(int betMoney) {
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int computerMoney = Integer.parseInt(gui.getTxtComputerMoney().replace(",", ""));

		if (computerMoney < 0 || computerMoney - betMoney < 0) {
			return false;
		}

		// 100�� ���� �ݾ��� ������
		int change = betMoney % 100;
		betMoney -= change;
		computerMoney += change;

		computerMoney -= betMoney;
		plateMoney += betMoney;

		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtComputerMoney(df.format(computerMoney));
		gui.setTxtNotice("��ǻ�Ͱ� " + betMoney + "���� �����߽��ϴ�.");

		return true;
	}

	private boolean betPlayer(int betMoney) {
		// �� ��, �÷��̾� ���� �ݾ�
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""));

		if (playerMoney < 0 || playerMoney - betMoney < 0) {
			JOptionPane.showMessageDialog(gui.getBoard(), "���� �����մϴ�.");
			return false;
		}

		// 100�� ���� �ݾ��� ������
		int change = betMoney % 100;
		betMoney -= change;
		playerMoney += change;

		playerMoney -= betMoney;
		plateMoney += betMoney;

		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtPlayerMoney(df.format(playerMoney));
		gui.setTxtNotice("�÷��̾ " + betMoney + "���� �����߽��ϴ�.");

		return true;
	}

	public void bet(String betType) {
		if (betType.equals(RaiseType.Half.value)) {
			int plateHalfMoney = Integer.parseInt(gui.getTxtPlate().replace(",", "")) / 2;
			if (!betPlayer(plateHalfMoney)) {
				return;
			}
			plateHalfMoney = Integer.parseInt(gui.getTxtPlate().replace(",", "")) / 2;
			betComputer(plateHalfMoney);
			cardSpread(1);
		} else if (betType.equals(RaiseType.Check.value)) {
			cardSpread(1);
		}
	}

	private void btnRaiseEnable() {
		for (int i = 0; i < btnRaise.length; i++) {
			btnRaise[i].setEnabled(true);
		}
	}

	private void btnImageInsert(JLabel lbl, String fileName) {
		lbl.setIcon(new ImageIcon("img/" + fileName + ".png"));
	}

	private void btnImageInsert(JLabel lbl, String suit, int number) {
		lbl.setIcon(new ImageIcon("img/" + suit + "-" + number + ".png"));
	}

	public void cardSpread(int count) {
		// ���� �ѷ��� ī���� index
		int spreadCard = getSpreadCardIndex();

		gui.setTxtNotice("ī�带 " + count + "�� �����ϴ�.");

		for (int i = spreadCard; i < spreadCard + count; i++) {
			Card computerCard = cards.get(0);
			this.computerCard.add(computerCard);

			// ��ǻ�ʹ� �ι�°, ����°, ������ ī�常 ����
			if (i == 1 || i == 2 || i == (playerCardLbl.length - 1)) {
				btnImageInsert(computerCardLbl[i], computerCard.getSuit(), computerCard.getNumberOrder());
			} else {
				btnImageInsert(computerCardLbl[i], "back");
			}
			computerCardLbl[i].setName(computerCard.getSuit() + "-" + computerCard.getNumberOrder());
			cards.remove(0);

			Card playerCard = cards.get(0);
			this.playerCard.add(playerCard);
			btnImageInsert(playerCardLbl[i], playerCard.getSuit(), playerCard.getNumberOrder());
			playerCardLbl[i].setName(playerCard.getSuit() + "-" + playerCard.getNumberOrder());
			cards.remove(0);
		}
	}

	public void lastCardCheck() {
		int spreadCard = getSpreadCardIndex();
		if (spreadCard == -1) {
			gui.setTxtNotice("������ ī�带 ���Ƚ��ϴ�.");

			// ������ ��ǻ�� ī�� �̹��� open
			computerCardOpen();

			// ���� ��ư �ʱ�ȭ
			gui.resetRaiseBtn();

			// ���� Ȯ��
			cardHandCheck();

			// �÷��̾� �⺻ ���� �ݾ��� �ִ��� Ȯ��
			int moneyCheck = playMoneyCheck(gui.getBetDefaultMoney());
			if (moneyCheck == 0) {
				// ���� ���� ����
				gamePlay();
			} else if (moneyCheck == 1) {
				JOptionPane.showMessageDialog(gui.getBoard(), "�� �̻� ������ �Ͻ� �� �����ϴ�.");
			} else if (moneyCheck == 2) {
				JOptionPane.showMessageDialog(gui.getBoard(), "��ǻ�Ͱ� ���� �����ϴ�.");
			}

		}
	}

	private int playMoneyCheck(int betMoney) {
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""));
		if (playerMoney < betMoney) {
			return 1;
		}

		int computerMoney = Integer.parseInt(gui.getTxtComputerMoney().replace(",", ""));
		if (computerMoney < betMoney) {
			return 2;
		}

		return 0;
	}

	private void computerCardOpen() {
		for (JLabel lbl : computerCardLbl) {
			lbl.setIcon(new ImageIcon("img/" + lbl.getName() + ".png"));
		}
	}

	private void cardHandCheck() {
		CardHandType computerHandType = gui.rankCheck(computerCard);
		CardHandType playerHandType = gui.rankCheck(playerCard);

		String computerHand = computerHandType.name();
		String playerHand = playerHandType.name();
		String winner = "";

 		if (computerHandType.getNumber() > playerHandType.getNumber()) {
			winner = "��ǻ�� ��";
		} else if (computerHandType.getNumber() < playerHandType.getNumber()) {
			winner = "�÷��̾� ��";
			takeMoney();
		} else {
			winner = "���º�";
			drawMoney();
		}

		JOptionPane.showMessageDialog(gui.getBoard(),
				"��ǻ�� ��:" + computerHand + "\n�÷��̾� ��:" + playerHand + "\n" + winner);
		gui.setTxtNotice("[" + winner + "]");
	}

	private int getSpreadCardIndex() {
		for (int i = 0; i < computerCardLbl.length; i++) {
			if (computerCardLbl[i].getName() == null || computerCardLbl[i].getName().equals("")) {
				return i;
			}
		}

		return -1;
	}

	private void drawMoney() {
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""))+(plateMoney / 2);
		int computerMoney = Integer.parseInt(gui.getTxtComputerMoney().replace(",", ""))+plateMoney / 2;
		gui.setTxtPlayerMoney(df.format(playerMoney));
		gui.setTxtComputerMoney(df.format(computerMoney));
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
