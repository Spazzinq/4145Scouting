package com.robotics.worbots4145.worbotsscouting;

public class SummativeRobotData {
    public String robotNumber = "0";
    public int numLineCross = 0;
    public double cycleTimeAverage = 0;
    public double climbTimeAverage = 0;
    public double DropCellAverage = 0;
    public double PickUpCellAverage = 0;
    public double numSpinsAverage = 0;
    public double numTimesShotAverage = 0;
    public double numTimesShotAutonomousAverage = 0;
    public int numClimbedLowLevel = 0;
    public int numClimbedMidLevel = 0;
    public int numClimbedHighLevel = 0;
    public SummativeRobotData(){robotNumber = "";}
    public SummativeRobotData(String num){robotNumber = num;}
}
