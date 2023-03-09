package aips.lab06.tictactoe;

import java.io.*;

import aips.search.Action;
import aips.search.adversarial.*;

/**
 * This class models a human player in the Tic-Tac-Toe game.
 * @author K. Hui
 *
 */
public class HumanPlayer extends Player
{
/**
 * Creates a human player.
 * @param name The player's name. We will print this in the prompt for action.
 * @param role The player's role. We need this to calculate the Minimax score.
 */
public HumanPlayer(String name,PlayerRole role)
{
super(name,role);	//nothing special. Just call the superclass constructor to initialise the attributes.
} //end method

/**
 * Return the action of the human player based on the current state.
 * @param The search problem. A human player does not need this but it is defined on the method anyway.
 * @param state The current state of the game. Print the game board to the screen so that the human player knows what is happening.
 * @return The action decided by the human player.
 */
public Action getAction(MiniMaxSearch problem,State state)
{
Symbol symbol;
int x=0,y=0;

if (this.role==PlayerRole.MAX)		//based on the player's role, find the symbol to use
	symbol=Symbol.CROSS;
else symbol=Symbol.NAUGHT;

//
// I am lazy here by using a simple console input. You can implement a GUI if you want.
// And I am too lazy to do input validation. Ideally you should make sure that the X and Y values are valid.
// And the space is not occupied.
//
BufferedReader console=new BufferedReader(new InputStreamReader(System.in));

Symbol[][] board=((TttState)state).board;	//get the 2D array of symbols
boolean validMove=false;

while (!validMove)
	{
	try	{
		System.out.println(this.name+"'s turn.");
		System.out.print("Column: ");
		x=Integer.parseInt(console.readLine());
		System.out.print("Row: ");
		y=Integer.parseInt(console.readLine());
		
		if (board[y][x]==Symbol.BLANK)	//position must be blank
			validMove=true;				//to be valid
		} catch (Exception e)
			{
			validMove=false;
			}
	if (!validMove)
			System.out.println("Invalid move.");
	}
return new TttAction(x,y,symbol);
} //end method
} //end class
