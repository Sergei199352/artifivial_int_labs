package aips.lab03.sq8solution;

import java.util.*;

import aips.search.*;

public class Sq8Problem extends SearchProblem
{
public State goalState;			//the goal state required

public Sq8Problem(State start,State goal)
{
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
} //end method}
} //end class
