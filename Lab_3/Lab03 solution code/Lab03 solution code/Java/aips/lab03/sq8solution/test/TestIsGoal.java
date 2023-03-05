package aips.lab03.sq8solution.test;

import aips.lab03.sq8solution.*;
import aips.search.*;

public class TestIsGoal
{
public static void main(String[] arg)
{
int[][] array1={{8,7,6},{5,4,3},{2,1,0}};	//initial state as an array
int[][] array2={{0,1,2},{3,4,5},{6,7,8}};	//goal state as an array
int[][] array3={{0,1,2},{3,4,5},{6,7,8}};	//goal state as another array

Sq8State initialState,goalState,anotherState;	//the states

initialState=new Sq8State(array1);
goalState=new Sq8State(array2);
anotherState=new Sq8State(array3);

System.out.println("Goal state:\n"+goalState.toString());

SearchProblem problem=new Sq8Problem(initialState,goalState);	//create problem instance

System.out.println(initialState.toString());
System.out.println(problem.isGoal(initialState) ? "is a goal state\n" : "is not a goal state\n");

System.out.println(anotherState.toString());
System.out.println(problem.isGoal(anotherState) ? "is a goal state\n" : "is not a goal state\n");

System.out.println(anotherState.toString());
System.out.println(problem.isGoal(anotherState) ? "is a goal state\n" : "is not a goal state\n");
} //end main
} //end class
