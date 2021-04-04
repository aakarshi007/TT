package com.example.VoiceTrainTT;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
public class SingleRowAct extends AppCompatActivity {
    TextView time;
    TimePicker tp;
    String times;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlerow);
        time=(TextView)findViewById(R.id.time);
        int hour = tp.getCurrentHour();
        int min = tp.getCurrentMinute();
        times= hour+":"+min;
        setTime(times);
    }
    public void setTime(String timing){
        //timing = time.getText().toString();
        time.setText(timing);

    }
}
