package aips.lab04.sq8solution;

import aips.search.*;

/**
 * This class models Greedy best-first search using a straight-line distance heuristic.
 * 
 * @author K. Hui
 *
 */
public class Sq8ProblemGreedyStraightLine extends Sq8ProblemGreedy
{
/**
 * Construct a Sq8Problem object from the initial and goal state.
 * @param initialState	The initial state.
 * @param goalState		The goal state.
 */
public Sq8ProblemGreedyStraightLine(State initialState, State goalState)
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
return this.sumStraightLineDistance(currentState);	//straight-line distance heuristic
} //end method

public double sumStraightLineDistance(State currentState)
{
double result=0.0;								//initial value is 0.0
int[][] tiles=((Sq8State)currentState).tiles;	//get the 2D array from the node/state

for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		{
		int tile=tiles[row][col];				//get tile
		if (tile!=0)								//not a space
			{
			int goalRow=tileRowInGoal(tile);		//find tile's row in goal state
			int goalCol=tileColInGoal(tile);		//find tile's column in goal state
			int yDistance=Math.abs(row-goalRow);
			int xDistance=Math.abs(col-goalCol);
			double distance=Math.sqrt(xDistance*xDistance+yDistance*yDistance);
			result+=distance;					//add into result
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
