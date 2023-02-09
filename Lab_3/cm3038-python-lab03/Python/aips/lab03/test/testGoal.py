import aips.lab03.sq8 as sq8

"""Test to make sure that a the goal test is working correctly.
This makes useof the __eq__(...) method defined in the state.
"""
from cm3038.lab05.ant import initialState
def run():
    array1=[[8,7,6],[5,4,3],[2,1,0]]
    array2=[[0,1,2],[3,4,5],[6,7,8]]
    array3=[[0,1,2],[3,4,5],[6,7,8]]

    initialState=sq8.Sq8State(array1)
    goalState=sq8.Sq8State(array2)
    anotherState=sq8.Sq8State(array3)

    print("Goal state:\n{}".format(goalState))   #print goal state defined

    problem=sq8.Sq8Problem(initialState,goalState)  #create search problem

    print("{} is goal {}".format(initialState,problem.isGoal(initialState)))    #should say False
    print("{} is goal {}".format(goalState,problem.isGoal(goalState)))          #should say True
    print("{} is goal {}".format(anotherState,problem.isGoal(anotherState)))    #should say True

if __name__=="__main__":
    run()
