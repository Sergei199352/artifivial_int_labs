package aips.lab03.sq8solution;

import aips.lab03.sq8solution.Sq8State;
import aips.search.*;

public class RunSq8
{
public static void main(String[] arg)
{
int[][] array1={{8,7,6},{5,4,3},{2,1,0}};	//initial state as an array
int[][] array2={{0,1,2},{3,4,5},{6,7,8}};	//goal state as an array

Sq8State initialState,goalState;	//the initial and goal states

initialState=new Sq8State(array1);	//create initial state
goalState=new Sq8State(array2);		//create goal state

SearchProblem problem=new Sq8Problem(initialState,goalState);	//create problem using BFS
//SearchProblem problem=new Sq8ProblemDFS(initialState,goalState);	//create problem using DFS

System.out.println("Searching...");		//print some message
Path path=problem.search();				//perform search, get result
System.out.println("Done!");			//print some message
if (path==null)							//if it is null, no solution
	System.out.println("No solution");
else	{
		path.print();							//otherwise print path
		System.out.println("Nodes visited: "+problem.nodeVisited);
		System.out.println("Cost: "+path.cost+"\n");
		}
} //end main
} //end class
