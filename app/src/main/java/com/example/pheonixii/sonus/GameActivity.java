package com.example.pheonixii.sonus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.net.URI;
import java.util.ArrayList;

import jm.music.data.Note;

import static jm.constants.Durations.Q;
import static jm.constants.Durations.WHOLE_NOTE;
import static jm.constants.Pitches.C4;

public class GameActivity extends AppCompatActivity {

    private int note1;
    private int note2;
    private MediaPlayer mediaPlayer;
    private MediaPlayer midiFileMediaPlayer1;
    private MediaPlayer midiFileMediaPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        ArrayList intervals = intent.getStringArrayListExtra("interval_list");
        Spinner spinner = (Spinner) findViewById(R.id.intervals_spinner);

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, intervals);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submit(View view) {
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }
    public void play(View view){
        int file = R.raw.fifty_eight;
        midiFileMediaPlayer1 = MediaPlayer.create(this, file);
        midiFileMediaPlayer1.start();
        midiFileMediaPlayer2 = MediaPlayer.create(this, R.raw.fourty_nine);
        midiFileMediaPlayer1.setNextMediaPlayer(midiFileMediaPlayer2);
        midiFileMediaPlayer1.start();

    }

    public void setNote1(int note1){note1 = this.note1;}
    public void setNote2(int note2){note2 = this.note2;}
}
