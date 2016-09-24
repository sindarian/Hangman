import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Alex Thoennes
 * 
 * Sept. 7, 2016
 * 
 * This presents the user with a gui
 * that contains buttons that are used 
 * to select the desired category
 */
public class CategorySelect
{
	static HangmanDisplay display;
	
	//frame
	JFrame frame;

	// button that when clicked, selects the category names
	JButton names = new JButton("Names");

	// button that when clicked, selects the category movies
	JButton movies = new JButton("Movies");

	// button that when clicked, selects the category animals
	JButton animals = new JButton("Animals");

	// button that when clicked, selects the category food
	JButton food = new JButton("Food");

	// button that when clicked, selects the category countries
	JButton countries = new JButton("Countries");

	// button that when clicked, selects the category pokemon
	JButton pokemon = new JButton("Pokemon");

	// button that when clicked, selects the category game of thrones
	JButton gameOfThrones = new JButton("Game of Thrones");

	// button that when clicked, selcts the category astronomy
	JButton astronomy = new JButton("Astronomy");
	
	static Clip clip;
	
	static boolean music = true;

	/**
	 * default constructor that sets up the basic
	 * frame with the buttons that are used to 
	 * select a category
	 */
	public CategorySelect()
	{
		frame = new JFrame("Category Select");

		frame.setSize(480, 150);

		setUpFrame();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * sets up the frame by adding the components
	 */
	private void setUpFrame()
	{
		//    R C
		frame.setLayout(new GridLayout(3,2));

		// add buttons to frame
		frame.add(names);
		frame.add(movies);
		frame.add(animals);
		frame.add(food);
		frame.add(countries);
		frame.add(pokemon);
		frame.add(gameOfThrones);
		frame.add(astronomy);
	}

	public void listen()
	{	
		// assign action listeners using inner classes
		names.addActionListener(new ActionListener() 
		{
			// what happens when button is clicked
			public void actionPerformed(ActionEvent e) 
			{
				MainMenu.clip.stop();
				try 
				{
					Hangman.wordToGuess("names");
					display = new HangmanDisplay("Names", 0, Hangman.guess);
					
					frame.dispose();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});

		// each body is only visible to the button that it is directly under

		movies.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenu.clip.stop();
				
				try 
				{
					Hangman.wordToGuess("movies");
					display = new HangmanDisplay("Movies", 0, Hangman.guess);
					
					frame.dispose();
					Hangman.run();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});

		animals.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenu.clip.stop();
				
				try 
				{
					Hangman.wordToGuess("animals");
					display = new HangmanDisplay("Animals", 0, Hangman.guess);
					
					frame.dispose();
					Hangman.run();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});

		food.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenu.clip.stop();
				
				try 
				{
					Hangman.wordToGuess("food");
					display = new HangmanDisplay("Food", 0, Hangman.guess);
					
					frame.dispose();
					Hangman.run();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});

		countries.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenu.clip.stop();
				
				try 
				{
					Hangman.wordToGuess("countries");
					display = new HangmanDisplay("Countries", 0, Hangman.guess);
					
					frame.dispose();
					Hangman.run();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});

		pokemon.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenu.clip.stop();
				
				try 
				{
					Hangman.wordToGuess("pokemon");
					display = new HangmanDisplay("Pokemon", 0, Hangman.guess);
					
					frame.dispose();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});

		gameOfThrones.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenu.clip.stop();
				
				try 
				{
					Hangman.wordToGuess("game of thrones");
					display = new HangmanDisplay("Game Of Thrones", 0, Hangman.guess);
					
					if (music)
					{
						Random rand = new Random();
						// 0 or 1
						int num = rand.nextInt(2);
						if (num == 0)
						{
							File audio = new File("Game of Thrones Theme.wav");
							AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
							AudioFormat format = stream.getFormat();
							DataLine.Info info = new DataLine.Info(Clip.class, format);
							clip = (Clip) AudioSystem.getLine(info);
							clip.open(stream);
							clip.start();
						}
						else if (num == 1)
						{
							File audio = new File("Rains of Castamere.wav");
							AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
							AudioFormat format = stream.getFormat();
							DataLine.Info info = new DataLine.Info(Clip.class, format);
							clip = (Clip) AudioSystem.getLine(info);
							clip.open(stream);
							clip.start();
						}
					}
					
					frame.dispose();
				} 
				catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
				{
					e1.printStackTrace();
				}
			}
		});

		astronomy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MainMenu.clip.stop();
				
				try 
				{
					Hangman.wordToGuess("astronomy");
					display = new HangmanDisplay("Astronomy", 0, Hangman.guess);
					
					if (music)
					{
						File audio = new File("Interstellar Theme.wav");
						AudioInputStream stream = AudioSystem.getAudioInputStream(audio);
						AudioFormat format = stream.getFormat();
						DataLine.Info info = new DataLine.Info(Clip.class, format);
						clip = (Clip) AudioSystem.getLine(info);
						clip.open(stream);
						clip.start();
					}
					
					frame.dispose();
				} 
				catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
	}
}