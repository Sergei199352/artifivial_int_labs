package aips.search.adversarial;

import java.util.*;

import aips.search.ActionStatePair;

/**
 * This interface defines the methods required on a problem state.
 * Any class that represents a problem state in an adversarial search must implement this interface.
 *
 * @author K. Hui
 */
public interface State extends aips.search.State
{
/**
 * This is the successor function which generates all action-states of a state when it is expanded.
 * This method must be tailored for each domain.
 * The returned result is a {@link java.util.List} of {@link ActionStatePair} objects.
 * It is a list, not a set as we want to keep the order of the children nodes.
 * If use a {@link java.util.Set}, the order is not kept/guaranteed.
 * @param role The role of the player.
 * @return All possible action-state pairs as a {@link java.util.List} of {@link ActionStatePair} objects.
 */
public List<ActionStatePair> successor(PlayerRole role);
} //end interface
