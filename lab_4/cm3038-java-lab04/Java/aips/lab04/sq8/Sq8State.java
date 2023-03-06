package aips.lab04.sq8;

import java.util.*;

import aips.search.*;

/**
 * This class models the state of the 8-Puzzle problem.
 * @author Kit-ying Hui
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
this.tiles=new int[3][3];	//create a 3*3 array
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
this.tiles=new int[3][3];	//create a 3*3 array
for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		this.tiles[row][col]=initialPos[row][col];	//copy value from param array
} //end method

/**
 * Convert a Sq8State object into a String.
 */
public String toString()
{
String result="";

for (int row=0;row<3;row++)
	{
	for (int col=0;col<3;col++)
		{
			if (this.tiles[row][col]==0)			//if it is a blank
				result+="-";						//append a "-"
			else result+=this.tiles[row][col];	//otherwise append the number to the string
		}
	result+="\n";	//append a newline at the end of each row
	}
return result;
} //end method

/**
 * This method tests if the current Sq8State equals to another object.
 * @param obj The other object to test against.
 * @return true if the current Sq8State equals to the other object. false otherwise.
 */
public boolean equals(Object state)
{
if (!(state instanceof Sq8State))
	return false;

Sq8State sq8State=(Sq8State)state;	//cast param into a Sq8State for easier manipulation
for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		if (this.tiles[row][col]!=sq8State.tiles[row][col])		//As soon as there is a mismatch, it must be false.
			return false;										//There is no need to continue the loop.
return true;		//only if all elements are the same then return a true
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
return tiles[0][0]+tiles[0][1]*10+tiles[0][2]*100+
		tiles[1][0]*1000+tiles[1][1]*100000+tiles[1][2]*1000000+
		tiles[2][0]*10000000+tiles[2][1]*100000000+tiles[2][2]*1000000000;
//return result;
} //end method

/**
 * Apply an action to the current state.
 * @param action
 * @return
 */
public Sq8State applyAction(Sq8Action action)
{
Sq8State nextState=new Sq8State(this.tiles);	//create the next state as a copy of the current state

nextState.tiles[action.blankRow][action.blankCol]=action.tileToSlide;	//move tile into blank position
nextState.tiles[action.tileRow][action.tileCol]=0;					//tile position now becomes blank
return nextState;
} //end method

/**
 * Generate all children node of the current state.
 */
@Override
public List<ActionStatePair> successor()
{
List<ActionStatePair> result=new ArrayList<ActionStatePair>();

//
//find the index of the blank in the current state
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

//
// At this point blankRow and blankCol are the row and column of the blank.
//
if (blankCol>0)	//if blank not on column 0
	{
	int tileRow=blankRow;
	int tileCol=blankCol-1;	//We can slide the tile to the left of the blank.
	int tileToSlide=this.tiles[tileRow][tileCol];
	Sq8Action action=new Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol);
	Sq8State nextState=this.applyAction(action);
	ActionStatePair actionStatePair=new ActionStatePair(action,nextState);
	result.add(actionStatePair);
	}
if (blankCol<2)	//if blank not on column 2
	{
	int tileRow=blankRow;
	int tileCol=blankCol+1;	//We can slide the tile to the right of the blank.
	int tileToSlide=this.tiles[tileRow][tileCol];
	Sq8Action action=new Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol);
	Sq8State nextState=this.applyAction(action);
	ActionStatePair actionStatePair=new ActionStatePair(action,nextState);
	result.add(actionStatePair);
	}
if (blankRow>0)	//if blank not on row 0
	{
	int tileRow=blankRow-1;	//We can slide the tile above the blank.
	int tileCol=blankCol;
	int tileToSlide=this.tiles[tileRow][tileCol];
	Sq8Action action=new Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol);
	Sq8State nextState=this.applyAction(action);
	ActionStatePair actionStatePair=new ActionStatePair(action,nextState);
	result.add(actionStatePair);
	}
if (blankRow<2)	//if blank not on row 2
	{
	int tileRow=blankRow+1;	//We can slide the tile below the blank.
	int tileCol=blankCol;
	int tileToSlide=this.tiles[tileRow][tileCol];
	Sq8Action action=new Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol);
	Sq8State nextState=this.applyAction(action);
	ActionStatePair actionStatePair=new ActionStatePair(action,nextState);
	result.add(actionStatePair);
	}
return result;
} //end method
} //end class
