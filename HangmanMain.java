/*
 * Alex Thoennes
 * 
 * August 29, 2016
 * 
 * This class is designed to act as the top overlaying class
 * for the entire game.
 */

import java.io.IOException;

public class HangmanMain 
{	
	//static CategorySelect category = new CategorySelect();
	
	static MainMenu menu = new MainMenu();
	
	public static void main(String[] args) throws IOException
	{
		//category.listen();
		menu.listen();
	}
}