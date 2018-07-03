package com.example.pheonixii.sonus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {
    ArrayList<String> intervals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        setContentView(R.layout.content_stats);
        intervals = intent.getStringArrayListExtra("interval_list");
        double score = intent.getDoubleExtra("USER_SCORE", 0.0);
        TextView scoreText = (TextView)findViewById(R.id.textView);
        scoreText.setText("Your Score: " + score + "/10");

        // I commented out all the stuff below because I'm not sure what its for.

        /*setContentView(R.layout.content_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    /**
     * Gives the user the option to return to the main menu
     */
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Gives the user the option to replay with the same intervals
     */
    public void retry(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putStringArrayListExtra("interval_list", intervals);
        startActivity(intent);
    }
}
