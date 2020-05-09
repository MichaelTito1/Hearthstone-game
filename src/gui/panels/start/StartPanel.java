package gui.panels.start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StartPanel extends JPanel {
	
	private PlayerPanel player1, player2;
	private JButton startButton;
	
	public StartPanel() {
		super(new BorderLayout());
		player1 = new PlayerPanel("Player 1");
		player2 = new PlayerPanel("Player 2");
		startButton = new JButton("Start");
		startButton.setVisible(true);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(1000,530));
		this.add(player1, BorderLayout.WEST);
		this.add(player2 , BorderLayout.EAST);
		this.add(startButton, BorderLayout.NORTH);
		this.setVisible(true);
		this.validate();
	}

	public PlayerPanel getPlayer1() {
		return player1;
	}

	public PlayerPanel getPlayer2() {
		return player2;
	}

	public JButton getStartButton() {
		return startButton;
	}
	
}
