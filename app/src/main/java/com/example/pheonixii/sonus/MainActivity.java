package com.example.pheonixii.sonus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

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
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Note note = new Note(C4, CROTCHET, MF);

        Instrument instrument = new Instrument() {
            @Override
            public void createChain() throws AOException {

            }
        };

        Play play = new Play();
        play.audio(note, instrument);
        setContentView(R.layout.activity_main);
    }

    public void checkboxesClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        count = 0;
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.P1:
                if (checked)
                    count++;
            case R.id.m2:
                if (checked)
                    count++;
            case R.id.M2:
                if (checked)
                    count++;
            case R.id.m3:
                if (checked)
                    count++;
            case R.id.M3:
                if (checked)
                    count++;
            case R.id.P4:
                if (checked)
                    count++;
            case R.id.P5:
                if (checked)
                    count++;
            case R.id.m6:
                if (checked)
                    count++;
            case R.id.M6:
                if (checked)
                    count++;
            case R.id.m7:
                if (checked)
                    count++;
            case R.id.M7:
                if (checked)
                    count++;
            case R.id.P8:
                if (checked)
                    count++;
        }
    }

    public void play(View view) {

        Intent intent = new Intent(this, GameActivity.class);
        if(count >= 3)
            startActivity(intent);
    }



}


