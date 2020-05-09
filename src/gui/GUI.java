package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.panels.game.BoardPanel;
import gui.panels.game.SidePanel;

public class GUI extends JFrame {

	// Gameplay panels
	private BoardPanel board;
	private SidePanel side;
	private Clip music;
	
	
	public GUI() {
		super("Hearthstone");
		board = new BoardPanel();
		side = new SidePanel();

		//this.setSize(new Dimension(image.getIconWidth(),image.getIconHeight()));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
		this.setLocationRelativeTo(null);
		//adding the background image
		//ImageIcon image = new ImageIcon("res/images/Battlefieldfinal.jpg");
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res/images/Battlefieldfinal.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(this.getWidth(), this.getHeight(),
				Image.SCALE_SMOOTH);

		ImageIcon imageIcon = new ImageIcon(dimg);
		this.setContentPane(new JLabel(imageIcon));
		this.setVisible(true); //visibility
		this.setResizable(false);  // no resizability to avoid glitches
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout()); // border layout is better for the gameplay
		this.add(side, BorderLayout.EAST);
		this.add(board, BorderLayout.WEST);
		
		//Play some music
		File audio = new File("res/sound/Duel.wav");
		try {
			AudioInputStream a = AudioSystem.getAudioInputStream(audio);
			music = AudioSystem.getClip();
			music.open(a);
			music.loop(100);
		}
		catch(IOException | UnsupportedAudioFileException e) {}
		catch(LineUnavailableException e) {}
	}

	// panel Getters
	public BoardPanel getBoard() {
		return board;
	}

	public SidePanel getSide() {
		return side;
	}
	public Clip getMusic() {
		return music;
	}

}
