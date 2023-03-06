package aips.lab04.sq8;

import aips.search.*;
import aips.search.informed.*;

/**
 * This is an informed search problem specialised for the 8-Puzzle problem.
 * 
 * @author K. Hui
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
//
//*** Update this evaluation function.
//*** Currently it is doing Greedy best-first by using the heuristic alone. i.e. f(n)=h(n)
//*** It does not take into consideration the cost from the root to "node" so far.
//*** You can get the cost from the root to node so far (i.e. g(n)) by node.getCost().
//
return node.getCost() + heuristic(node.state);	//f(n)=h(n) mean greedy best-first
} //end method
	
/**
 * This heuristic function estimate how far this state is from a goal.
 * Note: You can always access the goal state by "this.goalState" as it is stored as an attribute.
 * @return The remaining distance/cost of the current state to a goal.
 */
public double heuristic(State currentState)
{
//*** Explore different ways to estimate the distance of the current state to the goal state.
//*** The misplaced tile heuristic is implemented as an example.
//***
//return this.misplacedTiles(currentState);
return this.sumManhatanDistance(currentState);
}

/**
 * Counting the number of misplaced tiles in the current state.
 * @param currentState
 * @return the no. of misplaced tiles in the state
 */
public double misplacedTiles(State currentState)
{
double result=0.0;
int tiles[][]=((Sq8State)currentState).tiles;
int goalTiles[][]=((Sq8State)this.goalState).tiles;

//
//***The current implementation is "counting the number of misplaced tiles".
//***Note that we only look for tiles in counting mismatches, not the space.
//***Including the space in counting mismatches will over-estimate.
//
for (int row=0;row<3;row++)
	for (int col=0;col<3;col++)
		if (tiles[row][col]!=0 && 					//if current tile is not a space
			tiles[row][col]!=goalTiles[row][col])	//and it does not match the one in goal
			result++;								//increment count by 1

return result;
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
private int tileRowInGoal(int tile){
	// in this method we go through the array and find the location of the given tile in the goal state
	int [][] tiles = ((Sq8State)this.goalState).tiles;// this is a type cast, where we cast the goal state form this class to the Sq8State class
	//in order to use the constructer so we could create the clone of the goal state array 
	for (int row=0; row<3; row++){// in this loop we iterate through the goal state array and find the row of the given tile
		for(int col = 0; col<3; col ++){
			if (tile ==tiles[row][col]){
				return row;
			} 
		}
	}
	return 0;
}
private int tileColInGoal(int tile){//the method to discover the location of the tile row in the goal state array
	int [][] tiles = ((Sq8State)this.goalState).tiles; //cast the goalstate to the Sq8State in order to get the 2D array
	//iterate through the given array
	for (int row=0; row<3; row++){
		for (int col = 0; col<3; col++){
			if (tile == tiles[row][col]){
				return col;
			}
		}
	}
	return 0;


}
public double sumManhatanDistance(State currentState){
	double result = 0.0; //initial value is 0.0
	int [][] tiles = ((Sq8State)currentState).tiles; // get the 2d array 
	
	//
	// for each tile, find its Manhattan distance to the goal position
	// *** make sure that you do not include the space because it is not a tile
	//
	for (int row = 0; row<3; row++){
		for (int col=0;col<3;col++){
			int tile =  tiles[row][col];// getr tile
			if (tile!=0){
				int goalRow = tileRowInGoal(tile);
				int goalCol = tileColInGoal(tile);
				int stepsNeeded = Math.abs(row-goalRow) + Math.abs(col-goalCol);
				result += stepsNeeded;
			}
		}
	}
	return result;
}//end method
} //end class


 
