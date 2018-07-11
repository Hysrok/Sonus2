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


public class GameActivity extends AppCompatActivity {
    static final String SAVE_FILE = "";

    VerifyNotes verifyNotes = new VerifyNotes();

    private String interval;
    // ImageViews to remember which view was used last in order to delete them later.
    boolean noteBool = true;
    boolean sharpBool = false;
    private int imageNote = 0;
    private int  imageSharp = 0;
    private ImageView noteB = null;
    private ImageView sharpB = null;
    private ImageView noteU = null;
    private ImageView sharpU = null;
    private ImageView noteR = null;
    private ImageView sharpR = null;
    public Map<Integer, Integer> imageMap = null;

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
        interval = randomInterval();
        randomBaseNote(); //has to go before the test note
        intervalTestNote();
        displayNote(verifyNotes.getBaseNoteKey(), "Base");
        SeekBar noteSelect = findViewById(R.id.noteSelect);

        noteSelect.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                displayGuess(getUserNote());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
     * GET INTERVALS
     * returns the arraylist of intervals
     */
    public ArrayList<String> getIntervals() {
        return intervals;
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
     *
     * @param note
     * @param mapType
     */
    public void displayNote(int note, String mapType){
        if(mapType.compareTo("User") == 0) {
            if (noteU != null) {
                noteU.setVisibility(View.INVISIBLE);
                sharpU.setVisibility(View.INVISIBLE);
            }
            chooseNote(note,mapType);
            noteU = findViewById(imageNote);
            sharpU = findViewById(imageSharp);
            if (noteBool)
                noteU.setVisibility(View.VISIBLE);
            if (sharpBool)
                sharpU.setVisibility(View.VISIBLE);
        }
        else if (mapType.compareTo("Base") == 0) {
            if (noteB != null) {
                noteB.setVisibility(View.INVISIBLE);
                sharpB.setVisibility(View.INVISIBLE);
            }
            chooseNote(note,mapType);
            noteB = findViewById(imageNote);
            sharpB = findViewById(imageSharp);
            if (noteBool)
                noteB.setVisibility(View.VISIBLE);
            if (sharpBool)
                sharpB.setVisibility(View.VISIBLE);
        }
    }

    /**
     * CHOOSE NOTE
     * @param note
     */
    public void chooseNote(int note,String mapType) {
        if(mapType.compareTo("User") == 0) {
            imageMap = ImageMap.userImages;
        }
        else if (mapType.compareTo("Base") == 0) {
            imageMap = ImageMap.baseImages;
        }


        imageNote = imageMap.get(60);
        noteBool = true;
        imageSharp = imageMap.get(61);
        sharpBool = false;


        switch (note) {
            case 60: {
                imageNote = imageMap.get(60);
                break;
            }
            case 61: {
                imageNote = imageMap.get(60);
                imageSharp = imageMap.get(61);
                sharpBool = true;
                break;
            }
            case 62: {
                imageNote = imageMap.get(62);
                break;
            }
            case 63: {
                imageNote = imageMap.get(62);
                imageSharp = imageMap.get(63);
                sharpBool = true;
                break;
            }
            case 64: {
                imageNote = imageMap.get(64);
                break;
            }
            case 65: {
                imageNote = imageMap.get(65);
                break;
            }
            case 66: {
                imageNote = imageMap.get(65);
                imageSharp = imageMap.get(66);
                sharpBool = true;
                break;
            }
            case 67: {
                imageNote = imageMap.get(67);
                break;
            }
            case 68: {
                imageNote = imageMap.get(67);
                imageSharp = imageMap.get(68);
                sharpBool = true;
                break;
            }
            case 69: {
                imageNote = imageMap.get(69);
                break;
            }
            case 70: {
                imageNote = imageMap.get(69);
                imageSharp = imageMap.get(70);
                sharpBool = true;
                break;
            }
            case 71: {
                imageNote = imageMap.get(71);
                break;
            }
            case 72: {
                imageNote = imageMap.get(72);
                break;
            }
            case 73: {
                imageNote = imageMap.get(72);
                imageSharp = imageMap.get(73);
                sharpBool = true;
                break;
            }
            case 74: {
                imageNote = imageMap.get(74);
                break;
            }
            case 75: {
                imageNote = imageMap.get(74);
                imageSharp = imageMap.get(75);
                sharpBool = true;
                break;
            }
            case 76: {
                imageNote = imageMap.get(76);
                break;
            }
            case 77: {
                imageNote = imageMap.get(77);
                break;
            }
            case 78: {
                imageNote = imageMap.get(77);
                imageSharp = imageMap.get(78);
                sharpBool = true;
                break;
            }
            case 79: {
                imageNote = imageMap.get(79);
                break;
            }
            case 80: {
                imageNote = imageMap.get(79);
                imageSharp = imageMap.get(80);
                sharpBool = true;
                break;
            }
            case 81: {
                imageNote = imageMap.get(81);
                break;
            }
            case 82: {
                imageNote = imageMap.get(81);
                imageSharp = imageMap.get(82);
                sharpBool = true;
                break;
            }
            default:
                noteBool = false;
                sharpBool = false;
        }
    }

    /**
     * DISPLAY CORRECT
     * @param note
     */
    public void displayCorrect(int note) {
        if (noteR != null) {
            noteR.setVisibility(View.INVISIBLE);
            sharpR.setVisibility(View.INVISIBLE);
        }
        noteR = findViewById(R.id.C4R);
        boolean noteB = true;
        sharpR = findViewById(R.id.C4sR);
        boolean sharpB = false;


        switch (note) {
            case 60: {
                noteR = findViewById(R.id.C4R);
                break;
            }
            case 61: {
                noteR = findViewById(R.id.C4R);
                sharpR = findViewById(R.id.C4sR);
                sharpB = true;
                break;
            }
            case 62: {
                noteR = findViewById(R.id.D4R);
                break;
            }
            case 63: {
                noteR = findViewById(R.id.D4R);
                sharpR = findViewById(R.id.D4sR);
                sharpB = true;
                break;
            }
            case 64: {
                noteR = findViewById(R.id.E4R);
                break;
            }
            case 65: {
                noteR = findViewById(R.id.F4R);
                break;
            }
            case 66: {
                noteR = findViewById(R.id.F4R);
                sharpR = findViewById(R.id.F4sR);
                sharpB = true;
                break;
            }
            case 67: {
                noteR = findViewById(R.id.G4R);
                break;
            }
            case 68: {
                noteR = findViewById(R.id.G4R);
                sharpR = findViewById(R.id.G4sR);
                sharpB = true;
                break;
            }
            case 69: {
                noteR = findViewById(R.id.A4R);
                break;
            }
            case 70: {
                noteR = findViewById(R.id.A4R);
                sharpR = findViewById(R.id.A4sR);
                sharpB = true;
                break;
            }
            case 71: {
                noteR = findViewById(R.id.B4R);
                break;
            }
            case 72: {
                noteR = findViewById(R.id.C5R);
                break;
            }
            case 73: {
                noteR = findViewById(R.id.C5R);
                sharpR = findViewById(R.id.C5sR);
                sharpB = true;
                break;
            }
            case 74: {
                noteR = findViewById(R.id.D5R);
                break;
            }
            case 75: {
                noteR = findViewById(R.id.D5R);
                sharpR = findViewById(R.id.D5sR);
                sharpB = true;
                break;
            }
            case 76: {
                noteR = findViewById(R.id.E5R);
                break;
            }
            case 77: {
                noteR = findViewById(R.id.F5R);
                break;
            }
            case 78: {
                noteR = findViewById(R.id.F5R);
                sharpR = findViewById(R.id.F5sR);
                sharpB = true;
                break;
            }
            case 79: {
                noteR = findViewById(R.id.G5R);
                break;
            }
            case 80: {
                noteR = findViewById(R.id.G5R);
                sharpR = findViewById(R.id.G5sR);
                sharpB = true;
                break;
            }
            case 81: {
                noteR = findViewById(R.id.A5R);
                break;
            }
            case 82: {
                noteR = findViewById(R.id.A5R);
                sharpR = findViewById(R.id.A5sR);
                sharpB = true;
                break;
            }
            default:
                noteB = false;
                sharpB = false;
        }
        if (noteB)
            noteR.setVisibility(View.VISIBLE);
        if (sharpB)
            sharpR.setVisibility(View.VISIBLE);
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
*/        displayNote(note,"User");
        return note;
    }

    /**
     * returns the round number
     */
    public int getRoundNum() { return roundNum; }

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
     * @return
     */
    public boolean verifyNote() {
        if(verifyNotes.getTestNoteKey() == getUserNote())
        {
            //Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            //Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
            return false;
        }
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
        else
            displayCorrect(verifyNotes.getTestNoteKey());
    }
}
