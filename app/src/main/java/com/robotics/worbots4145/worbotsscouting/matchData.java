package com.robotics.worbots4145.worbotsscouting;

import java.util.ArrayList;

public class matchData {
    public boolean hadBreakdown = false;
    public boolean hadPenalty = false;
    public boolean check1 = false;
    public boolean check2 = false;
    public boolean check3 = false;
    public boolean check4 = false;
    public boolean check5 = false;
    public boolean check6 = false;
    public boolean check7 = false;
    public boolean check8 = false;
    public boolean check9 = false;
    public boolean lineCross = false;
    public double averageCycleTime = 0.0;
    public double timedClimb = 0.0;
    public String startPosition = "";
    public String round = "";
    public String notes = "";
    public int numCellPickUp = 0;
    public int numCellDropped = 0;
    public int numSpins = 0;
    public int numTimesShot = 0;
    public int numClimbs = 0;
    public String botNumber = "";
    public ArrayList<String> moves = new ArrayList<String>();
    public matchData(){}
    public matchData(String roundNumber){ round = roundNumber; }
    public void change1(){
        if(check1)
            check1 = false;
        else
            check1 = true;
    }
    public void change2(){
        if(check2)
            check2 = false;
        else
            check2 = true;
    }
    public void change3(){
        if(check3)
            check3 = false;
        else
            check3 = true;
    }
    public void change4(){
        if(check4)
            check4 = false;
        else
            check4 = true;
    }
    public void change5(){
        if(check5)
            check5 = false;
        else
            check5 = true;
    }
    public void change6(){
        if(check6)
            check6 = false;
        else
            check6 = true;
    }
    public void change7(){
        if(check7)
            check7 = false;
        else
            check7 = true;
    }
    public void change8(){
        if(check8)
            check8 = false;
        else
            check8 = true;
    }
    public void change9(){
        if(check9)
            check9 = false;
        else
            check9 = true;
    }
    public void setNotes(String tempNotes){notes = tempNotes;}
    public void addMoves(String move) { moves.add(move); }
    public int getSize() { return moves.size(); }
    public void setMoves(int index, String newNum){ moves.set(index, newNum); }
    public void setStart(String start) { startPosition = start; }
    public ArrayList<String> getMoves() { return moves; }
}
