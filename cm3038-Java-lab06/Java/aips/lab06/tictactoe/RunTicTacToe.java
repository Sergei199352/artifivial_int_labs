package aips.lab06.tictactoe;

import aips.search.*;
import aips.search.adversarial.*;

public class RunTicTacToe 
{
public static void main(String[] arg)
{
TttState gameState=new TttState();	//create initial state of empty board
MiniMaxSearch ttt=new TttProblem();	//create minimax problem instance

Player player1=new HumanPlayer("Human",PlayerRole.MAX);
Player player2=new ComputerPlayer("Computer 2",PlayerRole.MIN);
Player currentPlayer=player1;

System.out.println(gameState.toString());

while (!ttt.isTerminal(gameState))
	{
	Action action=currentPlayer.getAction(ttt,gameState);	//get player's action
	if (action==null)										//action is null
		System.out.println("No action needed.");
	else{
		System.out.println("Action: "+action.toString());	//otherwise print action
		gameState=gameState.applyAction(action);			//apply action to current state
		System.out.println(gameState.toString());			//print new game state
		}
	if (currentPlayer==player1)		//switch player
		currentPlayer=player2;
	else currentPlayer=player1;
	} //end while
System.out.println("Game Ended!");
} //end method
} //end class
