package aips.lab06.tictactoe;

import aips.search.*;

public class TttAction extends Action {
public int x;
public int y;
public Symbol symbol;

public TttAction(int x,int y,Symbol symbol)
{
this.x=x;
this.y=y;
this.symbol=symbol;	
} //end method

@Override
public String toString()
{
String result="";

//
//*** Complete this method to return the action as a String.
//*** You should compose a String that tells what symbol is put at what position.
//

return result;
} //end method
} //end class
