package com.robotics.worbots4145.worbotsscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.worbots.worbots4145.worbotsscouting.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoutingActivity extends AppCompatActivity {
    public int count = 0;
    public boolean canProceed = false;
    public matchData roundData = new matchData("0");
    public boolean blimpy = false;
    public String bob = "";
    public ToggleButton leftTop;
    public ToggleButton leftMiddle;
    public ToggleButton leftBottom;
    public ToggleButton rightTop;
    public ToggleButton rightMiddle;
    public ToggleButton rightBottom;
    public Button bb1;
    public Button bb2;
    public Button bb3;
    public Button rb1;
    public Button rb2;
    public Button rb3;
    public Button setBot;
    public String botNum = null;
    ArrayList<ArrayList<String>> matches = null;
    public Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);
        bob = getIntent().getStringExtra("RoundNumber");
        bb1 = (Button) findViewById(R.id.bb1);
        bb2 = (Button) findViewById(R.id.bb2);
        bb3 = (Button) findViewById(R.id.bb3);
        rb1 = (Button) findViewById(R.id.rb1);
        rb2 = (Button) findViewById(R.id.rb2);
        rb3 = (Button) findViewById(R.id.rb3);
        exit = (Button)findViewById(R.id.exit2);
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoutingActivity.this, MainActivity.class);
                intent.putExtra("NoBlow", "No");
                startActivity(intent);
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Schedule").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                matches = (ArrayList<ArrayList<String>>)dataSnapshot.getValue();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        hideAll();

        bb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                roundData.botNumber = matches.get(Integer.parseInt(bob) - 1).get(3);
                botNum = roundData.botNumber;
            }
        });
        bb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                roundData.botNumber = matches.get(Integer.parseInt(bob) - 1).get(4);
                botNum = roundData.botNumber;
            }
        });
        bb3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                roundData.botNumber = matches.get(Integer.parseInt(bob) - 1).get(5);
                botNum = roundData.botNumber;
            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                roundData.botNumber = matches.get(Integer.parseInt(bob) - 1).get(0);
                botNum = roundData.botNumber;
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                roundData.botNumber = matches.get(Integer.parseInt(bob) - 1).get(1);
                botNum = roundData.botNumber;
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideAll();
                roundData.botNumber = matches.get(Integer.parseInt(bob) - 1).get(2);
                botNum = roundData.botNumber;
            }
        });


        String temp = getIntent().getStringExtra("BotNum");

        roundData.round = bob;
        if(bob.equals("1f")) {
            bob = "1";
            showAll();
        }
        setBot = (Button) findViewById(R.id.SetBot);
        setBot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showAll();
                if(temp != null){
                    botNum = matches.get(Integer.parseInt(bob) - 1).get(Integer.parseInt(temp));
                    roundData.botNumber = botNum;
                    hideAll();
                }
            }
        });

        TextView title = (TextView)findViewById(R.id.Title);
        title.setText("Round " + bob + ": Da Stage To Choose The Starting Point For The Robot");
        Button b1;
        leftTop = (ToggleButton) findViewById(R.id.LeftTop);
        leftMiddle = (ToggleButton) findViewById(R.id.LeftMiddle);
        leftBottom = (ToggleButton) findViewById(R.id.LeftBottom);
        rightTop = (ToggleButton) findViewById(R.id.RightTop);
        rightMiddle = (ToggleButton) findViewById(R.id.RightMiddle);
        rightBottom = (ToggleButton) findViewById(R.id.RightBottom);

        leftTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && count == 0) {
                    roundData.setStart("leftTop");
                    count++;
                    canProceed = true;
                }
                else if(isChecked && count != 0){
                    Toast.makeText(ScoutingActivity.this, "Only one of the starting positions should be checked", Toast.LENGTH_SHORT).show();
                    leftTop.setChecked(false);
                }
                else
                    count--;
            }
        });
        leftMiddle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && count == 0) {
                    roundData.setStart("leftMiddle");
                    count++;
                    canProceed = true;
                }
                else if(isChecked && count != 0){
                    Toast.makeText(ScoutingActivity.this, "Only one of the starting positions should be checked", Toast.LENGTH_SHORT).show();
                    leftMiddle.setChecked(false);
                }
                else
                    count--;
            }
        });
        leftBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && count == 0) {
                    roundData.setStart("leftBottom");
                    count++;
                    canProceed = true;
                }
                else if(isChecked && count != 0){
                    Toast.makeText(ScoutingActivity.this, "Only one of the starting positions should be checked", Toast.LENGTH_SHORT).show();
                    leftBottom.setChecked(false);
                }
                else
                    count--;
            }
        });
        rightTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && count == 0) {
                    roundData.setStart("rightTop");
                    count++;
                    canProceed = true;
                }
                else if(isChecked && count != 0){
                    Toast.makeText(ScoutingActivity.this, "Only one of the starting positions should be checked", Toast.LENGTH_SHORT).show();
                    rightTop.setChecked(false);
                }
                else
                    count--;
            }
        });
        rightMiddle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && count == 0) {
                    roundData.setStart("rightMiddle");
                    count++;
                    canProceed = true;
                }
                else if(isChecked && count != 0){
                    Toast.makeText(ScoutingActivity.this, "Only one of the starting positions should be checked", Toast.LENGTH_SHORT).show();
                    rightMiddle.setChecked(false);
                }
                else
                    count--;
            }
        });
        rightBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && count == 0) {
                    roundData.setStart("rightBottom");
                    count++;
                    canProceed = true;
                }
                else if(isChecked && count != 0){
                    Toast.makeText(ScoutingActivity.this, "Only one of the starting positions should be checked", Toast.LENGTH_SHORT).show();
                    rightBottom.setChecked(false);
                }
                else
                    count--;
            }
        });

        b1 = (Button) findViewById(R.id.r1);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "1");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r2);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "2");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r3);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "3");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r4);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "4");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r5);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "5");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r6);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "6");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r7);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "7");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r8);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "8");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r9);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "9");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r10);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "10");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r11);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "11");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r12);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "12");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r13);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "13");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r14);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "14");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r15);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "15");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r16);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "16");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r17);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "17");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r18);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "18");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r19);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "19");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r20);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "20");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r21);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "21");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r22);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "22");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r23);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "23");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r24);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "24");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r25);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "25");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r26);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "26");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r27);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "27");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r28);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "28");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r29);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "29");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r30);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "30");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r31);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "31");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r32);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "32");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r33);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "33");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r34);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "34");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r35);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "35");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r36);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "36");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r37);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "37");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r38);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "38");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r39);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "39");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r40);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "40");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r41);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "41");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r42);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "42");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r43);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "43");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r44);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "44");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r45);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "45");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r46);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "46");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r47);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "47");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r48);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "48");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r49);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "49");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r50);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "50");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r51);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "51");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r52);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "52");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r53);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "53");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r54);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "54");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r55);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "55");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r56);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "56");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r57);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "57");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r58);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "58");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r59);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "59");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r60);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "60");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r61);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "61");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r62);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "62");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r63);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "63");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r64);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "64");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r65);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "65");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r66);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "66");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r67);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "67");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r68);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "68");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r69);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "69");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r70);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "70");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r71);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "71");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r72);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "72");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r73);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "73");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r74);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "74");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r75);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "75");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r76);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "76");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r77);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "77");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r78);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "78");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r79);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "79");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r80);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "80");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r81);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "81");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r82);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "82");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r83);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "83");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r84);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "84");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r85);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "85");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r86);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "86");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r87);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "87");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r88);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "88");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r89);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "89");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r90);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "90");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r91);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "91");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r92);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "92");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r93);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "93");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r94);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "94");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r95);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "95");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r96);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "96");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r97);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "97");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r98);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "98");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r99);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "99");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.r100);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoutingActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "100");
                startActivity(intent);
            }
        });





        b1 = (Button) findViewById(R.id.Save);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setBot.performClick();
                if(canProceed && count == 1) {
                    if(botNum != null) {
                        blimpy = true;
                        if(bob.equals("1f")) {
                            bob = "1";
                            showAll();
                        }
                        else if(temp != null){
                            botNum = matches.get(Integer.parseInt(bob) - 1).get(Integer.parseInt(temp));
                            roundData.botNumber = botNum;
                        }
                        Toast.makeText(ScoutingActivity.this, "SAVED", Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference().child("MatchData").child(bob).child(botNum).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("MatchData").child(bob).child(botNum).push().setValue(roundData);
                    }
                    else {
                        Toast.makeText(ScoutingActivity.this, "You did not specify which robot you are scouting. Click 'set bot' to change it", Toast.LENGTH_SHORT).show();
                        blimpy = false;
                    }
                }
                else {
                    Toast.makeText(ScoutingActivity.this, "Starting position not right", Toast.LENGTH_SHORT).show();
                    blimpy = false;
                }
            }
        });
        b1 = (Button) findViewById(R.id.MatchStarter);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(blimpy) {
                    Intent intent = new Intent(ScoutingActivity.this, CompetitionActivity.class);
                    intent.putExtra("RoundNumber", bob);
                    intent.putExtra("BotNum", botNum);
                    startActivity(intent);
                }
            }
        });
    }
    public void hideAll(){
        bb1.setVisibility(View.INVISIBLE);
        bb2.setVisibility(View.INVISIBLE);
        bb3.setVisibility(View.INVISIBLE);
        rb1.setVisibility(View.INVISIBLE);
        rb2.setVisibility(View.INVISIBLE);
        rb3.setVisibility(View.INVISIBLE);
    }
    public void showAll()
    {
        bb1.setVisibility(View.VISIBLE);
        bb2.setVisibility(View.VISIBLE);
        bb3.setVisibility(View.VISIBLE);
        rb1.setVisibility(View.VISIBLE);
        rb2.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.VISIBLE);
    }
}