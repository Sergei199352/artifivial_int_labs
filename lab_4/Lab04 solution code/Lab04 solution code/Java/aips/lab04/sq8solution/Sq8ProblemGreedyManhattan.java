package aips.lab04.sq8solution;

import aips.search.*;

/**
 * This class models Greedy best-first using the Manhattan distance heuristic.
 *  
 * @author K. Hui
 *
 */
public class Sq8ProblemGreedyManhattan extends Sq8ProblemGreedy
{
/**
 * Construct a Sq8Problem object from the initial and goal state.
 * @param initialState	The initial state.
 * @param goalState		The goal state.
 */
public Sq8ProblemGreedyManhattan(State initialState, State goalState)
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
return this.sumManhattanDistance(currentState);			//manhattan distance heuristic
} //end method

/**
 * Implement sum of Manhattan distances of all tiles to their correct position.
 * @param currentState
 * @return
 */
public double sumManhattanDistance(State currentState)
{
//
// The following is the sum of Manhattan distance across all tiles.
//
double result=0.0;								//initial value is 0.0
int[][] tiles=((Sq8State)currentState).tiles;	//get the 2D array from the node/state

for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		{
		//
		// for each tile, find its Manhattan distance to the goal position
		// *** make sure that you do not include the space because it is not a tile
		//
		int tile=tiles[row][col];				//get tile
		if (tile!=0)								//not a space
			{
			int goalRow=tileRowInGoal(tile);		//find tile's row in goal state
			int goalCol=tileColInGoal(tile);		//find tile's column in goal state
			int stepsNeeded=Math.abs(row-goalRow)+Math.abs(col-goalCol);	//find the minimum no. of steps to move tile into correct position
			result+=stepsNeeded;					//add into result
			}
		}
return result;
} //end method

/**
 * Find out the row of the tile in the goal state.
 * @param tile
 * @return
 */
private int tileRowInGoal(int tile)
{
int[][] tiles=((Sq8State)this.goalState).tiles;	//get 2D array of int

//go through the 2D array to find the tile label
//when you find it, return its row

for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		if (tile==tiles[row][col])
			return row;				//as soon as we find the tile, return row
return 0;	//this should never happen but just to make Java happy
} //end method

/**
 * Find out the column of the tile in the goal state.
 * @param tile
 * @return
 */
private int tileColInGoal(int tile)
{
int[][] tiles=((Sq8State)this.goalState).tiles;	//get 2D array of int

// go through the 2D array to find the tile label
// and return its column

for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		if (tile==tiles[row][col])	//as soon as we find the tile, return column
			return col;
return 0;	//this should never happen but just to make Java happy
} //end method
} //end class
