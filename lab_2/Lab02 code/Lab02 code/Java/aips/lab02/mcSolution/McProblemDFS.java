package aips.lab02.mcSolution;

import java.util.List;

import aips.search.Node;

public class McProblemDFS extends McProblem
{
/**
 * Given an initial state, this constructor creates a problem object.
 * @param initialState The initial problem state.
 */
public McProblemDFS(McState initialState,McState goalState)
{
super(initialState,goalState);
} //end method

/**
 * Our McProblem class currently uses the inherited addChild(...) method from its superclass aips.search.SearchProblem.
 * 
 * If you want to change how children are added to the fringe, uncomment this method and it will override the one defined in the superclass.
 * 
 * The line commented out adds a child node to the end of the fringe, giving you breadth-first search.
 * The current active line adds a child to the head of the fringe, giving you depth-first sesarch.
 */
protected void addChild(List<Node> fringe,Node childNode)
{
fringe.add(0,childNode);	//***Adding child node to the beginning of the fringe makes it a FILO stack.
} //end method
} //end class
