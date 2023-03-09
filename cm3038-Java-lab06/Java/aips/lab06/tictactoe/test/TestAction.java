package aips.lab06.tictactoe.test;

import aips.lab06.tictactoe.*;

public class TestAction {

public static void main(String[] arg)
{
TttAction action=new TttAction(1,2,Symbol.CROSS);
System.out.println("Action:\n"+action.toString()+"\n");

action=new TttAction(2,1,Symbol.NAUGHT);
System.out.println("Action:\n"+action.toString()+"\n");
} //end method
} //end class
