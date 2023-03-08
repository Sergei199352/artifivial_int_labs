package aips.lab05.antSolution;

import java.util.*;

import aips.search.*;

/**
 * This class models the state of an ant in a 2D world.
 * @author kit
 *
 */
public class AntState implements State {

	/**
	 * The X coordinate of the ant.
	 * This is also called the column.
	 * Note that X must be between 0 and width of world -1.
	 */
	public int x;
	
	/**
	 * The Y coordinate of the ant.
	 * This is also called the row.
	 * Note that Y must be between 0 and height of world -1.
	 */
	public int y;
	
	/**
	 * A reference to the AntWorld object.
	 * Strictly speaking this is not needed to model the ant.
	 * But when we find the actions, we need to know if there is any barrier,
	 * and the action-finding method only has the AntState as input.
	 * So it is handy to have a reference to the AntWorld in an AntState.
	 * Note that all AntWorld objects created will refer to the one-and-only-one AntWorld object.
	 * There is no need to create multiple AntWorld objects.
	 */
	public AntWorld world;

	/**
	 * Create a new AntWorld object with at position (x,y), with reference to an AntWorld.
	 * @param x The X coordinate of the ant.
	 * @param y The Y coordinate of the ant.
	 * @param world A reference to the AntWorld object.
	 */
	public AntState(int x,int y,AntWorld world)
	{
	this.x=x;
	this.y=y;
	this.world=world;
	} //end method
	
	/**
	 * Create a list of action-pair from the current AntState.
	 * @return A List<ActionStatePair> that contains all valid action and next-state pairs.
	 */
	@Override
	public List<ActionStatePair> successor()
	{
	List<ActionStatePair> result=new ArrayList<ActionStatePair>();

	/**
	 * See if ant can move to the north.
	 * If yes, create and add the action-state pair into the list of result.
	 */
	if (this.y>0)							//ant is not on the north border
		{
		if (!this.world.grid[y-1][x])		//check to see if space in north is free
			{
			AntAction action=new AntAction(x,y,Direction.NORTH);					//create Action object
			AntState nextState=this.applyAction(action);							//apply action to find next state
			ActionStatePair actionStatePair=new ActionStatePair(action,nextState);	//create action-state pair
			result.add(actionStatePair);											//add action-state pair into list
			}
		}
	if (this.y<this.world.grid.length-1)
		{
		if (!this.world.grid[y+1][x])
			{
			AntAction action=new AntAction(x,y,Direction.SOUTH);
			AntState nextState=this.applyAction(action);
			ActionStatePair actionStatePair=new ActionStatePair(action,nextState);
			result.add(actionStatePair);
			}
		}
	if (this.x>0)
		{
		if (!this.world.grid[y][x-1])
			{
			AntAction action=new AntAction(x,y,Direction.WEST);
			AntState nextState=this.applyAction(action);
			ActionStatePair actionStatePair=new ActionStatePair(action,nextState);
			result.add(actionStatePair);
			}
		}
	if (this.x<this.world.grid[y].length-1)
		{
		if (!this.world.grid[y][x+1])
			{
			AntAction action=new AntAction(x,y,Direction.EAST);
			AntState nextState=this.applyAction(action);
			ActionStatePair actionStatePair=new ActionStatePair(action,nextState);
			result.add(actionStatePair);
			}
		}
	return result;	//return the list of action-state pair
	} //end method
	
	/**
	 * Compare if the current AntState equals to another state.
	 * We only compare the x and y values.
	 * There is no need to compare the AntWorld.
	 * @return true if the 2 AntState objects are equal. false otherwise.
	 */
	public boolean equals(Object state)
	{
	if (!(state instanceof AntState))				//make sure that state is an AntState object
		return false;								//if it is not, return false

	AntState antState=(AntState)state;					//cast state into an AntState object
	return this.x==antState.x && this.y==antState.y;	//true if x and y are the same
	} //end method

	/**
	 * Compute a hash code of the AntState.
	 * This is needed as we store our AntState objects into a hash map.
	 * We take the simple formula of y*100+x.
	 */
	public int hashCode()
	{
	return this.x+this.y*100;
	} //end method
	
	/**
	 * Apply an action to the current state, giving the next state.
	 * @param action The action to apply.
	 * @return A next state for the ant.
	 */
	public AntState applyAction(AntAction action)
	{
	int newX=0,newY=0;				//to hold new x and y after action is applied

	switch (action.movement)
		{
		case NORTH:	newX=this.x;		//moving north, x remains unchange
					newY=this.y-1;		//decrement y
					break;
		case SOUTH:	newX=this.x;
					newY=this.y+1;
					break;
		case WEST:	newX=this.x-1;
					newY=this.y;
					break;
		case EAST:	newX=this.x+1;
					newY=this.y;
					break;
		default:	newX=this.x;
					newY=this.y;
		}
	AntState result=new AntState(newX,newY,this.world);	//create next state from new x,y and ant world
	return result;	//return next state as result
	} //end method
	
	/**
	 * Return the AntState as a String.
	 * In the returned String:
	 * "X" means a barrier/obstacle
	 * "." means an empty space
	 * "O" means the ant
	 */
	public String toString()
	{
	String result="";

	for (int y=0;y<this.world.grid.length;y++)
		{
		for (int x=0;x<this.world.grid[y].length;x++)
			{
			if (this.x==x && this.y==y)		//if x & y equals to x & y of ant
				result+="O";				//append an "O"
			else if (this.world.grid[y][x])	//if grid is occupied
				result+="X";				//append an "X"
			else result+=".";				//if empty, append a "."
			}
		result+="\n";	//append a newline at the end of each row
		}
	return result;		//return result String
	} //end method
} //end class
