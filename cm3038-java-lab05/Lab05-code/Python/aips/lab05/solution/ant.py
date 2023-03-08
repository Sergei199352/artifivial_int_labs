import aips.search as search
import aips.informed.search as search_informed
import math

"""Model a 2D world of the ant as an array of int.
"""
class AntWorld:
    """Construct a AntWorld giving a grid.
    :param grid: A 2D array representing the world.
    :type grid:
    """
    def __init__(self,grid):
        self.grid=[]
        for worldRow in grid:
            self.grid.append(worldRow.copy())
    
    """Return the AntWorld as a printable str.
    """
    def __str__(self):
        result=""
        for worldRow in self.grid:
            for cell in worldRow:
                if cell==1:
                    result+="X"
                else:
                    result+="."
            result+="\n"
        return result


"""Model act of an ant.
"""
class AntAction(search.Action):
    """Construct an action.
    :param fromX: The current X (or column) of the ant.
    :param fromY: The current Y (or row) of the ant.
    :param direction: The moving direction.
    :type direction: A str of "N", "S", "E", or "W".
    """
    def __init__(self,fromX,fromY,direction):
        super().__init__()
        self.fromX=fromX
        self.fromY=fromY
        self.direction=direction
    
    """Return the action as a str for printing if needed.
    """
    def __str__(self):
        switcher={"N": lambda x,y: (x,y-1),
                  "S": lambda x,y: (x,y+1),
                  "W": lambda x,y: (x-1,y),
                  "E": lambda x,y: (x+1,y)
                  }
#        function=switcher.get(self.direction)
#        (toX,toY)=function(self.fromX,self.fromY)
        (toX,toY)=switcher.get(self.direction)(self.fromX,self.fromY)
        return "move from ({},{}) to ({},{})\n".format(self.fromX,self.fromY,toX,toY)

"""Model the state of an ant.
"""
class AntState(search.State):
    """Construct a state.
    :param x: The ant's X coordinate (or column).
    :param y: The ant's Y coordinate (or row).
    :param world: A 2D array representing the world. See the XXX class.
    """
    def __init__(self,x,y,world):
        self.x=x
        self.y=y
        self.world=world
    
    """Compare equality of 2 states.
    :rtype: A boolean value.
    """
    def __eq__(self,other):
        if not isinstance(other,AntState):
            return False
        return self.x==other.x and self.y==other.y

    """Compute a hash code of the state.
    """
    def __hash__(self):
        return self.x+self.y*100

    """Apply an action to the current state.
    :param action: The action to apply to the current state.
    :type action: An AntAction.
    :returns: A new state after the action is applied.
    :rtype: An AntState.
    """
    def applyAction(self,action):
        switcher={"N": lambda x,y: (x,y-1),
                  "S": lambda x,y: (x,y+1),
                  "W": lambda x,y: (x-1,y),
                  "E": lambda x,y: (x+1,y)
                  }
        (newX,newY)=switcher.get(action.direction)(self.x,self.y)
        return AntState(newX,newY,self.world)

    """Find all successors of the current state.
    :returns: All valid actions and next states as a list of ActionStatePair.
    :rtype: A list of ActionStatePair.
    """            
    def successor(self):
        result=[]
        if self.y>0:
            if self.world.grid[self.y-1][self.x]==0:
                action=AntAction(self.x,self.y,"N")
                nextState=self.applyAction(action)
                actionStatePair=search.ActionStatePair(action,nextState)
                result.append(actionStatePair)

        if self.y<len(self.world.grid)-1:
            if self.world.grid[self.y+1][self.x]==0:
                action=AntAction(self.x,self.y,"S")
                nextState=self.applyAction(action)
                actionStatePair=search.ActionStatePair(action,nextState)
                result.append(actionStatePair)
                
        if self.x>0:
            if self.world.grid[self.y][self.x-1]==0:
                action=AntAction(self.x,self.y,"W")
                nextState=self.applyAction(action)
                actionStatePair=search.ActionStatePair(action,nextState)
                result.append(actionStatePair)
                
        if self.x<len(self.world.grid[self.y])-1:
            if self.world.grid[self.y][self.x+1]==0:
                action=AntAction(self.x,self.y,"E")
                nextState=self.applyAction(action)
                actionStatePair=search.ActionStatePair(action,nextState)
                result.append(actionStatePair)

        return result


    """Return the state as a str.
    """
    def __str__(self):
        result=""
        for y in range(0,len(self.world.grid)):
            for x in range(0,len(self.world.grid[y])):
                if self.x==x and self.y==y:
                    result+="O"
                elif self.world.grid[y][x]==1:
                    result+="X"
                else:
                    result+="."
            result+="\n"
        return result


"""Model ant routing as a BFS problem.
"""
class AntRoutingBFS(search.SearchProblem):
    def __init__(self,start,goal,world):
        super().__init__(start)
        self.goalState=goal
        self.world=world
        
    def isGoal(self,state):
        return self.goalState==state

"""Model ant routing as a DFS problem.
This class is similar to AntRoutingBFS except for overriding the addChild(...) method which
adds a new child to the beginning of the fringe.
"""
class AntRoutingDFS(search.SearchProblem):
    def __init__(self,start,goal,world):
        super().__init__(start)
        self.goalState=goal
        self.world=world
        
    def isGoal(self,state):
        return self.goalState==state
        
    def addChild(self,fringe,child):
        fringe.insert(0,child)


"""Model the search problem using diagonal distance heuristic.
"""
class AntRoutingAStarDiagonal(search_informed.BestFirstSearchProblem):
    def __init__(self,start,goal,world):
        super().__init__(start,goal)
        self.world=world
        
    def isGoal(self,state):
        return self.goalState==state
        
    """The evaluation function.
    :param node: The node.
    :type node: A Node object.
    :returns: f(n) value of the node.
    """
    def evaluation(self, node):
        return node.getCost()+self.heuristic(node.state)
    
    """The heuristic function.
    :param state: The state.
    :type state: An AntState object.
    :returns: The h(n) value of the state.
    """
    def heuristic(self,state):
        #diagonal distance heuristic
        return max(abs(state.x-self.goalState.x),abs(state.y-self.goalState.y))


"""Model the search problem using straight line distance heuristic.
It is the same as AntRoutingAStarDiagonal except the different heuristic.
"""
class AntRoutingAStarStraightLine(AntRoutingAStarDiagonal):
    def __init__(self,start,goal,world):
        super().__init__(start,goal,world)
        self.world=world
        
    """The heuristic function.
    :param state: The state.
    :type state: An AntState object.
    :returns: The h(n) value of the state.
    """
    def heuristic(self,state):
        #straightline distance heuristic
        xDiff=state.x-self.goalState.x
        yDiff=state.y-self.goalState.y
        return math.sqrt(xDiff*xDiff+yDiff*yDiff)

"""Model the search problem using Manhattan distance heuristic.
It is the same as AntRoutingAStarDiagonal except the different heuristic.
"""
class AntRoutingAStarManhattan(AntRoutingAStarDiagonal):
    def __init__(self,start,goal,world):
        super().__init__(start,goal,world)
        self.world=world
        
    """The heuristic function.
    :param state: The state.
    :type state: An AntState object.
    :returns: The h(n) value of the state.
    """
    def heuristic(self,state):
        #Manhattan distance heuristic
        return abs(state.x-self.goalState.x)+abs(state.y-self.goalState.y)

"""Model the search problem using greedy best-first, diagonal distance heuristic.
"""
class AntRoutingGreedyDiagonal(AntRoutingAStarDiagonal):
    def __init__(self,start,goal,world):
        super().__init__(start,goal,world)
        
    """The evaluation function.
    :param node: The node.
    :type node: A Node object.
    :returns: f(n) value of the node.
    """
    def evaluation(self, node):
        return self.heuristic(node.state)


"""Model the search problem using greedy best-first, straight line distance heuristic.
"""
class AntRoutingGreedyStraightLine(AntRoutingAStarStraightLine):
    def __init__(self,start,goal,world):
        super().__init__(start,goal,world)
        
    """The evaluation function.
    :param node: The node.
    :type node: A Node object.
    :returns: f(n) value of the node.
    """
    def evaluation(self, node):
        return self.heuristic(node.state)

"""Model the search problem using greedy best-first, Manhattan distance heuristic.
"""
class AntRoutingGreedyManhattan(AntRoutingAStarManhattan):
    def __init__(self,start,goal,world):
        super().__init__(start,goal,world)
        
    """The evaluation function.
    :param node: The node.
    :type node: A Node object.
    :returns: f(n) value of the node.
    """
    def evaluation(self, node):
        return self.heuristic(node.state)

    
"""The main function.
"""
def run():
    #The world map as a 2D int array of 0 and 1.
    #0 is empty space.
    #1 is barrier.
    worldMatrix=[   [0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\
                    [0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\
                    [0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\
                    [0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\
                    [0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0],\
                    [0,1,1,1,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0],\
                    [0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],\
                    [0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],\
                    [0,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],\
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,0,0],\
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],\
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],\
                    [0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0],\
                    [0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,1,1,1,1,1,1,1],\
                    [0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0],\
                    [0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0],\
                    [0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0],\
                    [0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0],\
                    [0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],\
                    [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0]\
                    ];
    
    world=AntWorld(worldMatrix)         #create the world object
    initialState=AntState(3,3,world)    #initial state
    goalState=AntState(39,19,world)     #goal state
   
#    problem=AntRoutingBFS(initialState,goalState,world)   #create search problem as BFS
#    problem=AntRoutingDFS(initialState,goalState,world)   #create search problem as DFS
#    problem=AntRoutingAStarDiagonal(initialState,goalState,world)   #create search problem as A* using Manhattan distance heuristic
#    problem=AntRoutingAStarStraightLine(initialState,goalState,world)   #create search problem as A* using Manhattan distance heuristic
#    problem=AntRoutingAStarManhattan(initialState,goalState,world)   #create search problem as A* using Manhattan distance heuristic
#    problem=AntRoutingGreedyDiagonal(initialState,goalState,world)   #create search problem as A* using Manhattan distance heuristic
#    problem=AntRoutingGreedyStraightLine(initialState,goalState,world)   #create search problem as A* using Manhattan distance heuristic
    problem=AntRoutingGreedyManhattan(initialState,goalState,world)   #create search problem as A* using Manhattan distance heuristic

    path=problem.search()       #search
    print("Done!\n")
    if path==None:
        print("No solution")    #no solution
    else:
        visualisePath(world,path)
        print("Nodes visited: {}\n".format(problem.nodeVisited))
        print("Cost: {}\n".format(path.cost))

"""
This methods prints the ant world with the path overlaid on top.
Note that we have to construct another 2D array to put every node visited by the path into it
before we can do any printing.
:param world: An AntWorld object.
:param path: The path found.
"""
def visualisePath(world,path):
    #first we copy the 2D boolean array of ant world into a new array of int
    #as we have more values to store other than a true/false
    grid=[]
    for row in world.grid:          #go through rows in ant world
        grid.append(row.copy())     #clone and copy into grid

    #        
    #Now we have to overlap the path onto the new int array.
    #   
    for actionState in path.list:       #loop through all action-state pairs in path
        state=actionState.state         #get the state
        grid[state.y][state.x]=-3       #put a -3 into the (x,y) position
    startState=path.head                #back to the head of the path
    endState=path.list[-1].state        #get the last action-state pair in path, then its state
    grid[startState.y][startState.x]=-1 #put a -1 into the starting position
    grid[endState.y][endState.x]=-2     #put a -2 into the ending position

    #
    #The final printing loop.
    #
    switcher={  0:".",  #0 means space
                1:"X",  #1 means barrier
                -1:"S", #-1 means starting position
                -2:"E", #-2 means ending position
                -3:"#", #-3 means path
                }
    for row in grid:
        for cell in row:
            print(switcher[cell],end="")
        print("")             #print a new line at the end of every row
        
if __name__=="__main__":
    run()
    