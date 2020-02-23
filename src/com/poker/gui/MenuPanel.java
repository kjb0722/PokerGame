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
import javax.swing.border.LineBorder;

import com.poker.emun.MenuType;

public class MenuPanel extends JPanel {
	private final int btnWidth = 150; // ��ư
	private final int btnHeight = 50;
	private final int btnX = 240;
	private final int btnY = 210;
	private final int btnGapY = 65;

	private final int txtNoticeWidth = 400; // ����
	private final int txtNoticeHeight = 480;
	private final int txtNoticeX = 0;
	private final int txtNoticeY = 5;
	private final int lblNoticeX = 20;
	private final int lblNoticeY = 500;
	private final int lblNoticeWidth = 200;
	private final int lblNoticeHeight = 150;

	private JButton[] btnMenu;
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
		setBackground(Color.LIGHT_GRAY);
		setBounds(GameGui.WIDTH - 400, 0, 400, GameGui.HEIGHT);
		setLayout(null);
	}

	public void setTxtNotice(String text) {
		txtNotice.setText(txtNotice.getText() + text + "\n");
		txtNotice.setCaretPosition(txtNotice.getDocument().getLength());
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
		txtNotice.setFont(new Font("����", Font.BOLD, 15));
		txtNotice.setEditable(false);
		txtNotice.setForeground(Color.black);
		noticeScroll = new JScrollPane(txtNotice);
		noticeScroll.setBounds(txtNoticeX, txtNoticeY, txtNoticeWidth, txtNoticeHeight);
		add(noticeScroll);

		lblNotice = new JLabel();
		String text = "<html>";
		text += "- 1:1 ��ǻ�� ��Ŀ �����Դϴ�.<br/>";
		text += "- ��ǻ�� �⺻ �ݾ�: " + gui.getComputerDefaultMoney() + "��<br/>";
		text += "- �÷��̾� �⺻ �ݾ�: " + gui.getPlayerDefaultMoney() + "��<br/>";
		text += "</html>";
		lblNotice.setText(text);
		lblNotice.setBorder(new LineBorder(Color.DARK_GRAY, 3));
		lblNotice.setBounds(lblNoticeX, lblNoticeY, lblNoticeWidth, lblNoticeHeight);
		add(lblNotice);
	}

	private void createBtn() {
		btnMenu = new JButton[3];
		for (int i = 0; i < btnMenu.length; i++) {
			String btnName = "";
			btnMenu[i] = new JButton();
			btnMenu[i].setBounds(btnX, (GameGui.HEIGHT - btnY) + (btnGapY * i), btnWidth, btnHeight);
			add(btnMenu[i]);
			if (MenuType.START.ordinal() == i) {
				btnName = MenuType.START.name();
				btnMenu[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gameRun();
					}
				});
			} else if (MenuType.RESET.ordinal() == i) {
				btnName = MenuType.RESET.name();
				btnMenu[i].setEnabled(false);
				btnMenu[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						resetBoard();
					}
				});
			} else if (MenuType.EXIT.ordinal() == i) {
				btnName = MenuType.EXIT.name();
				btnMenu[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
			}
			btnMenu[i].setText(btnName);
		}
	}

	private void gameRun() {
		gui.gameRun();
		btnMenuEnable();
	}

	private void resetBoard() {
		gui.resetPlayer();
		gui.resetBoard();
		gui.resetPlayerMoney();
		btnMenuEnable();
		gui.resetTurn();
		gui.btnRaiseEnable(false);
		gui.resetResult();
	}

	public void btnMenuEnable() {
		if (btnMenu[MenuType.START.getNumber()].isEnabled()) {
			btnMenu[MenuType.START.getNumber()].setEnabled(false);
		} else {
			btnMenu[MenuType.START.getNumber()].setEnabled(true);
		}

		if (btnMenu[MenuType.RESET.getNumber()].isEnabled()) {
			btnMenu[MenuType.RESET.getNumber()].setEnabled(false);
		} else {
			btnMenu[MenuType.RESET.getNumber()].setEnabled(true);
		}
	}

	public void btnResetEnable(boolean enable) {
		btnMenu[MenuType.RESET.ordinal()].setEnabled(enable);
	}
}
