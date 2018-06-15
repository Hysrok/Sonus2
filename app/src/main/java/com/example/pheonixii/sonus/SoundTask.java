package com.example.pheonixii.sonus;

import android.os.AsyncTask;
import static jm.constants.Durations.WHOLE_NOTE;
import jm.music.data.Note;
import jm.util.Play;

public class SoundTask extends AsyncTask<Note,Note,Note> {


    @Override
    protected Note doInBackground(Note ... note) {
        try{
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return note[0];
    }
    @Override
    protected void onProgressUpdate(Note ... note) {

        Play play = new Play();
        play.midi(note[0]);

    }

    @Override
    protected void onPostExecute(Note result) {

    }
}
