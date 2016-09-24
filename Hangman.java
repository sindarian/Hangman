/* Hangman by Alexander Thoennes
 * 
 * Made: June 13, 2013
 * 
 * Picked back up: October 30, 2015
 * 
 * Rules:
 * 1) Choose a category by typing one of their numbers
 * 2) Guess the word 
 * OR
 * 3) If you think you know the word, then type $ (dollar sign) to guess the full word
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Hangman 
{

	//integer values
	int tries = 0; //number of tries the user has taken to guess
	static int wordNum;	//identifies the word in the chosen array (category) that is to be guessed
	int categoryNum; //number user inputs to choose a category
	static int lengthOfWord; // length of guessed word, when 0 you win (in other words, you guessed the word correctly)

	//character values
	static char lastGuess; //holds the last character that user guessed

	//character arrays
	static char [] guessedLetters = new char[128]; // 128 for 128 ASCII values
	static char [] guess; //helps display underscore
	static char [] wordchar; //looks for guessed letter in the word to be guessed

	//string values
	static String word; //the word to be guessed
	static String wordGuess; //user guess as to what the word is

	//booleans
	static boolean wordWasGuessed = false;

	// empty arrays for the categories
	static ArrayList<String> nameArray = new ArrayList<String>(); // filled with names if chosen
	static ArrayList<String> movieArray = new ArrayList<String>(); // filled with movies if chosen
	static ArrayList<String> animalArray = new ArrayList<String>(); // filled with animals if chosen
	static ArrayList<String> foodArray = new ArrayList<String>(); // filled with foods if chosen
	static ArrayList<String> countryArray = new ArrayList<String>(); // filled with countries if chosen
	static ArrayList<String> pokemonArray = new ArrayList<String>(); // filled with pokemon if chosen
	static ArrayList<String> gameOfThronesArray = new ArrayList<String>(); // filled with game of thrones characters, places, etc. if chosen
	static ArrayList<String> astronomyArray = new ArrayList<String>(); // filled with astronomy terms and celestial bodies if chosen

	final static String NAMES = "names";
	final static String MOVIES = "movies";
	final static String ANIMALS = "animals";
	final static String FOOD = "food";
	final static String COUNTRY = "countries";
	final static String POKEMON = "pokemon";
	final static String GAME_OF_THRONES = "game of thrones";
	final static String ASTRONOMY = "astronomy";

	static boolean done = false;

	/**
	 * default constructor
	 */
	public Hangman()
	{
		super();
	}

	/**
	 * run() processes the letter taken in by
	 * the user and checks to see if it is in
	 * the word
	 * @throws IOException 
	 */
	public static void run()
	{
		if (HangmanDisplay.clicked)
		{
			// as long as there are still underscores, you still guess
			if (lengthOfWord > 0)
			{
				lastGuess = HangmanDisplay.userGuess.getText().charAt(0);
				HangmanDisplay.clicked = false;
			}

			// continue guessing the letters of the word
			if (lastGuess != '$') 
			{
				guessed();

			} 

			// lets you have one final guess to guess the word
			else if (lastGuess == '$')
			{
				winningGuess();
				win();
			}
			HangmanDisplay.wordCharArray = guess;
		}
	}

	/**
	 * picks a word for the user to guess based
	 * on the category selected (categories are 
	 * selected based on numbers)
	 * 
	 * @param num
	 * @return word
	 * @throws IOException
	 */
	public static String wordToGuess(String cat) throws IOException
	{
		Random n = new Random();

		if (cat.equals(NAMES))
		{
			fillArray(NAMES);

			// randomly choose a number based on the name array size
			wordNum = n.nextInt(nameArray.size());
			// and then retrieve the word at that location
			word = nameArray.get(wordNum);
		}
		else if (cat.equals(MOVIES))
		{
			fillArray(MOVIES);

			wordNum = n.nextInt(movieArray.size());
			word = movieArray.get(wordNum);
		}
		else if (cat.equals(ANIMALS))
		{
			fillArray(ANIMALS);

			wordNum = n.nextInt(animalArray.size());
			word = animalArray.get(wordNum);
		}
		else if (cat.equals(FOOD))
		{
			fillArray(FOOD);

			wordNum = n.nextInt(foodArray.size());
			word = foodArray.get(wordNum);
		}
		else if (cat.equals(COUNTRY))
		{	
			fillArray(COUNTRY);

			wordNum = n.nextInt(countryArray.size());
			word = countryArray.get(wordNum);
		}
		else if (cat.equals(POKEMON))
		{
			fillArray(POKEMON);

			wordNum = n.nextInt(pokemonArray.size());
			word = pokemonArray.get(wordNum);
		}
		else if (cat.equals(GAME_OF_THRONES))
		{
			fillArray(GAME_OF_THRONES);

			wordNum = n.nextInt(gameOfThronesArray.size());
			word = gameOfThronesArray.get(wordNum);
		}
		else if (cat.equals(ASTRONOMY))
		{
			fillArray(ASTRONOMY);

			wordNum = n.nextInt(astronomyArray.size());
			word = astronomyArray.get(wordNum);
		}

		lengthOfWord = word.length();
		
		convertWord();
		
		return word;
	}

	public static void convertWord()
	{
		//convert word to char array so we can go character by character
		wordchar = word.toCharArray();

		guess = new char[word.length()];

		// fill the guess array with the word to be guessed
		for (int i = 0; i < guess.length; i++)
		{
			guess[i] = word.charAt(i);
		}


		// this converts the word's letters to underscores except the spaces and hypens
		for (int i = 0; i < guess.length; i++)
		{	
			// do not want to guess spaces
			if (guess[i] == ' ')
			{
				guess[i] = ' ';
				lengthOfWord--;
			}
			else if (guess[i] == '-')
			{
				guess[i] = '-';
			}
			else
			{
				guess[i] = '_';

			}
		}
	}

	/**
	 * determines if you have won
	 * 
	 * @return
	 */
	public static boolean win()
	{
		boolean result;
		if (wordGuess.equals(word))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}

	/**
	 * checks to see if the user entered a letter
	 * that they have already guessed
	 */
	public static void guessed()
	{
		// check to see if we've already tried this guess
		if (guessedLetters[(int) lastGuess] == 1)
		{
			System.out.println("Sorry, you guessed " + lastGuess + " already.  Try again.");
		}
		else
		{
			guessedLetters[(int) lastGuess] = 1;

			for (int i = 0; i < wordchar.length; i++)
			{
				if (wordchar[i] == lastGuess)
				{								
					guess[i] = lastGuess;
					lengthOfWord--;
					System.out.println(lengthOfWord);
					
					if (lengthOfWord == 0)
					{
						done = true;
						Arrays.fill(guessedLetters, ' ');
					}
				}
			}
		}
	}

	/**
	 * prompts user for the final winning guess
	 * 
	 * A right guess means you win and a wrong
	 * guess means you lose
	 */
	public static void winningGuess()
	{
		wordWasGuessed = true;

		// checks the user guess against the word to be guessed
		if (win())
		{
			System.out.println("YOU WIN");
		}
		else
		{
			System.out.println("YOU LOSE");
			System.out.println("The word was " + word);
		}
	}

	/**
	 * Reads in the proper line of the text file "Categories" and 
	 * fills the proper array list with the words from the specified
	 * text file line
	 * 
	 * @param array
	 * @throws IOException 
	 */
	public static void fillArray(String array) throws IOException
	{
		String line = "";

		String TextFileName = "Categories.txt";

		BufferedReader buffer = new BufferedReader(new FileReader(TextFileName));

		if (buffer.ready())
		{
			if (array.equals(NAMES))
			{
				line = buffer.readLine();

				StringTokenizer tokens = new StringTokenizer(line, ",");

				while (tokens.hasMoreTokens())
				{
					nameArray.add(tokens.nextToken());
				}
			}
			else if (array.equals(MOVIES))
			{
				// read up to the second line
				for (int i = 0; i < 1; i++)
				{
					buffer.readLine();
				}

				// read the line for your slected category
				line = buffer.readLine();

				// put all words into the array list
				StringTokenizer tokens = new StringTokenizer(line, ",");

				while (tokens.hasMoreTokens())
				{
					movieArray.add(tokens.nextToken());
				}
			}
			else if (array.equals(ANIMALS))
			{
				for (int i = 0; i < 2; i++)
				{
					buffer.readLine();
				}

				line = buffer.readLine();

				StringTokenizer tokens = new StringTokenizer(line, ",");

				while (tokens.hasMoreTokens())
				{
					animalArray.add(tokens.nextToken());
				}
			}
			else if (array.equals(FOOD))
			{
				for (int i = 0; i < 3; i++)
				{
					buffer.readLine();
				}

				line = buffer.readLine();

				StringTokenizer tokens = new StringTokenizer(line, ",");

				while (tokens.hasMoreTokens())
				{
					foodArray.add(tokens.nextToken());
				}
			}
			else if (array.equals(COUNTRY))
			{
				for (int i = 0; i < 4; i++)
				{
					buffer.readLine();
				}

				line = buffer.readLine();

				StringTokenizer tokens = new StringTokenizer(line,",");

				while (tokens.hasMoreTokens())
				{
					countryArray.add(tokens.nextToken());
				}
			}
			else if (array.equals(POKEMON))
			{
				for (int i = 0; i < 5; i++)
				{
					buffer.readLine();
				}

				line = buffer.readLine();

				StringTokenizer tokens = new StringTokenizer(line,",");

				while (tokens.hasMoreTokens())
				{
					pokemonArray.add(tokens.nextToken());
				}
			}
			else if (array.equals(GAME_OF_THRONES))
			{
				for (int i = 0; i < 6; i++)
				{
					buffer.readLine();
				}

				line = buffer.readLine();

				StringTokenizer tokens = new StringTokenizer(line,",");

				while (tokens.hasMoreTokens())
				{
					gameOfThronesArray.add(tokens.nextToken());
				}
			}
			else if (array.equals(ASTRONOMY))
			{
				for (int i = 0; i < 7; i++)
				{
					buffer.readLine();
				}

				line = buffer.readLine();

				StringTokenizer tokens = new StringTokenizer(line,",");

				while (tokens.hasMoreTokens())
				{
					astronomyArray.add(tokens.nextToken());
				}
			}
		}
		buffer.close();
	}
}