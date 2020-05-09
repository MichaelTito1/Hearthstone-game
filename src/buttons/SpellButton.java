package buttons;
import model.cards.spells.*;

import java.awt.BorderLayout;

import javax.swing.JLabel;

public class SpellButton extends CardButton{
	private JLabel spellName = new JLabel("");
	private JLabel manaCost = new JLabel("");
	private JLabel rarity = new JLabel("");
	
	public SpellButton() {
		super();
		Spell spell = (Spell)(this.getCard());
		spellName.setText(spell.getName());
		manaCost.setText("MC:"+spell.getManaCost()+"");
		rarity.setText(spell.getRarity()+"");
		
		spellName.setVisible(true);
		manaCost.setVisible(true);
		rarity.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.add(spellName, BorderLayout.CENTER);
		this.add(manaCost, BorderLayout.SOUTH);
		this.add(rarity, BorderLayout.NORTH);
		this.setVisible(true);
		this.validate();
	}
	
	public SpellButton(String s, Spell c) {
		super(s, c);
		Spell spell = (Spell)(this.getCard());
		spellName.setText(spell.getName());
		manaCost.setText("MC:"+spell.getManaCost()+"");
		rarity.setText(spell.getRarity()+"");
		
		spellName.setVisible(true);
		manaCost.setVisible(true);
		rarity.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.add(spellName, BorderLayout.CENTER);
		this.add(manaCost, BorderLayout.SOUTH);
		this.add(rarity, BorderLayout.NORTH);
		this.setVisible(true);
		this.validate();
	}

	public JLabel getSpellName() {
		return spellName;
	}

	public void setSpellName(JLabel name) {
		this.spellName = name;
	}

	public JLabel getManaCost() {
		return manaCost;
	}

	public void setManaCost(JLabel manaCost) {
		this.manaCost = manaCost;
	}

	public JLabel getRarity() {
		return rarity;
	}

	public void setRarity(JLabel rarity) {
		this.rarity = rarity;
	}
}
