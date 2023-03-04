package aips.lab02.mc;

import java.util.*;

import aips.search.ActionStatePair;
import aips.search.State;

/**
 * This class implements the State to model the state of the Missionaries & Cannibals problem.
 * The state of the M&C world is captured by 5 object-level attributes.
 * 
 * @author kit
 *
 */
public class McState implements State
{
/**
 * Number of missionaries in the northern bank.
 */
public int northMissionaries;

/**
 * Number of cannibals in the northern bank.
 */
public int northCannibals;

/**
 * Number of missionaries in the southern bank.
 */
public int southMissionaries;

/**
 * Number of cannibals in the southern bank.
 */
public int southCannibals;

/**
 * The location of the raft as defined in the {@link RiverBank} enumerated type.
 */
public RiverBank raftLocation;

/**
 * Create a McState object with the given initial values.
 * 
 * @param nm Number of missionaries in the northern bank.
 * @param nc Number of cannibals in the northern bank.
 * @param sm Number of missionaries in the southern bank.
 * @param sc Number of cannibals in the southern bank.
 * @param raft The location of the raft as defined.
 */
public McState(int nm,int nc,int sm,int sc,RiverBank raft)
{
this.northMissionaries=nm;	
this.northCannibals=nc;	
this.southMissionaries=sm;	
this.southCannibals=sc;
this.raftLocation=raft;
} //end method

/**
 * Converting a {@link McState} object into a {@link String}.
 */
public String toString()
{
String result="North: "+this.northMissionaries+"M "+this.northCannibals+"C";

if (raftLocation==RiverBank.NORTH)
	result+=" Raft";
result+="\nSouth: "+this.southMissionaries+"M "+this.southCannibals+"C";
if (raftLocation==RiverBank.SOUTH)
	result+=" Raft";
return result;
} //end method

/**
 * Check for equivalence of 2 states.
 * This method overrides the {@link java.lang.Object#equals(Object) equals(Object} method defined on the {@link java.lang.Object} class.
 * Note that the input parameter is an {@link java.lang.Object} object.
 * 
 * In the M&C problem, 2 states are consider equal if the number of missionaries and cannibals are the same on both banks,
 * and the locations of the raft are the same.
 * This method is used by the {@link java.util.Set#contains(Object) Set.contains(Object)} method to test for
 * membership in a {@link java.util.Set Set.}
 * The method signature must be kept for the history testing to work.
 * @param state The other state to test.
 * @return true if the parameter state is the same as the current state object.
 */
public boolean equals(Object state)
{
if (!(state instanceof McState))	//if the given parameter is not a McState
		return false;				//it must be false
McState mcState=(McState)state;
return	this.northMissionaries==mcState.northMissionaries &&
		this.northCannibals==mcState.northCannibals &&
		this.southMissionaries==mcState.southMissionaries &&
		this.southCannibals==mcState.southCannibals &&
		this.raftLocation==mcState.raftLocation;
} //end method

/**
 * This method overrides the default one inherited from the superclass(es).
 * Because our {@link McState} objects are being put into a history which is implemented
 * as a {@link java.util.HashSet HashSet}, we need to ensure that {@link McState} objects which are "equal" have the same
 * <code>hashCode()</code> value.
 * In this implementation, we are using the the number of missionaries and cannibals
 * on both banks to calculate an <code>int</code> hash value.
 */
public int hashCode()
{
return this.northMissionaries*1000 +
		this.northCannibals*100 +
		this.southMissionaries*10 +
		this.southCannibals;
} //end method

/**
 * Find the actions and next states of the current state.
 * @return A list of ActionStatePair objects.
 */
public List<ActionStatePair> successor()
{
List<ActionStatePair> result=new ArrayList<ActionStatePair>();		//I chose to use an ArrayList object as the list will be short.

//
//This should not happen but just to be safe.
//
if (this.isInvalid())				//if current state is invalid
	return result;					//return an empty set

//
// I changed the code here a bit to make it easier to understand.
//
int numMissionariesOnBank=0;	//the number of M on the current bank
int numCannibalsOnBank=0;	//the number of C

//
// set up values of these 2 variables properly depending on where the raft is
//
if (this.raftLocation==RiverBank.NORTH)
	{
	numMissionariesOnBank=this.northMissionaries;
	numCannibalsOnBank=this.northCannibals;
	}
else	{
	numMissionariesOnBank=this.southMissionaries;
	numCannibalsOnBank=this.southCannibals;
	}

//
// The main loops going through all combinations of M and C.
// Note that we start from the max value of M and C down to 0.
// This makes us generate actions that prefer moving more M and C than fewer.
//
// For either M or C, the max number we can move is the minimum between the no. of M/C on the current bank and the raft size.
//
for (int m=Math.min(numMissionariesOnBank,McProblem.RAFT_SIZE);m>=0;m--)
	for (int c=Math.min(numCannibalsOnBank,McProblem.RAFT_SIZE);c>=0;c--)
	{
	//
	// You need at least 1 person on the raft, and not more than raft size.
	// If M+C is within the acceptable range, create an action.
	//
	if (m+c <= McProblem.RAFT_SIZE && m+c>0)
		{
		McAction action=new McAction(m,c,oppositeBank(this.raftLocation));
		McState nextState=this.applyAction(action);
		if (!nextState.isInvalid())	//check for valid combination of M&C on both sides
			result.add(new ActionStatePair(action,nextState));
		} //end if
	} //end for
return result;
}

/**
 * Check if a state is invalid.
 * @return true if a state is invalid. Or false otherwise.
 */
public boolean isInvalid()
{
//
// any number <0 is invalid
//
if (this.northMissionaries<0 || this.northCannibals<0 || this.southMissionaries<0 || this.southCannibals<0)
	return true;

//
//more cannibals than missionaries on the N
//
if (this.northMissionaries>0 && this.northCannibals>this.northMissionaries)
	return true;

//
//more cannibals than missionaries on the S
//
if (this.southMissionaries>0 && this.southCannibals>this.southMissionaries)
	return true;
return false;	//if none of the above, then state is valid
} //end method

/**
 * Apply an action to the current state.
 * @param action The action to apply.
 * @return The next state after the action is applied to the current state object.
 */
public McState applyAction(McAction action)
{
	if (action.toBank==RiverBank.NORTH)
		return new McState(	this.northMissionaries+action.missionaries,
							this.northCannibals+action.cannibals,
							this.southMissionaries-action.missionaries,
							this.southCannibals-action.cannibals,
							RiverBank.NORTH);
	return new McState(	this.northMissionaries-action.missionaries,
			this.northCannibals-action.cannibals,
			this.southMissionaries+action.missionaries,
			this.southCannibals+action.cannibals,
			RiverBank.SOUTH);
} //end method

/**
 * A handy method to find the opposite bank of the river.
 * @param current
 * @return
 */
private RiverBank oppositeBank(RiverBank current)
{
if (current==RiverBank.NORTH)
	return RiverBank.SOUTH;
return RiverBank.NORTH;
} //end method
} //end class
