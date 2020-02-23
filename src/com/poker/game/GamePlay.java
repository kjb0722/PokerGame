package com.poker.game;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.poker.emun.CardHandType;
import com.poker.emun.NumberType;
import com.poker.emun.RaiseType;
import com.poker.emun.SuitType;
import com.poker.gui.Board;
import com.poker.gui.GameGui;
import com.poker.player.Player;

public class GamePlay {
	private ArrayList<Card> cards;
	private GameGui gui;
	private JLabel[] lblComputerCard;
	private JLabel[] lblPlayerCard;
	private DecimalFormat df;
	private Player[] player;

	public GamePlay(GameGui gui) {
		this.gui = gui;
		this.lblComputerCard = gui.getLblComputerCard();
		this.lblPlayerCard = gui.getLblPlayerCard();
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
			for (NumberType numbers : NumberType.values()) {
				cards.add(new Card(suits, numbers));
			}
		}
	}

	public void gamePlay() {
		gui.setTxtNotice("[게임 시작]");

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

		// 베팅
		betting();
	}

	private void betting() {
		// 플레이어 베팅 순서
		playerBetOrder();

		if (player[0].getTurn()) {
			// 베팅 버튼 활성화
			gui.btnRaiseEnable(true);

			gui.turnChange(player[0]);
		} else {
			// 베팅 버튼 비활성화
			gui.btnRaiseEnable(false);

			betComputer();
			gui.turnChange(player[1]);
		}
	}

	private void playerBetOrder() {
		int spreadCard = getSpreadCardIndex();

		spreadCard -= 1;
		if (spreadCard == 2) {
			if (player[0].getCard(0).getNumberOrder() > player[1].getCard(0).getNumberOrder()) {
				player[0].setTurn(true);
				player[1].setTurn(false);
			} else if (player[0].getCard(0).getNumberOrder() < player[1].getCard(0).getNumberOrder()) {
				player[0].setTurn(false);
				player[1].setTurn(true);
			} else if (player[0].getCard(0).getNumberOrder() == player[1].getCard(0).getNumberOrder()) {
				if (player[0].getCard(0).getSuitType().getRank() > player[1].getCard(0).getSuitType().getRank()) {
					player[0].setTurn(true);
					player[1].setTurn(false);
				} else if (player[0].getCard(0).getSuitType().getRank() < player[1].getCard(0).getSuitType()
						.getRank()) {
					player[0].setTurn(false);
					player[1].setTurn(true);
				}
			}
		}
	}

	private void playerCardClear() {
		player[0].getArrayCard().clear();
		player[1].getArrayCard().clear();
	}

	private void betDefaultMoney() {
		int betDefaultMoney = gui.getBetDefaultMoney();
		betMoney(player[0], betDefaultMoney);
		betMoney(player[1], betDefaultMoney);
	}

	public void betMoney(Player player, int betMoney) {
		int plateMoney = gui.getTxtPlate();
		int money = player.getMoney();

		if (!moneyCheck(player, betMoney)) {
			betMoney = money;

			if (player.getName().equals(Board.PLAYER_NAME)) {
				gui.setLblPlayerResult("ALL IN");
			} else {
				gui.setLblComputerResult("ALL IN");
			}
		}

		// 10원 단위 금액은 돌려줌
		int change = betMoney % 100;
		betMoney -= change;

		money -= betMoney;
		plateMoney += betMoney;

		player.setMoney(money);

		gui.setTxtPlate(df.format(plateMoney));
		if (player.getName().equals(Board.PLAYER_NAME)) {
			gui.setTxtPlayerMoney(df.format(money));
			gui.setLblPlayerBetMoney(betMoney);
		} else if (player.getName().equals(Board.COMPUTER_NAME)) {
			gui.setTxtComputerMoney(df.format(money));
			gui.setLblComputerBetMoney(betMoney);
		}

		gui.setTxtNotice(player.getName() + "님이 " + betMoney + "원을 베팅했습니다.");
	}

	public void betPlayer(String betType) {
		gui.btnRaiseEnable(false);

		// 하프 버튼이면 베팅
		if (betType.equals(RaiseType.Half.getValue())) {
			int plateHalfMoney = gui.getTxtPlate() / 2;
			betMoney(player[0], plateHalfMoney);
		}

		if (getSpreadCardIndex() != 7) {
			// 컴퓨터 베팅
			betComputer();
		}

		betAfter();
	}

	private void betComputer() {
		new Thread() {
			@Override
			public void run() {
				try {
					gui.btnResetEnable(false);

					gui.turnChange(player[1]);

					sleep(1000);

					// 컴퓨터 베팅은 하프로 고정
					gui.setTxtNotice("[" + player[1].getName() + "] - 하프");
					int plateHalfMoney = gui.getTxtPlate() / 2;
					betMoney(player[1], plateHalfMoney);

					gui.btnRaiseEnable(true);

					gui.turnChange(player[0]);

					gui.btnResetEnable(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void betAfter() {
		// 마지막 카드인지 확인
		if (lastCardCheck()) {
			return;
		}

		// 하프 금액 표시
		halfMoneyDisplay();

		// 카드 나눠주기
		cardSpread(1);
	}

	private void halfMoneyDisplay() {
		int halfMoney = gui.getTxtPlate() / 2;
		gui.setTxtHalf(halfMoney);
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
			if (i >= player[0].CARD_TOTAL_COUNT) {
				break;
			}

			Card card1 = cards.get(0);
			player[0].setCard(card1);
			lblPlayerCard[i].setName(card1.getSuit() + "-" + card1.getNumberOrder());
			btnImageInsert(lblPlayerCard[i], card1.getSuit(), card1.getNumberOrder());
			cards.remove(0);
			cardSpreadMove(player[0], lblPlayerCard[i]);

			Card card2 = cards.get(0);
			player[1].setCard(card2);
			lblComputerCard[i].setName(card2.getSuit() + "-" + card2.getNumberOrder());
			// 컴퓨터는 두번째, 세번째, 마지막 카드는 오픈 안함
			if (i == 1 || i == 2 || i == (player[1].CARD_TOTAL_COUNT - 1)) {
				btnImageInsert(lblComputerCard[i], "back");
			} else {
				btnImageInsert(lblComputerCard[i], card2.getSuit(), card2.getNumberOrder());
			}
			cardSpreadMove(player[1], lblComputerCard[i]);
			cards.remove(0);
		}
	}

	private void cardSpreadMove(Player player, JLabel label) {
		new Thread() {
			public void run() {
				int location = 0;

				if (player.getName().equals(Board.PLAYER_NAME)) {
					location = 1;
				} else if (player.getName().equals(Board.COMPUTER_NAME)) {
					location = -1;
				}

				int labelX = label.getX();
				int labelY = label.getY();
				int x = labelX - 150;
				int y = labelY - (150 * location);
				while (x != labelX && y != labelY) {
					if (x < labelX) {
						x++;
					} else {
						x--;
					}
					if (y < labelY) {
						y++;
					} else {
						y--;
					}
					label.setLocation(x, y);
					try {
						sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public boolean lastCardCheck() {
		int spreadCard = getSpreadCardIndex();

		if (spreadCard == player[0].CARD_TOTAL_COUNT) {
			gui.setTxtNotice("마지막 카드를 돌렸습니다.");

			// 가려진 컴퓨터 카드 이미지 open
			computerCardOpen();

			// 족보 확인
			cardHandCheck();

			// 판 돈, 하프 금액 초기화
			gui.setMoneyInit();

			// 플레이어 기본 베팅 금액이 있는지 확인
			// 컴퓨터
			if (!moneyCheck(player[0], gui.getBetDefaultMoney())) {
				gameEnd(player[0]);
				return false;
			}
			// 플레이어
			if (!moneyCheck(player[1], gui.getBetDefaultMoney())) {
				gameEnd(player[1]);
				return false;
			}

			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 다음 게임 시작
			gamePlay();

			return true;
		}
		return false;
	}

	private void gameEnd(Player player) {
		gui.resetTurn();
		gui.btnRaiseEnable(false);
		if (player.getName().equals(Board.PLAYER_NAME)) {
			gui.winnerDisplay(this.player[1]);
			gui.setLblComputerResult("WIN");
		} else {
			gui.winnerDisplay(this.player[0]);
			gui.setLblPlayerResult("WIN");
		}
	}

	private void computerCardOpen() {
		for (JLabel lbl : lblComputerCard) {
			lbl.setIcon(new ImageIcon("img/" + lbl.getName() + ".png"));
		}
	}

	private void cardHandCheck() {
		gui.rankCheck(player[0]);
		gui.rankCheck(player[1]);

		CardHandType playerHand = player[0].getHand();
		CardHandType computerHand = player[1].getHand();

		if (playerHand.getNumber() < computerHand.getNumber()) {
			takeMoney(player[1]);
			gui.setLblComputerResult("WIN");
		} else if (playerHand.getNumber() > computerHand.getNumber()) {
			takeMoney(player[0]);
			gui.setLblPlayerResult("WIN");
		} else {
			int gameWin = gui.drawCheck(player);
			if (gameWin == 0) {
				takeMoney(player[0]);
				gui.setLblPlayerResult("WIN");
			} else if (gameWin == 1) {
				takeMoney(player[1]);
				gui.setLblComputerResult("WIN");
			}
		}

		String playerSuitSymbol = suitSymbol(player[0].getBestCard().getSuit());
		String computerSuitSymbol = suitSymbol(player[1].getBestCard().getSuit());
		String playerNumSymbol = numberSymbol(player[1].getBestCard().getNumberOrder());
		String computerNumSymbol = numberSymbol(player[0].getBestCard().getNumberOrder());

		gui.setLblPlayerRank(playerHand.name() + "(" + playerSuitSymbol + computerNumSymbol + ")");
		gui.setLblComputerRank(computerHand.name() + "(" + computerSuitSymbol + playerNumSymbol + ")");
	}

	private String numberSymbol(int number) {
		switch (number) {
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		case 14:
			return "Ace";
		default:
			return Integer.toString(number);
		}
	}

	private String suitSymbol(String suit) {
		if (suit.equals(SuitType.hearts.name())) {
			return "♥";
		} else if (suit.equals(SuitType.clubs.name())) {
			return "♣";
		} else if (suit.equals(SuitType.spades.name())) {
			return "♠";
		} else if (suit.equals(SuitType.diamonds.name())) {
			return "◆";
		}
		return "";
	}

	private int getSpreadCardIndex() {
		return player[0].getArrayCard().size();
	}

	private void takeMoney(Player player) {
		StringBuilder text = new StringBuilder(player.getName() + "님 금액 " + player.getMoney() + " - > ");

		int plateMoney = gui.getTxtPlate();
		int money = player.getMoney() + plateMoney;
		player.setMoney(money);
		if (player.getName().equals(Board.COMPUTER_NAME)) {
			gui.setTxtComputerMoney(df.format(player.getMoney()));
		} else if (player.getName().equals(Board.PLAYER_NAME)) {
			gui.setTxtPlayerMoney(df.format(player.getMoney()));
		}

		text.append(player.getMoney());
		gui.setTxtNotice(text.toString());
	}

	private boolean moneyCheck(Player player, int betMoney) {
		int money = 0;
		if (player.getName().equals(Board.PLAYER_NAME)) {
			money = gui.getTxtPlayerMoney();
		} else if (player.getName().equals(Board.COMPUTER_NAME)) {
			money = gui.getTxtComputerMoney();
		}

		if (money < 0 || money - betMoney < 0) {
			return false;
		}
		return true;
	}

	private boolean playerMoneyCheck() {
		if (moneyCheck(player[0], gui.getBetDefaultMoney())) {
			return false;
		}
		if (moneyCheck(player[1], gui.getBetDefaultMoney())) {
			return false;
		}
		return true;
	}

	public void raiseHalf() {
		new Thread() {
			@Override
			public void run() {
				gui.setTxtNotice("[" + player[0].getName() + "] - 하프");
				betPlayer(RaiseType.Half.getValue());
			}
		}.start();
	}

	public void raiseDie() {
		gui.setTxtNotice("[" + player[0].getName() + "] - 다이");
		if (playerMoneyCheck()) {
			return;
		}

		takeMoney(player[1]);

		gui.btnRaiseEnable(false);

		gamePlay();
	}

	public void raiseCheck() {
		new Thread() {
			@Override
			public void run() {
				gui.setTxtNotice("[" + player[0].getName() + "] - 체크");
				betPlayer(RaiseType.Check.getValue());
			}
		}.start();
	}
}
