
import sys
sys.path.append("C:/Users/1908138.RGU/artifivial_int_labs/lab_4/Python")
import aips.search as search
import aips.informed.search as search_informed
import math

"""Modeling an action in the 8-puzzle.
"""
class Sq8Action(search.Action):
    def __init__(self,tile,fromRow,fromCol,toRow,toCol):
        super().__init__()
        self.tile=tile
        self.fromRow=fromRow
        self.fromCol=fromCol
        self.toRow=toRow
        self.toCol=toCol

    def __str__(self):
        return "slide tile {} from ({},{}) to ({},{})\n". \
                format(self.tile,self.fromCol,self.fromRow,self.toCol,self.toRow)

"""Modeling a state in the 8-puzzle.
"""
class Sq8State(search.State):
    def __init__(self,initialPos=None):
        super().__init__()
        if initialPos==None:
            self.tiles=[[0]*3]*3
            print(self.tiles)
        else:
            self.tiles=[]
            for row in initialPos:
                self.tiles.append(row.copy())
        
    def __str__(self):
        result=""
        for row in self.tiles:
            for cell in row:
                if cell==0:
                    result+="-"
                else:
                    result+=str(cell)
            result+="\n"
        return result

    def __eq__(self,other):
        if not isinstance(other,Sq8State):
            return False
        return self.tiles==other.tiles

    def __hash__(self):
        result=0
        for row in self.tiles:
            for cell in row:
                result=result*10+cell
        return result
    
    def applyAction(self,action):
        nextState=Sq8State(self.tiles)  #create next state as copy of current state
        nextState.tiles[action.toRow][action.toCol]=action.tile
        nextState.tiles[action.fromRow][action.fromCol]=0
        return nextState
    
    def successor(self):
        result=list()
        for blankRow in range(0,len(self.tiles)):       #loop through row index
            try:
                blankCol=self.tiles[blankRow].index(0)  #find 0 in this row
                break                                   #stop loop
            except ValueError:                          #if 0 is not found
                pass                                    #just continue to next row

        if blankCol>0:  #blank not on column 0, can move tile W of blank to E
            tileRow=blankRow        #tile on same row
            tileCol=blankCol-1      #tile column is to the W of blank
            tileToSlide=self.tiles[tileRow][tileCol]    #tile number
            action=Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol)
            nextState=self.applyAction(action)
            actionStatePair=search.ActionStatePair(action,nextState)
            result.append(actionStatePair)
            
        if blankCol<2:  #blank not on column 2, can move tile E of blank to W
            tileRow=blankRow        #tile on same row
            tileCol=blankCol+1      #tile column is to the E of blank
            tileToSlide=self.tiles[tileRow][tileCol]    #tile number
            action=Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol)
            nextState=self.applyAction(action)
            actionStatePair=search.ActionStatePair(action,nextState)
            result.append(actionStatePair)

        if blankRow>0:  #blank not on row 0, can move tile N of blank to S
            tileRow=blankRow-1    #tile row is N of blank
            tileCol=blankCol      #tile is on the same row of blank
            tileToSlide=self.tiles[tileRow][tileCol]    #tile number
            action=Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol)
            nextState=self.applyAction(action)
            actionStatePair=search.ActionStatePair(action,nextState)
            result.append(actionStatePair)
            
        if blankRow<2:  #blank not on row 2, can move tile S of blank to N
            tileRow=blankRow+1    #tile row is S of blank
            tileCol=blankCol      #tile is on the same row of blank
            tileToSlide=self.tiles[tileRow][tileCol]    #tile number
            action=Sq8Action(tileToSlide,tileRow,tileCol,blankRow,blankCol)
            nextState=self.applyAction(action)
            actionStatePair=search.ActionStatePair(action,nextState)
            result.append(actionStatePair)
        
        return result

"""Uninformed search using BFS.
Default behaviour of superclass SearchProblem is BFS.
"""
class Sq8ProblemBFS(search.SearchProblem):
    def __init__(self,start,goal):
        super().__init__(start)
        self.goalState=goal

    def isGoal(self,state):
        return state==self.goalState

"""Uninformed search using DFS.
Override addChild(...) function to add new child to head of fringe.
"""
class Sq8ProblemDFS(search.SearchProblem):
    def __init__(self,start,goal):
        super().__init__(start)
        self.goalState=goal

    def isGoal(self,state):
        return state==self.goalState

    def addChild(self, fringe, childNode):
        fringe.insert(0,childNode)

"""Informed search.
Note the use of a different superclass BestFirstSearchProblem.
You can make it Greedy Best-first or A* depending on the evaluation function.
"""
class Sq8Problem(search_informed.BestFirstSearchProblem):
    def __init__(self,start,goal):
        super().__init__(start,goal)
        
    def isGoal(self,state):
        return state==self.goalState
    
    """The evaluation function.
    At the moment we have f(n)=h(n), which is Greedy best-first.
    node.getCost() will give you g(n).
    """
    def evaluation(self,node):
        return self.heuristic(node.state)

    """The misplaced tile heuristic is implemented for you.
    """    
    def heuristic(self,state):
        return self.__misplacedTileCount__(state)
    
    #heuristic using misplaced tile count
    def __misplacedTileCount__(self,state):
        result=0.0
        for row in range(0,len(state.tiles)):
            for col in range(0,len(state.tiles[row])):
                if state.tiles[row][col]!=0:    #now a space
                    if state.tiles[row][col]!=self.goalState.tiles[row][col]:
                        result+=1.0
        return result

"""Main function.
"""    
def run():    
    initial=Sq8State([[0,1,2],[3,4,5],[7,8,6]])
    goal=Sq8State([[0,1,2],[3,4,5],[6,7,8]])

#    problem=Sq8ProblemBFS(initial,goal) #BFS
#    problem=Sq8ProblemDFS(initial,goal) #DFS
    problem=Sq8Problem(initial,goal) #default is GBF using misplaced tile heuristic
    
    print("Searching...\n")
    path=problem.search()
    if path==None:
        print("No solution\n")
    else:
        print(path)
        print("Nodes visited: {}\n".format(problem.nodeVisited))
        print("Cost: {}\n".format(path.cost))
        
if __name__=="__main__":
    run()
    