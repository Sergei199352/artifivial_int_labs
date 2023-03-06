package aips.lab04.sq8solution;

import aips.search.*;

/**
 * This class models the A* search using a straight-line distance heuristic.
 * 
 * @author K. Hui
 *
 */
public class Sq8ProblemAStarStraightLine extends Sq8ProblemGreedyStraightLine
{
/**
 * Construct a Sq8Problem object from the initial and goal state.
 * @param initialState	The initial state.
 * @param goalState		The goal state.
 */
public Sq8ProblemAStarStraightLine(State initialState, State goalState)
{
super(initialState,goalState);
} //end method

/**
 * The evaluation function required by A* search.
 * Override f(n) in Greedy BF to give A* behaviour.
 * 
 * @param node	The node to be evaluated.
 * @return The score of the node. The lower the score, the more promising the node.
 */
@Override
public double evaluation(Node node)
{
return node.getCost()+heuristic(node.state);	//f(n)=g(n)+h(n) means A* if h(n) is admissible
} //end method
} //end class
