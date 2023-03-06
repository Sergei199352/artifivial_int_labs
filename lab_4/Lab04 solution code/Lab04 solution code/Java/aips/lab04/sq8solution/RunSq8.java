package aips.lab04.sq8solution;

import aips.search.*;

/**
 * Solving the 8-Puzzle problem using best-first search.
 * 
 * @author K. Hui
 *
 */
public class RunSq8
{
public static void main(String[] arg)
{
int[][] initialBoard={{0,1,2},{3,4,5},{7,8,6}};		//this initial state can trick Greedy Best-first
int[][] goalBoard={{0,1,2},{3,4,5},{6,7,8}};		//the goal board

Sq8State initialState=new Sq8State(initialBoard);	//create initial state
Sq8State goalState=new Sq8State(goalBoard);			//create goal state

//SearchProblem problem=new Sq8ProblemBFS(initialState,goalState);	//BFS
//SearchProblem problem=new Sq8ProblemDFS(initialState,goalState);	//DFS
//SearchProblem problem=new Sq8ProblemGreedyMisplacedTile(initialState,goalState);	//GBF using misplaced-tile heuristic
//SearchProblem problem=new Sq8ProblemGreedyStraightLine(initialState,goalState);	//GBF using straight-line distance heuristic
//SearchProblem problem=new Sq8ProblemGreedyManhattan(initialState,goalState);		//GBF using Manhattan distance heuristic
//SearchProblem problem=new Sq8ProblemAStarMisplacedTile(initialState,goalState);	//A* using misplaced-tile heuristic
//SearchProblem problem=new Sq8ProblemAStarStraightLine(initialState,goalState);	//A* using straight-line distance heuristic
SearchProblem problem=new Sq8ProblemAStarManhattan(initialState,goalState);		//A* using Manhattan distance heuristic

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
} //end method
} //end class
