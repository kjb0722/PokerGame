package com.poker.game;

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
	private JButton[] computerBtn;
	private JButton[] playerBtn;
	private JButton[] raiseBtn;
	private DecimalFormat df;

	public GamePlay(GameGui gui) {
		this.gui = gui;
		this.computerBtn = gui.getComputerBtn();
		this.playerBtn = gui.getPlayerBtn();
		this.raiseBtn = gui.getRaiseBtn();

		cards = new ArrayList<Card>();
		computerCard = new ArrayList<Card>();
		playerCard = new ArrayList<Card>();

		df = new DecimalFormat("#,###");

		createCards();
		shuffle();
	}

	private void shuffle() {
		Collections.shuffle(cards);
	}

	private void createCards() {
		for (SuitType suits : SuitType.values()) {
			String suit = suits.name();
			for (NumberType numbers : NumberType.values()) {
				cards.add(new Card(suit, numbers.name(), numbers.getNumber()));

			}
		}
	}

	public void gamePlay() {
		// ī�� ������(���� ����, ī�� ����)
		cardSpread(3);
		bet(gui.getBetDefaultMoney());
		raiseBtnEnable();
	}

	private void bet(int betMoney) {
		// �� ��, �÷��̾� ���� �ݾ�
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""));

		playerMoney -= betMoney;
		plateMoney += betMoney;
		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtPlayerMoney(df.format(playerMoney));
	}

	public void bet(String betType) {
		if (betType.equals(RaiseType.Half.value)) {
			int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", "")) / 2;
			bet(plateMoney);
			cardSpread(1);
		} else if (betType.equals(RaiseType.Check.name())) {
			cardSpread(1);
		}
	}

	private void raiseBtnEnable() {
		for (int i = 0; i < raiseBtn.length; i++) {
			raiseBtn[i].setEnabled(true);
		}
	}

	public void cardSpread(int count) {
		int spreadCard = getSpreadStartIndex();

		for (int i = spreadCard; i < spreadCard + count; i++) {
			Card computerCard = cards.get(0);
			this.computerCard.add(computerCard);
			computerBtn[i].setIcon(
					new ImageIcon("img/" + computerCard.getSuit() + "-" + computerCard.getNumberOrder() + ".png"));
			computerBtn[i].setName(computerCard.getSuit() + "-" + computerCard.getNumber());
			cards.remove(0);

			Card playerCard = cards.get(0);
			this.playerCard.add(playerCard);
			playerBtn[i]
					.setIcon(new ImageIcon("img/" + playerCard.getSuit() + "-" + playerCard.getNumberOrder() + ".png"));
			playerBtn[i].setName(playerCard.getSuit() + "-" + playerCard.getNumber());
			cards.remove(0);
		}

		if (spreadCard == (playerBtn.length - 1)) {
			gui.setNoticeText(gui.getNoticeText() + "������ ī�带 ���Ƚ��ϴ�.\n");
			gui.resetRaiseBtn();

			cardHandCheck();

			gui.resetBoard();

			gui.gameRun();
		} else {
			gui.setNoticeText(gui.getNoticeText() + "ī�带 " + count + "�徿 �����ϴ�.\n");
		}
	}

	private void cardHandCheck() {
		CardHandType computerHand = gui.rankCheck(computerCard);
		CardHandType playerHand = gui.rankCheck(playerCard);

		String winner = "";

		if (computerHand.number > playerHand.number) {
			winner = "��ǻ�� ��";
		} else if (computerHand.number < playerHand.number) {
			winner = "�÷��̾� ��";
		} else if (computerHand.number == playerHand.number) {
			winner = "���º�";
		}

		JOptionPane.showMessageDialog(gui.getBoard(),
				"��ǻ�� ��:" + computerHand.name() + "\n�÷��̾� ��:" + playerHand.name() + "\n" + winner);
	}

	private int getSpreadStartIndex() {
		for (int i = 0; i < computerBtn.length; i++) {
			if (computerBtn[i].getName() == null || computerBtn[i].getName().equals("")) {
				return i;
			}
		}

		return -1;
	}
}
