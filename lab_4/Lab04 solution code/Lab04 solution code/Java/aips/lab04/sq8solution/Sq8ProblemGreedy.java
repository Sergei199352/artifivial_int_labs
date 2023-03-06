package aips.lab04.sq8solution;

import aips.search.*;
import aips.search.informed.*;

/**
 * This abstract class models Greedy best-first search which using a evaluation function of: f(n)=h(n).
 * The heuristic h(n) is to be supplied by any concrete subclass.
 * 
 * @author K. Hui
 *
 */
public abstract class Sq8ProblemGreedy extends BestFirstSearchProblem
{
/**
 * Construct a Sq8Problem object from the initial and goal state.
 * @param initialState	The initial state.
 * @param goalState		The goal state.
 */
public Sq8ProblemGreedy(State initialState, State goalState)
{
super(initialState,goalState);
} //end method

/**
 * The evaluation function required by an informed search.
 * @param node	The node to be evaluated.
 * @return The score of the node. The lower the score, the more promising the node.
 */
@Override
public double evaluation(Node node)
{
return heuristic(node.state);	//f(n)=h(n) means Greedy best-first
} //end method
	
/**
 * This heuristic function estimate how far this state is from a goal.
 * @return The remaining distance/cost of the current state to a goal.
 */
public abstract double heuristic(State currentState);

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
