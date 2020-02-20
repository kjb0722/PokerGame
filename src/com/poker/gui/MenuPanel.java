package com.poker.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.poker.emun.MenuType;

public class MenuPanel extends JPanel {
	private final int btnWidth = 150;
	private final int btnHeight = 50;
	private final int btnX = 800;
	private final int btnY = 250;
	private final int btnGapY = 65;
	private final int txtNoticeWidth = 235;
	private final int txtNoticeHeight = 270;
	private final int txtNoticeX = 755;
	private final int txtNoticeY = 5;

	private JButton[] menuBtn;
	private JTextArea txtNotice;
	private JScrollPane noticeScroll;
	private JLabel lblNotice;
	private GameGui gui;

	MenuPanel(GameGui gui) {
		this.gui = gui;
		createComponent();
		init();
	}

	private void init() {
		setBackground(Color.lightGray);
		setBounds(GameGui.WIDTH - 250, 0, 200, GameGui.HEIGHT);
		setLayout(null);
	}

	public void setTxtNotice(String text) {
		this.txtNotice.setText(txtNotice.getText() + text + "\n");
	}

	public String getTxtNotice() {
		return txtNotice.getText();
	}

	private void createComponent() {
		createNotice();
		createBtn();
	}
	
	private void createNotice() {
		txtNotice = new JTextArea();
		txtNotice.setFont(new Font("����", Font.BOLD, 11));
		noticeScroll = new JScrollPane(txtNotice);
		noticeScroll.setBounds(txtNoticeX, txtNoticeY, txtNoticeWidth, txtNoticeHeight);
		noticeScroll.setEnabled(false);
		add(noticeScroll);
		
		lblNotice = new JLabel();
		String text = "<html>";
		text += "- 1:1 ��ǻ�� ��Ŀ �����Դϴ�.<br/>";
		text += "- ��ǻ�� �⺻ �ݾ�: "+gui.getComputerDefaultMoney()+"��<br/>";
		text += "- �÷��̾� �⺻ �ݾ�: "+gui.getPlayerDefaultMoney()+"��<br/>";
		text += "</html>";
		lblNotice.setText(text);
		lblNotice.setBounds(755, 150, 200, 300);
		add(lblNotice);
	}

	private void createBtn() {
		menuBtn = new JButton[3];

		for (int i = 0; i < menuBtn.length; i++) {
			String btnName = "";
			menuBtn[i] = new JButton();
			menuBtn[i].setBounds(btnX, (GameGui.HEIGHT - btnY) + (btnGapY * i), btnWidth, btnHeight);
			add(menuBtn[i]);
			if (MenuType.start.ordinal() == i) {
				btnName = "���� ����";
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gameRun();
					}
				});
			} else if (MenuType.reset.ordinal() == i) {
				btnName = "�ٽ� �ϱ�";
				menuBtn[i].setEnabled(false);
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						resetBoard();
					}
				});
			} else if (MenuType.exit.ordinal() == i) {
				btnName = "����";
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
		enableMenuBtn();
	}

	private void resetBoard() {
		gui.resetPlayer();
		gui.resetBoard();
		gui.resetPlayerMoney();
		enableMenuBtn();
	}

	public void enableMenuBtn() {
		if (menuBtn[MenuType.start.getNumber()].isEnabled()) {
			menuBtn[MenuType.start.getNumber()].setEnabled(false);
		} else {
			menuBtn[MenuType.start.getNumber()].setEnabled(true);
		}

		if (menuBtn[MenuType.reset.getNumber()].isEnabled()) {
			menuBtn[MenuType.reset.getNumber()].setEnabled(false);
		} else {
			menuBtn[MenuType.reset.getNumber()].setEnabled(true);
		}
	}
}
