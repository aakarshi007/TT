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

import java.util.ArrayList;

public class HomeActivity1 extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    SpeechRecognizer speechRecognizer;
    TextToSpeech textToSpeech;
    TextView txt;
    String x;
    public static final String msg="com.aakarsh.voicebasedm_indicator.TrainStatus";
    Intent intent;
    int count=0;
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
                Intent intent1 = getIntent();
                x = intent1.getStringExtra("Count");
                if (x.equalsIgnoreCase("A")) {
                    String welcome = "Please choose the type of train for which you want to get the status... Speak Local for Local Train, express for express train or metro for metro train ";
                    textToSpeech.speak(welcome, TextToSpeech.QUEUE_FLUSH, null, null);
                    txt.setText(welcome);
                    try {
                        Thread.sleep(10000);
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
                }
                else
                {
                    String welcome="कृपया उस ट्रेन का प्रकार चुनें, जिसके लिए आप स्थिति प्राप्त करना चाहते हैं ... लोकल ट्रेन के लिए बोलें Local, एक्सप्रेस ट्रेन के लिए एक्सप्रेस या मेट्रो ट्रेन के लिए मेट्रो ";
                    textToSpeech.speak(welcome, TextToSpeech.QUEUE_FLUSH,null,null);
                    try {
                        Thread.sleep(12000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    speechRecognizer.startListening(intent);
                    txt.setText("अब बोलो");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
                        if (text1.equalsIgnoreCase("local")) {
                            if(lang ==0) {
                                localTrain();
                            }
                            else
                            {
                                localTrainHindi();
                            }
                        } else if (text1.equalsIgnoreCase("Express")) {
                            if(lang ==0) {
                                expressTrain();                            }
                            else
                            {
                                expressTrainHindi();
                            }

                        } else if (text1.equalsIgnoreCase("Metro")) {
                            if(lang ==0) {
                                metroTrain();
                            }
                            else
                            {
                                metroTrainHindi();
                            }

                        } else {
                            if(lang ==0) {
                                String welcome = "Please choose the type of train for which you want to get the status... Speak Local for Local Train, express for express train or metro for metro train ";
                                textToSpeech.speak("We didn't get you, Please try again....." + welcome, TextToSpeech.QUEUE_FLUSH, null, null);
                                txt.setText(welcome);
                                try {
                                    Thread.sleep(10000);
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
                            }
                            else
                            {
                                String welcome = "कृपया वह ट्रेन चुनें, जिसके लिए आप स्टेटस प्राप्त करना चाहते हैं ... लोकल ट्रेन के लिए लोकल बोलें, एक्सप्रेस ट्रेन के लिए एक्सप्रेस या मेट्रो ट्रेन के लिए मेट्रो";
                                textToSpeech.speak("हम आपको समझ नहीं पाए, कृपया पुनः प्रयास करें....." + welcome, TextToSpeech.QUEUE_FLUSH, null, null);
                                txt.setText(welcome);
                                try {
                                    Thread.sleep(10000);
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
                            }



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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void localTrain(){
        String message="You have choosen local train, Please Choose the line, Simply click on  any one line or Speak 1 for Central 2 for Western and 3 for Harbour";
        Intent int2=new Intent(this,TrainsStatus.class);
        int2.putExtra(msg,message);
        startActivity(int2);

        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH,null,null);
    }

    @SuppressLint("NewApi")
    public void expressTrain(){
        String message="You have choosen Express train, Please speak destination Station";
        Intent int2=new Intent(this,TrainsStatus.class);
        int2.putExtra(msg,message);
        startActivity(int2);

        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH,null,null);

    }
    @SuppressLint("NewApi")
    public void metroTrain(){
        String message="You have choosen metro train, Please speak destination Station";
        Intent int2=new Intent(this,TrainsStatus.class);
        int2.putExtra(msg,message);
        startActivity(int2);

        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH,null,null);
    }
    private void localTrainHindi(){
        String message="आपने लोकल ट्रेन को चुना है, कृपया लाइन चुनें, बस किसी एक लाइन पर क्लिक करें या वेस्टर्न के लिए सेंट्रल 2 के लिए 1 बोलें और हार्बर के लिए 3  बोले";
        Intent int2=new Intent(this,TrainsStatus.class);
        int2.putExtra(msg,message);
        startActivity(int2);

        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH,null,null);
    }

    @SuppressLint("NewApi")
    public void expressTrainHindi(){
        String message="You have choosen Express train, Please speak destination Station";
        Intent int2=new Intent(this,TrainsStatus.class);
        int2.putExtra(msg,message);
        startActivity(int2);

        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH,null,null);

    }
    @SuppressLint("NewApi")
    public void metroTrainHindi(){
        String message="You have choosen metro train, Please speak destination Station";
        Intent int2=new Intent(this,TrainsStatus.class);
        int2.putExtra(msg,message);
        startActivity(int2);

        textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH,null,null);
    }

}