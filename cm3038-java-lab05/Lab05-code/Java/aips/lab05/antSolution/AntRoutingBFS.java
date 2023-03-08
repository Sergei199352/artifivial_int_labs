package aips.lab05.antSolution;

import aips.search.*;

/**
 * This class models the ant routing problem as a breadth-first search.
 * It uses the aips.search.SearchProblem class.
 * @author Kit-ying Hui
 */
public class AntRoutingBFS extends SearchProblem
{
/**
 * The goal state.
 */
public AntState goal;
/**
 * A reference to the ant world.
 */
public AntWorld world;

/**
 * Create an AntRoutingBreadthFirst object from the initial state, goal state, and ant world.
 * @param start	The initial state.
 * @param goal	The goal state.
 * @param world	The world as an AntWorld object.
 */
public AntRoutingBFS(AntState start, AntState goal,AntWorld world) 
{
super(start);		//initial the SearchProblem by calling the superclass constructor.
this.goal=goal;		//store the goal state
this.world=world;	//store the world object
} //end method

/**
 * This method override the superclass isGoal(...) method to check for a goal state.
 * It simply checks if the state in question equals to the goal state (stored in the goal attribute).
 */
@Override
public boolean isGoal(State state)
{
return state.equals(this.goal);
} //end method
} //end class
