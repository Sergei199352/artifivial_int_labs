package aips.lab03.sq8solution;

import aips.search.*;

public class Sq8Action extends Action {
	public int blankRow,blankCol;	//index of the blank space
	public int tileToSlide;	//the tile to slide
	public int tileRow,tileCol;	//index of the tile to slide

	/**
	 * Create a Sq8Action object.
	 * 
	 * @param tileToSlide	The tile to slide.
	 * @param tileIndex		The index of the tile to slide in the array (0-8).
	 * @param blankIndex	The index of the blank space in the array.
	 */
	public Sq8Action(int tileToSlide,int tileRow,int tileCol,int blankRow,int blankCol)
	{
	this.blankRow=blankRow;
	this.blankCol=blankCol;
	this.tileToSlide=tileToSlide;
	this.tileRow=tileRow;
	this.tileCol=tileCol;
	} //end method

	public String toString()
	{
	return "Slide tile: "+tileToSlide;
	} //end method
	} //end class
