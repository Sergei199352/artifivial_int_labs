package aips.search;

import java.util.*;

/**
 * This interface defines the methods required on a problem state.
 * Any class that represents a problem state must implement this interface.
 *
 * @author K. Hui
 */
public interface State {

/**
 * Return the state into a printable {@link String}.
 * You should customise this method for your state so that it return a {@link String}
 * which can be easily printed in the console.
 * @return A {@link String} representation of the state.
 */
public String toString();

/**
 * Checks if two states are equal.
 * This method is used when we check for repeated states in the history,
 * and see if we have reached a goal state.
 * 
 * As our problem states are represented by Java (or Python) objects,
 * they may be 2 different {@link State} objects but with the same attribute values.
 * 
 * Two states are consider "equal" if they have the same attribute values.
 * e.g. the case of 2 twins.
 * 
 * If you get this wrong for your {@link State} class, the search may fall into an infinite loop (as it cannot see a repeated state),
 * or fail to recognise a goal when it reaches one.
 * 
 * @param state The other state to compare with.
 * @return true if the current state and the other are the same (value-wise). false otherwise.
 */
public boolean equals(Object state);

/**
 * Calculate the hash code of a state.
 * 
 * This method is needed because internally we are using a {@link HashSet} to store the history (of states),
 * and the {@link java.util.Set#contains(Object) Set.contains(Object)} method of the
 * {@link java.util.HashSet} to check if a state is in the history.
 * 
 * For this to work correctly in Java, the object (i.e. State) to be stored into the
 * {@link java.util.HashSet} must have a <code>int hashCode()</code> method defined.
 * 
 * The key requirement is for objects which are equal (e.g. all attribute values are the same) to have the same hash code.
 * The hash code is usually calculated based on the attribute values of the object.
 * @return The hash code of the State object.
 */
public int hashCode();

/**
 * This is the successor function which generates all action-states of a state when it is expanded.
 * This method must be tailored for each domain.
 * 
 * The returned result is a {@link java.util.List} of {@link ActionStatePair} objects.
 * It is a list, not a set as we want to keep the order of the children nodes.
 * If use a {@link java.util.Set}, the order is not kept/guaranteed.
 * @return All possible action-state pairs as a {@link java.util.List} of {@link ActionStatePair} objects.
 */
public List<ActionStatePair> successor();
}
