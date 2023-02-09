import aips.lab03.sq8 as sq8


"""Test to make sure that a state does not change even
if the 2D int array to create it is changed.
"""
def run():
    num=[[0,1,2],[3,4,5],[6,7,8]]   #an 2D int array
    s=sq8.Sq8State(num)             #create state from int array

    for row in range(3):    #change elements in 2D array
        for col in range(3):
            num[row][col]=0
    
    print("State:\n{}".format(s))   #print state

if __name__=="__main__":
    run()
