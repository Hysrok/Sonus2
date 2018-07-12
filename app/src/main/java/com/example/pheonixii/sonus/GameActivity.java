package com.example.pheonixii.sonus;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    static final String SAVE_FILE = "";

    VerifyNotes verifyNotes = new VerifyNotes();

    private String interval;
    // ImageViews to remember which view was used last in order to delete them later.

    private int submits = 0;
    private ImageView noteB = null;
    private ImageView sharpB = null;
    private ImageView noteU = null;
    private ImageView sharpU = null;
    private ImageView noteR = null;
    private ImageView sharpR = null;
    public ImageMap imageMap = new ImageMap();

    int highestNote = 82;
    private int roundNum = 0;

    boolean correct = false;
    Spinner spinner;
    double score = 0.0;

    private ArrayList<String> intervals;
    ArrayAdapter<String> adapter;

    boolean hasSubmitted;


    /**
     * ON CREATE
     * - Sets up the view spinner
     * - Sets intervals with data from last activity
     * - Roundnum = 0
     * - Calls startRound
     *
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
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, intervals);

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
    public void startRound() {
        interval = randomInterval();
        randomBaseNote(); //has to go before the test note
        intervalTestNote();
        hasSubmitted = false;
        SeekBar noteSelect = findViewById(R.id.noteSelect);

        noteSelect.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                displayNote(getUserNote(), "User");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        RadioGroup sharpSelect = findViewById(R.id.sharpSelect);

        sharpSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                displayNote(getUserNote(), "User");
            }
        });
        displayNote(verifyNotes.getBaseNoteKey(), "Base");
        soundOff();
    }

    /**
     * GO HOME
     *
     * @param view
     */
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * GET INTERVALS
     * returns the arraylist of intervals
     */
    public ArrayList<String> getIntervals() {
        return intervals;
    }

    /**
     * SUBMIT
     * - Verifies note and sets hasSubmitted to true
     *
     * @param view
     */
    public void submit(View view) {
        if (!hasSubmitted) {
            verifyAnswer();
            hasSubmitted = true;
        }
        Toast.makeText(this, interval, Toast.LENGTH_SHORT).show();
    }

    /**
     * NEXT
     * - sets the correct note to invisible
     * - sets the red X or green checkmark to invisible
     * - add 1 to round number
     * - sends to stat page if the user has played 10 rounds
     */
    public void next(View view) {

        if (!hasSubmitted) {
            Toast.makeText(this, "You have to submit first!", Toast.LENGTH_SHORT).show();
            return;
        }
        roundNum++;
        //set correct note to 1 (doesn't exist in map and will make note bool false) to stop displaying correct note
        displayNote(1, "Correct");
        ImageView incorrect = findViewById(R.id.redx);
        incorrect.setVisibility(View.INVISIBLE);
        ImageView correct = findViewById(R.id.greencheck);
        correct.setVisibility(View.INVISIBLE);
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
     * @param note
     * @param mapType
     */
    public void displayNote(int note, String mapType) {
        if (mapType.compareTo("User") == 0) {
            if (noteU != null) {
                noteU.setVisibility(View.INVISIBLE);
                sharpU.setVisibility(View.INVISIBLE);
            }
            imageMap.chooseNote(note, mapType);
            noteU = findViewById(imageMap.imageNote);
            sharpU = findViewById(imageMap.imageSharp);
            if (imageMap.noteBool)
                noteU.setVisibility(View.VISIBLE);
            if (imageMap.sharpBool)
                sharpU.setVisibility(View.VISIBLE);
        } else if (mapType.compareTo("Base") == 0) {
            if (noteB != null) {
                noteB.setVisibility(View.INVISIBLE);
                sharpB.setVisibility(View.INVISIBLE);
            }
            imageMap.chooseNote(note, mapType);
            noteB = findViewById(imageMap.imageNote);
            sharpB = findViewById(imageMap.imageSharp);
            if (imageMap.noteBool)
                noteB.setVisibility(View.VISIBLE);
            if (imageMap.sharpBool)
                sharpB.setVisibility(View.VISIBLE);
        } else if (mapType.compareTo("Correct") == 0) {
            if (noteR != null) {
                noteR.setVisibility(View.INVISIBLE);
                sharpR.setVisibility(View.INVISIBLE);
            }
            imageMap.chooseNote(note, mapType);
            noteR = findViewById(imageMap.imageNote);
            sharpR = findViewById(imageMap.imageSharp);
            if (imageMap.noteBool)
                noteR.setVisibility(View.VISIBLE);
            if (imageMap.sharpBool)
                sharpR.setVisibility(View.VISIBLE);
        }
    }

    public void soundOff() {
        MediaPlayer midiFileMediaPlayer1;
        MediaPlayer midiFileMediaPlayer2;
        midiFileMediaPlayer1 = MediaPlayer.create(this, VerifyNotes.Notes.get(verifyNotes.getBaseNoteKey()));
        midiFileMediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
                mediaPlayer.release();
            }
        });
        midiFileMediaPlayer2 = MediaPlayer.create(this, VerifyNotes.Notes.get(verifyNotes.getTestNoteKey()));
        midiFileMediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
                mediaPlayer.release();
            }
        });
        midiFileMediaPlayer1.setNextMediaPlayer(midiFileMediaPlayer2);

        midiFileMediaPlayer1.start();
    }

    public void play(View view) {
        soundOff();
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
*/
        return note;
    }

    /**
     * returns the round number
     */
    public int getRoundNum() {
        return roundNum;
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
     *
     * @return
     */
    public boolean verifyNote() {
        return verifyNotes.getTestNoteKey() == getUserNote();
    }

    /**
     * VERIFY INTERVAL
     * Check if they chose the correct interval. If so return true else return false.
     */
    public boolean verifyInterval() {
        String correctInterval = interval;
        String userInterval = spinner.getSelectedItem().toString();

        spinner.setSelection(intervals.indexOf(interval));
            //int selectionPosition= adapter.getPosition(randomInterval());
            //spinner.setSelection(selectionPosition);
        return correctInterval.equals(userInterval);
    }

    /**
     * VERIFY ANSWER
     * Check if they got the interval correct. If so add .5 to their score.
     * Check if they got the note correct. If so add another .5 to their score.
     */
    public void verifyAnswer() {
        if (verifyInterval()) {
            score += .5;
        }
        if (verifyNote()) {
            score += .5;
            //correct = false;
            feedback(true);
        } else {
            displayNote(verifyNotes.getTestNoteKey(), "Correct");
            feedback(false);
        }
    }

    public void feedback(boolean correct) {
        if (correct) {
            ImageView check = findViewById(R.id.greencheck);
            check.setVisibility(View.VISIBLE);
        } else {
            ImageView incorrect = findViewById(R.id.redx);
            incorrect.setVisibility(View.VISIBLE);
        }
    }
}
