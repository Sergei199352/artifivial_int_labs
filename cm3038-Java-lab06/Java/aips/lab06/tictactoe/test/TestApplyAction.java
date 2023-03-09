package aips.lab06.tictactoe.test;

import aips.lab06.tictactoe.*;

public class TestApplyAction {

public static void main(String[] arg)
{
int[][] symbol={	{1,0,2},
				{0,0,0},
				{2,0,1},
				};
		
TttState before=new TttState(symbol);				//create a state
TttAction action=new TttAction(2,1,Symbol.CROSS);	//create action
TttState after=before.applyAction(action);			//apply action to create next state

System.out.println("Before:\n"+before.toString());
System.out.println("Action:\n"+action.toString()+"\n");
System.out.println("After:\n"+after.toString());
} //end method
} //end class
