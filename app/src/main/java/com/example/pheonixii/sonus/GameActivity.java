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
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    static final String SAVE_FILE = "";

    VerifyNotes verifyNotes = new VerifyNotes();

    // ImageViews to remember which view was used last in order to delete them later.
    private ImageView noteP = null;
    private ImageView sharpP = null;
    private ImageView noteU = null;
    private ImageView sharpU = null;

    int highestNote = 82;
    private int roundNum = 0;

    boolean correct = false;
    Spinner spinner;
    double score = 0.0;

    private ArrayList<String> intervals;

    /**
     * ON CREATE
     * - Sets up the view spinner
     * - Sets intervals with data from last activity
     * - Roundnum = 0
     * - Calls startRound
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //initialize intent
        Intent intent = getIntent();

        //set intervals
        intervals = intent.getStringArrayListExtra("interval_list");

        //set empty spinner to view spinner
        spinner = findViewById(R.id.intervals_spinner);

        //preparing adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, intervals);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //add adapter to spinner
        spinner.setAdapter(adapter);

        roundNum = 0;

        startRound();
    }

    /**
     * START ROUND
     * - Sets a new random interval, base note and test note
     * - Displays base note
     */
    public void startRound(){
        verifyNotes.setInterval(randomInterval());
        randomBaseNote(); //has to go before the test note
        intervalTestNote();
        displayNote(verifyNotes.getBaseNoteKey());
        soundOff();
    }

    /**
     * GO HOME
     * @param view
     */
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * SUBMIT
     * - Verifies note and add a 1 to round number
     * - Goes to status act if the user has played 10 rounds
     * @param view
     */
    public void submit(View view) {
        verifyAnswer();
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

    /**
     * DISPLAY GUESS
     * @param note
     */
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

    /**
     * DISPLAY NOTE
     * @param note
     */
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
        midiFileMediaPlayer1 = MediaPlayer.create(this, VerifyNotes.Notes.get(verifyNotes.getBaseNoteKey()));
        midiFileMediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        midiFileMediaPlayer2 = MediaPlayer.create(this, VerifyNotes.Notes.get(verifyNotes.getTestNoteKey()));
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

    /**********************************************************
     * GET USER NOTE
     * Checks the seekbar to see which note the user has selected
     * returns the note as an int.
     *****************************************************/
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
/*        RadioButton userFlat = findViewById(R.id.userFlat);
        if (userFlat.isChecked()) {
            note--;
            // we shouldn't have anything lower than 60
            if (note < 60)
                note = 60;
        }
        //Toast.makeText(this, "Note = " + note, Toast.LENGTH_LONG).show();
*/        displayGuess(note);
        return note;
    }

    /****************************
     * RANDOM BASE NOTE
     * Get a random base note and key
     *******************************/
    public void randomBaseNote() {
        int maxNote = highestNote - convertIntervalToInt();
        Random rand = new Random();
        verifyNotes.setBaseNoteKey(rand.nextInt((maxNote - 60) + 1) + 60); //rand.nextInt((max - min) + 1) + min;
    }


    /*********************
     * CONVERT INTERVAL TO INT
     * Intervals are strings and need to be ints
     **********************/
    public int convertIntervalToInt() {
        switch (verifyNotes.getInterval()) {
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
     * INTERVAL TEST NOTE
     * Get test note base off the base note and interval
     **********************/
    public void intervalTestNote() {
        verifyNotes.setTestNoteKey(verifyNotes.getBaseNoteKey() + convertIntervalToInt());
       // Toast.makeText(this, "Note = " + baseNoteKey + " Note2 = " + testNoteKey, Toast.LENGTH_LONG).show();
    }

    /**
     * RANDOM INTERVAL
     * Chooses a random interval which will be used to determine the test note.
     * Uses only intervals that user has chosen.
     */
    public String randomInterval() {
        String answerInterval = "Empty";
        if (!intervals.isEmpty())
            answerInterval = intervals.get(new Random().nextInt(intervals.size()));
        return answerInterval;
    }


    /**
     * VERIFY NOTE
     * @return
     */
    public boolean verifyNote() {
        if(verifyNotes.getBaseNoteKey() == getUserNote())
        {
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
        return true;}
        else{
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            return false;}
    }

    /**
     * VERIFY INTERVAL
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
     * VERIFY ANSWER
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
