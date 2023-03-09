package aips.lab06.tictactoe.test;

import aips.lab06.tictactoe.*;

public class TestToString {
public static void main(String[] arg)
{
int[][] symbol={	{1,0,2},
				{0,1,0},
				{2,0,1},
				};
TttState state=new TttState(symbol);					//create state of game board
System.out.println(state.toString());
} //end method
} //end class
