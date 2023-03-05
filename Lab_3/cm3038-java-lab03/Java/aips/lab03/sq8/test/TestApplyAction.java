package aips.lab03.sq8.test;

import aips.lab03.sq8.*;

public class TestApplyAction {

public static void main(String[] arg)
{
int num[][]={{8,7,6},{5,4,3},{2,1,0}};
Sq8State before=new Sq8State(num);			//create initial state
Sq8Action action=new Sq8Action(3,1,2,2,2);	//create action to move tile "3" downward
Sq8State after=before.applyAction(action);	//apply action

System.out.println("Before:\n"+before.toString());
System.out.println("Action:\n"+action.toString()+"\n");
System.out.println("After:\n"+after.toString());
} //end method
} //end class
