package com.poker.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.poker.game.GamePlay;
import com.poker.game.Rank;
import com.poker.player.Player;

public class GameGui extends JFrame {
	static final int WIDTH = 1100;
	static final int HEIGHT = 700;
	private Board board;
	private MenuPanel menu;
	private GamePlay play;
	private Rank rank;

	public GameGui() {
		init();
	}

	public void gameRun() {
		play = new GamePlay(this);
		play.gamePlay();
	}

	public void resetBoard() {
		board.resetBoard();
	}

	public void cardSpread(int count) {
		play.cardSpread(count);
	}

	private void init() {
		board = new Board(this);
		add(board);

		menu = new MenuPanel(this);
		add(menu);

		rank = new Rank();

		setLayout(null);
		setTitle("1:1 포커 게임");
//		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void resetPlayerMoney() {
		board.resetPlayerMoney();
	}

	public JLabel[] getLblComputerCard() {
		return board.getLblComputerCard();
	}

	public JLabel[] getLblPlayerCard() {
		return board.getLblPlayerCard();
	}
	
	public String getTxtNotice() {
		return menu.getTxtNotice();
	}

	public void setTxtNotice(String text) {
		menu.setTxtNotice(text);
	}

	public void rankCheck(Player player) {
		rank.rankChecker(player);
	}

	public Board getBoard() {
		return board;
	}

	public int getTxtPlate() {
		return Integer.parseInt(board.getTxtPlate().replace(",", ""));
	}

	public void setTxtPlate(String text) {
		board.setTxtPlate(text);
	}

	public int getTxtPlayerMoney() {
		return Integer.parseInt(board.getTxtPlayerMoney().replace(",", ""));
	}

	public void setTxtPlayerMoney(String text) {
		board.setTxtPlayerMoney(text);
	}

	public int getBetDefaultMoney() {
		return board.getBetDefaultMoney();
	}

	public void lastCardCheck() {
		play.lastCardCheck();
	}

	public void raiseHalf() {
		play.raiseHalf();
	}

	public void raiseDie() {
		play.raiseDie();
	}

	public void resetCardLbl() {
		board.resetCardLbl();
	}

	public void raiseCheck() {
		play.raiseCheck();
	}

	public int getTxtComputerMoney() {
		return Integer.parseInt(board.getTxtComputerMoney().replace(",", ""));
	}

	public void setTxtComputerMoney(String text) {
		board.setTxtComputerMoney(text);
	}

	public Player[] getPlayer() {
		return board.getPlayer();
	}

	public void resetPlayer() {
		board.resetPlayer();
	}

	public int drawCheck(Player[] player) {
		return rank.drawCheck(player);
	}

	public int getComputerDefaultMoney() {
		return board.getComputerDefaultMoney();
	}

	public int getPlayerDefaultMoney() {
		return board.getPlayerDefaultMoney();
	}

	public void setTxtHalf(int money) {
		board.setTxtHalf(Integer.toString(money));
	}

	public void setMoneyInit() {
		board.setMoneyInit();
	}

	public void setLblPlayerRank(String text) {
		board.setlblPlayerRank(text);
	}

	public void setLblComputerRank(String text) {
		board.setLblComputerRank(text);
	}

	public void turnChange(Player player) {
		board.turnChange(player);
	}

	public void resetTurn() {
		board.resetTurn();
	}

	public void setLblPlayerBetMoney(int betMoney) {
		board.setLblPlayerBetMoney(Integer.toString(betMoney));
	}

	public void setLblComputerBetMoney(int betMoney) {
		board.setLblComputerBetMoney(Integer.toString(betMoney));
	}

	public void setLblPlayerResult(String text) {
		board.setLblPlayerResult(text);
	}

	public void setLblComputerResult(String text) {
		board.setLblComputerResult(text);
	}

	public void resetResult() {
		board.resetResult();
	}

	public void btnRaiseEnable(boolean enable) {
		board.btnRaiseEnable(enable);
	}

	public void winnerDisplay(Player player) {
		board.winnerDisplay(player);
	}

	public void btnResetEnable(boolean enable) {
		menu.btnResetEnable(enable);
	}
}
