package aips.lab05.ant;

/**
 * This class models a 2D world of the ant.
 * @author kit
 *
 */
public class AntWorld {
	
/**
 * In this 2D array, true means the grid is occupied. false means empty.
 * Note that when you access a square in the grid, the y (i.e. row) is the 1st index,
 * and the x (i.e. column) is the 2nd index.
 * So grid[y][x] gives you the content of the square at (x,y), or column x, row y.
 */
public boolean grid[][];

/**
 * Create an AntWorld object from a 2D int array.
 * @param grid A 2D int array that contains 0 and 1. 0 means a square is empty. Non-0 means it is occupied.
 */
public AntWorld(int[][] grid)
{
this.grid=new boolean[grid.length][grid[0].length];		//create 2D boolean array of the same size as the int array
for (int y=0;y<grid.length;y++)			//loop through the rows
	for (int x=0;x<grid[y].length;x++)	//loop through columns in each row
		this.grid[y][x]= grid[y][x]!=0;	//if it is not a 0, make it true, otherwise a false
} //end method

/**
 * Return the AntWorld map as a String.
 * @return A string representing the 2D ant world. This can be printed.
 */
public String toString()
{
String result="";

for (int y=0;y<this.grid.length;y++)			//loop through the rows
	{
	for (int x=0;x<this.grid[y].length;x++)		//loop through the columns in each row
		if (this.grid[y][x])
			result+="X";		//barrier is an X
		else result+=".";		//free square is a dot
	result+="\n";				//add a newline at the end of each row
	}
return result;
} //end method
} //end class
