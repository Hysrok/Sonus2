package com.example.pheonixii.sonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import jm.util.Play;

import android.view.View;

public class MainActivity extends AppCompatActivity {
//I made a comment // Another comment // Once again
    //new comment // One more change
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void play(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
