#aips informed search library
#By K. Hui

import aips.search as search
import math

"""This class models a best-first search problem.
To create your own specialised problem of informed search, extend this class.
You still need to supply the evaluation function which is necessary for best-first search.
The heuristic function is only needed if your evaluation function uses one.
"""
class BestFirstSearchProblem(search.SearchProblem):
    goalState=None                  #most best-first search need a goal to compute f(n)

    """Constructor of BestFirstSearchProblem.
    We assume there is an initial and goal states.
    :param start: The initial state.
    :type other: A State object.
    """
    def __init__(self,start,goal):
        super().__init__(start)
        self.goalState=goal

    """Perform best-first search on the problem.
    :returns: The solution as a Path. Or None if no solution is found.
    :rtype: A Path.
    """
    def search(self):
        visitedNodes={} #create empty history dictionary
        fringe=[]       #empty fringe list
        rootNode=search.Node(self.startState,None,None)    #create root node
        fringe.append(rootNode)                     #add root node into fringe
        
        visitedNodes[rootNode.state]=rootNode       #put state-node pair into visited node map
        self.nodeVisited+=1                         #increment visited node count

        if self.nodeVisited%1000==0:
            print("No. of nodes explored: {}\n".format(self.nodeVisited))   #print message every 1000 nodes

        while True:
            if fringe==[]:  #fringe is empty
                return None #no solution
            
            node=fringe.pop(0)                      #remove 1st node from fringe list
            if self.isGoal(node.state):             #goal state found
                return self.constructPath(node)     #construct path and return

            successors=node.state.successor()  #get all successors
            for child in successors:
                self.nodeVisited+=1
                if self.nodeVisited%1000==0:
                    print("No. of nodes explored: {}\n".format(self.nodeVisited))   #print message every 1000 nodes
                action=child.action     #get action from action-state pair
                nextState=child.state   #get next state from action-state pair
                lastSeenNode=visitedNodes.get(nextState)    #look up next state from history map
                if lastSeenNode==None:  #have not seen this state before
                    childNode=search.Node(nextState,node,action)   #create child node from state
                    self.addChild(fringe,childNode)                 #add child into fringe
                    visitedNodes[nextState]=childNode               #add next state and childnode pair into history map
                else:
                    if lastSeenNode.getCost()>action.cost+node.getCost():    #this new path is cheaper
                        lastSeenNode.parent=node    #go through the current node to reach this next state
                        lastSeenNode.action=action  #update action too

    """Add a new node into the fringe using linear search based on f(n) value.
    This method is NOT USED anymore as it is slower than binary insertion.
    :param fringe: The list of nodes to be explored.
    :type fringe: A list of Node objects.
    :param childNode: The node to be inserted into the fringe.
    :type childNode: A Node object.
    """
    def addChildLinear(self,fringe,childNode):
        for i in range(0,len(fringe)):              #scan fringe list
            if self.evaluation(childNode)<self.evaluation(fringe[i]): #find position where node is just bigger than child in evaluation function value
                fringe.insert(i,childNode)      #add child just before that node
                return                          #exit, no need to continue
        fringe.append(childNode)    #if you hit the end of list, add child to the end
    
    """Add a new node into the fringe using binary insertion based on f(n) value.
    :param fringe: The list of nodes to be explored.
    :type fringe: A list of Node objects.
    :param childNode: The node to be inserted into the fringe.
    :type childNode: A Node object.
    """
    def addChild(self,fringe,childNode):
        self.binaryInsert(fringe,childNode,0,len(fringe)-1) #insert node into fringe using binary insertion
    
    #add a new node into a section of the fringe
    #
    """Add a new node into a section of the fringe within 2 indices.
    This gives a O(log(n)) complexity over the O(n) in linear search.
    :param fringe: The list of nodes to be explored.
    :type fringe: A list of Node objects.
    :param childNode: The node to be inserted into the fringe.
    :type childNode: A Node object.
    :param left: The starting index of the list to consider.
    :type left: An int value in 0..length-1.
    :param right: The ending index of the list to consider.
    :type right: An int value in 0..length-1.
    """
    def binaryInsert(self,fringe,node,left=None,right=None):
        if left==None:
            left=0
        if right==None:
            right=len(fringe)-1
    
        while True:        
            if left>right:
                fringe.insert(left,node)
                return

            nodeValue=self.evaluation(node)                     #f(n) value of new node   
            if left==right:                                     #left meets right
                leftValue=self.evaluation(fringe[left])         #f(n) of node at position left
                if leftValue>nodeValue:         #new node goes before left
                    fringe.insert(left,node)
                    return
                elif leftValue==nodeValue:      #new node goes before left
                    if fringe[left].getCost()>node.getCost():   #g(n) of new node is lower, thus less certain
                        fringe.insert(left+1,node)              #new node goes after old node
                        return
                    fringe.insert(left,node)                    #otherwise new node goes before old node
                    return
                else:
                    fringe.insert(left+1,node)   #value goes after left
                    return
            #has at least 2 elements in range
            else:    
                mid=math.floor((left+right)/2)          #find middle position
                midValue=self.evaluation(fringe[mid])   #find f(n) value of node at position mid
                if midValue==nodeValue:                 #the same f(n) value
                    if fringe[mid].getCost()>node.getCost():    #g(n) of new node is lower, thus less certain
                        fringe.insert(mid+1,node)               #new node goes after old node
                        return
                    fringe.insert(mid,node)             #otherwise new node goes before mid
                    return
                if midValue>nodeValue:                  #new node go into the segment before mid
                    right=mid-1
                else:
                    left=mid+1

    """The evaluation function to be defined in a best-first search problem.
    This should be provided in a subclass.
    :param node: The Node to be evaluated. In most cases you will need the state inside the node.
    :type node: A Node object.
    :returns: The f(n) value of the node.
    :rtype: A double/floating point number.
    """

    #evaluation to be defined in the concrete search problem    
    def evaluation(self,node):
        pass
