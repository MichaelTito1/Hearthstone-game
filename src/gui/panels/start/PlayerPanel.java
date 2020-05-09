package gui.panels.start;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerPanel extends JPanel {

	private JLabel playerName;
	private JPanel buttons;
	private JPanel heroProfile;
	private JButton[] hero = new JButton[5];
	
	public PlayerPanel(String player) {
		super(new BorderLayout());
		
		playerName = new JLabel(player, JLabel.CENTER);
		playerName.setForeground(Color.WHITE);
		playerName.setFont(playerName.getFont().deriveFont(30.0f));
		playerName.setVisible(true);
		buttons = new JPanel(new GridLayout(1,5,3,1));
		buttons.setOpaque(false);
		buttons.setVisible(true);;
		hero[0] = new JButton("Hunter");
		hero[1] = new JButton("Mage");
		hero[2] = new JButton("Paladin");
		hero[3] = new JButton("Priest");
		hero[4] = new JButton("Warlock");
		
		for(int i = 0; i < 5; i++) {
			buttons.add(hero[i]);
			hero[i].setEnabled(true);
			hero[i].setVisible(true);
		}
		heroProfile = new JPanel();
		heroProfile.setLayout(new BorderLayout());
		heroProfile.setOpaque(false);
		heroProfile.setVisible(true);
		this.add(playerName, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.SOUTH);
		this.add(heroProfile, BorderLayout.WEST);
		//this.add(details, BorderLayout.EAST);
		
		this.setVisible(true);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(470,50));
		this.validate();
		/*
		 * On choosing a hero, its image and details should appear on the screen
		 */
	}

	public JLabel getPlayerName() {
		return playerName;
	}

	public JPanel getButtons() {
		return buttons;
	}

	public JButton[] getHero() {
		return hero;
	}
	public JPanel getHeroProfile() {
		return heroProfile;
	}
}
