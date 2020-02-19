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
		this.player = gui.getPlayer();

		cards = new ArrayList<Card>();
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
		JOptionPane.showMessageDialog(gui.getBoard(), "게임 시작");

		// 플레이어 카드 초기화
		playerCardClear();

		// 보드 초기화
		gui.resetBoard();

		// 카드 생성
		createCards();

		// 카드 섞기
		shuffle();

		// 기본 판 돈 베팅
		betDefaultMoney();

		// 카드 돌리기(횟수)
		cardSpread(3);

		// 베팅 버튼 활성화
		btnRaiseEnable();
	}

	private void playerCardClear() {
		player[0].getArrayCard().clear();
		player[1].getArrayCard().clear();
	}

	private void betDefaultMoney() {
		int betDefaultMoney = gui.getBetDefaultMoney();
		betPlayer(betDefaultMoney);
		betComputer(betDefaultMoney);
	}

	public boolean betComputer(int betMoney) {
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int computerMoney = player[1].getMoney();

		if (computerMoney < 0 || computerMoney - betMoney < 0) {
			return false;
		}

		// 100원 단위 금액은 돌려줌
		int change = betMoney % 100;
		betMoney -= change;

		computerMoney -= betMoney;
		plateMoney += betMoney;

		player[1].setMoney(computerMoney);
		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtComputerMoney(df.format(computerMoney));
		gui.setTxtNotice("컴퓨터가 " + betMoney + "원을 베팅했습니다.");

		return true;
	}

	private boolean betPlayer(int betMoney) {
		// 판 돈, 플레이어 보유 금액
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = player[0].getMoney();

		if (playerMoney < 0 || playerMoney - betMoney < 0) {
			JOptionPane.showMessageDialog(gui.getBoard(), "돈이 부족합니다.");
			return false;
		}

		// 100원 단위 금액은 돌려줌
		int change = betMoney % 100;
		betMoney -= change;

		playerMoney -= betMoney;
		plateMoney += betMoney;

		player[0].setMoney(playerMoney);
		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtPlayerMoney(df.format(playerMoney));
		gui.setTxtNotice("플레이어가 " + betMoney + "원을 베팅했습니다.");

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
		// 현재 뿌려준 카드의 index
		int spreadCard = getSpreadCardIndex();

		gui.setTxtNotice("카드를 " + count + "장 돌립니다.");

		for (int i = spreadCard; i < spreadCard + count; i++) {
			Card card1 = cards.get(0);
			player[0].setCard(card1);
			playerCardLbl[i].setName(card1.getSuit() + "-" + card1.getNumberOrder());
			btnImageInsert(playerCardLbl[i], card1.getSuit(), card1.getNumberOrder());
			cards.remove(0);

			Card card2 = cards.get(0);
			player[1].setCard(card2);
			computerCardLbl[i].setName(card2.getSuit() + "-" + card2.getNumberOrder());

			// 컴퓨터는 두번째, 세번째, 마지막 카드는 오픈 안함
			if (i == 1 || i == 2 || i == (player[1].getArrayCard().size())) {
				btnImageInsert(computerCardLbl[i], "back");
			} else {
				btnImageInsert(computerCardLbl[i], card2.getSuit(), card2.getNumberOrder());
			}
			cards.remove(0);
		}
	}

	public void lastCardCheck() {
		int spreadCard = getSpreadCardIndex();
		if (spreadCard == 7) {
			gui.setTxtNotice("마지막 카드를 돌렸습니다.");

			// 가려진 컴퓨터 카드 이미지 open
			computerCardOpen();

			// 베팅 버튼 초기화
			gui.resetRaiseBtn();

			// 족보 확인
			cardHandCheck();

			// 플레이어 기본 베팅 금액이 있는지 확인
			int moneyCheck = playMoneyCheck(gui.getBetDefaultMoney());
			if (moneyCheck == 0) {
				// 다음 게임 시작
				gamePlay();
			} else if (moneyCheck == 1) {
				JOptionPane.showMessageDialog(gui.getBoard(), "더 이상 베팅을 하실 수 없습니다.");
			} else if (moneyCheck == 2) {
				JOptionPane.showMessageDialog(gui.getBoard(), "컴퓨터가 돈이 없습니다.");
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
		gui.rankCheck(player[0]);
		gui.rankCheck(player[1]);

		CardHandType playerHand = player[0].getHand();
		CardHandType computerHand = player[1].getHand();

		String winner = "";
		if (playerHand.getNumber() > computerHand.getNumber()) {
			winner = "컴퓨터 승";
		} else if (playerHand.getNumber() < computerHand.getNumber()) {
			winner = "플레이어 승";
		}

		JOptionPane.showMessageDialog(gui.getBoard(),
				"컴퓨터 카드:" + computerHand + "\n플레이어 카드:" + playerHand + "\n" + winner);
		gui.setTxtNotice("[" + winner + "]");
	}

	private int getSpreadCardIndex() {
		return player[0].getArrayCard().size();
	}

	private void drawMoney() {
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", "")) + (plateMoney / 2);
		int computerMoney = Integer.parseInt(gui.getTxtComputerMoney().replace(",", "")) + plateMoney / 2;
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
