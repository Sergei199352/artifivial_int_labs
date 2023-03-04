package aips.lab02.mc;
import aips.search.*;

/**
 * Solving the Missionaries and Cannibals problem.
 * @author kit
 *
 */
public class RunMc {
public static void main(String[] arg)
{
McState initialState=new McState(0,0,3,3,RiverBank.SOUTH);					//create initial state object
McState goalState=new McState(3,3,0,0,RiverBank.NORTH);						//create goal state object
SearchProblem mcProblem=new McProblem(initialState,goalState);	//create problem instance with initial state
System.out.println("Searching...");			//print some message
Path path=mcProblem.search();				//perform search, get result
if (path==null)								//if it is null, no solution
	System.out.println("No solution");
else	{
		path.print();							//otherwise print path
		System.out.println("Nodes visited: "+mcProblem.nodeVisited);
		System.out.println("Cost: "+path.cost+"\n");
		}
} //end main
} //end class
