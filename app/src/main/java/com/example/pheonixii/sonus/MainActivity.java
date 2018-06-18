package com.example.pheonixii.sonus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import jm.audio.AOException;
import jm.audio.Instrument;
import jm.music.data.Note;
import jm.util.Play;

import static jm.constants.Durations.CROTCHET;
import static jm.constants.Pitches.C4;
import static jm.constants.Volumes.MF;

public class MainActivity extends AppCompatActivity {
    //I made a comment // Another comment // Once again
    //new comment // One more change
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        List<String> intervals = new ArrayList<String>();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.P1:
                if (checked) {
                    count++;
                }
            case R.id.m2:
                if (checked) {
                    count++;
                }
            case R.id.M2:
                if (checked) {
                    count++;
                }
            case R.id.m3:
                if (checked) {
                    count++;
                }
            case R.id.M3:
                if (checked) {
                    count++;
                }
            case R.id.P4:
                if (checked) {
                    count++;
                }
            case R.id.P5:
                if (checked) {
                    count++;
                }
            case R.id.m6:
                if (checked) {
                    count++;
                }
            case R.id.M6:
                if (checked) {
                    count++;
                }
            case R.id.m7:
                if (checked) {
                    count++;
                }
            case R.id.M7:
                if (checked) {
                    count++;
                }
            case R.id.P8:
                if (checked) {
                    count++;
                }
        }
    }

    public void play(View view) {

        Intent intent = new Intent(this, GameActivity.class);
        if(count >= 3)
            startActivity(intent);
    }



}


