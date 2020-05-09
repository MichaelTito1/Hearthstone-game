package launcher;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import buttons.CardButton;
import buttons.MinionButton;
import buttons.SpellButton;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import gui.GUI;
import gui.panels.game.BoardPanel;
import gui.panels.game.FieldPanel;
import gui.panels.game.SidePanel;
import gui.panels.game.Stats;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;

public class Controller implements ActionListener, GameListener {
	private GUI gui;
	private Game game;
	private boolean attackMode, spellMode;
	private CardButton lastButton;
	private Hero upper;
	private Hero lower;
	String p1,p2;

	public Controller(Game g, GUI gui) {
		game = g;
		upper = game.getFirstHero();
		lower = game.getSecondHero();
		this.gui = gui;

		p1 = JOptionPane.showInputDialog("Enter the first player name");
		p2 = JOptionPane.showInputDialog("Enter the second player name");
		this.gui.getSide().getStatsplayer1().getPlayerName().setText("Name: " + p1);
		this.gui.getSide().getStatsplayer2().getPlayerName().setText("Name: " + p2);

		game.setListener(this);
		addActionListeners();
		update();
	}

	public void addActionListeners() {
		SidePanel sp = gui.getSide();
		ArrayList<JButton> arr = sp.getAllButtons();
		for (JButton i : arr) {
			i.addActionListener(this);
			i.setActionCommand(i.getText());
		}

		BoardPanel bp = gui.getBoard();
		ArrayList<CardButton> a = bp.getAllButtons();
		for (CardButton i : a) {
			i.addActionListener(this);
			// i.setActionCommand(i.getCard().getName());
		}
		bp.getLower().getEndturn().addActionListener(this);
		bp.getLower().getEndturn().setActionCommand(bp.getLower().getEndturn().getText());
	}

	public void update() {
		updateHand();
		updateFloor();
		updateSide();
	}

	public void updateSide() {
		settingSide(game.getCurrentHero());
		settingSide(game.getOpponent());
	}

	public void settingSide(Hero player) {

		if (player == lower) {
			gui.getSide().getStatsplayer2().getHealthpoints().setText("Health Points: " + player.getCurrentHP() + " Pts");
			gui.getSide().getStatsplayer2().getCurrentmana().setText("Mana Crystals: " + player.getCurrentManaCrystals() + " Manas");
			gui.getSide().getStatsplayer2().getDeck().setText("Remaining Cards: "+player.getDeck().size());
			gui.getSide().getStatsplayer2().getTotalmana().setText("Total Mana: " + player.getTotalManaCrystals());
		} else {
			gui.getSide().getStatsplayer1().getHealthpoints().setText("Health Points: " + player.getCurrentHP() + " Pts");
			gui.getSide().getStatsplayer1().getCurrentmana().setText("Mana Crystals: "  + player.getCurrentManaCrystals() + " Manas");
			gui.getSide().getStatsplayer1().getDeck().setText("Remaining Cards: "+player.getDeck().size());
			gui.getSide().getStatsplayer1().getTotalmana().setText("Total Mana: " + player.getTotalManaCrystals());
		}

	}

	public void updateHand() {
		settingHandCards(game.getCurrentHero(), true);
		settingHandCards(game.getOpponent(), false);
	}

	public void settingHandCards(Hero player, boolean current) {
		ArrayList<Card> hand = player.getHand();
		ArrayList<CardButton> handButtons;
		if (player == lower)
			handButtons = gui.getBoard().getLower().getHand().getCardsbuttons();
		else
			handButtons = gui.getBoard().getUpper().getHand().getCardsbuttons();

		int i = 0;

		for (; i < hand.size(); i++) {
			Card card = hand.get(i);
			CardButton button = handButtons.get(i);
			button.setActionCommand(card.getName());
			if (card instanceof Minion) {
				Minion monster = (Minion) card;
				button.setText("ATK/" + monster.getAttack() + " DEF/" + monster.getRarity());
				button.setCard(card);
			} else {
				button.setCard(card);
				Spell spell = (Spell) card;
				button.setText(spell.getName());
			}

			// button.setCard(card);
			// button.setText(card.getName());
			// if (card instanceof Minion) {
			// Minion minion=(Minion)card;
			// //button =new MinionButton(card.getName(),minion);
			// } else {
			// Spell spell=(Spell)card;
			// //button=new SpellButton(card.getName(),spell);
			// }

			button.setVisible(true);
			button.setEnabled(true);
			if (!current) {
				button.setEnabled(false);
			}

			// if (card instanceof Minion) {
			// Minion monster = (Minion) card;
			// button.setText("ATK/" + monster.getAttack() + " DEF/" + monster.getRarity());
			// } else
			// button.setText("");

		}
		for (; i < 10; i++) {
			CardButton button = handButtons.get(i);
			button.setVisible(false);
		}
	}

	public void updateFloor() {
		setFloorCards(game.getCurrentHero(), true);
		setFloorCards(game.getOpponent(), false);
	}

	public void setFloorCards(Hero player, boolean current) {
		ArrayList<Minion> minions = player.getField();
		ArrayList<MinionButton> minionButtons;
		if (player == lower)
			minionButtons = gui.getBoard().getLower().getGround().getPlayedcards();
		else
			minionButtons = gui.getBoard().getUpper().getGround().getPlayedcards();

		for (int i = 0; i < minions.size(); i++) {

			Minion minion = minions.get(i);
			MinionButton button = minionButtons.get(i);
			button.getMinionName().setText(minion.getName());
			button.setCard(minion);
			// button.getMonsterMode().setText(monster.getMode() == Mode.ATTACK ? "Attack" :
			// "Defense");
			button.getSleeping().setText(minion.isSleeping() ? "Sleep" : "Awake");
			button.getAttack().setText("ATK "+String.valueOf(minion.getAttack()));
			button.getCurrentHP().setText("HP "+String.valueOf(minion.getCurrentHP()));
			button.getManaCost().setText("M "+String.valueOf(minion.getManaCost()));;
			button.getRarity().setText("R "+String.valueOf(minion.getRarity()));
			//button.getCharge().setText("C "+String.valueOf("I dont know from where the value"));
			button.setEnabled(true);
			// if (!current) {
			// 	button.setEnabled(false);
			// }
			button.getMinionName().setForeground(Color.WHITE);
			button.getCurrentHP().setForeground(Color.WHITE);
			button.getManaCost().setForeground(Color.WHITE);
			button.getRarity().setForeground(Color.WHITE);
			button.getAttack().setForeground(Color.WHITE);
			button.getSleeping().setForeground(Color.WHITE);
			button.setVisible(true);
			button.setText(minion.getName());

		}
		for (int i = minions.size(); i < 7; i++) {
			MinionButton button = minionButtons.get(i);
			button.getMinionName().setText("");
			button.getAttack().setText("");
			button.setEnabled(false);
			button.setVisible(false);
		}

	}

	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) (e.getSource());
		String com = e.getActionCommand();
		if (b.getText().equals(""))
			return;
		System.out.print(com);
		// Get the field of the player whose turn is this turn.
		FieldPanel fieldPanel;
		Stats stats;
		if (game.getCurrentHero() == game.getFirstHero()) {
			fieldPanel = gui.getBoard().getLower();
			stats = gui.getSide().getStatsplayer1();
		} else {
			fieldPanel = gui.getBoard().getUpper();
			stats = gui.getSide().getStatsplayer2();
		}

		if (attackMode) {
			if (com.equals("Attack hero")) {
				if(b == stats.getAttackHero())
					JOptionPane.showMessageDialog(gui, "You cannot attack your own hero!");
				else {
					try {
						game.getCurrentHero().attackWithMinion((Minion) (lastButton.getCard()), game.getOpponent());
						update();
					}
					catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
							| InvalidTargetException e1) {
						attackMode = false;
						gui.getSide().getCardpanel().getAttack().setVisible(true);
						lastButton = null;
						JOptionPane.showMessageDialog(gui, e1.getMessage());
					}
				}
			}
			else if (com.equals("Cancel")) {
				System.out.println("cancel");
				lastButton = null;
				gui.getSide().getCardpanel().getAttack().setVisible(true);
				attackMode = false;
				gui.getSide().getCardpanel().getAttack().setVisible(true);
				gui.getSide().getCardpanel().getPlay().setVisible(true);
				gui.getSide().getCardpanel().getCast().setVisible(true);
			} else if (com.equals("End Turn")) { // if end turn is pressed
				try {
					game.endTurn();
					update();
					gui.getSide().getCardpanel().getImage().setIcon(new ImageIcon(""));
					
				} catch (FullHandException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(gui, e1.getMessage());
					update();
				}
				
			}
			else if (b instanceof MinionButton) {
				try {
					game.getCurrentHero().attackWithMinion((Minion) (lastButton.getCard()),	
							(Minion) (((MinionButton) b).getCard()));

				} catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
						| NotSummonedException e1) {
					JOptionPane.showMessageDialog(gui, e1.getMessage());
				}
				/*
				 * WE SHOULD UPDATE HP, ETC.. HERE AFTER ATTACK YA MICHAAEELL
				 */
				update();
			}
			else if(b instanceof CardButton){
				JOptionPane.showMessageDialog(gui, "You cannot attack a card in hand.");
			}
			update();
			gui.getSide().getCardpanel().getImage().setIcon(new ImageIcon(""));
			lastButton = null;
			gui.getSide().getCardpanel().getAttack().setVisible(true);
			attackMode = false;
			gui.getSide().getCardpanel().getAttack().setVisible(true);
			gui.getSide().getCardpanel().getPlay().setVisible(true);
			gui.getSide().getCardpanel().getCast().setVisible(true);
		} 
		else if(spellMode) {
			Spell c = (Spell) lastButton.getCard();
			if (com.equals("Cancel")) {
				spellMode = false;
				lastButton = null;
			}
			else if(c instanceof MinionTargetSpell) {
				if(b instanceof MinionButton) {
					Minion m = (Minion) ((MinionButton) b).getCard();
					try {
						game.getCurrentHero().castSpell((MinionTargetSpell)c, m);
					} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
						spellMode = false;
						lastButton = null;
						JOptionPane.showMessageDialog(gui, e1.getMessage());
					}
				}
			}
			else if(c instanceof LeechingSpell) {
				if(b instanceof MinionButton) {
					Minion m = (Minion) ((MinionButton) b).getCard();
					try {
						game.getCurrentHero().castSpell((LeechingSpell) c, m);
					} catch (NotYourTurnException | NotEnoughManaException e1) {
						spellMode = false;
						lastButton = null;
						JOptionPane.showMessageDialog(gui, e1.getMessage());
					}
				}
			}
			else if(c instanceof HeroTargetSpell) {
				if (com.equals("Attack hero")) {
					if(b == stats.getAttackHero())
						JOptionPane.showMessageDialog(gui, "You cannot damage your own hero!");
					else {
						try {
							game.getCurrentHero().castSpell((HeroTargetSpell) c, game.getOpponent());
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							spellMode = false;
							lastButton = null;
							JOptionPane.showMessageDialog(gui, e1.getMessage());
						}
					}

				}

			}
			else if(c instanceof AOESpell) {
				try {
					game.getCurrentHero().castSpell((AOESpell) c, game.getOpponent().getField());
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					spellMode = false;
					lastButton = null;
					JOptionPane.showMessageDialog(gui, e1.getMessage());
				}
			}
			
			update();
			gui.getSide().getCardpanel().getImage().setIcon(new ImageIcon(""));
			lastButton = null;
			spellMode = false;
			gui.getSide().getCardpanel().getAttack().setVisible(true);
			gui.getSide().getCardpanel().getAttack().setVisible(true);
			gui.getSide().getCardpanel().getPlay().setVisible(true);
			gui.getSide().getCardpanel().getCast().setVisible(true);
		}
		else if (com.equals("Summon")) {
			if (lastButton == null) {
				JOptionPane.showMessageDialog(gui, "Choose a minion from your hand first");
			} else {
				if (lastButton instanceof MinionButton) {
					lastButton = (MinionButton) lastButton;
					try {
						game.getCurrentHero().playMinion((Minion) lastButton.getCard());
					} 
					catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
						JOptionPane.showMessageDialog(gui, e1.getMessage());
					}
				}
				else {
					JOptionPane.showMessageDialog(gui, "You can only summon minions!");
				}
			}
			update();
		} 
		else if (com.equals("Cast Spell")) {
			if (lastButton == null) {
				JOptionPane.showMessageDialog(gui, "Choose a spell from your hand first");
			} else {
				if (lastButton instanceof SpellButton) {
					Spell c = (Spell) lastButton.getCard();
					if(c instanceof FieldSpell) {
						try {
							game.getCurrentHero().castSpell( (FieldSpell) c);
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(c instanceof MinionTargetSpell) {
						spellMode = true;
						JOptionPane.showMessageDialog(gui, "Choose your target minion");
					}
					else if(c instanceof LeechingSpell) {
						spellMode = true;
						JOptionPane.showMessageDialog(gui, "Choose your target minion");
					}
					else if(c instanceof HeroTargetSpell) {
						spellMode = true;
						JOptionPane.showMessageDialog(gui, "Choose your target hero");
					}
					else if(c instanceof AOESpell) {
						spellMode = true;
						JOptionPane.showMessageDialog(gui, "Choose your target hero");
					}
				} else {
					JOptionPane.showMessageDialog(gui, "You can only cast spells!");
				}
			}
		} else if (com.equals("Use Hero Power")) {
			if (stats.getHeropowerused() == b) {
				try {
					game.getCurrentHero().useHeroPower();
					update();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullHandException | FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(gui, e1.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(gui, "This is not this hero's turn.");
			}
		} else {
			if (b instanceof MinionButton) {
				lastButton = (MinionButton) b;
				if (gui.getBoard().getLower().getGround().getPlayedcards().contains(b)
						|| gui.getBoard().getUpper().getGround().getPlayedcards().contains(b)) {
					gui.getSide().getCardpanel().getCast().setVisible(false);
					gui.getSide().getCardpanel().getPlay().setVisible(false);
					// gui.getSide().getCardpanel().getCancel().setVisible(false);

					if (((MinionButton) b).getSleeping().getText().equals("Awake")) {
						gui.getSide().getCardpanel().getAttack().setVisible(true);
					}

				} else if (fieldPanel.getHand().getCardsbuttons().contains(b)) {
					gui.getSide().getCardpanel().getCast().setVisible(false);
					// gui.getSide().getCardpanel().getCancel().setVisible(false);
					gui.getSide().getCardpanel().getPlay().setVisible(true);

				}
				// display minion image on the card panel

				String n = ((MinionButton) b).getMinionName().getText();
				gui.getSide().getCardpanel().getImage().setIcon(new ImageIcon("res/images/" + n + ".png"));
			} else if (b instanceof SpellButton) {
				lastButton = (SpellButton) b;
				gui.getSide().getCardpanel().getCast().setVisible(true);
				gui.getSide().getCardpanel().getPlay().setVisible(false);
				// gui.getSide().getCardpanel().getCancel().setVisible(false);

				// display spell image on the card panel
				String n = ((SpellButton) b).getSpellName().getText();
				gui.getSide().getCardpanel().getImage().setIcon(new ImageIcon("res/images/" + n + ".png"));
			} else if (com.equals("End Turn")) { // if end turn is pressed
				try {
					game.endTurn();
					update();
					gui.getSide().getCardpanel().getImage().setIcon(new ImageIcon(""));
					attackMode = false;
					gui.getSide().getCardpanel().getAttack().setVisible(true);
					gui.getSide().getCardpanel().getPlay().setVisible(true);
					gui.getSide().getCardpanel().getCast().setVisible(true);
					//gui.getSide().getCardpanel().getAttack().setVisible(false);
					// gui.getSide().getCardpanel().getCancel().setVisible(false);
				} catch (FullHandException | CloneNotSupportedException e1) {
					update();
					JOptionPane.showMessageDialog(gui, e1.getMessage());
					
				}
			} else if (com.equals("Attack!!")) {
				if (lastButton == null)
					JOptionPane.showMessageDialog(gui, "Choose your attacker minion first");
				else {
					if(! (lastButton instanceof MinionButton))
						JOptionPane.showMessageDialog(gui, "You can only attack with minions.");
					else if(! ( game.getCurrentHero().getField().contains(((MinionButton) lastButton).getCard())) ) {
						JOptionPane.showMessageDialog(gui, "You don't have this minion on the field.");
					}
					else {
						gui.getSide().getCardpanel().getAttack().setVisible(false);
						attackMode = true;
						JOptionPane.showMessageDialog(gui, "Choose your target minion/hero");
						// gui.getSide().getCardpanel().getCancel().setVisible(true);
					}
				}
			} else if (b instanceof CardButton) {
				String x = "res/images/" + com + ".png";
				gui.getSide().getCardpanel().getImage().setIcon(new ImageIcon(x));
				CardButton cardButton = ((CardButton) b);
				if (cardButton.getCard() instanceof Minion) {
					lastButton = new MinionButton(cardButton.getCard().getName(), (Minion) cardButton.getCard());
				} else {
					lastButton = new SpellButton(cardButton.getCard().getName(), (Spell) cardButton.getCard());

				}
				gui.getSide().getCardpanel().getAttack().setVisible(true);
				gui.getSide().getCardpanel().getPlay().setVisible(true);
				gui.getSide().getCardpanel().getCast().setVisible(true);

			}
		}

	}

	@Override
	public void onGameOver() {
		// TODO Auto-generated method stub
		if(game.getFirstHero().getCurrentHP() == 0) {
			JOptionPane.showMessageDialog(gui, "Winner is " + p2);
		}
		else if(game.getSecondHero().getCurrentHP() == 0) {
			JOptionPane.showMessageDialog(gui, "Winner is " + p1);
		}
		gui.setVisible(false);
		gui.getMusic().close();
		System.exit(0);
	}

}
