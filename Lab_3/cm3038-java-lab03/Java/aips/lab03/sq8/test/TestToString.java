package aips.lab03.sq8.test;

import aips.lab03.sq8.*;

public class TestToString {
public static void main(String[] arg)
{
int num[][]={{8,7,6},{5,4,3},{2,1,0}};	//2D int array specifying game board

Sq8State s=new Sq8State(num);			//create Sq8State from int array


for (int row=0;row<3;row++)				//change 2D int
	for (int col=0;col<3;col++)			//Sq8State object should not change
		num[row][col]=0;

System.out.println("Game board:\n"+s.toString());			//print out Sq8State as a String
} //end method
} //end class
