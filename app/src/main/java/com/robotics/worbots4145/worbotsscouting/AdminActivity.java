package com.robotics.worbots4145.worbotsscouting;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.worbots.worbots4145.worbotsscouting.R;

import java.util.ArrayList;
import java.util.Collections;

public class AdminActivity extends AppCompatActivity {
    public String m_text = "";
    public int intm_text = 0;
    public TextInputEditText botNum;
    public TextView botshowing;
    public matchData robotData;
    public final String possShot[] = new String[]{"", "H", "L", "NotAccurate", "VeryAccurate",
            "HNotAccurate", "HVeryAccurate", "LNotAccurate", "LVeryAccurate",
            "NotAccurateH", "NotAccurateL", "VeryAccurateH", "VeryAccurateL"};
    public final String possSpin[] = new String[]{"", "FailSpin", "SpecificSpin"};
    ArrayList<ArrayList<String>> matches;
    public ArrayList<String> robots;
    public int numMatchesPlayed = 0;
    public String advice = "";
    public String pitRoles = "";
    public String primaryRoles = "";
    public int numMatches = 0;
    public SummativeRobotData robot;
    public TextView robotScouting;
    public TextView data1;
    public TextView data2;
    public TextView data3;
    public TextView data4;
    public TextView data5;
    public TextView data6;
    public TextView data7;
    public TextView data8;
    public TextView data9;
    public TextView data10;
    public TextView data11;
    public TextView data12;
    public TextView data13;
    public TextView data14;
    public TextView data15;
    public TextView data16;
    public TextView data17;
    public TextView data19;
    public TextView data18;
    public TextView data20;
    public Button exit;
    public Button start;
    Button enter;
    public ArrayList<String> robotA = new ArrayList<String>();
    public double[][] stats;
    public TextView[] buts = new TextView[15];
    public SummativeRobotData[] statistics;
    int counter = 0;
    int numBots = 0;

    public static void sorty(double arr[][], int col) {
        for (int g = 0; g < arr[col].length; g++) {
            for (int h = arr[col].length - 1; h > 0; h--) {
                if (arr[col][h] > arr[col][h - 1]) {
                    for (int tt = 0; tt < 18; tt++) {
                        double t = arr[tt][h];
                        arr[tt][h] = arr[tt][h - 1];
                        arr[tt][h - 1] = t;
                    }
                }
            }
        }
    }
    public static void sortyBack(double arr[][], int col){
        for (int g = 0; g < arr[col].length; g++) {
            for (int h = arr[col].length - 1; h > 0; h--) {
                if (arr[col][h] < arr[col][h - 1]) {
                    for (int tt = 0; tt < 18; tt++) {
                        double t = arr[tt][h];
                        arr[tt][h] = arr[tt][h - 1];
                        arr[tt][h - 1] = t;
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        exit = (Button) findViewById(R.id.Exit);
        enter = (Button) findViewById(R.id.enter);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                intent.putExtra("NoBlow", "No");
                startActivity(intent);
            }
        });
        data1 = (TextView) findViewById(R.id.data1);
        data2 = (TextView) findViewById(R.id.data2);
        data3 = (TextView) findViewById(R.id.data3);
        data4 = (TextView) findViewById(R.id.data4);
        data5 = (TextView) findViewById(R.id.data5);
        data6 = (TextView) findViewById(R.id.data6);
        data7 = (TextView) findViewById(R.id.data7);
        data8 = (TextView) findViewById(R.id.data8);
        data9 = (TextView) findViewById(R.id.data9);
        data10 = (TextView) findViewById(R.id.data10);
        data11 = (TextView) findViewById(R.id.data11);
        data12 = (TextView) findViewById(R.id.data12);
        data13 = (TextView) findViewById(R.id.data13);
        data14 = (TextView) findViewById(R.id.data14);
        data15 = (TextView) findViewById(R.id.data15);
        data16 = (TextView) findViewById(R.id.data16);
        data17 = (TextView) findViewById(R.id.data17);
        data18 = (TextView) findViewById(R.id.data18);
        data19 = (TextView) findViewById(R.id.data19);
        data20 = (TextView) findViewById(R.id.data20);

        buts[0] = data1;
        buts[1] = data2;
        buts[2] = data3;
        buts[3] = data4;
        buts[4] = data5;
        buts[5] = data6;
        buts[6] = data7;
        buts[7] = data8;
        buts[8] = data9;
        buts[9] = data10;
        buts[10] = data11;
        buts[11] = data12;
        buts[12] = data18;
        buts[13] = data19;
        buts[14] = data20;

        robotScouting = (TextView) findViewById(R.id.robotScouting);
        botNum = (TextInputEditText) findViewById(R.id.TypeRobot);
        botNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        botshowing = (TextView) findViewById(R.id.RobotScouting);
        FirebaseDatabase.getInstance().getReference().child("Bots").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                robots = (ArrayList<String>) dataSnapshot.getValue();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Schedule").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                matches = (ArrayList<ArrayList<String>>) dataSnapshot.getValue();
                numMatches = matches.size();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        start = (Button)findViewById(R.id.start);
        start.setVisibility(View.VISIBLE);
        allHide();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numBots = robots.size();
                statistics = new SummativeRobotData[numBots];
                for (int i = 0; i < numBots; i++) {
                    statistics[i] = new SummativeRobotData(robots.get(i));
                }

                stats = new double[18][numBots];

                goDefault();
                FirebaseDatabase.getInstance().getReference().child("MatchData").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            for (DataSnapshot snap : snapshot.getChildren()) {
                                robotData = snap.getValue(matchData.class);
                                if (!robotData.botNumber.equals("")) {
                                    int position = 0;
                                    for (int i = 0; i < numBots; i++) {
                                        if (robots.get(i).equals(robotData.botNumber)) {
                                            position = i;
                                            break;
                                        }
                                    }

                                    robot = statistics[position];

                                    counter = (int) stats[0][position];
                                    if (robotData.averageCycleTime != 555) {
                                        robot.cycleTimeAverage *= counter;
                                    }
                                    if (robotData.timedClimb != 555) {
                                        robot.climbTimeAverage *= counter;
                                    }
                                    robot.DropCellAverage *= counter;
                                    robot.PickUpCellAverage *= counter;
                                    robot.numTimesShotAverage *= counter;
                                    robot.numSpinsAverage *= counter;
                                    robot.numTimesShotAutonomousAverage *= counter;
                                    counter++;
                                    stats[0][position] = counter;

                                    if (robotData.lineCross)
                                        robot.numLineCross++;
                                    robot.DropCellAverage += robotData.numCellDropped;
                                    robot.PickUpCellAverage += robotData.numCellPickUp;
                                    if (robotData.averageCycleTime != 555) {
                                        robot.cycleTimeAverage += robotData.averageCycleTime;
                                    }
                                    if (robotData.timedClimb != 555) {
                                        robot.climbTimeAverage += robotData.timedClimb;
                                    }
                                    for (int i = 0; i < possShot.length; i++) {
                                        robot.numTimesShotAverage += (int) Collections.frequency(robotData.moves, "rPowerPortT" + possShot[i])
                                                + (int) Collections.frequency(robotData.moves, "bPowerPortT" + possShot[i]);
                                    }
                                    for (int i = 0; i < possSpin.length; i++) {
                                        robot.numSpinsAverage += (int) Collections.frequency(robotData.moves, "bSpinner" + possSpin[i])
                                                + (int) Collections.frequency(robotData.moves, "rSpinner" + possSpin[i]);
                                    }
                                    for (int i = 0; i < possShot.length; i++) {
                                        robot.numTimesShotAutonomousAverage += (int) Collections.frequency(robotData.moves, "rPowerPortA" + possShot[i])
                                                + (int) Collections.frequency(robotData.moves, "bPowerPortA" + possShot[i]);
                                    }
                                    if (robotData.timedClimb != 555) {
                                        robot.climbTimeAverage /= counter;
                                    }
                                    if (robotData.averageCycleTime != 555) {
                                        robot.cycleTimeAverage /= counter;
                                    }
                                    robot.DropCellAverage /= counter;
                                    robot.PickUpCellAverage /= counter;
                                    robot.numSpinsAverage /= counter;
                                    robot.numTimesShotAverage /= counter;
                                    robot.numTimesShotAutonomousAverage /= counter;

                                    if (robotData.moves.contains("bGenerator") ||
                                            robotData.moves.contains("bGeneratorLowLevel") ||
                                            robotData.moves.contains("bGeneratorLowLevelSuccess") ||
                                            robotData.moves.contains("rGenerator") ||
                                            robotData.moves.contains("rGeneratorLowLevel") ||
                                            robotData.moves.contains("rGeneratorLowLevelSuccess")) {
                                        robot.numClimbedLowLevel++;
                                    } else if (robotData.moves.contains("bGenerator") ||
                                            robotData.moves.contains("bGeneratorMidLevel") ||
                                            robotData.moves.contains("bGeneratorMidLevelSuccess") ||
                                            robotData.moves.contains("rGenerator") ||
                                            robotData.moves.contains("rGeneratorMidLevel") ||
                                            robotData.moves.contains("rGeneratorMidLevelSuccess")) {
                                        robot.numClimbedMidLevel++;
                                    } else if (robotData.moves.contains("bGenerator") ||
                                            robotData.moves.contains("bGeneratorHighLevel") ||
                                            robotData.moves.contains("bGeneratorHighLevelSuccess") ||
                                            robotData.moves.contains("rGenerator") ||
                                            robotData.moves.contains("rGeneratorHighLevel") ||
                                            robotData.moves.contains("rGeneratorHighLevelSuccess")) {
                                        robot.numClimbedHighLevel++;
                                    }
                                    stats[1][position] = robot.DropCellAverage;
                                    stats[2][position] = robot.PickUpCellAverage;
                                    stats[3][position] = robot.numSpinsAverage;
                                    stats[4][position] = robot.numTimesShotAverage;
                                    stats[5][position] = robot.numTimesShotAutonomousAverage;
                                    stats[6][position] = robot.numClimbedLowLevel;
                                    stats[7][position] = robot.numClimbedMidLevel;
                                    stats[8][position] = robot.numClimbedHighLevel;
                                    stats[9][position] = robot.cycleTimeAverage;
                                    stats[10][position] = robot.climbTimeAverage;
                                    stats[11][position] = robot.numLineCross;
                                }
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) { }
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) { }
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) { }
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });

                for (int i = 0; i < numBots; i++) {
                    stats[17][i] = Integer.parseInt(robots.get(i));
                }
            }
        });

        data2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sortyBack(stats, 1);
                if (data2.getText().equals("Cell Drop Average: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[1][i]));
                    }
                }
            }
        });

        data3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 2);
                if (data3.getText().equals("Cell Pick Up Average: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[2][i]));
                    }
                }
            }
        });

        data4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 3);
                if (data4.getText().equals("Number of Spins Average: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[3][i]));
                    }
                }
            }
        });

        data5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 4);
                if (data5.getText().equals("Number of Times Shot Average: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][numBots - i - 1]) + ":" + Math.round(stats[4][i]));
                    }
                }
            }
        });

        data6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 5);
                if (data6.getText().equals("Number of Times Shot Autonomous Average: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[5][i]));
                    }
                }
            }
        });

        data7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 6);
                if (data7.getText().equals("Number of Climbs at Level 1: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[6][i]));
                    }
                }
            }
        });

        data8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 7);
                if (data8.getText().equals("Number of Climbs at Level 2: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[7][i]));
                    }
                }
            }
        });

        data9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 8);
                if (data9.getText().equals("Number of Climbs at Level 3: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[8][i]));
                    }
                }
            }
        });

        data10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sortyBack(stats, 9);
                if (data10.getText().equals("Cycle Time Average: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[9][i]));
                    }
                }
            }
        });

        data11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sortyBack(stats, 10);
                if (data11.getText().equals("Climb Time Average: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[10][i]));
                    }
                }
            }
        });

        data12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data16.setVisibility(View.INVISIBLE);
                data17.setVisibility(View.INVISIBLE);
                data18.setVisibility(View.INVISIBLE);
                data19.setVisibility(View.INVISIBLE);
                data20.setVisibility(View.INVISIBLE);
                sorty(stats, 11);
                if (data12.getText().equals("Number of Line Crosses: ")) {
                    for (int i = 0; i < 15; i++) {
                        buts[i].setText(Math.round(stats[17][i]) + ":" + Math.round(stats[11][i]));
                    }
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_text = botNum.getEditableText().toString();
                try {
                    intm_text = Integer.parseInt(m_text.trim());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(AdminActivity.this, "There is NO such robot!", Toast.LENGTH_SHORT).show();
                    goDefault();
                }
                if (!m_text.equals("")) {
                    FirebaseDatabase.getInstance().getReference().child("RobotData").child(m_text).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                            robotA = (ArrayList) dataSnapshot.getValue();
                            for (int i = 0; i < robotA.size(); i++) {
                                if (!robotA.get(i).equals("")) {
                                    pitRoles += robotA.get(i) + "|||";
                                }
                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                if (!m_text.equals("") && robots.contains(m_text)) {
                    numMatchesPlayed = 0;
                    robot = new SummativeRobotData(m_text);
                    for (int i = 0; i < numMatches; i++) {
                        FirebaseDatabase.getInstance().getReference().child("MatchData").child(Integer.toString(i)).child(m_text).addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                                robotData = dataSnapshot.getValue(matchData.class);
                                if (robotData.averageCycleTime != 555) {
                                    robot.cycleTimeAverage *= numMatchesPlayed;
                                }
                                if (robotData.timedClimb != 555) {
                                    robot.climbTimeAverage *= numMatchesPlayed;
                                }
                                robot.DropCellAverage *= numMatchesPlayed;
                                robot.PickUpCellAverage *= numMatchesPlayed;
                                robot.numSpinsAverage *= numMatchesPlayed;
                                robot.numTimesShotAverage *= numMatchesPlayed;
                                robot.numTimesShotAutonomousAverage *= numMatchesPlayed;
                                numMatchesPlayed++;

                                advice += robotData.notes + "|||";
                                if (robotData.check1) primaryRoles += "ShootsWell|||";
                                if (robotData.check2) primaryRoles += "ShootsPoorly|||";
                                if (robotData.check3) primaryRoles += "Can Spin|||";
                                if (robotData.check4) primaryRoles += "Can Load Cells|||";
                                if (robotData.check5) primaryRoles += "Can Climb|||";
                                if (robotData.check6) primaryRoles += "Good Autonomous|||";
                                if (robotData.check7) primaryRoles += "Primary Role Defense|||";
                                if (robotData.check8) primaryRoles += "Dysfunctional|||";
                                if (robotData.check9) primaryRoles += "Unreliable|||";

                                if (robotData.lineCross)
                                    robot.numLineCross++;
                                robot.DropCellAverage += robotData.numCellDropped;
                                robot.PickUpCellAverage += robotData.numCellDropped;
                                if (robotData.averageCycleTime != 555) {
                                    robot.cycleTimeAverage += robotData.averageCycleTime;
                                }
                                if (robotData.timedClimb != 555) {
                                    robot.climbTimeAverage += robotData.timedClimb;
                                }
                                for (int i = 0; i < possShot.length; i++) {
                                    robot.numTimesShotAverage += (int) Collections.frequency(robotData.moves, "rPowerPortT" + possShot[i])
                                            + (int) Collections.frequency(robotData.moves, "bPowerPortT" + possShot[i]);
                                }
                                for (int i = 0; i < possSpin.length; i++) {
                                    robot.numSpinsAverage += (int) Collections.frequency(robotData.moves, "bSpinner" + possSpin[i])
                                            + (int) Collections.frequency(robotData.moves, "rSpinner" + possSpin[i]);
                                }
                                for (int i = 0; i < possShot.length; i++) {
                                    robot.numTimesShotAutonomousAverage += (int) Collections.frequency(robotData.moves, "rPowerPortA" + possShot[i])
                                            + (int) Collections.frequency(robotData.moves, "bPowerPortA" + possShot[i]);
                                }

                                if (robotData.timedClimb != 555) {
                                    robot.climbTimeAverage /= numMatchesPlayed;
                                }
                                if (robotData.averageCycleTime != 555) {
                                    robot.cycleTimeAverage /= numMatchesPlayed;
                                }
                                robot.DropCellAverage /= numMatchesPlayed;
                                robot.DropCellAverage /= numMatchesPlayed;
                                robot.PickUpCellAverage /= numMatchesPlayed;
                                robot.numSpinsAverage /= numMatchesPlayed;
                                robot.numTimesShotAverage /= numMatchesPlayed;
                                robot.numTimesShotAutonomousAverage /= numMatchesPlayed;

                                if (robotData.moves.contains("bGenerator") ||
                                        robotData.moves.contains("bGeneratorLowLevel") ||
                                        robotData.moves.contains("bGeneratorLowLevelSuccess") ||
                                        robotData.moves.contains("rGenerator") ||
                                        robotData.moves.contains("rGeneratorLowLevel") ||
                                        robotData.moves.contains("rGeneratorLowLevelSuccess")) {
                                    robot.numClimbedLowLevel++;
                                } else if (robotData.moves.contains("bGenerator") ||
                                        robotData.moves.contains("bGeneratorMidLevel") ||
                                        robotData.moves.contains("bGeneratorMidLevelSuccess") ||
                                        robotData.moves.contains("rGenerator") ||
                                        robotData.moves.contains("rGeneratorMidLevel") ||
                                        robotData.moves.contains("rGeneratorMidLevelSuccess")) {
                                    robot.numClimbedMidLevel++;
                                } else if (robotData.moves.contains("bGenerator") ||
                                        robotData.moves.contains("bGeneratorHighLevel") ||
                                        robotData.moves.contains("bGeneratorHighLevelSuccess") ||
                                        robotData.moves.contains("rGenerator") ||
                                        robotData.moves.contains("rGeneratorHighLevel") ||
                                        robotData.moves.contains("rGeneratorHighLevelSuccess")) {
                                    robot.numClimbedHighLevel++;
                                }

                                data1.setText("Number of Matches Played: " + numMatchesPlayed);
                                data2.setText("Cell Drop Average: " + robot.DropCellAverage);
                                data3.setText("Cell Pick Up Average: " + robot.PickUpCellAverage);
                                data4.setText("Number of Spins Average: " + robot.numSpinsAverage);
                                data5.setText("Number of Times Shot Average: " + robot.numTimesShotAverage);
                                data6.setText("Number of Times Shot Autonomous Average: " + robot.numTimesShotAutonomousAverage);
                                data7.setText("Number of Climbs at Level 1: " + robot.numClimbedLowLevel);
                                data8.setText("Number of Climbs at Level 2: " + robot.numClimbedMidLevel);
                                data9.setText("Number of Climbs at Level 3: " + robot.numClimbedHighLevel);
                                data10.setText("Cycle Time Average: " + robot.cycleTimeAverage);
                                data11.setText("Climb Time Average: " + robot.climbTimeAverage);
                                data12.setText("Number of Line Crosses: " + robot.numLineCross);
                                data18.setText("Scouters' Notes: " + advice);
                                data19.setText("Primary Roles: " + primaryRoles);
                                data20.setText("Pit Scouting Notes: " + pitRoles);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                } else {
                    Toast.makeText(AdminActivity.this, "This robot is not competing in this competition!!!", Toast.LENGTH_SHORT).show();
                    goDefault();
                }
            }
        });
    }

    public void allHide() {
        data1.setVisibility(View.INVISIBLE);
        data2.setVisibility(View.INVISIBLE);
        data3.setVisibility(View.INVISIBLE);
        data4.setVisibility(View.INVISIBLE);
        data5.setVisibility(View.INVISIBLE);
        data6.setVisibility(View.INVISIBLE);
        data7.setVisibility(View.INVISIBLE);
        data8.setVisibility(View.INVISIBLE);
        data9.setVisibility(View.INVISIBLE);
        data10.setVisibility(View.INVISIBLE);
        data11.setVisibility(View.INVISIBLE);
        data12.setVisibility(View.INVISIBLE);
        data18.setVisibility(View.INVISIBLE);
        data19.setVisibility(View.INVISIBLE);
        data20.setVisibility(View.INVISIBLE);
        robotScouting.setVisibility(View.INVISIBLE);
        enter.setVisibility(View.INVISIBLE);
        botNum.setVisibility(View.INVISIBLE);
    }

    public void goDefault(){
        data1.setText("Number of Matches Played: ");
        data2.setText("Cell Drop Average: ");
        data3.setText("Cell Pick Up Average: ");
        data4.setText("Number of Spins Average: ");
        data5.setText("Number of Times Shot Average: ");
        data6.setText("Number of Times Shot Autonomous Average: ");
        data7.setText("Number of Climbs at Level 1: ");
        data8.setText("Number of Climbs at Level 2: ");
        data9.setText("Number of Climbs at Level 3: ");
        data10.setText("Cycle Time Average: ");
        data11.setText("Climb Time Average: ");
        data12.setText("Number of Line Crosses: ");
        data18.setText("Scouters' Notes: ");
        data19.setText("Primary Roles: ");
        data20.setText("Pit Scouting Notes: ");

        data1.setVisibility(View.VISIBLE);
        data2.setVisibility(View.VISIBLE);
        data3.setVisibility(View.VISIBLE);
        data4.setVisibility(View.VISIBLE);
        data5.setVisibility(View.VISIBLE);
        data6.setVisibility(View.VISIBLE);
        data7.setVisibility(View.VISIBLE);
        data8.setVisibility(View.VISIBLE);
        data9.setVisibility(View.VISIBLE);
        data10.setVisibility(View.VISIBLE);
        data11.setVisibility(View.VISIBLE);
        data12.setVisibility(View.VISIBLE);
        data18.setVisibility(View.VISIBLE);
        data19.setVisibility(View.VISIBLE);
        data20.setVisibility(View.VISIBLE);

        robotScouting.setVisibility(View.VISIBLE);
        enter.setVisibility(View.VISIBLE);
        botNum.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
    }
}