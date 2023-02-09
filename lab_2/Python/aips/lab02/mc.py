import search as search

"""Model an action in the MC problem.
This class extends the given aips.search.Action class.
"""
class McAction(search.Action):
    """Create a McAction object.
    :param m: Number of missionaries to move.
    :type m: An int value.
    :param c: Number of cannibals to move.
    :type m: An int value.
    :param to: Moving to which bank. Either "north" or "south".
    :type to: A str.
    """
    def __init__(self,m,c,to):
        super().__init__()
        self.missionaries=m
        self.cannibals=c
        self.toBank=to

    """Return McAction object as a printable str.
    :returns: The action as a str.
    :rtype: A str.
    """    
    def __str__(self):
        return "move {} missionaries & {} cannibals to {}".format(self.missionaries,self.cannibals,self.toBank)

"""Model the state of a MC puzzle.
This class extends the aips.searc.State class.
"""
class McState(search.State):
    """Create a MCState object.
    :param nm: No. of missionaries in the north.
    :param nc: No. of cannibals in the north.
    :param sm: No. of missionaries in the south.
    :param sc: No. of cannibales in the south.
    :param raft: Position of the raft. Either "north" or "south".
    """
    def __init__(self,nm,nc,sm,sc,raft):
        self.northMissionaries=nm
        self.northCannibals=nc
        self.southMissionaries=sm
        self.southCannibals=sc
        self.raftLocation=raft
    
    """Return state as a str.
    """
    def __str__(self):
        result="North: {}M {}C".format(self.northMissionaries,self.northCannibals)
        if self.raftLocation=="north":
            result+=" Raft"
        result+="\n~~~~~~~~~~~~~\n"
        result+="South: {}M {}C".format(self.southMissionaries,self.southCannibals)
        if self.raftLocation=="south":
            result+=" Raft"
        result+="\n"
        return result

    """Check equality of current McState object with another object.
    :param other: Another object to compare with.
    :returns: True if the 2 objects are equal in attribute values. False otherwise.
    """
    def __eq__(self,other):
        if not isinstance(other,McState):
            return False
        otherMcState=other #cast into McState
        return self.northMissionaries==otherMcState.northMissionaries and \
                self.northCannibals==otherMcState.northCannibals and \
                self.southMissionaries==otherMcState.southMissionaries and \
                self.southCannibals==otherMcState.southCannibals and \
                self.raftLocation==otherMcState.raftLocation
                
    """Compute a hash value from them McState object.
    :returns: A hash value.
    :rtype: int
    """
    def __hash__(self):
        return self.northMissionaries*1000+ \
                self.northCannibals*100+ \
                self.southMissionaries*10 + \
                self.southCannibals

    """Apply an action to the current McState.
    :param action: The action to be applied to the current state.
    :type action: A McAction object.
    :returns: The new state after applying the action.
    :rtype: A McState object.
    """
    def applyAction(self,action):
        if action.toBank=="north":
            newNM=self.northMissionaries+action.missionaries
            newNC=self.northCannibals+action.cannibals
            newSM=self.southMissionaries-action.missionaries
            newSC=self.southCannibals-action.cannibals
            newRaft="north"
        else:
            newNM=self.northMissionaries-action.missionaries
            newNC=self.northCannibals-action.cannibals
            newSM=self.southMissionaries+action.missionaries
            newSC=self.southCannibals+action.cannibals
            newRaft="south"
        return McState(newNM,newNC,newSM,newSC,newRaft)

    """Find all valid action-state pairs from the current state.
    :returns: All valid actions and new states.
    :rtype: A list of ActionStatePair objects.
    """
    def successor(self):
        result=[]
        if self.isInvalid():
            return result
        for m in range(McProblem.RAFT_SIZE+1,-1,-1):
            for c in range(McProblem.RAFT_SIZE+1,-1,-1):
                if m+c>0 and m+c<=McProblem.RAFT_SIZE:
                    action=McAction(m,c,self.oppositeBank(self.raftLocation))
                    nextState=self.applyAction(action)
                    if not nextState.isInvalid():
                        result.append(search.ActionStatePair(action,nextState))
        return result
    
    """A handy function to find the opposite bank.
    :param current: The current river bank, either "north" or "south".
    :type current: A str.
    """
    def oppositeBank(self,current):
        if current=="north":
            return "south"
        return "north"

    """Check to see if the state is invalid. i.e. Missionaries get eaten.
    :returns: True or False.
    """
    def isInvalid(self):
        #any negative value is invalid
        if self.northMissionaries<0 or self.northCannibals<0 or self.southMissionaries<0 or self.southCannibals<0:
            return True
        #more cannibals than missionaries in the north
        if self.northMissionaries>0 and self.northCannibals>self.northMissionaries:
            return True
        #more cannibals than missionaries in the south
        if self.southMissionaries>0 and self.southCannibals>self.southMissionaries:
            return True

"""Model the M&C problem as a breath-first search problem.
"""
class McProblem(search.SearchProblem):
    RAFT_SIZE=2
    
    def __init__(self,initialState,goalState):
        super().__init__(initialState)
        self.goalState=goalState       #attribute storing goal state
        
    """Override the method inherited to check if a given state is goal.
    :param state: The state to be checked.
    :returns: True if state is a goal, False otherwise.
    """    
    def isGoal(self,state):
        return state==self.goalState    #state attributes equal to goal state attributes
    

    def addChild(self,fringe,childNode):
        fringe.insert(0, childNode)
         

"""Main function.
"""
def run():
    initialState=McState(0,0,3,3,"south")   #initial state
    goalState=McState(3,3,0,0,"north")      #goal state
    
    mcProblem=McProblem(initialState,goalState)  #create a M&C BFS problem
    
    #use the following line for DFS
    #mcProblem=McProblemDFS(initialState,goalState)
    print("Searching...")
    path=mcProblem.search()     #perform search
    if path==None:              #no solution found
        print("No solution")
    else:
        print(path)             #print solution found
        print("Nodes visited: {}".format(mcProblem.nodeVisited))
        print("Cost: {}".format(path.cost))

if (__name__=="__main__"):
    run()

    