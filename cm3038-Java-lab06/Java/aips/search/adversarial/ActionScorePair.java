package aips.search.adversarial;

import aips.search.*;

/**
 * This class models an action-score pair which is returned by the maxValue(...) and minValue(...) methods.
 * This is needed as these methods return the value of a node also also the action leading to it.
 * @author K. Hui
 *
 */
public class ActionScorePair
{
/**
 * The action.
 */
public Action action;

/**
 * The score of the state that the action is leading to.
 */
public double score;

/**
 * Create an action-score pair.
 * @param action	The action.
 * @param score	The value of the node.
 */
public ActionScorePair(Action action,double score)
{
this.action=action;
this.score=score;
} //end method
} //end class
