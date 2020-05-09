package launcher;

import engine.Game;
import exceptions.FullHandException;
import gui.GUI;
import gui.panels.start.StartPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class Launcher extends JFrame implements ActionListener{
	private StartPanel st;
	private Clip music;
	private Hero hero1,hero2;



	public Launcher() {
		super();
		this.setLayout(new BorderLayout());
		this.setTitle("Welcome to Hearthstone");
		ImageIcon image = new ImageIcon("res/images/startbackground.jpg");
		JLabel label = new JLabel(image);
		this.setContentPane(label);
		this.setSize(image.getIconWidth(), image.getIconHeight()+45);
		this.setLocation(10,10);
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		st = new StartPanel();
		this.add(st);

		// adding action listeners to each button
		st.getStartButton().addActionListener(this);
		st.getStartButton().setActionCommand("Start");
		for(int i = 0; i < 5; i++) {
			String title = st.getPlayer1().getHero()[i].getText();
			st.getPlayer1().getHero()[i].addActionListener(this);
			st.getPlayer1().getHero()[i].setActionCommand(title + "1");

			st.getPlayer2().getHero()[i].addActionListener(this);
			st.getPlayer2().getHero()[i].setActionCommand(title + "2");
		}

		//Play some music
		File audio = new File("res/sound/Main_Title.wav");
		try {
			AudioInputStream a = AudioSystem.getAudioInputStream(audio);
			music = AudioSystem.getClip();
			music.open(a);
			music.loop(100);
		}
		catch(IOException | UnsupportedAudioFileException e) {}
		catch(LineUnavailableException e) {}

		this.setVisible(true);
		this.validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Start")&&hero1!=null&&hero2!=null){
			try {
				start();
			} catch (FullHandException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Hunter1")){
			JPanel panel = st.getPlayer1().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Rexxar(484).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\n2 damage to enemy\n     hero\n"
					+ "2 KillCommand \n      Spells\n"
					+ "2 MultiShot spells\n"
					+ "Legendary Minion:\n King Krush");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero1= new Hunter();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Mage1")){
			JPanel panel = st.getPlayer1().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Jaina_Proudmoore(320).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nDeal 1 damage\n"
					+ "2 Polymorph Spells\n"
					+ "2 FlameStrike spells\n"
					+ "2 PyroBlast spells\n"
					+ "Legendary Minion:\n Kalycgos");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero1=new Mage();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Paladin1")){
			JPanel panel = st.getPlayer1().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Uther_Lightbringer(257).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nSummon 1/1 minion\n"
					+ "2 SealOfChampions\n Spells\n"
					+ "2 LevelUp spells\n"
					+ "Taunt & divine Minion:\n Tirion Fordring");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero1=new Paladin();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Priest1")){
			JPanel panel = st.getPlayer1().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Anduin_Wrynn(110).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nRestore 2 Health\n"
					+ "2 DivineSpirit spells\n"
					+ "2 HolyNova spells\n"
					+ "2 ShadowWordDeath\n spells\n"
					+ "Legendary Minion:\n Prophet Velen");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero1=new Priest();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Warlock1")){
			JPanel panel = st.getPlayer1().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Gul'dan(618).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nDraw card or \n take 2 damage\n"
					+ "2 CurseOfWeakness\n Spells\n"
					+ "2 SiphonSoul spells\n"
					+ "2 Twisting Nether\n spells\n"
					+ "Legendary Minion:\n Wilfred Fizzlebang");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero1= new Warlock();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//Player 2 selections
		else if(action.equals("Hunter2")){
			JPanel panel = st.getPlayer2().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Rexxar(484).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\n2 damage to enemy\n     hero\n"
					+ "2 KillCommand \n      Spells\n"
					+ "2 MultiShot spells\n"
					+ "Legendary Minion:\n King Krush");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero2= new Hunter();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Mage2")){
			JPanel panel = st.getPlayer2().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Jaina_Proudmoore(320).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nDeal 1 damage\n"
					+ "2 Polymorph Spells\n"
					+ "2 FlameStrike spells\n"
					+ "2 PyroBlast spells\n"
					+ "Legendary Minion:\n Kalycgos");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero2=new Mage();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Paladin2")){
			JPanel panel = st.getPlayer2().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Uther_Lightbringer(257).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nSummon 1/1 minion\n"
					+ "2 SealOfChampions\n Spells\n"
					+ "2 LevelUp spells\n"
					+ "Taunt & divine Minion:\n Tirion Fordring");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero2=new Paladin();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Priest2")){
			JPanel panel = st.getPlayer2().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Anduin_Wrynn(110).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nRestore 2 Health\n"
					+ "2 DivineSpirit spells\n"
					+ "2 HolyNova spells\n"
					+ "2 ShadowWordDeath\n spells\n"
					+ "Legendary Minion:\n Prophet Velen");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero2=new Priest();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(action.equals("Warlock2")){
			JPanel panel = st.getPlayer2().getHeroProfile();
			panel.removeAll();

			ImageIcon i = new ImageIcon("res/images/Gul'dan(618).png");
			JLabel label = new JLabel(i);
			JTextArea text = new JTextArea("\n\n\n\n\n\nDraw card or \n take 2 damage\n"
					+ "2 CurseOfWeakness\n Spells\n"
					+ "2 SiphonSoul spells\n"
					+ "2 Twisting Nether\n spells\n"
					+ "Legendary Minion:\n Wilfred Fizzlebang");
			text.setForeground(Color.white);
			text.setFont(text.getFont().deriveFont(18.0f));

			text.setOpaque(false);

			panel.add(label,BorderLayout.CENTER);
			panel.add(text,BorderLayout.EAST);

			label.setVisible(true);
			text.setVisible(true);
			panel.setVisible(true);
			panel.validate();
			this.validate();
			try {
				hero2= new Warlock();
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void start() throws FullHandException, CloneNotSupportedException, IOException {
		Game a = new Game(hero1,hero2);
		GUI g = new GUI();
		Controller controller = new Controller(a, g);
		this.setVisible(false);	
		music.close();
	}
	public static void main(String[] args) {
		Launcher l = new Launcher();

	}

}
