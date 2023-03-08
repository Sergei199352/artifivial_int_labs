package aips.lab05.antSolution;

import aips.search.*;
import aips.search.informed.*;

public class AntRoutingAStarDiagonal extends BestFirstSearchProblem
{
public AntState goal;	//the goal state
public AntWorld world;	//the ant world

/**
 * Create an AntRoutingAStarDiagonal object.	
 * @param start	The initial state.
 * @param goal	The goal state.
 * @param world	The ant world.
 */
public AntRoutingAStarDiagonal(State start, State goal, AntWorld world) 
{
super(start, goal);
this.world=world;
} //end method

/**
 * This method overrides the evaluation method defined in the aips.search.informed.BestFirstSearchProblem class.
 * It return the f(n) value of a node n.
 * @param node	The node to be evaluated.
 */
@Override
public double evaluation(Node node)
{
return node.getCost()+this.heuristic(node.state);	//f(n)=g(n)+h(n)
} //end method

/**
 * The heuristic function that estimates the distance of a state/node to a goal state.
 * For A* to work, this heuristic cannot over-estimate.
 * @param state The state to be estimated.
 * @return The estimated distance of state to a goal.
 */
public double heuristic(State state)
{
AntState antState=(AntState)state;				//cast State into AntState
AntState goalAntState=(AntState)this.goalState;	//cast goal state into AntState

int xDiff=antState.x-goalAntState.x;		//find X difference
int yDiff=antState.y-goalAntState.y;		//find y difference
return Math.max(Math.abs(xDiff), Math.abs(yDiff));	//return the max between x and y differences
} //end method

/**
 * The method checks if a state is a goal.
 */
@Override
public boolean isGoal(State state)
{
return state.equals(this.goalState);
} //end method
} //end class
