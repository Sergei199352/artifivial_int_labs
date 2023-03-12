package aips.lab02.mcSolution;
import aips.search.*;

/**
 * Solving the Missionaries and Cannibals problem.
 * @author K. Hui
 *
 */
public class RunMc {
public static void main(String[] arg)
{
McState initialState=new McState(0,0,3,3,RiverBank.SOUTH);					//create initial state object
McState goalState=new McState(3,3,0,0,RiverBank.NORTH);						//create goal state object

//create a problem instance with the initial and goal states
//SearchProblem mcProblem=new McProblem(initialState,goalState);

//
//If you want to use DFS, use the following line.
//
SearchProblem mcProblem=new McProblemDFS(initialState,goalState);

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
