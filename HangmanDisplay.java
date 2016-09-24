import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*	Alex Thoennes August 13,2016
 * 
 * This class is designed to handle the GUI
 * 	aspect of the Hangman game. The GUI will
 * 	display the letters guessed (both right and
 * 	wrong), category selected, and a text box for
 * 	the user to use to guess a letter or the word.
 * 
 */

public class HangmanDisplay implements ActionListener
{	
	// title of the frame
	final String TITLE = "Hangman";

	// X
	final int WIDTH = 700;
	// Y
	final int HEIGHT = 150;

	// frame in which the game takes place
	JFrame gameFrame;

	// tells the user what category they have chosen
	JLabel category = new JLabel();

	// tells the user how many guesses they have tried
	// this is a bit more complex in the sense that I need to 
	JLabel numOfGuessesLabel = new JLabel();

	// the word to guess (appears in underscores and should show letters when right letters are guessed)
	JLabel word = new JLabel();
	
	//shows the user what letters they have guessed
	JLabel guessedLetters = new JLabel();

	// enter button to be clicked when user wants to guess
	JButton clickToGuess = new JButton();

	// text field where the user will enter their guess
	static JTextField userGuess;// = new JTextField();

	//
	String numOfGuessesText = "Number of guesses: ";
	int numOfGuesses;

	static char [] wordCharArray;

	static boolean clicked;

	public HangmanDisplay() 
	{
		gameFrame = new JFrame();
		gameFrame.setTitle(TITLE);
		gameFrame.setSize(WIDTH, HEIGHT);

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

	public HangmanDisplay(String category, int numOfGuesses, char [] guess)
	{
		clicked = false;

		wordCharArray = guess;

		HangmanDisplay.userGuess = new JTextField();
		gameFrame = new JFrame();
		gameFrame.setTitle(TITLE);
		gameFrame.setSize(WIDTH, HEIGHT);

		this.numOfGuesses = numOfGuesses;

		this.category.setText("Category: " + category);

		this.numOfGuessesLabel.setText(numOfGuessesText + String.valueOf(numOfGuesses));

		this.word.setText(String.valueOf(wordCharArray));

		HangmanDisplay.userGuess.setSize(WIDTH, 175);

		this.clickToGuess.setText("Enter");
		
		guessedLetters.setText("Letters: ");

		setUpFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

	/**
	 * This method add all the required components
	 * and adds the required actionlistener. This
	 * is all done around a grid layout
	 */
	private void setUpFrame()
	{
		/*THIS CODE IS INTENTIONALLY FORMATTED THIS WAY*/
		// this is the way that the fame is set up
		
		gameFrame.setLayout(new GridLayout(4,1));
		// category                  # of user guesses
		gameFrame.add(category);	gameFrame.add(numOfGuessesLabel);
		
		// text field					enter button
		gameFrame.add(userGuess);	gameFrame.add(clickToGuess);
		
		//  word to guess				letters guessed
		gameFrame.add(word);		gameFrame.add(guessedLetters);
		
		clickToGuess.addActionListener(this);
	}

	/**
	 * refreshes the display, adding to the
	 * number of guesses, displaying wrong
	 * or right guesses
	 */
	public void refresh()
	{
		Hangman.run();

		// add 1 to number of guesses
		numOfGuesses++;
		numOfGuessesLabel.setText(numOfGuessesText + numOfGuesses);

		userGuess.setText("");

		word.setText(String.valueOf(wordCharArray));
		
		System.out.println(Hangman.guessedLetters);
		guessedLetters.setText("Letters: " + String.valueOf(Hangman.guessedLetters));

		if (Hangman.done)
		{
			EndConditions ec = new EndConditions();
			if (ec.continueGame())
			{
				CategorySelect.clip.stop();
				this.gameFrame.dispose();
				Hangman.done = false;
				CategorySelect category = new CategorySelect();
				category.listen();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (!userGuess.getText().isEmpty())
		{
			clicked = true;
			refresh();
		}
	}
}
