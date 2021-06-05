package com.robotics.worbots4145.worbotsscouting;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.worbots.worbots4145.worbotsscouting.R;

import java.util.ArrayList;
import java.util.Scanner;

public class EndMatchActivity extends AppCompatActivity {
    public String roundNum;
    public matchData roundData;
    public Button checkBox1;
    public Button checkBox2;
    public Button checkBox3;
    public Button checkBox4;
    public Button checkBox5;
    public Button checkBox6;
    public Button checkBox7;
    public Button checkBox8;
    public Button checkBox9;
    public Button submit;
    public TextInputEditText otherNotes;
    public String botNum = "";
    ArrayList<ArrayList<String>> matches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_match);
        roundNum = getIntent().getStringExtra("RoundNumber");
        botNum = getIntent().getStringExtra("BotNum");

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
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        checkBox1 = (Button) findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change1();
            }
        });
        checkBox2 = (Button) findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change2();
            }
        });
        checkBox3 = (Button) findViewById(R.id.checkBox3);
        checkBox3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change3();
            }
        });
        checkBox4 = (Button) findViewById(R.id.checkBox4);
        checkBox4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change4();
            }
        });
        checkBox5 = (Button) findViewById(R.id.checkBox5);
        checkBox5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change5();
            }
        });
        checkBox6 = (Button) findViewById(R.id.checkBox6);
        checkBox6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change6();
            }
        });
        checkBox7 = (Button) findViewById(R.id.checkBox7);
        checkBox7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change7();
            }
        });
        checkBox8 = (Button) findViewById(R.id.checkBox8);
        checkBox8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change8();
            }
        });
        checkBox9 = (Button) findViewById(R.id.checkBox9);
        checkBox9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.change9();
            }
        });

        otherNotes = (TextInputEditText) findViewById(R.id.otherNotes);

        submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roundData.setNotes(otherNotes.getEditableText().toString());
                FirebaseDatabase.getInstance().getReference().child("MatchData").child(roundNum).child(botNum).removeValue();
                FirebaseDatabase.getInstance().getReference().child("MatchData").child(roundNum).child(botNum).push().setValue(roundData);
                for(int i = 0; i<6; i++){
                    if(Integer.parseInt(matches.get(Integer.parseInt(roundNum) - 1).get(i)) == Integer.parseInt(botNum)){
                        botNum = Integer.toString(i);
                        break;
                    }
                }

                Intent intent = new Intent(EndMatchActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", String.valueOf(Integer.valueOf(roundNum)+1));
                intent.putExtra("BotNum", botNum);
                startActivity(intent);
            }
        });
    }
}
