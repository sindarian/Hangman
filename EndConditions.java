/*
 * Alex Thoennes
 * 
 * Sept. 9, 2016
 * 
 * This class presents the user with an option
 * pane that asks if they want to continue
 * the game or not
 */

import javax.swing.JOptionPane;

public class EndConditions 
{
	JOptionPane pane = new JOptionPane();

	public EndConditions()
	{
		super();
	}

	public boolean continueGame()
	{
		int result = JOptionPane.showConfirmDialog(null, "Play again?",null, JOptionPane.YES_NO_OPTION);
		
		if(result == JOptionPane.YES_OPTION) 
		{
			return true;
		}
		else
		{
			System.exit(0);
			return false;
		}
	}
}
