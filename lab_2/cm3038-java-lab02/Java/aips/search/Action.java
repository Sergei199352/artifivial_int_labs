package aips.search;

/**
 * This abstract class models a generic action in a search problem.
 * All domain-specific action classes must extend this class.
 * This class has a {@link Action#cost} attribute defined as every action has a cost.
 * That means you DO NOT need to re-declare the attribute in your domain-specific action class.
 * 
 * @author Kit-ying Hui
 */
public abstract class Action
{
/**
 * The cost of the action.
 * This is default to 1.0 for problem, which is effectively counting the number of steps.
 * 
 * Depending on your problem domain, you may need to assign a value to this attribute
 * in your domain-specific action subclass.
 * 
 * Never re-declare this attribute in your subclass as the search engine uses this attribute.
 * If you declare another action attribute in your action subclass,
 * you will confuse yourself as there will be 2 attributes with the same name.
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
 * Converting the action into a printable string.
 * 
 * In your action subclass, you should tailor it to return a String
 * containing details of the action.
 * 
 * @return A String representation of the action, which can be used for printing.
 */
public abstract String toString();
}
