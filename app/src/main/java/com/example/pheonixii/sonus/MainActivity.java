package com.example.pheonixii.sonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jm.audio.AOException;
import jm.audio.Instrument;
import jm.music.data.Note;
import jm.util.Play;

import android.view.View;

import static jm.constants.Durations.CROTCHET;
import static jm.constants.Pitches.C4;
import static jm.constants.Volumes.MF;

public class MainActivity extends AppCompatActivity {
//I made a comment // Another comment // Once again
    //new comment // One more change
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

    public void play(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
