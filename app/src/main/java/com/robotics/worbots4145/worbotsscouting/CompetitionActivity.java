package com.robotics.worbots4145.worbotsscouting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.worbots.worbots4145.worbotsscouting.R;

public class CompetitionActivity extends AppCompatActivity {
    public int counter = 15;
    public Button b1;
    public Button notAccurate;
    public Button veryAccurate;
    public Button High;
    public Button Low;
    public Button failed;
    public Button success;
    public Button failSpin;
    public Button specificSpin;
    public Button LowLevel;
    public Button MidLevel;
    public Button HighLevel;
    public Button undo;
    public Button endMatch;
    public Button dropCell;
    public Button pickUpCell;
    public Button rGenerator;
    public Button bGenerator;
    public Button bSpinner;
    public Button rSpinner;
    public Button bLoadingZone;
    public Button rLoadingZone;
    public Button bPowerPort;
    public Button rPowerPort;
    public Button penalty;
    public Button breakdown;
    public Button linecross;
    public matchData roundData;
    public boolean blimpy = true;
    public CountDownTimer bob;
    public CountDownTimer billy;
    public TextView lastMove;
    public String temp = "";
    public String roundNum = "";
    public String botNum = "";
    public long startTime = 0;
    public long endTime = 0;
    public double seconds = 0;
    public int numCycles = 0;
    public int totalTime = 0;
    public double climbTime = 555;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        roundNum = getIntent().getStringExtra("RoundNumber");
        botNum = getIntent().getStringExtra("BotNum");
        TextView thingy = (TextView)findViewById(R.id.RoundNumber);
        thingy.setText("Round Number " + roundNum + "\nScouting: " + botNum);
        b1 = (Button) findViewById(R.id.Timer);
        FirebaseDatabase.getInstance().getReference().child("MatchData").child(roundNum).child(botNum).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                roundData = dataSnapshot.getValue(matchData.class);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                roundData = dataSnapshot.getValue(matchData.class);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        linecross = (Button) findViewById(R.id.linecross);
        linecross.setVisibility(View.VISIBLE);
        lastMove = (TextView) findViewById(R.id.LastMove);
        bob = new CountDownTimer(15000, 1000){
            public void onTick(long millisUntilFinished){
                b1.setText(String.valueOf(counter));
                counter--;
            }
            public void onFinish(){
                b1.setText("Switch To Teleop");
                b1.setBackgroundColor(Color.YELLOW);
            }
        }.start();

        billy = new CountDownTimer(25000, 1000){
            public void onTick(long millisUntilFinished){
            }
            public void onFinish(){
                b1.setText("Teleop");
                billy.cancel();
                bob.cancel();
                b1.setBackgroundColor(Color.WHITE);
                linecross.setVisibility(View.INVISIBLE);
            }
        }.start();

        //timer
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(blimpy) {
                    b1.setText("Teleop");
                    bob.cancel();
                }
            }
        });

        //Undo
        undo = (Button) findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                if(roundData.getSize() < 1)
                    Toast.makeText(CompetitionActivity.this, "You have not added any events!", Toast.LENGTH_SHORT).show();
                else
                {
                    roundData.getMoves().remove(roundData.getSize()-1);
                    Toast.makeText(CompetitionActivity.this, "Undone!", Toast.LENGTH_SHORT).show();
                    if(roundData.getSize() < 1)
                        lastMove.setText("");
                    else
                        lastMove.setText(roundData.getMoves().get(roundData.getSize()-1));
                }
            }
        });

        //Line Cross
        linecross.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp = "linecross";
                roundData.lineCross = true;
                linecross.setVisibility(View.INVISIBLE);
                lastMove.setText(temp);
                roundData.addMoves(temp);
                Toast.makeText(CompetitionActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        //Breakdown
        breakdown = (Button) findViewById(R.id.breakdown);
        breakdown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp = "breakdown";
                roundData.hadBreakdown = true;
                lastMove.setText(temp);
                roundData.addMoves(temp);
                Toast.makeText(CompetitionActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        //Penalty
        penalty = (Button) findViewById(R.id.penalty);
        penalty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp = "penalty";
                roundData.hadPenalty = true;
                lastMove.setText(temp);
                roundData.addMoves(temp);
                Toast.makeText(CompetitionActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        //End Match
        endMatch = (Button) findViewById(R.id.endMatch);
        endMatch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CompetitionActivity.this);
                alertDialog.setTitle("Confirm End Match");
                alertDialog.setMessage("Are you sure you want to end the match?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        if(numCycles != 0) {
                            roundData.averageCycleTime = (double) totalTime / numCycles;
                        }
                        else{
                            roundData.averageCycleTime = 555;
                        }
                        roundData.timedClimb = climbTime;
                        FirebaseDatabase.getInstance().getReference().child("MatchData").child(roundNum).child(botNum).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("MatchData").child(roundNum).child(botNum).push().setValue(roundData);
                        Intent intent = new Intent(CompetitionActivity.this, EndMatchActivity.class);
                        intent.putExtra("RoundNumber", roundNum);
                        intent.putExtra("BotNum", botNum);
                        startActivity(intent);
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

        //Picked Up Cell
        pickUpCell = (Button) findViewById(R.id.PickUpCell);
        pickUpCell.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                roundData.numCellPickUp++;
                lastMove.setText("pickedUpCell");
                roundData.addMoves("pickedUpCell");
                Toast.makeText(CompetitionActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        //Dropped Cell
        dropCell = (Button) findViewById(R.id.DropCell);
        dropCell.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.numCellDropped++;
                lastMove.setText("droppedCell");
                roundData.addMoves("droppedCell");
                Toast.makeText(CompetitionActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

        //Da choice buttons
        High = (Button) findViewById(R.id.high);
        High.setVisibility(View.INVISIBLE);
        High.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp += "H";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                Low.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });
        Low = (Button) findViewById(R.id.low);
        Low.setVisibility(View.INVISIBLE);
        Low.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp += "L";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                High.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });
        notAccurate = (Button) findViewById(R.id.notAccurate);
        notAccurate.setVisibility(View.INVISIBLE);
        notAccurate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp += "NotAccurate";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                veryAccurate.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });
        veryAccurate = (Button) findViewById(R.id.veryAccurate);
        veryAccurate.setVisibility(View.INVISIBLE);
        veryAccurate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp += "VeryAccurate";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                notAccurate.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });
        failSpin = (Button) findViewById(R.id.failSpin);
        failSpin.setVisibility(View.INVISIBLE);
        failSpin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp += "FailSpin";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                specificSpin.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
                roundData.numSpins--;
            }
        });
        specificSpin = (Button) findViewById(R.id.specificSpin);
        specificSpin.setVisibility(View.INVISIBLE);
        specificSpin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp += "SpecificSpin";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                failSpin.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });

        //climb buttons
        failed = (Button) findViewById(R.id.failed);
        failed.setVisibility(View.INVISIBLE);
        failed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                temp += "Fail";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                success.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });
        success = (Button) findViewById(R.id.success);
        success.setVisibility(View.INVISIBLE);
        success.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endTime = System.currentTimeMillis();
                seconds = (double)(endTime - startTime) / 1000.0;
                climbTime = seconds;
                Toast.makeText(CompetitionActivity.this, "Climb Time: " + Double.toString(seconds), Toast.LENGTH_SHORT).show();
                temp += "Success";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                failed.setVisibility(View.INVISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
                roundData.numClimbs++;
            }
        });
        HighLevel = (Button) findViewById(R.id.HighLevel);
        HighLevel.setVisibility(View.INVISIBLE);
        HighLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                temp += "HighLevel";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                MidLevel.setVisibility(View.INVISIBLE);
                LowLevel.setVisibility(View.INVISIBLE);
                success.setVisibility(View.VISIBLE);
                failed.setVisibility(View.VISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });
        MidLevel = (Button) findViewById(R.id.MidLevel);
        MidLevel.setVisibility(View.INVISIBLE);
        MidLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                temp += "MidLevel";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                HighLevel.setVisibility(View.INVISIBLE);
                LowLevel.setVisibility(View.INVISIBLE);
                failed.setVisibility(View.VISIBLE);
                success.setVisibility(View.VISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });
        LowLevel = (Button) findViewById(R.id.LowLevel);
        LowLevel.setVisibility(View.INVISIBLE);
        LowLevel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                temp += "LowLevel";
                lastMove.setText(temp);
                v.setVisibility(View.INVISIBLE);
                HighLevel.setVisibility(View.INVISIBLE);
                MidLevel.setVisibility(View.INVISIBLE);
                failed.setVisibility(View.VISIBLE);
                success.setVisibility(View.VISIBLE);
                roundData.setMoves(roundData.getSize()-1, temp);
            }
        });

        //Blue Spinner
        bSpinner = (Button) findViewById(R.id.bSpinner);
        bSpinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                temp = "bSpinner";
                roundData.addMoves(temp);
                failSpin.setVisibility(View.VISIBLE);
                specificSpin.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
                roundData.numSpins++;
            }
        });

        //Red Spinner
        rSpinner = (Button) findViewById(R.id.rSpinner);
        rSpinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                temp = "rSpinner";
                roundData.addMoves(temp);
                failSpin.setVisibility(View.VISIBLE);
                specificSpin.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
                roundData.numSpins++;
            }
        });

        //Blue Power Port
        bPowerPort = (Button) findViewById(R.id.bPowerPort);
        bPowerPort.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endTime = System.currentTimeMillis();
                seconds = (double)(endTime - startTime) / 1000.0;
                if(seconds < 300) {
                    numCycles++;
                    totalTime += seconds;
                }
                Toast.makeText(CompetitionActivity.this, Double.toString(seconds), Toast.LENGTH_SHORT).show();
                hideAll();
                temp = "bPowerPort";
                if(b1.getText().equals("Teleop"))
                    temp += "T";
                else
                    temp += "A";
                roundData.addMoves(temp);
                High.setVisibility(View.VISIBLE);
                Low.setVisibility(View.VISIBLE);
                notAccurate.setVisibility(View.VISIBLE);
                veryAccurate.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
                roundData.numTimesShot++;
            }
        });

        //Red Power Port
        rPowerPort = (Button) findViewById(R.id.rPowerPort);
        rPowerPort.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                endTime = System.currentTimeMillis();
                seconds = (double)(endTime - startTime) / 1000.0;
                if(seconds < 300) {
                    numCycles++;
                    totalTime += seconds;
                }
                Toast.makeText(CompetitionActivity.this, Double.toString(seconds), Toast.LENGTH_SHORT).show();
                hideAll();
                temp = "rPowerPort";
                if(b1.getText().equals("Teleop"))
                    temp += "T";
                else
                    temp += "A";
                roundData.addMoves(temp);
                High.setVisibility(View.VISIBLE);
                Low.setVisibility(View.VISIBLE);
                notAccurate.setVisibility(View.VISIBLE);
                veryAccurate.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
                roundData.numTimesShot++;
            }
        });

        //Blue Loading Zone
        bLoadingZone = (Button) findViewById(R.id.bLoadingZone);
        bLoadingZone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                hideAll();
                temp = "pickedUpCell";
                roundData.addMoves(temp);
                lastMove.setText(temp);
                Toast.makeText(CompetitionActivity.this, "Cell Picked Up", Toast.LENGTH_SHORT).show();
            }
        });

        //Red Loading Zone
        rLoadingZone = (Button) findViewById(R.id.rLoadingZone);
        rLoadingZone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                hideAll();
                temp = "pickedUpCell";
                roundData.addMoves(temp);
                lastMove.setText(temp);
                Toast.makeText(CompetitionActivity.this, "Cell Picked Up", Toast.LENGTH_SHORT).show();
            }
        });

        //Blue Spinner
        bSpinner = (Button) findViewById(R.id.bSpinner);
        bSpinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                temp = "bSpinner";
                roundData.addMoves(temp);
                failSpin.setVisibility(View.VISIBLE);
                specificSpin.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
            }
        });

        //Red Spinner
        rSpinner = (Button) findViewById(R.id.rSpinner);
        rSpinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                temp = "rSpinner";
                roundData.addMoves(temp);
                failSpin.setVisibility(View.VISIBLE);
                specificSpin.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
            }
        });

        //Blue Platform
        bGenerator = (Button) findViewById(R.id.bGenerator);
        bGenerator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                temp = "bGenerator";
                roundData.addMoves(temp);
                LowLevel.setVisibility(View.VISIBLE);
                MidLevel.setVisibility(View.VISIBLE);
                HighLevel.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
            }
        });

        //Red Platform
        rGenerator = (Button) findViewById(R.id.rGenerator);
        rGenerator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                temp = "rGenerator";
                roundData.addMoves(temp);
                LowLevel.setVisibility(View.VISIBLE);
                MidLevel.setVisibility(View.VISIBLE);
                HighLevel.setVisibility(View.VISIBLE);
                lastMove.setText(temp);
            }
        });
    }
    public void hideAll(){
        specificSpin.setVisibility(View.INVISIBLE);
        failSpin.setVisibility(View.INVISIBLE);
        veryAccurate.setVisibility(View.INVISIBLE);
        notAccurate.setVisibility(View.INVISIBLE);
        failed.setVisibility(View.INVISIBLE);
        success.setVisibility(View.INVISIBLE);
        HighLevel.setVisibility(View.INVISIBLE);
        MidLevel.setVisibility(View.INVISIBLE);
        LowLevel.setVisibility(View.INVISIBLE);
        High.setVisibility(View.INVISIBLE);
        Low.setVisibility(View.INVISIBLE);
    }
}