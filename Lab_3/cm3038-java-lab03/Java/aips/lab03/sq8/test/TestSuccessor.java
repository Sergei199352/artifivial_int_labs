package aips.lab03.sq8.test;

import aips.lab03.sq8.*;

public class TestSuccessor {
public static void main(String[] arg)
{
int[][] num1={{8,7,6},{5,4,3},{2,1,0}};	//array for creating state
Sq8State s=new Sq8State(num1);			//create state

System.out.println("State:\n"+s.toString());
System.out.println("All action-state:\n"+s.successor().toString());

int[][] num2={{8,7,6},{5,0,4},{3,2,1}};	//a different board configuration
s=new Sq8State(num2);					//create state

System.out.println("State:\n"+s.toString());
System.out.println("All action-state:\n"+s.successor().toString());
} //end method
} //end class