package aips.lab05.antSolution;

import aips.search.*;

/**
 * This class runs the ant routing problem.
 * @author kit
 *
 */
public class RunAntRouting {
	public static void main(String[] arg)
	{
		/**
		 * This 2D int array defines our ant world.
		 * A free square is marked by 0. A non-0 means a barrier.
		 * 
		 * The matrix below is 40 columns * 20 rows.
		 */
	int[][] worldMatrix={	{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
							{0,1,1,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
							{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
							{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
							{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
							{0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,1},
							{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
							{0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0},
							{0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0},
							{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0},
							{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
							{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0}
						};

	//
	// Create the ant world, the initial state and goal state.
	//
	AntWorld antWorld=new AntWorld(worldMatrix);			//create AntWorld object from 2D int array
	AntState initialState=new AntState(3,3,antWorld);		//create initial state
	AntState goalState=new AntState(39,19,antWorld);		//create goal state
	
	//
	// Create an ant routing problem using depth-first search.
	//
//	SearchProblem problem=new AntRoutingBFS(initialState,goalState,antWorld);		//create depth-first problem
//	SearchProblem problem=new AntRoutingDFS(initialState,goalState,antWorld);		//create depth-first problem
	SearchProblem problem=new AntRoutingAStarDiagonal(initialState,goalState,antWorld);		//create A* diagonal problem
//	SearchProblem problem=new AntRoutingAStarStraightLine(initialState,goalState,antWorld);		//create A* straight-line distance problem
//	SearchProblem problem=new AntRoutingAStarManhattan(initialState,goalState,antWorld);	//create A* Manhattan distance problem
//	SearchProblem problem=new AntRoutingGreedyDiagonal(initialState,goalState,antWorld);			//create greedy best-first problem
//	SearchProblem problem=new AntRoutingGreedyStraightLine(initialState,goalState,antWorld);			//create greedy best-first problem
//	SearchProblem problem=new AntRoutingGreedyManhattan(initialState,goalState,antWorld);			//create greedy best-first problem

	System.out.println("Searching...");		//print some message
	Path path=problem.search();				//perform search, get result
	System.out.println("Done!");			//print some message
	if (path==null)							//if it is null, no solution
		System.out.println("No solution");
	else	{
			//path.print();							//otherwise print path
			visualisePath(antWorld,path);
			System.out.println("Nodes visited: "+problem.nodeVisited);
			System.out.println("Cost: "+path.cost+"\n");
			}
	} //end method
	
	/**
	 * This methods prints the ant world with the path overlaid on top.
	 * Note that we have to construct another 2D array to put every node visited by the path into it
	 * before we can do any printing.
	 * @param world The AntWorld object.
	 * @param path The path found.
	 */
	public static void visualisePath(AntWorld world,Path path)
	{
	int height=world.grid.length;					//get height of ant world
	int width=world.grid[0].length;					//get width of ant world
	
	/**
	 * first we copy the 2D boolean array of ant world into a new array of int
	 * as we have more values to store other than a true/false
	 */
	int[][] grid=new int[height][width];			//create a new int array of the same dimension
		for (int y=0;y<height;y++)					//base on value in original boolean array
			for (int x=0;x<width;x++)
				if (world.grid[y][x])				//true means a barrier
					grid[y][x]=1;					//put a 1 into the new array
				else grid[y][x]=0;					//false means empty, put a 0 into the new array
		
		/**
		 * Now we have to overlap the path onto the new int array.
		 */
		Object[] actionStateArray=path.toArray();		//convert list of action-state pair into an array
		for (int i=0;i<actionStateArray.length;i++)		//loop through all action-state pairs
			{
			ActionStatePair actionStatePair=(ActionStatePair)actionStateArray[i];	//get 1 action-state pair
			AntState antState=(AntState)actionStatePair.state;						//get the state
			grid[antState.y][antState.x]=-3;										//put a -3 into the (x,y) position
			}
		AntState startState=(AntState)path.head;			//back to the head of the path
		AntState endState=(AntState)path.getLast().state;	//and the end state
		grid[startState.y][startState.x]=-1;				//put a -1 into the starting position
		grid[endState.y][endState.x]=-2;					//put a -2 into the ending position

		/**
		 * The final printing loop.
		 */
		for (int y=0;y<height;y++)					//loop through the rows
			{
			for (int x=0;x<width;x++)				//loop through the columns in each row
				switch (grid[y][x])					//base on the square content
					{
					case 0:	System.out.print(".");		//0 means empty
							break;
					case 1:	System.out.print("X");		//1 means a barrier
							break;
					case -3:	System.out.print("#");	//-3 means a path
					break;
					case -1:	System.out.print("S");	//-1 mean starting position
						break;
					case -2:	System.out.print("E");	//-2 means ending position
					break;
					}
			System.out.println();						//print a newline at the end of every row
			}
	} //end method
} //end class
