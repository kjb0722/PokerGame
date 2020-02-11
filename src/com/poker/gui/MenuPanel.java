package com.poker.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MenuPanel extends JPanel {
	private final int btnWidth = 150;
	private final int btnHeight = 50;
	private int btnX = 800;
	private int btnY = 250;
	private int btnGapY = 65;
	private int txtNoticeWidth = 235;
	private int txtNoticeHeight = 270;
	private int txtNoticeX = 755;
	private int txtNoticeY = 5;
	private JButton[] menuBtn;
	private JTextArea noticeText;
	private JScrollPane noticeScroll;
	private GameGui gui;

	MenuPanel(GameGui gui) {
		this.gui = gui;
		createComponent();
		init();
	}

	private enum menuType {
		start, reset, exit
	}

	public void resetMenu() {
		noticeText.setText("");
		enableMenuBtn(true);
	}

	public void setNoticeText(String text) {
		this.noticeText.setText(text);
	}

	public String getNoticeText() {
		return noticeText.getText();
	}

	private void init() {
		setBackground(Color.lightGray);
		setBounds(GameGui.WIDTH - 250, 0, 200, GameGui.HEIGHT);
		setLayout(null);
	}

	private void createComponent() {
		createNoticeText();
		createBtn();
	}

	private void createNoticeText() {
		noticeText = new JTextArea();
		noticeText.setFont(new Font("돋움", Font.BOLD, 15));
		noticeScroll = new JScrollPane(noticeText);
		noticeScroll.setBounds(txtNoticeX, txtNoticeY, txtNoticeWidth, txtNoticeHeight);
		noticeScroll.setEnabled(false);
		add(noticeScroll);
	}

	private void createBtn() {
		menuBtn = new JButton[3];

		for (int i = 0; i < menuBtn.length; i++) {
			String btnName = "";
			menuBtn[i] = new JButton();
			menuBtn[i].setBounds(btnX, (GameGui.HEIGHT - btnY) + (btnGapY * i), btnWidth, btnHeight);
			add(menuBtn[i]);
			if (menuType.start.ordinal() == i) {
				btnName = "게임 시작";
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gameRun();
					}
				});
			} else if (menuType.reset.ordinal() == i) {
				btnName = "다시 하기";
				menuBtn[i].setEnabled(false);
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						resetBoard();
					}
				});
			} else if (menuType.exit.ordinal() == i) {
				btnName = "종료";
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
			}
			menuBtn[i].setText(btnName);
		}
	}

	private void gameRun() {
		gui.gameRun();
		enableMenuBtn(false);
	}

	private void resetBoard() {
		gui.resetBoard();
		enableMenuBtn(true);
	}

	public void enableMenuBtn(boolean enable) {
		menuBtn[menuType.start.ordinal()].setEnabled(enable);
		menuBtn[menuType.reset.ordinal()].setEnabled(!enable);
	}
}
