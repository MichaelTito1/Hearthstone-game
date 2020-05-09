package gui.panels.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import buttons.CardButton;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class HandPanel extends JPanel {
	private ArrayList<CardButton> cardsbuttons = new ArrayList<CardButton>(10);
	
	public HandPanel(boolean lower){
		//super(new GridLayout(1,10,1,1));
		super(new FlowLayout((FlowLayout.CENTER)));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(((int) dim.getWidth())-((int) dim.getWidth())/3, (int) dim.getHeight()/4));
		this.setOpaque(false);
		cardsbuttons= new ArrayList <CardButton>();
		
		//for testing
		  //Minion m = new Minion("Kalycgos", 9, Rarity.LEGENDARY, 8, 8, false, false, false);
		
		for(int i =0; i<10; i++) {
			CardButton cb = new CardButton();
			cb.setVisible(false);
			if(lower) {
				cb.setActionCommand("Player1");
				this.setAlignmentX(BOTTOM_ALIGNMENT);
			}
			else {
				cb.setActionCommand("Player2");
				this.setAlignmentX(TOP_ALIGNMENT);
			}
			cardsbuttons.add(cb);
			cardsbuttons.get(i).setPreferredSize(new Dimension(47,70));
			//ImageIcon image = new ImageIcon("res/images/card back.png");

			//cb.setRolloverIcon(image);
			cb.setBackground(Color.gray);

			this.add(cardsbuttons.get(i));
		}
		//this.setSize(new Dimension(50,100));
		this.setOpaque(false);
		this.setVisible(true);
		this.validate();
	}
	public ArrayList<CardButton> getCardsbuttons() {
		return cardsbuttons;
	}
	public void setCardsbuttons(ArrayList<CardButton> cardsbuttons) {
		this.cardsbuttons = cardsbuttons;
	}

}
