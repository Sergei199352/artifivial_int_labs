package aips.lab04.sq8solution;

import aips.search.*;
import aips.search.informed.*;

/**
 * This is a informed search problem specialised for the 8-Puzzle problem.
 * @author kit
 *
 */
public class Sq8Problem extends BestFirstSearchProblem
{
/**
 * Construct a Sq8Problem object from the initial and goal state.
 * @param initialState	The initial state.
 * @param goalState		The goal state.
 */
public Sq8Problem(State initialState, State goalState)
{
super(initialState,goalState);
} //end method

/**
 * The evaluation function required by an informed search.
 * @param node	The node to be evaluated.
 * @return The score of the node. The lower the score, the more promising the node.
 */
public double evaluation(Node node)
{
//return node.getCost();						//f(n)=g(n) means h(n)=0, i.e. breath-first search
//return node.getCost()+heuristic(node.state);	//f(n)=g(n)+h(n) means A* if h(n) is admissible
return heuristic(node.state);					//f(n)=h(n) means greedy best-first
} //end method
	
/**
 * This heuristic function estimate how far this state is from a goal.
 * @return The remaining distance/cost of the current state to a goal.
 */
public double heuristic(State currentState)
{
//return this.misplacedTiles(currentState);				//misplaced tile heuristic
//return this.sumStraightLineDistance(currentState);	//straight-line distance heuristic
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
int[][] goalTiles=((Sq8State)this.goalState).tiles;

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

public double sumStraightLineDistance(State currentState)
{
double result=0.0;								//initial value is 0.0
int[][] tiles=((Sq8State)currentState).tiles;	//get the 2D array from the node/state
int[][] goalTiles=((Sq8State)this.goalState).tiles;

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

/**
 * This isGoal testing method defines that the a state must be
 * equal to the goal state (as an attribute in the problem object)
 * to be a goal.
 */
@Override
public boolean isGoal(State state) {
	return state.equals(this.goalState);
} //end method
} //end class
