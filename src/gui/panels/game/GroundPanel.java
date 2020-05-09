package gui.panels.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import buttons.MinionButton;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class GroundPanel extends JPanel {
	private ArrayList<MinionButton> playedcards = new ArrayList<MinionButton>();

	public GroundPanel(Boolean lower) {

		super(new GridLayout(1, 7, 2, 50));
		// lower=false;
		this.setOpaque(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(
				new Dimension((int) dim.getWidth() - ((int) dim.getWidth()) / 2, (int) dim.getHeight() / 5));

		for (int i = 0; i < 7; i++) {
			// MinionButton a = new MinionButton("<html><font size=-70>"+"Name: King
			// Krush"+"<br>"+" ManaCost: 9"+ "<br>"+ "Rarity:LEGENDARY"+ "<br>"
			// +"Attack:8"+"<br>"+ "HP:8"+"<br>"+
			// "Taunt:false"+"<br>"+"Divine:false"+"<html>");
			// Minion m = new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false,
			// false);
			// m.setSleeping(false);
			MinionButton a = new MinionButton();
			a.setPreferredSize(new Dimension(95, 145));

			ImageIcon image = new ImageIcon("res/images/card back.png");

			Image img = image.getImage();
			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img, 200, 200, a.getWidth(), a.getHeight(), null, null);
			ImageIcon newIcon = new ImageIcon(bi);

			a.setRolloverIcon(newIcon);
			// you can set it to be the card itself
			a.setBackground(Color.gray);

			playedcards.add(a);

			playedcards.get(i).setEnabled(true);
			playedcards.get(i).setOpaque(true);
			playedcards.get(i).setVisible(false);
			this.add(playedcards.get(i));
		}
		// this.setSize(new Dimension(700,150));
		this.setVisible(true);
		this.validate();

	}

	public ArrayList<MinionButton> getPlayedcards() {
		return playedcards;
	}

	public void setPlayedcards(ArrayList<MinionButton> playedcards) {
		this.playedcards = playedcards;
	}

}
