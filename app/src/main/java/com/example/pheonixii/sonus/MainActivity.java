package com.example.pheonixii.sonus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //I made a comment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public int checkedNum(){
        // Is the view now checked?
        int count = 0;
        CheckBox P1 = findViewById(R.id.P1);
        if(P1.isChecked())
            count++;
        CheckBox m2 = findViewById(R.id.m2);
        if(m2.isChecked())
            count++;
        CheckBox M2 = findViewById(R.id.M2);
        if(M2.isChecked())
            count++;
        CheckBox m3 = findViewById(R.id.m3);
        if(m3.isChecked())
            count++;
        CheckBox M3 = findViewById(R.id.M3);
        if(M3.isChecked())
            count++;
        CheckBox P4 = findViewById(R.id.P4);
        if(P4.isChecked())
            count++;
        CheckBox P5 = findViewById(R.id.P5);
        if(P5.isChecked())
            count++;
        CheckBox m6 = findViewById(R.id.m6);
        if(m6.isChecked())
            count++;
        CheckBox M6 = findViewById(R.id.M6);
        if(M6.isChecked())
            count++;
        CheckBox m7 = findViewById(R.id.m7);
        if(m7.isChecked())
            count++;
        CheckBox M7 = findViewById(R.id.M7);
        if(M7.isChecked())
            count++;
        CheckBox P8 = findViewById(R.id.P8);
        if(P8.isChecked())
            count++;

        return count;
    }

    public void play(View view) {
        if(checkedNum()>=3) {
            Intent intent = new Intent(this, GameActivity.class);

            startActivity(intent);
        }
        else
            Toast.makeText(this, "You require more intervals", Toast.LENGTH_LONG).show();
    }



}


