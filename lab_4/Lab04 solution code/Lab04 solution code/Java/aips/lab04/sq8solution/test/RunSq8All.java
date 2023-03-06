package aips.lab04.sq8solution.test;

import aips.lab04.sq8solution.*;
import aips.search.*;

/**
 * Solving the 8-Puzzle problem using best-first search.
 * @author kit
 *
 */
public class RunSq8All
{
public static void main(String[] arg)
{
int[][] initialBoard={{8,7,6},{5,4,3},{2,1,0}};		//the initial board as an int array
int[][] goalBoard={{0,1,2},{3,4,5},{6,7,8}};			//the goal board

Sq8State initialState=new Sq8State(initialBoard);	//create initial state
Sq8State goalState=new Sq8State(goalBoard);			//create goal state

SearchProblem aStarMDProblem=new Sq8ProblemAStarManhattanDistance(initialState,goalState);	//create problem instance
SearchProblem aStarSLDProblem=new Sq8ProblemAStarStraightLineDistance(initialState,goalState);	//create problem instance
SearchProblem aStarMTProblem=new Sq8ProblemAStarMisplacedTiles(initialState,goalState);	//create problem instance
//SearchProblem greedyProblem=new Sq8ProblemGreedy(initialState,goalState);	//create problem instance

System.out.println("A* Manhattan distance searching...");		//print some message
Path path=aStarMDProblem.search();				//perform search, get result
System.out.println("Done!");				//print some message
if (path==null)							//if it is null, no solution
	System.out.println("No solution");
else	{
		System.out.println("Nodes visited: "+aStarMDProblem.nodeVisited);
		System.out.println("Cost: "+path.cost+"\n");
		}

System.out.println("A* straightline distance searching...");		//print some message
path=aStarSLDProblem.search();				//perform search, get result
System.out.println("Done!");				//print some message
if (path==null)							//if it is null, no solution
	System.out.println("No solution");
else	{
		System.out.println("Nodes visited: "+aStarSLDProblem.nodeVisited);
		System.out.println("Cost: "+path.cost+"\n");
		}

System.out.println("A* misplaced tiles searching...");		//print some message
path=aStarMTProblem.search();				//perform search, get result
System.out.println("Done!");				//print some message
if (path==null)							//if it is null, no solution
	System.out.println("No solution");
else	{
		System.out.println("Nodes visited: "+aStarMTProblem.nodeVisited);
		System.out.println("Cost: "+path.cost+"\n");
		}


} //end method
} //end class
