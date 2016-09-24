/*
 * Alex Thoennes
 * 
 * Sept. 19, 2016
 * 
 * This is the very first thing the user
 * will see. This frame gives them the 
 * options to either start the game, 
 * turn the music on or off, or
 * exit the game
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu 
{
	// frame to hold all components
	JFrame frame;
	
	// buttons that turn music on/off, start the game
	// or exit the game
	JButton toggleMusic;
	JButton play;
	JButton exit;
	
	// displays the welcome sign to the user
	JLabel welcome;
	JLabel author;
	
	JPanel topPanel;
	JPanel middlePanel;
	JPanel bottomPanel;
	
	static Clip clip;
	
	/**
	 * constructor for this class which
	 * initializes the buttons, labels,
	 * frame features,and panels.
	 */
	public MainMenu()
	{
		frame = new JFrame("Main Menu");
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		toggleMusic = new JButton("Music");
		play = new JButton("Play");
		exit = new JButton("Exit");
		
		welcome = new JLabel("WELCOME TO HANGMAN!");
		author = new JLabel("By: Alex Thoennes");
		
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(this.topPanel, BoxLayout.Y_AXIS));
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		populateFrame();
		
		try
		{
			File audio = new File("Gallows Pole.wav");
			AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
			AudioFormat format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		}
		catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
		{
			e1.printStackTrace();
		}
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * fills the frame with the components and
	 * panels needed to run
	 */
	private void populateFrame()
	{
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		author.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		topPanel.add(welcome, Box.LEFT_ALIGNMENT);
		topPanel.add(author);
		
		try 
		{
			String path = "Hangman.png";
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			JLabel label = new JLabel(new ImageIcon(image));
			middlePanel.add(label, BorderLayout.CENTER);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		bottomPanel.add(play);
		bottomPanel.add(toggleMusic);
		bottomPanel.add(exit);
	}
	
	/**
	 * listens to the frame for any button clicks.
	 * The only time this will ever progress to the 
	 * category select is if 'play' has been clicked.
	 */
	public void listen()
	{
		play.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CategorySelect category = new CategorySelect();
				category.listen();
				frame.dispose();
			}
		});
		
		toggleMusic.addActionListener(new ActionListener()
		{
			Object[] options = {"OK"};
			
			public void actionPerformed(ActionEvent e)
			{
				if (CategorySelect.music)
				{
					clip.stop();
					
					CategorySelect.music = false;
					
					JOptionPane.showOptionDialog(frame,"Music has been turned off.","Notification",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				}
				else
				{
					clip.start();
					
					CategorySelect.music = true;
					
					JOptionPane.showOptionDialog(frame,"Music has been turned on.","Notification",JOptionPane.PLAIN_MESSAGE,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				}
			}
		});
		
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
}
