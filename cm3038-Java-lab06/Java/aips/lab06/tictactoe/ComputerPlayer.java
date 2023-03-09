package aips.lab06.tictactoe;

import aips.search.Action;
import aips.search.adversarial.*;

/**
 * This class models a computer player in the Tic-Tac-Toe game.
 * @author K. Hui
 *
 */
public class ComputerPlayer extends Player
{
/**
 * Creates a computer player.
 * @param name
 * @param role
 */
public ComputerPlayer(String name,PlayerRole role)
{
super(name,role);
} //end method

/**
 * Get the action based on the current state.
 * @param problem The current search problem. We need this as the search method is defined on the problem object.
 * @param state The current game state. This surely affects our decision.
 * @return The best action to take at this state.
 */
public Action getAction(MiniMaxSearch problem,State state)
{
System.out.println(this.name+"'s turn.");
ActionScorePair result=problem.search(state,this.role);		//find a move for the current state and role
if (result==null)											//this should not happen. Just for safety.
	return null;
System.out.println("Node visited: "+problem.nodeVisited);	//print some statistics in making this move
System.out.println("Action score: "+result.score);
return result.action;										//return the action decided
} //end method
} //end class
