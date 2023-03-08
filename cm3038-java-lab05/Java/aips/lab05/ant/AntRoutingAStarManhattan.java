package aips.lab05.ant;

import aips.search.*;

/**
 * This class models the ant routing problem as an A* search.
 * @author Kit-ying Hui
 */
public class AntRoutingAStarManhattan extends AntRoutingAStarDiagonal
{
/**
 * Create an AntRoutingAStar object.	
 * @param start	The initial state.
 * @param goal	The goal state.
 * @param world	The ant world.
 */
public AntRoutingAStarManhattan(State start, State goal,AntWorld world)
{
super(start, goal,world);
} //end method

/**
 * The heuristic function that estimates the distance of a state/node to a goal state.
 * For A* to work, this heuristic cannot over-estimate.
 * @param state The state to be estimated.
 * @return The estimated distance of state to a goal.
 */
public double heuristic(State state)
{
AntState antState=(AntState)state;	//cast State into AntState
AntState goalAntState=(AntState)this.goalState;
double result = 0.0;

int xDiff=antState.x-goalAntState.x;
int yDiff=antState.y-goalAntState.y;

int stepsNeeded = Math.abs(xDiff)+Math.abs(yDiff);
/***
 * *** Compute and return the heuristic value here!!!
 */
//System.out.println("Complete heuristic(...) method in AntRoutingAStateManhattan.");

return result+=stepsNeeded;	//compute and return heuristic value here!!!
} //end method
} //end class
