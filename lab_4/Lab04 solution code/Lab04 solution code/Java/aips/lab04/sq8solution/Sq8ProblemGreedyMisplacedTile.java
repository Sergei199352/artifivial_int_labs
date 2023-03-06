package aips.lab04.sq8solution;

import aips.search.*;

/**
 * This class models Greedy best-first search using the misplaced-tile heuristic.
 * 
 * @author K. Hui
 *
 */
public class Sq8ProblemGreedyMisplacedTile extends Sq8ProblemGreedy
{
/**
 * Construct a Sq8Problem object from the initial and goal state.
 * @param initialState	The initial state.
 * @param goalState		The goal state.
 */
public Sq8ProblemGreedyMisplacedTile(State initialState, State goalState)
{
super(initialState,goalState);
} //end method

/**
 * This heuristic function estimate how far this state is from a goal.
 * @return The remaining distance/cost of the current state to a goal.
 */
@Override
public double heuristic(State currentState)
{
return this.misplacedTiles(currentState);				//misplaced tile heuristic
} //end method

/**
 * Count the number of misplaced tiles.
 * @param currentState
 * @return
 */
public double misplacedTiles(State currentState)
{
double result=0.0;								//initial value is 0.0
int[][] tiles=((Sq8State)currentState).tiles;	//get the 2D array from the node/state
int[][] goalTiles=((Sq8State)this.goalState).tiles;

for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		{
  		if (tiles[row][col]!=0 &&						//it is not the space
  				tiles[row][col]!=goalTiles[row][col])	//and it is not in the correct place
			result++;
		}
return result;
} //end method
} //end class
