package gui.panels.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
	 
	 private CardPanel cardpanel;
	 private Stats statsplayer1;
	private Stats statsplayer2;
	public SidePanel(){
		super(new BorderLayout());
		cardpanel= new CardPanel();
		statsplayer1=new Stats();
		statsplayer2= new Stats();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension((int) dim.getWidth()/5, (int) dim.getHeight()));
		 
		//cardpanel.setSize(new Dimension((int) dim.getWidth()/5, (int) dim.getHeight()/3)); dimensions will be dismissed as you return to its class
		//statsplayer1.setSize(new Dimension((int) dim.getWidth()/5, (int) dim.getHeight()/3));
		//statsplayer2.setSize(new Dimension((int) dim.getWidth()/5, (int) dim.getHeight()/3));
		this.add(cardpanel,BorderLayout.CENTER);
		this.add(statsplayer1,BorderLayout.NORTH);
		this.add(statsplayer2,BorderLayout.SOUTH);
		cardpanel.setOpaque(false);
		statsplayer1.setOpaque(false);
		statsplayer2.setOpaque(false);
		cardpanel.setVisible(true);
		statsplayer1.setVisible(true);
		statsplayer2.setVisible(true);
		this.setOpaque(false);
		this.setVisible(true);
		this.validate();
		
		
	}
	public CardPanel getCardpanel() {
		return cardpanel;
	}
	public void setCardpanel(CardPanel cardpanel) {
		this.cardpanel = cardpanel;
	}
	public Stats getStatsplayer1() {
		return statsplayer1;
	}
	public void setStatsplayer1(Stats statsplayer1) {
		this.statsplayer1 = statsplayer1;
	}
	public Stats getStatsplayer2() {
		return statsplayer2;
	}
	public void setStatsplayer2(Stats statsplayer2) {
		this.statsplayer2 = statsplayer2;
	}
	
	public ArrayList<JButton> getAllButtons() {
		ArrayList<JButton> ans = new ArrayList<JButton>();
		ans.add(statsplayer1.getHeropowerused());
		ans.add(statsplayer2.getHeropowerused());
		ans.add(statsplayer1.getAttackHero());
		ans.add(statsplayer2.getAttackHero());
		ans.add(cardpanel.getAttack());
		ans.add(cardpanel.getCast());
		ans.add(cardpanel.getPlay());
		ans.add(cardpanel.getCancel());
		return ans;
	}

}
