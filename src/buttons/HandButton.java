package buttons;

import model.cards.Card;

public class HandButton extends CardButton {

	public HandButton() {
		super();
		this.setVisible(true);
	}
	
	public HandButton(String s, Card c) {
		super(s, c);
		this.setVisible(true);
	}
}
