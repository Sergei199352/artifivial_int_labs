package aips.lab05.antSolution;

import java.util.List;

import aips.search.ActionStatePair;
import aips.search.Node;
import aips.search.Path;
import aips.search.SearchProblem;
import aips.search.State;

/**
 * This class models the ant routing finding problem as a depth-first search.
 * It extends our uninformed search library class aips.search.SearchProblem.
 * @author kit
 *
 */
public class AntRoutingDFS extends SearchProblem
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
 * Create an AntRoutingDepthFirst object from the initial state, goal state, and ant world.
 * @param start	The initial state.
 * @param goal	The goal state.
 * @param world	The world as an AntWorld object.
 */
public AntRoutingDFS(AntState start, AntState goal,AntWorld world)
{
super(start);
this.goal=goal;
this.world=world;
} //end method

/**
 * We override the addChild(List<Node>,Node) method to add the new child to the beginning of the fringe.
 * This gives a depth-first behaviour.
 */
protected void addChild(List<Node> fringe,Node childNode)
{
fringe.add(0,childNode);
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
