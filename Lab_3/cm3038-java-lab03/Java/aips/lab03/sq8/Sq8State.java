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

// the other method
/**
 * Given a 2D int array, create a Sq8State to represent this state.
 * @param initialPos The game board as a 2D int array.
 */




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
 
for (int row = 0; row <3; row ++){
	for (int col =0; col <3; col ++)
		this.tiles[row][col] = initialPos[row][col]; // copie the values from each position of the 2d array
}


//System.out.println("*** Complete the 2nd constructor of the Sq8State class.");
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
for (int row = 0; row<3; row++){
	for (int col = 0; col<3; col++)
	{if (this.tiles[row][col]==0)
		{result+="- ";
		
	
	}else
	{result+=this.tiles[row][col];
	result +=" ";}
	}result+="\n";
};
//System.out.println("*** Complete toString() method in Sq8State");

return result;	//return result of composition
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

	Sq8State sqState = (Sq8State)state;
	for (int row =0; row<3; row ++){
		for (int col=0; col<3; col++){
			if (this.tiles[row][col] != sqState.tiles[row][col]){
				return false;
			}
			
		}
	}
	return true;
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
//System.out.println("*** Complete equals(...) method in Sq8State");

		//*** This is a dummy return state to make the compiler happy.
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
nextState.tiles[action.blankRow][action.blankCol]=action.tileToSlide;	//move tile into blank position
nextState.tiles[action.tileRow][action.tileCol]=0;					//tile position now becomes blank
return nextState;


	//return the next state after applying action
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