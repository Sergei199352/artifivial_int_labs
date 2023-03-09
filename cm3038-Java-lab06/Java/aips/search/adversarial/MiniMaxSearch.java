package aips.search.adversarial;
import java.util.*;

import aips.search.Action;
import aips.search.ActionStatePair;

/**
 * This class models a problem with Minimax search facility.
 * @author K. Hui
 *
 */
public abstract class MiniMaxSearch
{
	/**
	 * This attribute counts the number of nodes visited during the search.
	 */
public int nodeVisited=0;

/**
 * Perform a minimax search on the current state and player role.
 * @param state The current state.
 * @param role	The role of the player. It must be PlayerRole.MAX or PlayerRole.MIN.
 * @return Return an ActionScorePair. The action attribute is the action to take. The score attribute is the utility value.
 */
public ActionScorePair search(State state,PlayerRole role)
{
	this.nodeVisited=0;			//reset node count to 0
	
	if (role==PlayerRole.MAX)	//base on the role, call the appropriate method
		return maxValue(state);
	if (role==PlayerRole.MIN)
		return minValue(state);
	return null;
} //end method



/**
 * Find the action to take at a max level.
 * @param state The current state.
 * @return An ActionScorePair. The action attribute contains the action to take.
 */
protected ActionScorePair maxValue(State state)
{
	this.nodeVisited++;		//increment node count by 1
	
	//If current state is a terminal state, return the utility score in an ActionScorePair.
	//There is no action for max player to take as it is already a terminal state.
	//This is the result of the min player's action.
	//So we calculate the utility score with respect to the min player.
	//
	if (this.isTerminal(state))												
		return new ActionScorePair(null,this.utility(state,PlayerRole.MIN));

	//If it is not yet a terminal state, the max player can search for the best action among its children.
	//To start, set score to -infinity so that any score from a child will be bigger (as this is a max node).
	
	double score=Double.NEGATIVE_INFINITY;
	Action action=null;														//initially no action to take
	
	List<ActionStatePair> childrenList=state.successor(PlayerRole.MAX);		//generate all successors of a MAX level
	Object[] childrenArray=childrenList.toArray();							//convert list of ActionStatePair to an array for easier manipulation
	for (int i=0;i<childrenArray.length;i++)								//loop through all children
		{
		ActionStatePair actionStatePair=(ActionStatePair)childrenArray[i];	//get a child
		State child=(State)actionStatePair.state;							//cast it to a aips.search.adversarial.State object

		//do a recursive call on this child to find its score
		//this child is a min node
		//
		double childScore=minValue(child).score;
		if (childScore>score)					//if score of child is bigger than current best score
			{
			score=childScore;					//replace current best score with child score
			action=actionStatePair.action;		//also remember action leading to this child
			}
		}
	//after going through all children
	//we know the best child score and the action leading to it
	//return action and score as an ActionScorePair
	//
	return new ActionScorePair(action,score);
} //end method


/**
 * Find the action to take at a min level.
 * @param state The current state.
 * @return An ActionScorePair. The action attribute contains the action to take.
 */
protected ActionScorePair minValue(State state)
{
	this.nodeVisited++;														//increment node count by 1
	
	//If current state is a terminal state, return the utility score in an ActionScorePair.
	//There is no action for max player to take as it is already a terminal state.
	//This is the result of the max player's action.
	//So we calculate the utility score with respect to the max player.
	//
	
	if (this.isTerminal(state))
		return new ActionScorePair(null,this.utility(state,PlayerRole.MAX));

	//If it is not yet a terminal state, the min player can search for the best action among its children.
	//To start, set score to +infinity so that any score from a child will be lower (as this is a min node).
	
	double score=Double.POSITIVE_INFINITY;									//otherwise prepare to search
	Action action=null;
	List<ActionStatePair> childrenList=state.successor(PlayerRole.MIN);		//find all successors of a MIN level
	Object[] childrenArray=childrenList.toArray();
	
	for (int i=0;i<childrenArray.length;i++)	//go through each child
		{
		ActionStatePair actionStatePair=(ActionStatePair)childrenArray[i];	//cast element into ActionStatePair
		State child=(State)actionStatePair.state;							//get the child state

		//do a recursive call on this child to find its score
		//this child is a max node
		//
		double childScore=maxValue(child).score;
		if (childScore<score)					//if child score is smaller
			{
			score=childScore;					//remember this child score as it is the best so far
			action=actionStatePair.action;		//also remember the action leading to this child
			}
		}
	//after going through all children
	//we know the best child score and the action leading to it
	//return action and score as an ActionScorePair
	//
	return new ActionScorePair(action,score);
} //end method

/**
 * Check if a state is terminal.
 * This method is domain-dependent and must be implemented by a class that extends the MiniMaxSearch abstract class.
 * @param state The state to check.
 * @return true if it is a terminal state. false otherwise.
 */
public abstract boolean isTerminal(State state);

/**
 * Find the utility score of a state.
 * This method is domain-dependent and must be implemented by a class that extends the MiniMaxSearch abstract class.
 * @param state The state.
 * @return The utility score.
 */
public abstract double utility(State state,PlayerRole role);
} //end class
