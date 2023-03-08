package aips.lab05.antSolution;

import aips.search.Node;
import aips.search.State;
import aips.search.informed.BestFirstSearchProblem;

public class AntRoutingGreedyManhattan extends AntRoutingAStarManhattan
{
public AntRoutingGreedyManhattan(State start, State goal, AntWorld world) 
{
super(start, goal, world);
} //end method

@Override
public double evaluation(Node node)
{
return this.heuristic(node.state);
} //end method
} //end class
