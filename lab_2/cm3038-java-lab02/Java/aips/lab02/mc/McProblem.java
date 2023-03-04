package aips.lab02.mc;

import java.util.List;

import aips.search.*;

/**
 * This subclass of SearchProblem defines a specialised search problem for the M&C domain.
 * @author K. Hui
 *
 */
public class McProblem extends SearchProblem
{
	/**
	 * The raft size defined as a class-level constant in the class.
	 */
public static final int RAFT_SIZE=2;

/**
 * In general, a state object has a isGoal(State) method defined on it to tell if that state is a goal.
 * For the M&C class, I stored the generally accepted goal state of a 3-3 M&C problem.
 * The isGoal(State) method in our specialised McState class is testing if this goal state is reached.
 * If you want to change the number of missionaries and cannibals, you need to change this object too.
 */
public McState goalState=null;


/**
 * Given an initial state, this constructor creates a problem object.
 * @param initialState The initial problem state.
 */
public McProblem(McState initialState,McState goalState)
{
super(initialState);
this.goalState=goalState;
} //end method

/**
 * Our McProblem class currently uses the inherited addChild(...) method from its superclass aips.search.SearchProblem.
 * 
 * If you want to change how children are added to the fringe, you can:
 * 1. Uncomment this method and it will override the one defined in the superclass.
 * 2. OR define a subclass of McProblem, and override the addChild(...) method there.
 * 
 * The default implementation below adds a child node to the end of the fringe. i.e. BFS.
 * You need to modify it for DFS.
 */
/*
protected void addChild(List<Node> fringe,Node childNode)
{
fringe.add(childNode);	//*** Adding child node to end of fringe. Fringe is a FIFO queue.
} //end method
*/

/**
 * Testing if the current state is a goal.
 * In our implementation of the M&C problem, we have defined a goal state in the {@link McProblem} class.
 * Note that if you want a different goal state (e.g. apart from the 3M+3C setting), you must change the
 * goal state in the {@link McProblem} class.
 */
public boolean isGoal(State state)
{
return state.equals(this.goalState);
} //end method
} //end class
