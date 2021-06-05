package com.robotics.worbots4145.worbotsscouting;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.worbots.worbots4145.worbotsscouting.R;

import java.util.ArrayList;

public class PitActivity extends AppCompatActivity {
    public final int NUM_CC = 16;
    public TextInputEditText botNum;
    public String m_text;
    public int intm_text;
    public ArrayList<String> robotArray = new ArrayList<String>();
    public Boolean blimpy = false;
    public TextView botshowing;
    public CheckBox cc1;
    public CheckBox cc2;
    public CheckBox cc3;
    public CheckBox cc4;
    public CheckBox cc5;
    public CheckBox cc6;
    public CheckBox cc7;
    public CheckBox cc8;
    public CheckBox cc9;
    public CheckBox cc10;
    public CheckBox cc11;
    public CheckBox cc12;
    public CheckBox cc13;
    public CheckBox cc14;
    public CheckBox cc15;
    public CheckBox cc16;
    public CheckBox cc[] = {};
    public TextInputEditText txt1;
    public TextInputEditText txt2;
    public TextInputEditText txt3;
    public TextInputEditText txt4;
    public TextInputEditText txt5;
    public TextInputEditText txt6;
    public TextInputEditText txt7;
    public TextInputEditText txt8;
    public TextInputEditText txt9;
    public TextInputEditText txt10;
    public TextInputEditText txt[] = {};
    public Button saveB;
    public Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit);
        botNum = (TextInputEditText) findViewById(R.id.TypeRobot);
        botNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        botNum.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        botshowing = (TextView) findViewById(R.id.RobotScouting);
        exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PitActivity.this, MainActivity.class);
                intent.putExtra("NoBlow", "No");
                startActivity(intent);
            }
        });
        cc1 = (CheckBox) findViewById(R.id.cb1);
        cc2 = (CheckBox) findViewById(R.id.cb2);
        cc3 = (CheckBox) findViewById(R.id.cb3);
        cc4 = (CheckBox) findViewById(R.id.cb4);
        cc5 = (CheckBox) findViewById(R.id.cb5);
        cc6 = (CheckBox) findViewById(R.id.cb6);
        cc7 = (CheckBox) findViewById(R.id.cb7);
        cc8 = (CheckBox) findViewById(R.id.cb8);
        cc9 = (CheckBox) findViewById(R.id.cb9);
        cc10 = (CheckBox) findViewById(R.id.cb10);
        cc11 = (CheckBox) findViewById(R.id.cb11);
        cc12 = (CheckBox) findViewById(R.id.cb12);
        cc13 = (CheckBox) findViewById(R.id.cb13);
        cc14 = (CheckBox) findViewById(R.id.cb14);
        cc15 = (CheckBox) findViewById(R.id.cb15);
        cc16 = (CheckBox) findViewById(R.id.cb16);
        cc = new CheckBox[]{cc1, cc2, cc3, cc4, cc5, cc6, cc7, cc8, cc9, cc10, cc11, cc12, cc13, cc14, cc15, cc16};
        txt1 = (TextInputEditText) findViewById(R.id.txt11);
        txt2 = (TextInputEditText) findViewById(R.id.txt22);
        txt3 = (TextInputEditText) findViewById(R.id.txt33);
        txt4 = (TextInputEditText) findViewById(R.id.txt44);
        txt5 = (TextInputEditText) findViewById(R.id.txt55);
        txt6 = (TextInputEditText) findViewById(R.id.txt66);
        txt7 = (TextInputEditText) findViewById(R.id.txt77);
        txt8 = (TextInputEditText) findViewById(R.id.txt88);
        txt9 = (TextInputEditText) findViewById(R.id.txt99);
        txt10 = (TextInputEditText) findViewById(R.id.txt1010);
        txt = new TextInputEditText[]{txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10};
        saveB = (Button) findViewById(R.id.SaveScout);
        allInvisible();
        Button enter = (Button) findViewById(R.id.Enter);

        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_text = botNum.getEditableText().toString();
                try {
                    intm_text = Integer.parseInt(m_text.trim());
                } catch (NumberFormatException nfe) {
                    Toast.makeText(PitActivity.this, "There is NO such robot!", Toast.LENGTH_SHORT).show();
                }

                if(!m_text.equals("")){
                FirebaseDatabase.getInstance().getReference().child("RobotData").child(m_text).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                        blimpy = true;
                        robotArray = (ArrayList<String>) dataSnapshot.getValue();
                        botshowing.setText("Scouting: " + m_text);
                        allVisible();
                        for(int i = 0; i<cc.length; i++){
                            if(robotArray.get(i).equals(""))
                                cc[i].setChecked(false);
                            else
                                cc[i].setChecked(true);
                        }
                        for(int i = 0; i<txt.length; i++)
                            txt[i].setText(robotArray.get(i+NUM_CC));

                        saveB.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v) {
                                for(int i = 0; i<cc.length; i++){
                                    if(cc[i].isChecked())
                                        robotArray.set(i, cc[i].getText().toString());
                                    else
                                        robotArray.set(i, "");
                                }
                                for(int i = 0; i<txt.length; i++){
                                    if(!txt[i].getEditableText().toString().equals("")){
                                        String temp = txt[i].getEditableText().toString();
                                        robotArray.set(i+NUM_CC, temp);
                                        txt[i].setText(temp);
                                    }
                                }
                                Toast.makeText(PitActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                                FirebaseDatabase.getInstance().getReference().child("RobotData").child(m_text).removeValue();
                                FirebaseDatabase.getInstance().getReference().child("RobotData").child(m_text).push().setValue(robotArray);
                            }
                        });
                        blimpy = false;
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
                });}
                if(!blimpy) {
                    botshowing.setText("Error");
                    for(int i = 0; i<cc.length; i++){
                        cc[i].setChecked(false);
                    }
                    allInvisible();
                }
            }
        });
    }
    void allInvisible() {
        for(int i = 0; i<cc.length; i++){
            cc[i].setVisibility(View.INVISIBLE);
        }
        for(int i = 0; i<txt.length; i++){
            txt[i].setVisibility(View.INVISIBLE);
        }
        saveB.setVisibility(View.INVISIBLE);
    }
    void allVisible() {
        for(int i = 0; i<cc.length; i++){
            cc[i].setVisibility(View.VISIBLE);
        }
        for(int i = 0; i<txt.length; i++){
            txt[i].setVisibility(View.VISIBLE);
        }
        saveB.setVisibility(View.VISIBLE);
    }
    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }
}