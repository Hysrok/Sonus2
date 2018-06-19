package com.example.pheonixii.sonus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import jm.music.data.Note;

import static jm.constants.Durations.WHOLE_NOTE;
import static jm.constants.Pitches.C4;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        ArrayList intervals = intent.getStringArrayListExtra("interval_list");
        Spinner spinner = (Spinner) findViewById(R.id.intervals_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, intervals);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        Note note = new Note(C4, WHOLE_NOTE);
        SoundTask play = new SoundTask();
        play.execute(note);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submit(View view) {
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }
}
