package aips.search.informed;

import java.util.*;

import aips.search.*;

/**
 * This class models a best-first search problem.
 * To create your own specialised problem of informed search, extend this class.
 * You still need to supply the evaluation function which is necessary for best-first search.
 * The heuristic function is only needed if your evaluation function uses one.
 * 
 * @author K. Hui
 *
 */
public abstract class BestFirstSearchProblem extends aips.search.SearchProblem 
{
  /**
 * Most informed searches need to compute the distance between the current state and the goal.
 * So I am storing the goal state into this attribute.
 * You can always refer to this attribute and check if you have reached the goal state.
 * Note that this attribute is only defined in the {@link BestFirstSearchProblem} class
 * but not in its {@link SearchProblem} superclass.
 */
public State goalState=null;

/**
 * Construct a {@link BestFirstSearchProblem} object.
 * Note that BestFirstSearchProblem is an abstract class.
 * So you will not directly create a {@link BestFirstSearchProblem} object but from one of its concrete subclasses.
 * This constructor is expected to be invoked from a concrete subclass.
 * 
 * @param start	The initial state.
 * @param goal	The goal state.
 */
public BestFirstSearchProblem(State start,State goal)
{
super(start);
this.goalState=goal;
} //end method

/**
 * This is a specialised version of search tailored for informed search.
 * It takes care of repeating states.
 * If there are multiple paths to reach a certain state, it chooses the one with the lower cost.
 * 
 * The initial and goal states should be defined when the {@link BestFirstSearchProblem} object is  * created.
 * This method simply starts the search.
 * For debugging purpose, it prints a message for every 1000 nodes explored.
 * 
 * @return The path to the goal. Or <code>null</code> if there is no solution.
 */
@Override
public Path search()
{
Map<State,Node> visitedNodes=new HashMap<State,Node>();	//history
List<Node> fringe=new LinkedList<Node>();				//the list of fringe nodes

Node rootNode=new Node(this.startState,null,null);	//create root node
fringe.add(rootNode);								//add root node into fringe
visitedNodes.put(rootNode.state,rootNode);			//seen root node and state
this.nodeVisited++;									//increment node count
if (nodeVisited%1000==0)								//print message every 1000 nodes
	System.out.println("No. of nodes explored: "+nodeVisited);

while (true)
	{
	if (fringe.isEmpty())	//no more node to expand
		return null;		//no solution
	
	Node node=fringe.remove(0);		//remove and take 1st node
	if (this.isGoal(node.state))	//if goal is found
		return constructPath(node);	//construct path and return path
	
	List<ActionStatePair> childrenNodes=node.state.successor();	//get successors
	for (int i=0;i<childrenNodes.size();i++)
		{
		this.nodeVisited++;									//increment node count
		if (nodeVisited%1000==0)								//print message every 1000 nodes
			System.out.println("No. of nodes explored: "+nodeVisited);
		ActionStatePair child=(ActionStatePair)childrenNodes.get(i);
		Action action=child.action;
		State nextState=child.state;
		Node lastSeenNode=(Node)visitedNodes.get(nextState);	//look up state in history
		
		if (lastSeenNode==null)		//have not seen this state before
			{
			Node childNode=new Node(nextState,node,action);	//create child node from state
			addChild(fringe,childNode);				//add child into fringe
			visitedNodes.put(nextState,childNode);			//add into history
			}
		else{
			//
			//state is in history
			//
			if (lastSeenNode.getCost()>action.cost+node.getCost())	//going this new path is cheaper
				{
				lastSeenNode.parent=node;	//to reach this next state, you should go through the current node
				lastSeenNode.action=action;					//update the action too
				}
			}
		} //end for
	} //end while
} //end method

/**
 * Adding a child node into the fringe list.
 * We sort the fringe by the evaluation function values of the nodes.
 * This method can be customised to change the order of expanding children nodes.
 * In best-first search, this the child with the lowest f(n) score is placed
 * at the head of the queue.
 * 
 * @param fringe	A list of {@link Node}.
 * @param childNode	The child {@link Node} to add into the fringe.
 */
protected void addChildLinear(List<Node> fringe,Node childNode)
{
for (int i=0;i<fringe.size();i++)	//scan fringe from beginning to end
	{
	if (evaluation(childNode) < evaluation((Node)fringe.get(i)))	//find position where node is just bigger than child in evaluation function value
		{
			fringe.add(i,childNode);	//add child just before that node
			return;						//exit, no need to continue
		}
	}
fringe.add(childNode);		//if you hit end of list, add child to the end
} //end method

/**
 * Adding a child node into the fringe list.
 * This method can be customised to change the order of expanding children nodes.
 *
 * In best-first search, the fringe is sorted by in ascending order
 * of the evaluation function values of the nodes.
 * This means the child with the lowest f(n) score is placed at the head of the queue.
 *
 * @param fringe
 * @param childNode
 */
@Override
protected void addChild(List<Node> fringe,Node childNode)
{
//Add the child node into the fringe using binary insertion.
//Starting index is 0, ending index is length-1
//This covers the whole list.

this.addChildBinary(fringe,childNode,0,fringe.size()-1);
}

/**
 * Use binary insertion to insert the child into the fringe.
 * This given a complexity of O(log(n)) instead of O(n) where n is the length of the list.
 *
 * @param fringe	The list containing nodes to be explored.
 * @param node		The child {@link Node} to be inserted.
 * @param left		The left index into the section of the list to be inserted.
 * @param right		The right index into the list.
 */
protected void addChildBinary(List<Node> fringe,Node node,int left,int right)
{
while (true)
	{
	if (left>right)
		{
		fringe.add(left,node);
		return;
		}
	double nodeValue=this.evaluation(node);		//f(n) value of new node   
    if (left==right)							//left meets right
    	{
        double leftValue=this.evaluation(fringe.get(left));	//f(n) of node at position left
        if (leftValue>nodeValue)
        	{
            fringe.add(left,node);							//new node goes before left
            return;
        	}
        if (leftValue==nodeValue)							//node goes before left
        	{
        	if (fringe.get(left).getCost()>node.getCost())	//g(n) of new node is lower, thus less certain
        		{
        		fringe.add(left+1,node);            //#new node goes after old node
        		return;
				}
            fringe.add(left,node);					//otherwise new node goes before old node
            return;
        	}
        fringe.add(left+1,node);	//new node goes after left
        return;
    	}
    //has at least 2 elements in range
    int mid=(left+right)/2;								//find middle position
    double midValue=this.evaluation(fringe.get(mid));	//find f(n) value of node at position mid
    if (midValue==nodeValue)							//the same f(n) value
    	{
    	if (fringe.get(mid).getCost()>node.getCost())	//g(n) of new node is lower, thus less certain
    		{
            fringe.add(mid+1,node);			//new node goes after old node
            return;
    		}
		fringe.add(mid,node);				//otherwise new node goes before mid
        return;
    	}
    if (midValue>nodeValue)					//new node go into the segment before mid
    	right=mid-1;
    else left=mid+1;
	}
} //end method

/**
 * This evaluation function tells us how promising a {@link Node} is.
 * It should return a lower score for a more promising {@link Node}.
 * This must be tailored for a domain and supplied in a concrete subclass.
 * 
 * Note that in best-first search, only the evaluation function f(n) is necessary.
 * Not all best-first searches use a heuristic function h(n).
 * 
 * In this method, you can use the {@link Node#getCost()} method to get the cost of the path up to the node.
 * If you need a heuristic function, you can declare it in the {@link BestFirstSearchProblem} class
 * and invoke it from your evaluation function.
 * 
 * @param node	The node to consider. You will be mainly looking at the {@link State} contains in the {@link Node}.
 * @return The score of the node. The lower the score is, the better the node.
 */
public abstract double evaluation(Node node);
} //end class
