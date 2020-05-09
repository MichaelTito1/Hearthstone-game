package gui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import buttons.CardButton;
import buttons.MinionButton;

public class BoardPanel extends JPanel{
	private FieldPanel lower, upper;
	 private JButton endturn ;
	
	public BoardPanel() {
		super(new BorderLayout());
		
		lower = new FieldPanel(true);
		upper = new FieldPanel(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension(((int) dim.getWidth())-((int) dim.getWidth())/5, (int) dim.getHeight()));
		
		//this.setPreferredSize(new Dimension(900,600));
		this.setOpaque(false);
		this.add(lower, BorderLayout.SOUTH);
		this.add(upper, BorderLayout.NORTH);
	
		endturn = new JButton("End Turn");
		this.add(endturn,BorderLayout.WEST);
		endturn.setSize(new Dimension(75,50));
		endturn.setEnabled(true);
		endturn.setVisible(true);
		
		this.setVisible(true);
		this.validate();
	}

	public FieldPanel getLower() {
		return lower;
	}

	public FieldPanel getUpper() {
		return upper;
	}
	public JButton getEndturn() {
		return endturn;
	}

	
	public ArrayList<CardButton> getAllButtons() {
		ArrayList<CardButton> ans = new ArrayList<CardButton>();
		
		ArrayList<MinionButton> hand1 = lower.getGround().getPlayedcards();
		ArrayList<CardButton> spell1 = lower.getHand().getCardsbuttons();
		ArrayList<MinionButton> hand2 = upper.getGround().getPlayedcards();
		ArrayList<CardButton> spell2 = upper.getHand().getCardsbuttons();
		
		for(MinionButton i : hand1)
			ans.add(i);
		for(MinionButton i : hand2)
			ans.add(i);
		for(CardButton i : spell1)
			ans.add(i);
		for(CardButton i : spell2)
			ans.add(i);
		return ans;
	}
}
