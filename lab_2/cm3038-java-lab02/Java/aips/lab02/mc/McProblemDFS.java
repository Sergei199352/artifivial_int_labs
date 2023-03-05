package aips.lab02.mc;

import java.util.List;

import aips.search.Node;
import aips.search.SearchProblem;
import aips.search.State;

public class McProblemDFS extends McProblem {

    public McProblemDFS(McState initialState, McState goalState) {
        super(initialState, goalState);
    }

    // Override the addChild method to add nodes to the front of the fringe (LIFO queue)
    @Override
    protected void addChild(List<Node> fringe, Node childNode) {
        fringe.add(0, childNode);
    }

    // Override the isGoal method to check if the current state is the goal state
    @Override
    public boolean isGoal(State state) {
        return state.equals(this.goalState);
    }
}