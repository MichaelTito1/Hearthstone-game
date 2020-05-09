package gui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
	
	JLabel image ;
	JButton attack,cast,play;
	JButton cancel;

	public CardPanel(){
		super(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension((int) dim.getWidth()/5, (int) dim.getHeight()/3));
		image= new JLabel();
		//image.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("res/images/Chromaggus(14451).png")).getImage().getScaledInstance((int) this.getWidth(), (int) this.getHeight()-(int) this.getHeight()/3), Image.SCALE_SMOOTH));
		image.setPreferredSize(new Dimension((int) this.getWidth()-200, (int) this.getHeight()-(int) this.getHeight()/3-200));
		//image.setIcon(new ImageIcon("res/images/Chromaggus(14451).png"));
		attack= new JButton("Attack!!");
		//attack.setPreferredSize(new Dimension((int) this.getWidth()/3, (int) this.getHeight()/5));
		attack.setOpaque(false);
		cast = new JButton("Cast Spell");
		cast.setOpaque(false);
		//cast.setPreferredSize(new Dimension((int) this.getWidth()/3, (int) this.getHeight()/5));
		play = new JButton("Summon");
		play.setOpaque(false);
		//play.setPreferredSize(new Dimension((int) this.getWidth()/3, (int) this.getHeight()/5));
		
		// Cancel button for the attacking mode of a minion
    	cancel = new JButton("Cancel");
		cancel.setOpaque(false);
//		cancel.setSize(new Dimension(30,50));
		attack.setFont(new Font("Arial", Font.PLAIN, 6));
		cast.setFont(new Font("Arial", Font.PLAIN, 6));
		play.setFont(new Font("Arial", Font.PLAIN, 6));
		cancel.setFont(new Font("Arial", Font.PLAIN, 6));
		
		attack.setPreferredSize(play.getPreferredSize());
		cast.setPreferredSize(play.getPreferredSize());
		cancel.setPreferredSize(play.getPreferredSize());
		JPanel tmp= new JPanel(new FlowLayout());
		tmp.add(attack);
		tmp.add(cast);
		tmp.add(play);
		tmp.add(cancel);
		this.add(image,BorderLayout.CENTER);
		this.add(tmp,BorderLayout.SOUTH);
		
		tmp.setSize(new Dimension((int) this.getWidth(), (int) this.getHeight()/5));
		tmp.setOpaque(false);
		tmp.setVisible(true);
		
		image.setOpaque(false);
		image.setVisible(true);
		attack.setVisible(true);
		play.setVisible(true);
		cast.setVisible(true);
		this.validate();
		
		
	}
	public JLabel getImage() {
		return image;
	}
	public JButton getAttack() {
		return attack;
	}
	public JButton getCast() {
		return cast;
	}
	public JButton getPlay() {
		return play;
	}
	
	public JButton getCancel() {
		return cancel;
	}

}
