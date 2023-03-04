package aips.search;

/**
 * This abstract class defines all methods that an action (which changes state) must have.
 * It also has an cost attribute which defaults to 1.0.
 * 
 * If your action has a cost other than 1.0, make sure that you assign the correct value to the cost attribute.
 * 
 * @author K. Hui
 */
public abstract class Action
{
/**
 * The cost of an action which is default to 1.0.
 * If the action cost is not 1.0 in your domain, you can simply assign the correct value to this attribute.
 * 
 * DO NOT try to re-declare this cost attribute in your subclass as it is inherited automatically.
 * If you do, you may confuse the search engine or yourself.
 */
public double cost;

/**
 * The default constructor that gives a cost of 1.0 to an {@link Action}.
 */
public Action()
{
this.cost=1.0;	
} //end method

/**
 * Return the action into a printable string.
 * This method should be tailored in your action sub-class to customise the way an {@link Action} is printed.
 * 
 * @return A String representation of the action, which can be used for printing.
 */
public abstract String toString();
}
