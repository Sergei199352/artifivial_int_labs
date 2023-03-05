import aips.lab03.sq8 as sq8

"""Test to see if equality works in 2 different objects with
the same attribute values.
"""
def run():
    num1=[[0,1,2],[3,4,5],[6,7,8]]
    num2=[[0,1,2],[3,4,5],[6,7,8]]
    num3=[[8,7,6],[5,4,3],[2,1,0]]
    s1=sq8.Sq8State(num1)
    s2=sq8.Sq8State(num2)
    s3=sq8.Sq8State(num3)
    
    print("State 1:\n{}".format(s1))
    print("State 2:\n{}".format(s2))
    print("State 3:\n{}".format(s3))

    print("State 1 equal state 2: {}".format(s1==s2))   #should be True
    print("State 2 equal state 3: {}".format(s2==s3))   #shoudl be False

if __name__=="__main__":
    run()
    