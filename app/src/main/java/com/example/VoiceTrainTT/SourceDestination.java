package com.example.VoiceTrainTT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SourceDestination extends AppCompatActivity {
    TextToSpeech textToSpeech;
    public static final String msg1="com.example.VoiceTrainTT.MainActivity";
    public static final String msg2="com.example.VoiceTrainTT.MainActivity";

    String x,y;
    Intent intent;
    int count=0;
    ImageView img;
    SpeechRecognizer speechRecognizer;
    EditText source, destination;
    String[] src = {"Mumbai CST", "ByCulla", "Dadar", "Kurla", "Ghatkopar", "Bhandup", "Mulund", "Thane", "Diva", "Dombivali", "Kalyan"};
    String[] dest = {"Mumbai CST", "ByCulla", "Dadar", "Kurla", "Ghatkopar", "Bhandup", "Mulund", "Thane", "Diva", "Dombivali", "Kalyan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_destination);
        source = (EditText) findViewById(R.id.source1);
        destination = (EditText) findViewById(R.id.dest1);
        img=(ImageView)findViewById(R.id.imgMic);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        new CountDownTimer(2000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                getSource();


            }
        }.start();
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> match = bundle.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                String text1 = "";

                if (match != null) {
                    text1 = match.get(0);
                    if (count == 1) {
                        for (String arr : src) {
                            x = arr;
                            if (x.equalsIgnoreCase(text1)) {
                                source.setText(text1);
                                getDestination();
                            }
                        }
                    } else if (count == 2) {
                        for (String arr1 : dest) {
                            y = arr1;
                            if (text1.equalsIgnoreCase(y)) {
                                destination.setText(text1);
                            }
                        }
                        redirect(source.getText().toString(),destination.getText().toString());

                    } else {
                        textToSpeech.speak("Please speak Valid Source and Destination........Please select the source station", TextToSpeech.QUEUE_FLUSH, null, null);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        speechRecognizer.startListening(intent);
                        img.setBackgroundResource(R.drawable.ic_baseline_mic_24);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count = 1;
                        img.setBackgroundResource(R.drawable.ic_baseline_mic_off_24);
                    }
                }
                    }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
    }
    private void getSource(){
        textToSpeech.speak("Please select the source station", TextToSpeech.QUEUE_FLUSH, null, null);
        img.setBackgroundResource(R.drawable.ic_baseline_mic_24);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        speechRecognizer.startListening(intent);



        count=1;

    }
    private void getDestination(){
        textToSpeech.speak("Please select the destination station", TextToSpeech.QUEUE_FLUSH, null, null);
        img.setBackgroundResource(R.drawable.ic_baseline_mic_off_24);
        speechRecognizer.startListening(intent);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count=2;
        img.setBackgroundResource(R.drawable.ic_baseline_mic_24);


    }
    private  void redirect(String srcStn,String destStn){
        if(srcStn.equalsIgnoreCase(destStn)){
            TextView txtError=findViewById(R.id.txtError);
            txtError.setText("Invalid !! source and destination cannot be same");
            textToSpeech.speak("Invalid !! source and destiation cannot be same",TextToSpeech.QUEUE_FLUSH,null,null);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getSource();

        }
        else  if ((srcStn.equalsIgnoreCase("") || destStn.equalsIgnoreCase("") && (srcStn.equalsIgnoreCase("") && destStn.equalsIgnoreCase("")))){
            TextView txtError=findViewById(R.id.txtError);
            txtError.setText("Invalid !! Please provide a valid source and destination");
            textToSpeech.speak("Invalid !! Please provide a valid source and destination ",TextToSpeech.QUEUE_FLUSH,null,null);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getSource();

        }
        else{
            Intent int1= new Intent(getApplicationContext(),MainActivity.class);
            int1.putExtra("SOURCE",srcStn);
            int1.putExtra("DEST",destStn);
            startActivity(int1);
        }
    }
}