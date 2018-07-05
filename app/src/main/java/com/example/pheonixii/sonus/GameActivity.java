package com.example.pheonixii.sonus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    static final String SAVE_FILE = "";

    private static final Map<Integer, Integer> Notes = new TreeMap<Integer, Integer>() {{
        put(48, R.raw.fourty_eight);
        put(49, R.raw.fourty_nine);
        put(50, R.raw.fifty);
        put(51, R.raw.fifty_one);
        put(52, R.raw.fifty_two);
        put(53, R.raw.fifty_three);
        put(54, R.raw.fifty_four);
        put(55, R.raw.fifty_five);
        put(56, R.raw.fifty_six);
        put(57, R.raw.fifty_seven);
        put(58, R.raw.fifty_eight);
        put(59, R.raw.fifty_nine);
        put(60, R.raw.sixty);
        put(61, R.raw.sixty_one);
        put(62, R.raw.sixty_two);
        put(63, R.raw.sixty_three);
        put(64, R.raw.sixty_four);
        put(65, R.raw.sixty_five);
        put(66, R.raw.sixty_six);
        put(67, R.raw.sixty_seven);
        put(68, R.raw.sixty_eight);
        put(69, R.raw.sixty_nine);
        put(70, R.raw.seventy);
        put(71, R.raw.seventy_one);
        put(72, R.raw.seventy_two);
        put(73, R.raw.seventy_three);
        put(74, R.raw.seventy_four);
        put(75, R.raw.seventy_five);
        put(76, R.raw.seventy_six);
        put(77, R.raw.seventy_seven);
        put(78, R.raw.seventy_eight);
        put(79, R.raw.seventy_nine);
        put(80, R.raw.eighty);
        put(81, R.raw.eighty_one);
        put(82, R.raw.eighty_two);
        put(83, R.raw.eighty_three);
        put(84, R.raw.eighty_four);
        put(85, R.raw.eighty_five);
        put(86, R.raw.eighty_six);
        put(87, R.raw.eighty_seven);
        put(88, R.raw.eighty_eight);
        put(89, R.raw.eighty_nine);
        put(90, R.raw.ninety);
        put(91, R.raw.ninety_one);
        put(92, R.raw.ninety_two);
        put(93, R.raw.ninety_three);
        put(94, R.raw.ninety_four);
        put(95, R.raw.ninety_five);
        put(96, R.raw.ninety_six);
        put(97, R.raw.ninety_seven);
        put(98, R.raw.ninety_eight);
        put(99, R.raw.ninety_nine);
        put(100, R.raw.one_hundred);
        put(101, R.raw.one_hundred_one);
        put(102, R.raw.one_hundred_two);
        put(103, R.raw.one_hundred_three);
    }};

    private String interval;
    // ImageViews to remember which view was used last in order to delete them later.
    private ImageView noteP = null;
    private ImageView sharpP = null;
    private ImageView noteU = null;
    private ImageView sharpU = null;

    int highestNote = 82;
    private int baseNoteKey = 0;
    private int baseNote = 0;
    private int testNote = 0;
    private int testNoteKey = 0;
    private int userNoteKey = 0;
    private int roundNum = 0;

    boolean correct = false;
    Spinner spinner;
    double score = 0.0;

    private ArrayList<String> intervals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        intervals = intent.getStringArrayListExtra("interval_list");

        spinner = findViewById(R.id.intervals_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, intervals);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        roundNum = 0;

        startRound();
    }

    public void startRound(){
        interval = randomInterval();
        randomBaseNote(); //has to go before the test note
        intervalTestNote();
        displayNote(baseNoteKey);
        soundOff();
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submit(View view) {
        userNoteKey = getUserNote();
        //String interval = randomInterval();
        verifyAnswer();
        //Boolean trueorFalse = verifyInterval();
        Toast.makeText(this, "Score  " + score, Toast.LENGTH_LONG).show();
        //verifyAnswer();
        roundNum++;

        if (roundNum < 10) {
            startRound();
        } else {
            Intent intent = new Intent(this, Stats.class);
            // send the intervals that they used to the next page so that they can retry with those same intervals
            intent.putStringArrayListExtra("interval_list", intervals);
            // send the users score
            intent.putExtra("USER_SCORE", score);
            startActivity(intent);
        }
    }

    public void displayGuess(int note) {
        if (noteU != null) {
            noteU.setVisibility(View.INVISIBLE);
            sharpU.setVisibility(View.INVISIBLE);
        }
        noteU = findViewById(R.id.C4U);
        boolean noteB = true;
        sharpU = findViewById(R.id.C4sU);
        boolean sharpB = false;


        switch (note) {
            case 60: {
                noteU = findViewById(R.id.C4U);
                break;
            }
            case 61: {
                noteU = findViewById(R.id.C4U);
                sharpU = findViewById(R.id.C4sU);
                sharpB = true;
                break;
            }
            case 62: {
                noteU = findViewById(R.id.D4U);
                break;
            }
            case 63: {
                noteU = findViewById(R.id.D4U);
                sharpU = findViewById(R.id.D4sU);
                sharpB = true;
                break;
            }
            case 64: {
                noteU = findViewById(R.id.E4U);
                break;
            }
            case 65: {
                noteU = findViewById(R.id.F4U);
                break;
            }
            case 66: {
                noteU = findViewById(R.id.F4U);
                sharpU = findViewById(R.id.F4sU);
                sharpB = true;
                break;
            }
            case 67: {
                noteU = findViewById(R.id.G4U);
                break;
            }
            case 68: {
                noteU = findViewById(R.id.G4U);
                sharpU = findViewById(R.id.G4sU);
                sharpB = true;
                break;
            }
            case 69: {
                noteU = findViewById(R.id.A4U);
                break;
            }
            case 70: {
                noteU = findViewById(R.id.A4U);
                sharpU = findViewById(R.id.A4sU);
                sharpB = true;
                break;
            }
            case 71: {
                noteU = findViewById(R.id.B4U);
                break;
            }
            case 72: {
                noteU = findViewById(R.id.C5U);
                break;
            }
            case 73: {
                noteU = findViewById(R.id.C5U);
                sharpU = findViewById(R.id.C5sU);
                sharpB = true;
                break;
            }
            case 74: {
                noteU = findViewById(R.id.D5U);
                break;
            }
            case 75: {
                noteU = findViewById(R.id.D5U);
                sharpU = findViewById(R.id.D5sU);
                sharpB = true;
                break;
            }
            case 76: {
                noteU = findViewById(R.id.E5U);
                break;
            }
            case 77: {
                noteU = findViewById(R.id.F5U);
                break;
            }
            case 78: {
                noteU = findViewById(R.id.F5U);
                sharpU = findViewById(R.id.F5sU);
                sharpB = true;
                break;
            }
            case 79: {
                noteU = findViewById(R.id.G5U);
                break;
            }
            case 80: {
                noteU = findViewById(R.id.G5U);
                sharpU = findViewById(R.id.G5sU);
                sharpB = true;
                break;
            }
            case 81: {
                noteU = findViewById(R.id.A5U);
                break;
            }
            case 82: {
                noteU = findViewById(R.id.A5U);
                sharpU = findViewById(R.id.A5sU);
                sharpB = true;
                break;
            }
            default:
                noteB = false;
                sharpB = false;
        }
        if (noteB)
            noteU.setVisibility(View.VISIBLE);
        if (sharpB)
            sharpU.setVisibility(View.VISIBLE);

    }

    public void displayNote(int note) {
        if (noteP != null) {
            noteP.setVisibility(View.INVISIBLE);
            sharpP.setVisibility(View.INVISIBLE);
        }
        noteP = findViewById(R.id.C4);
        boolean noteB = true;
        sharpP = findViewById(R.id.C4s);
        boolean sharpB = false;


        switch (note) {
            case 60: {
                noteP = findViewById(R.id.C4);
                break;
            }
            case 61: {
                noteP = findViewById(R.id.C4);
                sharpP = findViewById(R.id.C4s);
                sharpB = true;
                break;
            }
            case 62: {
                noteP = findViewById(R.id.D4);
                break;
            }
            case 63: {
                noteP = findViewById(R.id.D4);
                sharpP = findViewById(R.id.D4s);
                sharpB = true;
                break;
            }
            case 64: {
                noteP = findViewById(R.id.E4);
                break;
            }
            case 65: {
                noteP = findViewById(R.id.F4);
                break;
            }
            case 66: {
                noteP = findViewById(R.id.F4);
                sharpP = findViewById(R.id.F4s);
                sharpB = true;
                break;
            }
            case 67: {
                noteP = findViewById(R.id.G4);
                break;
            }
            case 68: {
                noteP = findViewById(R.id.G4);
                sharpP = findViewById(R.id.G4s);
                sharpB = true;
                break;
            }
            case 69: {
                noteP = findViewById(R.id.A4);
                break;
            }
            case 70: {
                noteP = findViewById(R.id.A4);
                sharpP = findViewById(R.id.A4s);
                sharpB = true;
                break;
            }
            case 71: {
                noteP = findViewById(R.id.B4);
                break;
            }
            case 72: {
                noteP = findViewById(R.id.C5);
                break;
            }
            case 73: {
                noteP = findViewById(R.id.C5);
                sharpP = findViewById(R.id.C5s);
                sharpB = true;
                break;
            }
            case 74: {
                noteP = findViewById(R.id.D5);
                break;
            }
            case 75: {
                noteP = findViewById(R.id.D5);
                sharpP = findViewById(R.id.D5s);
                sharpB = true;
                break;
            }
            case 76: {
                noteP = findViewById(R.id.E5);
                break;
            }
            case 77: {
                noteP = findViewById(R.id.F5);
                break;
            }
            case 78: {
                noteP = findViewById(R.id.F5);
                sharpP = findViewById(R.id.F5s);
                sharpB = true;
                break;
            }
            case 79: {
                noteP = findViewById(R.id.G5);
                break;
            }
            case 80: {
                noteP = findViewById(R.id.G5);
                sharpP = findViewById(R.id.G5s);
                sharpB = true;
                break;
            }
            case 81: {
                noteP = findViewById(R.id.A5);
                break;
            }
            case 82: {
                noteP = findViewById(R.id.A5);
                sharpP = findViewById(R.id.A5s);
                sharpB = true;
                break;
            }
            default:
                noteB = false;
                sharpB = false;
        }
        if (noteB)
            noteP.setVisibility(View.VISIBLE);
        if (sharpB)
            sharpP.setVisibility(View.VISIBLE);
    }

    public void soundOff() {
        MediaPlayer midiFileMediaPlayer1;
        MediaPlayer midiFileMediaPlayer2;
        midiFileMediaPlayer1 = MediaPlayer.create(this, Notes.get(baseNoteKey));
        midiFileMediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        midiFileMediaPlayer2 = MediaPlayer.create(this, Notes.get(testNoteKey));
        midiFileMediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        midiFileMediaPlayer1.setNextMediaPlayer(midiFileMediaPlayer2);

        midiFileMediaPlayer1.start();
    }

    public void play(View view) {
        soundOff();
        getUserNote();
    }

    public int getUserNote() {
        SeekBar seekBar = findViewById(R.id.noteSelect);
        int seekValue = seekBar.getProgress();
        int note = 0;

        switch (seekValue) {
            case 0:
                note = 60;
                break;
            case 1:
                note = 62;
                break;
            case 2:
                note = 64;
                break;
            case 3:
                note = 65;
                break;
            case 4:
                note = 67;
                break;
            case 5:
                note = 69;
                break;
            case 6:
                note = 71;
                break;
            case 7:
                note = 72;
                break;
            case 8:
                note = 74;
                break;
            case 9:
                note = 76;
                break;
            case 10:
                note = 77;
                break;
            case 11:
                note = 79;
                break;
            case 12:
                note = 81;
        }


        RadioButton userSharp = findViewById(R.id.userSharp);
        if (userSharp.isChecked()) {
            note++;
        }
        RadioButton userFlat = findViewById(R.id.userFlat);
        if (userFlat.isChecked()) {
            note--;
            // we shouldn't have anything lower than 60
            if (note < 60)
                note = 60;
        }
        //Toast.makeText(this, "Note = " + note, Toast.LENGTH_LONG).show();
        displayGuess(note);
        return note;
    }

    /****************************
     * Get a random base note and key
     *******************************/
    public void randomBaseNote() {
        int maxNote = highestNote - convertIntervalToInt();
        Random rand = new Random();
        baseNoteKey = rand.nextInt((maxNote - 60) + 1) + 60; //rand.nextInt((max - min) + 1) + min;
        setBaseNote(Notes.get(baseNoteKey));
    }


    /*********************
     * Intervals are strings and need to be ints
     **********************/
    public int convertIntervalToInt() {
        switch (interval) {
            case "Perfect Unison":
                return 0;
            case "Minor Second":
                return 1;
            case "Major second":
                return 2;
            case "Minor Third":
                return 3;
            case "Major Third":
                return 4;
            case "Perfect Fourth":
                return 5;
            case "Perfect Fifth":
                return 7;
            case "Minor Sixth":
                return 8;
            case "Major Sixth":
                return 9;
            case "Minor Seventh":
                return 10;
            case "Major Seventh":
                return 11;
            case "Perfect Octave":
                return 12;
            default:
                //Toast.makeText(this, "FAIl", Toast.LENGTH_SHORT).show();
                break;
        }

        return -1; //fail

    }


    /*********************
     * Get test note base off the base note and interval
     **********************/
    public void intervalTestNote() {
        testNoteKey = baseNoteKey + convertIntervalToInt();
       // Toast.makeText(this, "Note = " + baseNoteKey + " Note2 = " + testNoteKey, Toast.LENGTH_LONG).show();
        if(Notes.get(testNoteKey) != null)
            setTestNote(Notes.get(testNoteKey));
    }

    /**
     * Chooses a random interval which will be used to determine the test note.
     * Uses only intervals that user has chosen.
     */
    public String randomInterval() {
        String answerInterval = "Empty";
        if (!intervals.isEmpty())
            answerInterval = intervals.get(new Random().nextInt(intervals.size()));
        return answerInterval;
    }

    public void createTestNote() {
        String interval = randomInterval();
        switch (interval){
            case "Perfect Unison" : setTestNote(baseNote);
        }
    }

    public void setBaseNote(int baseNote) {
        this.baseNote = baseNote;
    }

    public void setTestNote(int testNote) {
        this.testNote = testNote;
    }

    public void setUserNote(int userNote) {
        this.userNoteKey = userNote;
    }

    public int convertNote() {
        return -1;
    }

    public boolean verifyNote() {
        if(baseNoteKey == getUserNote())
        {
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
        return true;}
        else{
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            return false;}
    }

    /**
     * Check if they chose the correct interval. If so return true else return false.
     */
    public boolean verifyInterval() {
        String correctInterval = randomInterval();
        String userInterval = spinner.getSelectedItem().toString();
        if (correctInterval.equals(userInterval)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if they got the interval correct. If so add .5 to their score.
     * Check if they got the note correct. If so add another .5 to their score.
     */
    public void verifyAnswer() {
        if (verifyInterval()) {
            score += .5;
            //correct = true;
        }
        if (verifyNote()) {
            score += .5;
            //correct = false;
        }
    }

}
