package com.example.pheonixii.sonus;

import android.os.AsyncTask;
import static jm.constants.Durations.WHOLE_NOTE;
import jm.music.data.Note;
import jm.util.Play;

public class SoundTask extends AsyncTask<Note,Note,Note> {


    @Override
    protected Note doInBackground(Note ... note) {


        Play.midi(note[0]);
        return note[0];
    }
    @Override
    protected void onProgressUpdate(Note ... note) {




    }

    @Override
    protected void onPostExecute(Note result) {

    }
}
