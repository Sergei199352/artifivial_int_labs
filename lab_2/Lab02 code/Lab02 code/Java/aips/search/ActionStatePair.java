package aips.search;

/**
 * This class models an action-state pair which represents an action and the next state.
 * 
 * During the search process, we pick up a {@link Node} from the fringe, which contains a {@link State}.
 * To expand the {@link Node}, we invoke the {@link State#successor} method of
 * this {@link State} to get all valid actions and next states. (i.e. its children/successors)
 * The {@link State#successor} method of the {@link State}
 * generates and returns a list of {@link ActionStatePair} objects.
 * Each pair represents the action which can be applied to the current state,
 * and the new state after the application.
 * 
 * @author Kit-ying Hui
 *
 */
public class ActionStatePair
{
/**
 * The action component of this pair.
 */
	public Action action;
/**
 * The state after the action is applied to the current state.
 */
public State state;

/**
 * Creates an action-state object.
 * 
 * @param action The action component of the pair.
 * @param state The state component of the pair.
 */
public ActionStatePair(Action action,State state)
{
this.action=action;
this.state=state;
} //end method

/**
 * This convenient method converts an action-state pair into a String (for printing?).
 * This is mainly used for debugging.
 */
public String toString()
{
return action.toString()+"\n"+state.toString();
} //end method
} //end class
