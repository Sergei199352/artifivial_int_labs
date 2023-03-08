package aips.lab05.ant;

import aips.search.*;

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
