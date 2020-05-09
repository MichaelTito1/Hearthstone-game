package gui.panels.game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FieldPanel extends JPanel {
	
	private HandPanel hand;
	private GroundPanel ground;
	private JButton endturn ;
	public FieldPanel(boolean lower) {
		super(new FlowLayout());
//		lower=false;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension((int) dim.getWidth()-((int) dim.getWidth())/5, (int) dim.getHeight()/2));
		
		hand = new HandPanel(lower);
		ground = new GroundPanel(lower); 
		//hand.setSize(((int) dim.getWidth())-((int) dim.getWidth())/5, (int) dim.getHeight()/4);
		//ground.setSize(((int) dim.getWidth())-((int) dim.getWidth())/5, (int) dim.getHeight()/4);
		if(lower) {
			this.add(ground);
			this.add(hand);
		}
		else {
			this.add(hand);
			this.add(ground);
		}
		this.setVisible(true);
		this.setOpaque(false);

        if(lower){
        endturn = new JButton("End Turn");
		this.add(endturn);
		endturn.setSize(new Dimension(75,50));
		endturn.setEnabled(true);
		endturn.setVisible(true);}

	}
    
	public JButton getEndturn() {
		return endturn;
	}
	public HandPanel getHand() {
		return hand;
	}

	public GroundPanel getGround() {
		return ground;
	}

}
