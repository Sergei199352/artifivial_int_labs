package aips.lab03.sq8;

import java.util.*;

import aips.search.*;

/**
 * This class models the state of the 8-Puzzle problem.
 * @author K. Hui
 *
 */
public class Sq8State implements State
{
/**
 * Store the board as a 2D array of int.
 * 0 means the blank space.
 */
public int tiles[][];

/**
 * This constructor creates a Sq8State of all blanks.
 */
public Sq8State()
{
this.tiles=new int[3][3];		//create a 3*3 array
for (int row=0;row<3;row++)		//go through the rows
	for (int col=0;col<3;col++)	//go through the columns
		this.tiles[row][col]=0;	//each space is empty
} //end method

/**
 * Given a 2D int array, create a Sq8State to represent this state.
 * @param initialPos The game board as a 2D int array.
 */
public Sq8State(int[][] initialPos)
{
//
// *** You must make a copy of initialPos and put it into the tiles attribute.
// *** Do not make a simple assignment of initialPos into tiles.
// *** If you do so, any update to the parameter array may affect your state too.	
// *** Comment out the print statements when you are done.
//
	
this.tiles=new int[3][3];	//create a 3*3 array

System.out.println("*** Complete the 2nd constructor of the Sq8State class.");
} //end method

/**
 * Convert a Sq8State object into a String.
 */
public String toString()
{
String result="";	//this will hold your result

//
// *** Complete this method to return the current state as a String.
// *** No printing here. Just compose and return the String.
// ***
// *** We want the board state to be printed as a 3*3 grid.
// ***
// *** Hint: Scan through the 2D array. Append each element to the result string.
// ***		 Add newlines when needed.
//
System.out.println("*** Complete toString() method in Sq8State");

return result;	//return result of composition
} //end method

/**
 * This method tests if the current Sq8State equals to another object.
 * @param obj The other object to test against.
 * @return true if the current Sq8State equals to the other object. false otherwise.
 */
public boolean equals(Object obj)
{
// *** In Java, the "this" keyword refers to the current Sq8State object.
// *** You must test if it is equal to the "obj" one given in the input parameter.
//
// *** The first thing to do is to check if obj is a Sq8State object.
// *** If it is not, then there is no need for further test. It must be false.
//
// *** If it is a Sq8State object, you may want to cast it into a Sq8State (for easier manipulation)
// *** before testing all elements in the 2 tiles arrays.
// *** Only if everything matches then you return a true.
// *** If there is a mismatch, simply return a false.
//
System.out.println("*** Complete equals(...) method in Sq8State");

return true;		//*** This is a dummy return state to make the compiler happy.
				//*** Remove this when you have filled in the correct code.
} //end method


/**
 * The hashCode() method is needed as we use a HashSet to store the history of visited nodes.
 * It returns an int value for a Sq8State object.
 * The only requirement is that if 2 objects are equal, their hash code should be the same.
 *
 * This method has been implemented for you.
 * In this implementation, I use the simple formula of:
 * 
 * square1+square2*2+square3*3+...+square9*9
 */
public int hashCode()
{
int result=1;
int i=1;

for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		{
		result+=i*tiles[row][col];
		i++;
		}
return result;
} //end method

/**
 * Apply an action to the current state.
 * @param action
 * @return
 */
public Sq8State applyAction(Sq8Action action)
{
Sq8State nextState=new Sq8State(this.tiles);	//create the next state as a copy of the current state

//
// *** Update the tiles array in nextState according to the action.
// *** Basically you are swapping the tile and blank in the tiles array of "nextState".
// *** You can find the tile number/value from action.tileToSlide.
// *** The tile row and column (in the array) are in action.tileRow and action.tileCol.
// *** And the blank space row and column are in action.blankRow and action.blankCol.
//
System.out.println("*** Complete applyAction(...) method in Sq8State");

return nextState;	//return the next state after applying action
} //end method

/**
 * Generate all children node of the current state.
 */
@Override
public List<ActionStatePair> successor()
{
List<ActionStatePair> result=new ArrayList<ActionStatePair>();	//the result list of action-state pairs

//
// find the row & column of the blank in the current state
//
int blankRow=0,blankCol=0;

for (blankRow=0;blankRow<3;blankRow++)
	{
	for (blankCol=0;blankCol<3;blankCol++)
		if (this.tiles[blankRow][blankCol]==0)
			break;
	if (blankCol<3)
		break;
	}

//1st case: You can move a tile to its left into the blank space.
if (blankCol>0)	//if blank not on column 0
	{
	int tileRow=blankRow;
	int tileCol=blankCol-1;
	//1st action is to slide tile to the left of blank
	int tileToSlide=this.tiles[tileRow][tileCol];									//from its row & column, find the tile value
	Sq8Action action=new Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol);	//create an action object
	Sq8State nextState=this.applyAction(action);								//apply action to get next state
	ActionStatePair actionStatePair=new ActionStatePair(action,nextState);	//create action-state pair from action & next state
	result.add(actionStatePair);												//add action-state pair into result list
	}

//
//*** There are 3 more possible actions: slide the tile to the right, above or below the blank.
//*** Each of these actions have to be tested as it may not be possible (depending on where the blank is).
//***
//*** Note that when you slide the tile on the right/left of the blank, the tile column is blank column +/- 1.
//*** If you slide the tile is below/above the blank, the tile row should be blank row +/- 1.
//***
//*** Pay special attention to the boundary. When the blank is next to a boundary, some moves are not possible.
//
System.out.println("*** Complete successor(...) method in Sq8State");

return result;	//return result list of action-state pairs
}
} //end class
