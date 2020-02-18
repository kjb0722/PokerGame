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
		JOptionPane.showMessageDialog(gui.getBoard(), "게임 시작");

		// 카드 생성
		createCards();

		// 카드 섞기
		shuffle();

		// 보드 초기화
		gui.resetBoard();

		// 보유 카드 초기화
		computerCard.clear();
		playerCard.clear();

		// 카드 돌리기(횟수)
		cardSpread(3);

		// 기본 판 돈 베팅
		betDefaultMoney();

		// 베팅 버튼 활성화
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
		gui.setTxtNotice("컴퓨터가 " + betMoney + "원을 베팅했습니다.");
	}

	private void betPlayer(int betMoney) {
		// 판 돈, 플레이어 보유 금액
		int plateMoney = Integer.parseInt(gui.getTxtPlate().replace(",", ""));
		int playerMoney = Integer.parseInt(gui.getTxtPlayerMoney().replace(",", ""));

		playerMoney -= betMoney;
		plateMoney += betMoney;

		gui.setTxtPlate(df.format(plateMoney));
		gui.setTxtPlayerMoney(df.format(playerMoney));
		gui.setTxtNotice("플레이어가 " + betMoney + "원을 베팅했습니다.");
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
		// 현재 뿌려준 카드의 index
		int spreadCard = getSpreadCardIndex();

		for (int i = spreadCard; i < spreadCard + count; i++) {
			Card computerCard = cards.get(0);
			this.computerCard.add(computerCard);

			// 컴퓨터는 두번째, 세번째, 마지막 카드만 오픈
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
			gui.setTxtNotice("마지막 카드를 돌렸습니다.");

			// 가려진 컴퓨터 카드 이미지 보이게
			computerCardOpen();

			// 베팅 버튼 초기화
			gui.resetRaiseBtn();

			// 족보 확인
			cardHandCheck();

			// 플레이어 기본 베팅 금액이 있는지 확인
			if (playMoneyCheck(gui.getBetDefaultMoney())) {
				// 다음 게임 시작
				gamePlay();
			} else {
				JOptionPane.showMessageDialog(gui.getBoard(), "더 이상 베팅을 하실 수 없습니다.");
			}
		} else {
			gui.setTxtNotice("카드를 돌립니다.");
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
			winner = "무승부";
		}

		if (computerHandType.getNumber() > playerHandType.getNumber()) {
			winner = "컴퓨터 승";
		} else if (computerHandType.getNumber() < playerHandType.getNumber()) {
			winner = "플레이어 승";
			takeMoney();
		}

		JOptionPane.showMessageDialog(gui.getBoard(),
				"컴퓨터 패:" + computerHandType.name() + "\n플레이어 패:" + playerHandType.name() + "\n" + winner);
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
