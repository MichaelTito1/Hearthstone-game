package gui.panels.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Stats extends JPanel {
	private JButton heropowerused, attackHero ;
	private JLabel healthpoints,deck,currentmana,playerName,totalmana;
	
	
	public Stats() {
		super(new GridLayout(7,1));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension((int) dim.getWidth()/5, (int) dim.getHeight()/5));
		heropowerused=new JButton("Use Hero Power");
		attackHero = new JButton("Attack hero");
		ImageIcon image = new ImageIcon("res/images/fire.jpg");//test w teb2o t8ayarouha please
		heropowerused.setIcon(image);
		heropowerused.setHorizontalTextPosition(SwingConstants.CENTER); 
		
		healthpoints=new JLabel("Health Points");
		healthpoints.setSize(new Dimension(100,50));
		healthpoints.setForeground(Color.white);
		healthpoints.setFont(healthpoints.getFont().deriveFont(18.0f));
		
		
		deck=new JLabel("Remaining Cards");
		deck.setSize(new Dimension(100,50));
		deck.setForeground(Color.white);
		deck.setFont(deck.getFont().deriveFont(18.0f));
		
		
		playerName=new JLabel("Name");
		playerName.setSize(new Dimension(100,50));
		playerName.setForeground(Color.white);
		playerName.setFont(playerName.getFont().deriveFont(18.0f));
		
		
		totalmana=new JLabel("Total Mana");
		totalmana.setSize(new Dimension(100,50));
		totalmana.setForeground(Color.white);
		totalmana.setFont(totalmana.getFont().deriveFont(18.0f));
		
		
		currentmana = new JLabel("Current Mana");
		currentmana.setSize(new Dimension(100,50));
		currentmana.setForeground(Color.white);
		currentmana.setFont(currentmana.getFont().deriveFont(18.0f));
		
		
		this.add(playerName);
		this.add(currentmana);
		this.add(totalmana);
		this.add(healthpoints);
		this.add(deck);		
		this.add(heropowerused);
		this.add(attackHero);
		
		deck.setOpaque(false);
		healthpoints.setOpaque(false);
		currentmana.setOpaque(false);
		deck.setVisible(true);
		currentmana.setVisible(true);
		healthpoints.setVisible(true);
		attackHero.setVisible(true);
		
		this.setVisible(true);
		this.validate();
	}

	public JButton getAttackHero() {
		return attackHero;
	}

	public JButton getHeropowerused() {
		return heropowerused;
	}

	public JLabel getHealthpoints() {
		return healthpoints;
	}

	public JLabel getDeck() {
		return deck;
	}

	public JLabel getCurrentmana() {
		return currentmana;
	}

	public JLabel getPlayerName() {
		return playerName;
	}

	public JLabel getTotalmana() {
		return totalmana;
	}

}
