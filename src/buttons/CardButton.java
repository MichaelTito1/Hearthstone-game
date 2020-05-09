package buttons;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import model.cards.Card;

public class CardButton extends JButton {

	private Card card;

	public CardButton() {
		super("", new ImageIcon("res/images/card back.png"));
		//this.setBorder(new EmptyBorder(0, 0, 0, 0));
		//this.setBorder(BorderFactory.createEmptyBorder());
		//this.setForeground(Color.white);
		this.setVisible(true);
	}

	public CardButton(String s, Card c) {
		// TODO Auto-generated constructor stub
		super(s, new ImageIcon("res/images/card back.png"));
		card = c;
		this.setVisible(true);
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
