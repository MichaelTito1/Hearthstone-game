package buttons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import model.cards.minions.Minion;

public class MinionButton extends CardButton {
	private JLabel attack = new JLabel(""); // attacking power of the minion
	private JLabel sleeping = new JLabel(""); // indicates whether this minion is sleeping
	private JLabel minionName = new JLabel(""); // minion name
	private JLabel manaCost = new JLabel("");
	private JLabel currentHP = new JLabel("");
	private JLabel divine = new JLabel("");
	private JLabel charge = new JLabel("");
	private JLabel taunt = new JLabel("");
	private JLabel rarity = new JLabel("");

	public MinionButton() {
		super();
		Minion m = (Minion) (this.getCard());

		if (m != null) {
			// minionName.setText(m.getName());
			attack.setText("ATK" + m.getAttack());
			if (m.isSleeping())
				sleeping.setText(m.isSleeping() ? "Sleep" : "Awake");
			manaCost.setText("MC" + m.getManaCost());
			currentHP.setText("HP" + m.getCurrentHP());
			if (m.isDivine())
				divine.setText(m.isDivine() ? "Divine" : "Not divine");
			rarity.setText(m.getRarity() + "");
			if (m.isTaunt())
				taunt.setText(m.isTaunt() ? "Taunt" : "");
			/*
			 * HOW TO SET CHARGE?!!
			 */

		}

		minionName.setVisible(true);
		attack.setVisible(true);
		sleeping.setVisible(true);
		manaCost.setVisible(true);
		currentHP.setVisible(true);
		divine.setVisible(true);
		charge.setVisible(true);
		taunt.setVisible(true);
		rarity.setVisible(true);

		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(1, 1, 1, 1);
		this.add(attack, gbc);
		gbc.gridy++;
		this.add(sleeping,gbc);
		gbc.gridy++;
		this.add(manaCost, gbc);
		gbc.gridy++;
		this.add(currentHP, gbc);
		gbc.gridy++;
		this.add(divine,gbc);
		gbc.gridy++;
		this.add(charge,gbc);
		gbc.gridy++;
		this.add(taunt, gbc);
		gbc.gridy++;
		this.add(rarity, gbc);
		gbc.gridy++;
		this.add(minionName, gbc);
		gbc.gridy++;
		//
		this.setVisible(true);
		this.validate();
	}

	public MinionButton(String s, Minion c) {
		super(s, c);
		Minion m = (Minion) (this.getCard());
		if (m != null) {
			minionName.setText(m.getName());
			attack.setText("" + m.getAttack());
			if (m.isSleeping())
				sleeping.setText(m.isSleeping() + "");
			manaCost.setText(m.getManaCost() + "");
			currentHP.setText(m.getCurrentHP() + "");
			if (m.isDivine())
				divine.setText(m.isDivine() + "");
			rarity.setText(m.getRarity() + "");
			if (m.isTaunt())
				taunt.setText(m.isTaunt() + "");
			/*
			 * HOW TO SET CHARGE?!!
			 */
		}
		minionName.setVisible(true);
		attack.setVisible(true);
		sleeping.setVisible(true);
		manaCost.setVisible(true);
		currentHP.setVisible(true);
		divine.setVisible(true);
		charge.setVisible(true);
		taunt.setVisible(true);
		rarity.setVisible(true);

		this.setLayout(new BorderLayout());
		this.add(minionName, BorderLayout.CENTER);
		this.add(attack, BorderLayout.SOUTH);
		this.add(sleeping, BorderLayout.NORTH);
		this.add(manaCost, BorderLayout.SOUTH);
		this.add(currentHP, BorderLayout.PAGE_START);
		this.add(divine, BorderLayout.NORTH);
		this.add(charge, BorderLayout.NORTH);
		this.add(taunt, BorderLayout.WEST);
		this.add(rarity, BorderLayout.PAGE_END);

		// this.setBorder(new EmptyBorder(0,0,0,0));
		// this.setBorder(BorderFactory.createEmptyBorder());

		this.setVisible(true);
		this.validate();

	}

	public JLabel getAttack() {
		return attack;
	}

	public void setAttack(JLabel attack) {
		this.attack = attack;
	}

	public JLabel getSleeping() {
		return sleeping;
	}

	public void setSleeping(JLabel sleeping) {
		this.sleeping = sleeping;
	}

	public JLabel getMinionName() {
		return minionName;
	}

	public void setMinionName(JLabel name) {
		this.minionName = name;
	}

	public JLabel getManaCost() {
		return manaCost;
	}

	public void setManaCost(JLabel manaCost) {
		this.manaCost = manaCost;
	}

	public JLabel getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(JLabel currentHP) {
		this.currentHP = currentHP;
	}

	public JLabel getDivine() {
		return divine;
	}

	public void setDivine(JLabel divine) {
		this.divine = divine;
	}

	public JLabel getCharge() {
		return charge;
	}

	public void setCharge(JLabel charge) {
		this.charge = charge;
	}

	public JLabel getTaunt() {
		return taunt;
	}

	public void setTaunt(JLabel taunt) {
		this.taunt = taunt;
	}

	public JLabel getRarity() {
		return rarity;
	}

	public void setRarity(JLabel rarity) {
		this.rarity = rarity;
	}

}
