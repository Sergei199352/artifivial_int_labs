import aips.lab03.sq8 as sq8


"""Test to make sure that a state does not change even
if the 2D int array to create it is changed.
"""
from cm3038.search import Action
def run():
    num=[[8,7,6],[5,4,3],[2,1,0]]
    before=sq8.Sq8State(num)
    action=sq8.Sq8Action(3,1,2,2,2)
    after=before.applyAction(action)
    
    print("Before:\n{}".format(before))
    print("Action:\n{}".format(action))
    print("After:\n{}".format(after))

if __name__=="__main__":
    run()
