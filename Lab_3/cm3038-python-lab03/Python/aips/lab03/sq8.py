import sys
sys.path.append("C:/Users/sergey stanislavchuk/artifivial_int_labs/Lab_3/cm3038-python-lab03/Python")

import aips.search as search

##blah 
"""Model an action in the 8-puzzle.
This is a subclass of Action.
An action cost of 1.0 is inherited.
"""
class Sq8Action(search.Action):
    """Create an Sq8Action object.
    :param tile: A tile 1-9.
    :type tile: An int.
    :param fromRow: The row from where the tile is moved.
    :type fromRow: An int 0-2.
    :param fromCol: The column from where the tile is moved.
    :type fromCol: An int 0-2.
    :param toRow: The row to where the tile is moved.
    :type toRow: An int 0-2.
    :param toCol: The column to where the tile is moved.
    :type toCol: An int 0-2.
    """
    def __init__(self,tile,fromRow,fromCol,toRow,toCol):
        super().__init__()
        self.tile=tile
        self.fromRow=fromRow
        self.fromCol=fromCol
        self.toRow=toRow
        self.toCol=toCol

    """Return the action as str for printing.
    """
    def __str__(self):
        return "slide tile {} from ({},{}) to ({},{})\n". \
                format(self.tile,self.fromCol,self.fromRow,self.toCol,self.toRow)

"""Model a state in the 8-puzzle.
This is a subclass of State.
"""
class Sq8State(search.State):
    """Create a Sq8State.
    :param initialPos: Position of the tiles as a 2D int array. If this is None, it will fill the board with 0. (i.e. spaces)
    :type initialPos: None for an empty board, or a 2D int array.
    """
    def __init__(self,initialPos=None):
        super().__init__()
        if initialPos==None:        #if None is given, fill the array with 0 for spaces
            self.tiles=[[0]*3]*3
            print(self.tiles)
        else:
            self.tiles=[]
            #
            # Complete the constructor here.
            # *** You must make a copy of initialPos and put it into the tiles attribute.
            # *** Do not make a simple assignment of initialPos into tiles.
            # *** If you do so, any update to the parameter array will affect your state too.    
            # *** Comment out the print statements when you are done.
            #
            print("*** Complete the 2nd constructor of the Sq8State class.")


    """Return the state as a str for printing.
    """        
    def __str__(self):
        result=""
        #
        # *** Complete this method to return the current state as a String.
        # *** No printing here. Just compose and return the String.
        # ***
        # *** We want the board state to be printed as a 3*3 grid.
        # ***
        # *** Hint: Scan through the 2D array. Append each element to the result string.
        # ***         Add newlines when needed.
        #
        print("*** Complete __str__() function in Sq8State");
        return result

    """Compare if the current state equals to another object.
    :param other: The other object to compare with.
    :rtype: True or False.
    """
    def __eq__(self,other):
        #
        # *** In Python, "self" refers to the current Sq8State object.
        # *** You must test if it is equal to the "obj" one given in the input parameter.
        #
        # *** The first thing to do is to check if obj is a Sq8State object.
        # *** If it is not, then there is no need for further test. It must be false.
        #
        # *** If it is a Sq8State object, you may want to cast it into a Sq8State (for easier manipulation)
        # *** before testing all elements in the 2 tiles arrays.
        # *** Only if everything matches then you return a true.
        # *** If there is a mismatch, simply return a false.
        #
        if not isinstance(other,Sq8State):  #if the other object is not a Sq8State
            return False                    #false
        
        print("*** Complete __eq__(...) function in Sq8State");
        return True #*** This is a dummy return state to make the compiler happy.
                    #*** Remove this when you have filled in the correct code.

    """"The hashCode() method is needed as we use a HashSet to store the history of visited nodes.
    It returns an int value for a Sq8State object.
    The only requirement is that if 2 objects are equal, their hash code should be the same.
    This method has been implemented for you.
    In this implementation, I use the simple formula of:
    
    square1+square2*2+square3*3+...+square9*9

    :rtype: An int.
    """
    def __hash__(self):
        result=0
        for row in self.tiles:
            for cell in row:
                result=result*10+cell
        return result

    """Apply an action to the current state.
    :param action: The action to apply to the current state.
    :type action: A Sq8Action.
    :returns: The next state after the action is applied.
    :rtype: A Sq8State.
    """    
    def applyAction(self,action):
        nextState=Sq8State(self.tiles)  #create next state as copy of current state
        #
        # *** Update the tiles array in nextState according to the action.
        # *** Basically you are swapping the tile and blank in the tiles array of "nextState".
        # *** You can find the tile number/value from action.tileToSlide.
        # *** The tile row and column (in the array) are in action.tileRow and action.tileCol.
        # *** And the blank space row and column are in action.blankRow and action.blankCol.
        #
        print("*** Complete applyAction(...) function in Sq8State");
        return nextState
    
    """Find all valid action and next state pairs from he current state.
    :returns: All children as a list of ActionStatePair.
    :rtype: A list of ActionStatePair.
    """
    def successor(self):
        result=list()       #result starts as an empty list
        
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
            
        #
        #*** There are 3 more possible actions: slide the tile to the right, above or below the blank.
        #*** Each of these actions have to be tested as it may not be possible (depending on where the blank is).
        #***
        #*** Note that when you slide the tile on the right/left of the blank, the tile column is blank column +/- 1.
        #*** If you slide the tile is below/above the blank, the tile row should be blank row +/- 1.
        #***
        #*** Pay special attention to the boundary. When the blank is next to a boundary, some moves are not possible.
        #
        print("*** Complete successor(...) function in Sq8State");
        return result   #return list of successors

"""Model the 8-puzzle as a BFS problem.
"""
class Sq8Problem(search.SearchProblem):
    """Create a Sq8Problem.
    :param start: The initial state.
    :type start: A Sq8State.
    :param goal: The goal state.
    :type goal: A Sq8State.
    """
    def __init__(self,start,goal):
        super().__init__(start)
        self.goalState=goal

    """Check if a state is a goal state.
    :param state: The state to test.
    :type state: A Sq8State.
    :returns: True if the given state is a goal. False otherwise.
    :rtype: A boolean True/False.
    """
    def isGoal(self,state):
        #
        # *** Check if the given state is a goal state.
        # *** Hint: Check to see if the given state is a goal.
        # *** The goal state is in the "goalState" attribute of the current Sq8Problem object.
        #
        print("*** Complete isGoal(...) function in Sq8Problem class.")
        return False    #***This is a dummy return statement to make the compile happy.
                        #***Remove this when you have filled in the correct code.
  
"""The main function.
"""    
def run():
    
#    initial=Sq8State([[8,7,6],[5,4,3],[2,1,0]])    #this one takes a long time to solve in Python
    initial=Sq8State([[6,3,1],[7,4,2],[8,5,0]])     #an easier problem
    goal=Sq8State([[0,1,2],[3,4,5],[6,7,8]])
    
    problem=Sq8Problem(initial,goal)
    
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
