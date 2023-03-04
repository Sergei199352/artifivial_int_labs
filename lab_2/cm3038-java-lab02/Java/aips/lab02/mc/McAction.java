package aips.lab02.mc;

import aips.search.*;

/**
 * This specialised Action class models an action in the M&C problem.
 * In the M&C problem, an action specifies how many M&C you have in the raft,
 * and where the raft is moving to.
 * The "moving from" is implicit. So we do not need to store both "to" and "from" explicitly. 
 * @author kit
 *
 */
public class McAction extends Action
{
	/**
	 * The number of missionaries we are moving in this action.
	 */
public int missionaries;

/**
 * The number of cannibals we are moving.
 */
public int cannibals;

/**
 * We are moving the raft to this bank.
 * It must be either NORTH or SOUTH as defined in the RiverBank enumerated type.
 */
public RiverBank toBank;

/**
 * Create an McAction object.
 * @param m The number of missionaries to move.
 * @param c The number of cannibals.
 * @param to The destination of the raft.
 */
public McAction(int m,int c,RiverBank to)
{
this.missionaries=m;
this.cannibals=c;
this.toBank=to;
} //end method

/**
 * This method is required by the Action interface.
 * It converts the McAction object into a String for convenient printing purpose.
 */
public String toString()
{
String result;

if (this.toBank==RiverBank.NORTH)
	result="South->North ";
else result="North->South ";
result+=this.missionaries+"M "+this.cannibals+"C";
return result;
} //end method
} //end class
