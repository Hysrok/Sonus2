package com.example.pheonixii.sonus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;



public class GameActivity extends AppCompatActivity {
    private static final int INTERVAL1 = 1;
    private static final int INTERVAL2 = 2;
    private static final int INTERVAL3 = 3;
    private static final int INTERVAL4 = 4;
    private static final int INTERVAL5 = 5;
    private static final int INTERVAL6 = 6;
    private static final int INTERVAL7 = 7;
    private static final int INTERVAL8 = 8;
    private static final int INTERVAL9 = 9;
    private static final int INTERVAL10 = 10;
    private static final int INTERVAL11 = 11;
    private static final int INTERVAL12 = 12;

    private static final Map<Integer, Integer> Notes = new TreeMap<Integer,Integer>(){{
        put(48, R.raw.fourty_eight);put(49, R.raw.fourty_nine);put(50, R.raw.fifty);
        put(51, R.raw.fifty_one);put(52, R.raw.fifty_two);put(53, R.raw.fifty_three);
        put(54, R.raw.fifty_four);put(55, R.raw.fifty_five);put(56, R.raw.fifty_six);
        put(57, R.raw.fifty_seven);put(58, R.raw.fifty_eight);put(59, R.raw.fifty_nine);
        put(60, R.raw.sixty);put(61, R.raw.sixty_one);put(62, R.raw.sixty_two);
        put(63, R.raw.sixty_three);put(64, R.raw.sixty_four);put(65, R.raw.sixty_five);
        put(66, R.raw.sixty_six);put(67, R.raw.sixty_seven);put(68, R.raw.sixty_eight);
        put(69, R.raw.sixty_nine);put(70, R.raw.seventy);put(71, R.raw.seventy_one);
        put(72, R.raw.seventy_two);put(73, R.raw.seventy_three);put(74, R.raw.seventy_four);
        put(75, R.raw.seventy_five);put(76, R.raw.seventy_six);put(77, R.raw.seventy_seven);
        put(78, R.raw.seventy_eight);put(79, R.raw.seventy_nine);put(80, R.raw.eighty);
        put(81, R.raw.eighty_one);put(82, R.raw.eighty_two);put(83, R.raw.eighty_three);
        put(84, R.raw.eighty_four);put(85, R.raw.eighty_five);put(86, R.raw.eighty_six);
        put(87, R.raw.eighty_seven);put(88, R.raw.eighty_eight);put(89, R.raw.eighty_nine);
        put(91, R.raw.ninety_one);put(92, R.raw.ninety_two);put(93, R.raw.ninety_three);
        put(94, R.raw.ninety_four);put(95, R.raw.ninety_five);put(96, R.raw.ninety_six);
        put(97, R.raw.ninety_seven);put(98, R.raw.ninety_eight);put(99, R.raw.ninety_nine);
        put(100, R.raw.one_hundred);put(101, R.raw.one_hundred_one);put(102, R.raw.one_hundred_two);
        put(103, R.raw.one_hundred_three);}};


    private int baseNoteKey = 0;
    private int baseNote = 0;
    private int testNote = 0;
    private int userNoteKey = 0;
    private int attempts = 0;
    boolean correct = false;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, intervals);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        randomBaseNote();

        //Pick random base note


    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submit(View view) {
        userNoteKey = getUserNote();
        Toast.makeText(this, "Note = " + userNoteKey, Toast.LENGTH_LONG).show();
        verifyAnswer();
        if (attempts == 3 || correct == true) {
            Intent intent = new Intent(this, Stats.class);
            startActivity(intent);
        }
    }

    public void play(View view) {
        int fNote = 48;
        int lNote = 103;
        ImageView noteP = findViewById(R.id.C4);
        noteP.setVisibility(View.VISIBLE);
        midiFileMediaPlayer1 = MediaPlayer.create(this, Notes.get(fNote));
        midiFileMediaPlayer1.start();
        midiFileMediaPlayer2 = MediaPlayer.create(this, Notes.get(lNote));
        midiFileMediaPlayer1.setNextMediaPlayer(midiFileMediaPlayer2);
        midiFileMediaPlayer1.start();
    }

    public int getUserNote() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar2);
        int seekValue = seekBar.getProgress();
        int note = 0;

        switch(seekValue) {
            case 0:
                note = 72;
                break;
            case 1:
                note = 74;
                break;
            case 2:
                note = 76;
                break;
            case 3:
                note = 77;
                break;
            case 4:
                note = 79;
                break;
            case 5:
                note = 81;
                break;
            case 6:
                note = 83;
                break;
            case 7:
                note = 84;
                break;
            case 8:
                note = 86;
                break;
            case 9:
                note = 88;
                break;
            case 10:
                note = 89;
                break;
            case 11:
                note = 91;
                break;
            case 12:
                note = 93;
        }

        RadioButton userSharp = findViewById(R.id.userSharp);
        if (userSharp.isChecked()) {
            note = note + 1;
        }
        RadioButton userFlat = findViewById(R.id.userFlat);
        if (userFlat.isChecked()) {
            note--;
        }
        Toast.makeText(this, "Note = " + note, Toast.LENGTH_LONG).show();
        return note;
    }

    public void randomBaseNote(){
        Random rand = new Random();
        baseNoteKey = rand.nextInt((93 - 72) + 1) +72; //rand.nextInt((max - min) + 1) + min;
        setBaseNote(Notes.get(baseNoteKey));
    }

    public void intervalTestNote(){


    }

    public void setBaseNote(int baseNote) {
        this.baseNote = baseNote;
    }

    public void setTestNote(int testNote) {
        testNote = this.testNote;
    }

    public void setUserNote(int userNote) {
        userNote = this.userNoteKey;
    }

    public int convertNote() {
        return -1;
    }

    public boolean verifyNote() {
        int theirNote = convertNote();
        return false;
    }

    public boolean verifyInterval() {
        return false;
    }

    public void verifyAnswer(){
        if (verifyNote() && verifyInterval())
            correct = true;
        else if (!verifyNote() || !verifyInterval()) {
            correct = false;
            attempts++;
        }
    }
}
