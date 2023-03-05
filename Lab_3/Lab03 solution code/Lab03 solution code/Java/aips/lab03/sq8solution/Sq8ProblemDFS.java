package aips.lab03.sq8solution;

import java.util.*;

import aips.search.*;

/**
 * This version of Sq8Problem uses DFS instead of BFS.
 * 
 * @author K. Hui
 *
 */
public class Sq8ProblemDFS extends Sq8Problem {
	
public Sq8ProblemDFS(State start,State goal) {
	super(start,goal);
}

/**
 * The following method is defined in the SearchProblem super-super class.
 * 
 * The Sq8Problem (BFS version) does not change it so inherits the default BFS behaviour.
 * To do DFS in this version, we override it to insert a child node to the beginning of the fringe.
 * The original BFS addChild(...) appends a new child to the end of the fringe.
 */
@Override
protected void addChild(List<Node> fringe,Node childNode)
{
fringe.add(0,childNode);
} //end method
} //end class
