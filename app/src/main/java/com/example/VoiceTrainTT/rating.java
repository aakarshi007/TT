package com.example.VoiceTrainTT;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class rating extends AppCompatActivity {
    RatingBar ratingBar;
    TextView textView5;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbar);

        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        textView5=(TextView)findViewById(R.id.textView5);
        submit=(Button)findViewById(R.id.rate);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(1);
        ratingBar.setRating(1);



    }
}