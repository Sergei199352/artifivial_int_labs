import aips.lab03.sq8 as sq8


"""Test successor function of a state.
"""
def run():
    num1=[[8,7,6],[5,4,3],[2,1,0]]
    s=sq8.Sq8State(num1)

    print("State:\n{}".format(s))
    print("All action-state:\n")
    for actionState in s.successor():
        print("{}{}".format(actionState.action,actionState.state))

    num2=[[8,7,6],[5,0,4],[3,2,1]]
    s=sq8.Sq8State(num2)

    print("State:\n{}".format(s))
    print("All action-state:\n")
    for actionState in s.successor():
        print("{}{}".format(actionState.action,actionState.state))

if __name__=="__main__":
    run()
