package aips.search.informed;

import java.util.*;
import aips.search.*;

/**
 * This is a specialised {@link SearchProblem} abstract subclass for modelling best-first informed searches.
 * This class contains most ingredients of an informed search problem.
 * 
 * To create your customised sub-class of informed search,
 * simply extend this class and then supply the evaluation function.
 * 
 * @author K. Hui
 *
 */
public abstract class BestFirstSearchProblem extends aips.search.SearchProblem {

/**
 * Most informed searches need to compute the distance between the current state and a goal.
 * So I am storing the goal state in this attribute.
 * You can always refer to this attribute and check if you have reached the goal state
 * (using the {@link State#equals} method of your customised {@link State}).
 * Note that this attribute is only defined in the {@link BestFirstSearchProblem} class
 * but not in its {@link SearchProblem} superclass,
 * because uninformed searches do not need to look at the goal state to compute any evaluation function value.
 */
public State goalState=null;

/**
 * Construct a {@link BestFirstSearchProblem} object that presents a best-first search problem.
 * 
 * Note that as BestFirstSearchProblem is an abstract class,
 * you cannot directly create a {@link BestFirstSearchProblem}.
 * Instead, you are expect to create a concrete sub-class of {@link BestFirstSearchProblem}
 * and then create an object from there.
 * This constructor is thus expected to be invoked from a concrete subclass.
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
 * 
 * After you have create a customised {@link BestFirstSearchProblem} object,
 * you can simply invoke this method to get the solution path.
 * The initial and goal states should be defined when the {@link BestFirstSearchProblem} object is created.
 * 
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
			addChildBinary(fringe,childNode);				//add child into fringe
			visitedNodes.put(nextState,childNode);			//add into history
			}
		else{
			//
			//state is in history
			//
			if (lastSeenNode.getCost()>action.cost+node.getCost())	//going this new path is cheaper
				{
				lastSeenNode.parent=node;	//to reach this next state, you should go through the current node
/*
 * *** Updated on 2015/12/03:
 * 		Do not store cost of path in node as it leads to a bug when a shorter path is found to the same state.
 * 		Cost of path is now recursively calculated from action cost leading to the current node + cost of path to parent node.
 */
				lastSeenNode.action=action;					//update the action too
				}
			}
		} //end for
	} //end while
} //end method

/**
 * This specialised method adds a child node into the fringe list while
 * keeping the nodes in the list in ascending order of evaluation function value (i.e. f(n)).
 * This older version is not used anymore as it uses a linear scan.
 * 
 * @param fringe	A list of {@link Node}.
 * @param childNode	The child {@link Node} to add into the fringe.
 */
@Override
protected void addChild(List<Node> fringe,Node childNode)
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
 * This specialised method adds a child node into the fringe list while
 * maintaining the nodes in the list in ascending order of their f(n) value
 * using binary search technique.
 * This speeds up the insertion.
 * 
 * @param fringe
 * @param childNode
 */
protected void addChildBinary(List<Node> fringe,Node childNode)
{
this.addChildBinary(fringe,childNode,0,fringe.size()-1);
}
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
 * This abstract method defines the evaluation function is to be supplied for the customised informed search problem.
 * To define your own domain-specific best-first search problem, you must supply this method in your concrete sub-class.
 * 
 * Note that the {@link BestFirstSearchProblem} class only requires the evaluation function (i.e. f(n)).
 * If you are implementing A* search, your evaluation function will a heuristic function too (i.e. h(n)),
 * which you also have to supply.
 * 
 * If you want to get the cost of the path from the node up to a node,
 * you can invoke the {@link Node#getCost()} method.
 * 
 * @param node	The node to consider.
 * @return The score of the node. The lower the score is, the better the node.
 */
public abstract double evaluation(Node node);
} //end class
