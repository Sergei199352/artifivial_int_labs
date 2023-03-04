package aips.search;
import java.util.*;

/**
 * This class models a path that traces the route from a root node to a node in the tree.
 * It is simply a list of {@link ActionStatePair} objects plus a head node (of {@link State}).
 * Conceptually a path is a specialised list of action-state pairs with a head node.
 * That is why it is extending the {@link java.util.LinkedList} concrete class
 * which implements the {@link java.util.List} interface.
 * I chose the {@link java.util.LinkedList} class in this case.
 * It can be any class that implements {@link java.util.List}.
 * 
 * You do not need to do with this class.
 * A {@link Path} is returned as the result when you do a search.
 * If the returned value is not <code>null</code>,
 * you can simply call the {@link Path#print() print()} method to print out the path.
 * 
 * @author K. Hui
 *
 */
public class Path extends LinkedList<ActionStatePair>
{
/**
 * The root node of the path.
 */
public State head;

/**
 * The cost of the path from root to the final node/goal.
 */
public double cost;

/**
 * Creates an empty path with no head node and 0 cost.
 */
public Path()
{
this.head=null;
this.cost=0.0;
}

/**
 * Prints the path, with each node and action.
 * The output is controlled by the {@link aips.search.State#toString() toString()} method
 * of the {@link State} objects and {@link Action} objects,
 * which can be customised in the domain specific sub-classes.
 */
public void print()
{
if (head==null)
	return;
System.out.println(head.toString()+"\n");
Iterator<ActionStatePair> i=this.iterator();
while (i.hasNext())
	{
	ActionStatePair next=i.next();
	System.out.println(next.action.toString());
	System.out.println(next.state.toString());
	System.out.println();
	}
} //end method
} //end class
