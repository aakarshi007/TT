package com.example.VoiceTrainTT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    SpeechRecognizer speechRecognizer;
    TextToSpeech textToSpeech;
    TextView txt;
    public static final String msg="com.aakarsh.voicebasedm_indicator.TrainStatus";
    Intent intent;
    String count="";
    int lang=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.news :
                        Toast.makeText(getApplicationContext(),"News is Open", Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.maps :
                        Intent intent3=new Intent(getApplicationContext(),LocalMaps.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.rateus :
                        Intent intent4=new Intent(getApplicationContext(), rating.class);
                        startActivity(intent4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.contact :
                        Intent intent5 =new Intent(getApplicationContext(), Contact.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.feedback :
                        Intent intent2=new Intent(getApplicationContext(),feedback.class);
                        startActivity(intent2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(),Login_activity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
        txt=(TextView)findViewById(R.id.txt1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
        intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        speechRecognizer= SpeechRecognizer.createSpeechRecognizer(this);
        new CountDownTimer(3000,1){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @SuppressLint("NewApi")
            @Override
            public void onFinish() {

                String welcome="Welcome to Indian Railway Time Table System, Please choose the language ...speak one for english......भारतीय  रेलवे टाइम टेबल सिस्टम में आपका स्वागत है, कृपया  एक भाषा चुनें हिंदी भाषा के लिए do बोलें ";
                textToSpeech.speak(welcome, TextToSpeech.QUEUE_FLUSH,null,null);
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                speechRecognizer.startListening(intent);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> match = bundle.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
                String text1 = "";

                if (match != null) {
                    text1=match.get(0);
                    txt.setText(text1);
                    if (text1.equalsIgnoreCase("1")) {
                        count+="A";
                        Intent intent=new Intent(getApplicationContext(),HomeActivity1.class);
                        intent.putExtra("Count",count);
                        startActivity(intent);
                    } else if (text1.equalsIgnoreCase("do")) {
                        count+="B";
                        Intent intent=new Intent(getApplicationContext(),HomeActivity1.class);
                        intent.putExtra("Count",count);
                        startActivity(intent);

                    }
                    else if(count.equalsIgnoreCase(""))
                    {
                        String welcome = "Please choose the correct language... Please choose the language speak one for english......इंडियन रेलवे टाइम टेबल सिस्टम में आपका स्वागत है, कृपया  एक भाषा चुनें हिंदी भाषा के लिए do बोलें ";
                        textToSpeech.speak("We didn't get you, Please try again....."+welcome, TextToSpeech.QUEUE_FLUSH,null,null);
                        txt.setText(welcome);
                        try {
                            Thread.sleep(12000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        speechRecognizer.startListening(intent);
                        txt.setText("Speak Now");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count+=0;


                    }

                }
            }


            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

    }

}