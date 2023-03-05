package aips.lab03.sq8;

import aips.search.*;

public class RunSq8
{
public static void main(String[] arg)
{
int[][] array1={{8,7,6},{5,4,3},{2,1,0}};	//initial state as an array
int[][] array2={{0,1,2},{3,4,5},{6,7,8}};	//goal state as an array

Sq8State initialState,goalState;	//the initial and goal states

initialState=null;	//*** Remove this statement but create a Sq8State object here.
System.out.println("*** Need to create an initial state in RunSq8All class.");

goalState=null;		//*** Remove this but create a Sq8State object here.
System.out.println("*** Need to create a goal state in RunSq8All class.");

SearchProblem problem=new Sq8Problem(initialState,goalState);	//create problem instance
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
