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
if (symbol != Symbol.CROSS && symbol != Symbol.NAUGHT){
    result+="the actio  is uknown";
}
if (symbol == Symbol.CROSS){
    result +=" Human Action at "+ "Column " + x +" Row "+ y + " Sthe symbol placed is X";
    
 }
 if (symbol == Symbol.NAUGHT){
    result +=" Computer Action at "+ "Column " + x +" Row "+ y + " Sthe symbol placed is O";
 }

//
//*** Complete this method to return the action as a String.
//*** You should compose a String that tells what symbol is put at what position.
//

return result;
} //end method
} //end class
