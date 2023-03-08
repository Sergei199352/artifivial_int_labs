package aips.lab05.ant;

import aips.search.*;

/**
 * This class models an action of the ant.
 * @author Kit-ying Hui
 *
 */
public class AntAction extends Action
{
/**
 * The direction of movement.
 */
public Direction movement;
/**
 * The original X coordinate of the ant.
 */
int fromX;
/**
 * The original Y coordinate of the ant.
 */
int fromY;

/**
 * Create an AntAction object from a given (x,y) and movement direction.
 * @param fromX
 * @param fromY
 * @param movement
 */
public AntAction(int fromX,int fromY,Direction movement)
{
this.fromX=fromX;
this.fromY=fromY;
this.movement=movement;
} //end method

/**
 * Return details of an AntAction as a String.
 */
@Override
public String toString()
{
String result="";
int toX=0,toY=0;

//System.out.println("Complete toString() method of AntAction.");
switch (movement)
	{
	case NORTH:	result+="Move north";
				toX=fromX;
				toY=fromY-1;
				break;
	case SOUTH: result+="Move South";
				toX = fromX;
				toY = fromY+1;
				break;
	case EAST: result+="Move East";
				toX = fromX+1;
				toY = fromY;
				break;
	case WEST: result+="Move West";
				toX = fromX-1;
				toY = fromY;
				break;
				
	default:	return "Unknown!";
	}
return result+=" from ("+fromX+","+fromY+") to ("+toX+","+toY+")";	//return result as a String
} //end method
} //end class
