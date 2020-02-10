package com.poker.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MenuPanel extends JPanel {
	private final int btnWidth = 150;
	private final int btnHeight = 50;
	private int btnGapX = 200;
	private int btnGapY = 65;
	private JButton[] menuBtn;
	private JTextArea text;
	private GameGui gui;

	MenuPanel(GameGui gui) {
		this.gui = gui;
		createComponent();
		init();
	}
	
	public void resetMenu() {
		text.setText("");
	}

	public void setText(String text) {
		this.text.setText(text);
	}
	
	public String getText() {
		return text.getText();
	}

	private void init() {
		setBackground(Color.lightGray);
		setBounds(GameGui.WIDTH - 250, 0, 200, GameGui.HEIGHT);
		setLayout(null);
	}

	private void createComponent() {
		createText();
		createBtn();
	}

	private void createText() {
		text = new JTextArea(10, 10);
		text.setBounds(GameGui.WIDTH - 240, 10, 215, 300);
		text.setEnabled(false);
		add(text);
	}

	private void createBtn() {
		menuBtn = new JButton[3];

		for (int i = 0; i < menuBtn.length; i++) {
			String btnName = "";
			menuBtn[i] = new JButton();
			menuBtn[i].setBounds(GameGui.WIDTH - btnGapX, (GameGui.HEIGHT - 250) + (btnGapY * i), btnWidth, btnHeight);
			add(menuBtn[i]);
			switch (i) {
			case 0:
				btnName = "게임 시작";
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gui.gameRun();
						menuBtn[0].setEnabled(false);
						menuBtn[1].setEnabled(true);
					}
				});
				break;
			case 1:
				btnName = "다시 하기";
				menuBtn[i].setEnabled(false);
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						gui.resetBoard();
						menuBtn[0].setEnabled(true);
						menuBtn[1].setEnabled(false);
					}
				});
				break;
			case 2:
				btnName = "종료";
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				break;
			}
			menuBtn[i].setText(btnName);
		}
	}
}
