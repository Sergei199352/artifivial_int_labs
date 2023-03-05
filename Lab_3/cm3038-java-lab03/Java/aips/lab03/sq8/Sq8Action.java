package aips.lab03.sq8;

import aips.search.*;

/**
 * This class models an action in the 8-puzzle.
 * 
 * @author K. Hui
 *
 */
public class Sq8Action extends Action
{
public int blankRow,blankCol;	//row & column of the blank space
public int tileToSlide;			//the tile to slide (e.g. 1-8)
public int tileRow,tileCol;		//row & column of the tile to slide

/**
 * Create a Sq8Action object.
 * 
 * @param tileToSlide	The tile to slide.
 * @param tileRow		The row of the tile to slide.
 * @prarm tileCol		The column of the tile to slide.
 * @param blankRow		The row of the blank space in the array.
 * @param blankCol		The column of the blank space in the array.
 */
public Sq8Action(int tileToSlide,int tileRow,int tileCol,int blankRow,int blankCol)
{
this.blankRow=blankRow;
this.blankCol=blankCol;
this.tileToSlide=tileToSlide;
this.tileRow=tileRow;
this.tileCol=tileCol;
} //end method

/**
 * Convert a Sq8Action object into a String.
 */
public String toString()
{
return "Slide tile: "+tileToSlide;
} //end method
} //end class
