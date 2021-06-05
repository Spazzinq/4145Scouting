package com.robotics.worbots4145.worbotsscouting;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.worbots.worbots4145.worbotsscouting.R;

public class MainActivity extends AppCompatActivity {
    RobotData botData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_UNDEFINED)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button b1 = (Button) findViewById(R.id.Scout);

     //   RobotData bob = new RobotData();
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoutingActivity.class);
                intent.putExtra("RoundNumber", "1f");
                startActivity(intent);
            }
        });
        b1 = (Button) findViewById(R.id.Admin);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
            }
        });

        b1 = (Button) findViewById(R.id.pitScouting);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PitActivity.class));
            }
        });

        TextView link = (TextView)findViewById(R.id.link);
        Spanned Text = Html.fromHtml("<a>https://worbots4145dotorg.files.wordpress.com/2019/05/privacypolicy-1.html_.pdf</a>");

        link.setMovementMethod(LinkMovementMethod.getInstance());
        link.setText(Text);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
