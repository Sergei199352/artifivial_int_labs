	package aips.lab03.sq8;

import java.util.*;

import aips.search.*;

public class Sq8Problem extends SearchProblem {

	public State goalState;			//the goal state required

	/**
	 * Creating a Sq8Problem requires an initial state and a goal state.
	 * 
	 * @param start	The initial state.
	 * @param goal	The goal state.
	 */
	public Sq8Problem(State start,State goal) {
		super(start);
		this.goalState=goal;
	}

@Override
/**
 * This method tests if the current Sq8State is a goal state.
 * @return Return true if the current state is a goal. false otherwise.
 */
public boolean isGoal(State state)
{
	return state.equals(this.goalState);
//
// *** Check if the given state is a goal state.
// *** Hint: Check to see if the given state is a goal.
// *** The goal state is in the "goalState" attribute of the current Sq8Problem object.
//
	//System.out.println("*** Complete isGoal(...) method in Sq8Problem class.");
	//return false;	//***This is a dummy return statement to make the compile happy.
					//***Remove this when you have filled in the correct code.
} //end method
} //end class
