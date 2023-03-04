package aips.search;

/**
 * This class models a node in the search tree.
 * 
 * A node is a book-keeping structure as the search algorithm explores the space.
 * It has a reference to the state of the problem, plus other attributes for book-keeping purpose.
 *  * e.g. The parent pointer points back to the parent node so that we can trace the path back to the initial state when a goal is found.
 * The cost of the path from the root node to a Node is obtained through the getCost() method.
 *  * The action attribute keeps track of the action which brings us to this state/node.
 * The depth attribute tells us the level/depth of this node in the search tree.
 * This is helpful if we want to limit the depth of the search (e.g. in a depth-limited search).
 */

public class Node
{
/**
 * This attribute stores/points to the state of the problem this node represents.
 */
public State state;

/**
 * This point to the parent node. We need this to trace back the whole path (from to goal) when we find a goal.
 */
public Node parent;	//pointing to parent node of path

/*
 * ***Update on 2015/12/03:
 * 		No longer storing cost of path in a node as it leads to a problem when another shorter path is found to the same state.
 * 		Instead of storing the cost from root to node, now we use a getCost() method to calculate the cost of the path.
 * 		This ensures that the cost in nodes are correct all the time.
 * 
 * 		Also no longer storing the depth of a node as an attribute but calculate it by a recursive method.
 */

/**
 * This is the action that brings us from the parent to this node.
 * In the root node of the tree, this attribute must be null as there is no parent node.
 */
public Action action;	//the action that takes us here

/**
 * Creates a {@link Node} object.
 * @param state The state of the problem this node represents.
 * @param parent The parent node leading to this node.
 * @param action The action that leads us from the parent to this node.
 */
public Node(State state,Node parent,Action action)
{
this.state=state;
this.parent=parent;
this.action=action;
} //end method

/**
 * Return the cost of the path from the root node to this node.
 * @return	The cost of the path (i.e. sum of all actions) from the root to this node.
 */
public double getCost()
{
//recursive version may crash on very deep path
/*
if (this.parent==null)	//this is the root node
	return 0.0;			//0 cost to here
return this.action.cost+this.parent.getCost();	//otherwise recursively calculate the cost up to the root
*/

//iterative version removes tail recursion
double result=0.0;
Node current=this;				//start from current node
while (current.parent!=null)
	{
	result+=current.action.cost;	//bug fix. (2021-03-01)
	current=current.parent;		//move to parent
	}
return result;
} //end method

/**
 * Return the depth of this node.
 * @return	The depth of the node from root. The root node has a depth of 0.
 */
public int getDepth()
{
//recursive version may crash on very deep path
/*
if (this.parent==null)	//this is the root node
	return 0;			//depth is 0
return this.parent.getDepth()+1;
*/
	
//iterative version to remove tail recursion
Node current=this;
int result=0;
while (current.parent!=null)
	{
	result++;
	current=current.parent;
	}
return result;
} //end method
} //end class
