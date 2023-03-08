package aips.lab05.ant.test;

import aips.lab05.ant.*;

public class TestAction {

public static void main(String[] arg)
{
AntAction action=new AntAction(3,8,Direction.EAST);

System.out.println("Action:\n"+action.toString()+"\n");
} //end method
} //end class
