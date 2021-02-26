package com.example.VoiceTrainTT;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class TrainsStatus extends AppCompatActivity {

    private TextView txvResult, txtdis;
    public static final String msg = "com.example.VoiceTrainTT.MainActivity";
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains_status);
        txvResult = (TextView) findViewById(R.id.txvResult);
        txtdis = (TextView) findViewById(R.id.txtdis);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        new CountDownTimer(2000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                textToSpeech.speak("मध्य रेलवे के लिए एक बोले..पश्चिमी रेलवे के लिए दो बोले.. या फिर हार्वर रेलवे के लिए तीन बोले ", TextToSpeech.QUEUE_FLUSH, null, null);
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                } else {
                    Toast.makeText(getApplicationContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }
            }
        }.start();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtdis.setText(result.get(0));

                }
                break;

        }
        if (txtdis.getText().toString().equals("Ek")) {
            Intent intent = new Intent(getApplicationContext(), SourceDestination.class);
            String val = txtdis.getText().toString();
            intent.putExtra(msg, val);
            startActivity(intent);
        }

        else if(txtdis.getText().toString().equalsIgnoreCase("do")){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            String val=txtdis.getText().toString();
            intent.putExtra(msg,val);
            startActivity(intent);
        }
        else if(txtdis.getText().toString().equalsIgnoreCase("Tin")){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            String val=txtdis.getText().toString();
            intent.putExtra(msg,val);
            startActivity(intent);
        }
        else
        {
            textToSpeech.speak("आपकी द्वारा दिया गया विवरण अवैध दे हैं... कृपया करके सही विवरण का चुनाव करें... मध्य रेलवे के लिए एक बोले पश्चिमी रेलवे के लिए दो बोले या फिर हार्वर रेलवे के लिए तीन बोले", TextToSpeech.QUEUE_FLUSH,null,null);
            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 10);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void goCentral(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        String val = txtdis.getText().toString();
        intent.putExtra(msg, val);
        startActivity(intent);
    }

    public void goWestern(View v) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
           String val=txtdis.getText().toString();
          intent.putExtra(msg,val);
          startActivity(intent);
    }

    public void goHarbour(View v) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
           String val=txtdis.getText().toString();
          intent.putExtra(msg,val);
         startActivity(intent);
    }

}
