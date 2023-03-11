package aips.lab06.tictactoe;

import java.util.*;

import aips.search.*;
import aips.search.adversarial.PlayerRole;

/**
 * This class models a state in the Tic-Tac-Toe game.
 * @author K. Hui
 *
 */
public class TttState implements aips.search.adversarial.State, Cloneable
{
/**
 * This 2D array of Symbol models the game board.
 */
public Symbol board[][];

/**
 * Create an empty game board.
 */
public TttState()
{
this.board=new Symbol[3][3];		//create a 3*3 2D array of symbols.

for (int y=0;y<board.length;y++)		//initialise the board to empty
	for (int x=0;x<board[y].length;x++)
		this.board[y][x]=Symbol.BLANK;
} //end method

/**
 * This is a handy constructor to create a board state from an array of int.
 * This is mainly used for testing/debugging purpose to see if other methods are working properly.
 * @param symbol A 2D array of int. A 0 means blank. A 1 means "X". Any other value means "O".
 */
public TttState(int[][] symbol)
{
this.board=new Symbol[3][3];				//create 2D array of symbols

for (int y=0;y<board.length;y++)			//scan through the 2D int array and put the appropriate symbol into the game board array
	for (int x=0;x<board[y].length;x++)
	{
	if (symbol[y][x]==0)
		this.board[y][x]=Symbol.BLANK;
	else if (symbol[y][x]==1)
		this.board[y][x]=Symbol.CROSS;
	else this.board[y][x]=Symbol.NAUGHT;
	}
} //end method

/**
 * This method is NOT used in the adversarial search.
 * It just needs to be implemented as it is specified in the aips.search.State interface.
 */
@Override
public List<ActionStatePair> successor()
{
return null;
} //end method

/**
 * Return the board state as a String (so that we can print it).
 */
public String toString()
{
String result="";
for (int y=0;y<this.board.length;y++)			//scan through the rows
	{
	for (int x=0;x<this.board[y].length;x++)	//scan through the columns in a row
		{
			if(this.board[y][x] == Symbol.CROSS){
				result+="|X";
			} 
			if (this.board[y][x] == Symbol.NAUGHT){
				result+="|O";
			}
			if (this.board[y][x]==Symbol.BLANK){
				result += "| ";
			}
			
			
		//
		//*** Complete the toString() method here!!!
		//*** The space on the board contains a Symbol. 
		//*** Depending on its value, append an "X", "O" or "." to result.
		//*** See the Symbol enumeration type for the different possible values.
		//
		//System.out.println("Complete toString() method of TttState.");	//<--Replace this with your code.
		} //end for
	result+="\n";								//add newline at end of each row
	}
return result;
} //end method

/**
 * Apply a TttAction to a TttState.
 * @param action
 * @return
 */
public TttState applyAction(Action action)
{
TttAction tttAction=(TttAction)action;								//cast Action into domain-specific TttAction
TttState nextState=(TttState)this.clone();							//new state as a clone of the current state
//
//*** Complete the method here!!!
//*** Based on the action, put the correct symbol into the new state.
//
int x = tttAction.x;
int y = tttAction.y;

if (tttAction.symbol == Symbol.CROSS){
	nextState.board[y][x]=Symbol.CROSS;
} 
if (tttAction.symbol == Symbol.NAUGHT){
	nextState.board[y][x] = Symbol.NAUGHT;
}
//System.out.println("Complete applyAction(...) method in TttState.");	//<--Replace this with your code.
return nextState;													//return new state
} //end method

/**
 * Clone a TttState object.
 */
public Object clone()
{
TttState clone=new TttState();
for (int y=0;y<board.length;y++)
	for (int x=0;x<board[y].length;x++)
		clone.board[y][x]=this.board[y][x];
return clone;
} //end method

/**
 * Find all successors of the current state/node.
 * @param role The role of the current player. We need this to know what children states to generate.
 * @return All children nodes/states as a list of ActionStatePair objects.
 */
@Override
public List<ActionStatePair> successor(PlayerRole role)
{
Symbol playerSymbol;

if (role==PlayerRole.MAX)				//find out what symbol to put in the next states
	playerSymbol=Symbol.CROSS;
else playerSymbol=Symbol.NAUGHT;

List<ActionStatePair> result=new ArrayList<ActionStatePair>();		//create empty list which will hold all next states

for (int y=0;y<this.board.length;y++)			//scan through the rows
	for (int x=0;x<this.board[y].length;x++)	//scan through the columns
		{
		//
		//*** Complete the method here!!!
		//*** The 2 loops will scan through all (x,y) positions on the board.
		//*** You can access each cell by this.board[y][x].
		//*** For each cell, check if it is vacant.
		//*** Each cell is a Symbol enum value.
		//*** If it is, create an appropriate TttAction object and find the next state (after this action is applied).
		//*** Put them into a new ActionStatePair object and add this new move into the list of result.
		//
		}return result;			//return list
} //end method
} //end class
