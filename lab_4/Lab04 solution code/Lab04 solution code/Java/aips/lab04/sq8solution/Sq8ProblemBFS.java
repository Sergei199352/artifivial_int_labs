package aips.lab04.sq8solution;

import aips.search.*;

/**
 * This is an uninformed search problem specialised for the 8-Puzzle problem using BFS.
 * @author kit
 *
 */
public class Sq8ProblemBFS extends SearchProblem
{
State goalState;

/**
 * Construct a Sq8Problem object from the initial and goal state.
 * @param initialState	The initial state.
 * @param goalState		The goal state.
 */
public Sq8ProblemBFS(State initialState, State goalState)
{
super(initialState);
this.goalState=goalState;
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
