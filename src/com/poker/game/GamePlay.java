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
		// 카드 돌리기(배팅 여부, 카드 개수)
		cardSpread(3);
		bet(gui.getBetDefaultMoney());
		raiseBtnEnable();
	}

	private void bet(int betMoney) {
		// 판 돈, 플레이어 보유 금액
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
			gui.setNoticeText(gui.getNoticeText() + "마지막 카드를 돌렸습니다.\n");
			gui.resetRaiseBtn();

			cardHandCheck();

			gui.resetBoard();

			gui.gameRun();
		} else {
			gui.setNoticeText(gui.getNoticeText() + "카드를 " + count + "장씩 돌립니다.\n");
		}
	}

	private void cardHandCheck() {
		CardHandType computerHand = gui.rankCheck(computerCard);
		CardHandType playerHand = gui.rankCheck(playerCard);

		String winner = "";

		if (computerHand.number > playerHand.number) {
			winner = "컴퓨터 승";
		} else if (computerHand.number < playerHand.number) {
			winner = "플레이어 승";
		} else if (computerHand.number == playerHand.number) {
			winner = "무승부";
		}

		JOptionPane.showMessageDialog(gui.getBoard(),
				"컴퓨터 패:" + computerHand.name() + "\n플레이어 패:" + playerHand.name() + "\n" + winner);
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
